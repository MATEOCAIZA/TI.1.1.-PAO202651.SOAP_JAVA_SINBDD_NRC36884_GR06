package ec.edu.monster.vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import ec.edu.monster.R;
import ec.edu.monster.controller.SoapClient;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCategoria, spinnerConversion;
    private TextInputEditText etValor;
    private TextView tvResultado, tvUnidad;
    private Button btnConvertir, btnCerrarSesion;
    private CardView cardResultado;
    private FloatingActionButton fabLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        spinnerConversion = findViewById(R.id.spinnerConversion);
        etValor = findViewById(R.id.etValor);
        tvResultado = findViewById(R.id.tvResultado);
        tvUnidad = findViewById(R.id.tvUnidad);
        btnConvertir = findViewById(R.id.btnConvertir);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);
        cardResultado = findViewById(R.id.cardResultado);
        fabLimpiar = findViewById(R.id.fabLimpiar);

        // Configurar spinner de categorías
        String[] categorias = {"Seleccionar Categoría", "Longitud", "Temperatura", "Masa"};
        ArrayAdapter<String> adapterCat = new ArrayAdapter<>(this, R.layout.spinner_item, categorias);
        adapterCat.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterCat);

        // Listener para cambio de categoría
        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                actualizarConversiones(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Listener para botón convertir
        btnConvertir.setOnClickListener(v -> realizarConversion());
        
        // Listener para botón cerrar sesión
        btnCerrarSesion.setOnClickListener(v -> cerrarSesion());
        
        // Listener para botón flotante de limpiar
        fabLimpiar.setOnClickListener(v -> limpiarCampos());
    }
    
    private void cerrarSesion() {
        // Regresar al login
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
    }
    
    private void limpiarCampos() {
        // Limpiar el campo de valor
        etValor.setText("");
        
        // Ocultar el card de resultado
        cardResultado.setVisibility(View.GONE);
        
        // Limpiar los textos de resultado
        tvResultado.setText("");
        tvUnidad.setText("");
        
        // Mensaje de confirmación
        Toast.makeText(this, "Campos limpiados", Toast.LENGTH_SHORT).show();
    }

    // Unidades por categoría (mismo orden que los demás clientes): código para el servidor y nombre visible
    private static final String[][] LONGITUD = {{"m", "Metros"}, {"cm", "Centímetros"}, {"km", "Kilómetros"}, {"in", "Pulgadas"}, {"ft", "Pies"}};
    private static final String[][] TEMPERATURA = {{"C", "Celsius"}, {"F", "Fahrenheit"}, {"K", "Kelvin"}, {"R", "Rankine"}};
    private static final String[][] MASA = {{"kg", "Kilogramos"}, {"g", "Gramos"}, {"lb", "Libras"}, {"oz", "Onzas"}, {"t", "Toneladas"}};

    // Pares origen/destino que muestra el spinner de conversión (índice 0 = "Seleccionar Conversión")
    private final java.util.List<String[]> pares = new java.util.ArrayList<>();

    private static String[][] unidades(int categoria) {
        switch (categoria) {
            case 1: return LONGITUD;
            case 2: return TEMPERATURA;
            case 3: return MASA;
            default: return new String[0][];
        }
    }

    private void actualizarConversiones(int categoria) {
        pares.clear();
        java.util.List<String> conversiones = new java.util.ArrayList<>();
        String[][] u = unidades(categoria);
        if (u.length == 0) {
            conversiones.add("Primero seleccione una categoría");
        } else {
            conversiones.add("Seleccionar Conversión");
            for (String[] origen : u)
                for (String[] destino : u)
                    if (origen != destino) {
                        pares.add(new String[]{origen[0], destino[0]});
                        conversiones.add(origen[1] + " a " + destino[1]);
                    }
        }
        ArrayAdapter<String> adapterConv = new ArrayAdapter<>(this, R.layout.spinner_item, conversiones);
        adapterConv.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerConversion.setAdapter(adapterConv);
    }

    private void realizarConversion() {
        String valor = etValor.getText().toString().trim();
        if (valor.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese un valor", Toast.LENGTH_SHORT).show();
            return;
        }

        int categoria = spinnerCategoria.getSelectedItemPosition();
        int conversion = spinnerConversion.getSelectedItemPosition();

        if (categoria == 0 || conversion == 0) {
            Toast.makeText(this, "Por favor seleccione categoría y conversión", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] par = pares.get(conversion - 1);
        String nombreCategoria = spinnerCategoria.getSelectedItem().toString().toLowerCase();
        String unidadResultado = categoria == 2 ? (par[1].equals("K") ? "K" : "°" + par[1]) : par[1];
        convertir(nombreCategoria, par[0], par[1], valor, unidadResultado);
    }

    private void convertir(String categoria, String origen, String destino, String valorStr, String unidad) {
        new Thread(() -> {
            try {
                float valor = Float.parseFloat(valorStr);
                float resultado = SoapClient.callConvertir(categoria, origen, destino, valor);
                runOnUiThread(() -> {
                    cardResultado.setVisibility(View.VISIBLE);
                    tvResultado.setText(String.format("%.4f", resultado));
                    tvUnidad.setText(unidad);
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    cardResultado.setVisibility(View.VISIBLE);
                    tvResultado.setText("Error: " + e.getMessage());
                    tvUnidad.setText("");
                });
            }
        }).start();
    }
}
