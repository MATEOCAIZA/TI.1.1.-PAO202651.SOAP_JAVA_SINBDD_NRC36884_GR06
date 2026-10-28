# Guía Paso a Paso: Implementación de Vistas JFrame con NetBeans GUI Builder

Esta guía detalla cómo replicar visualmente los formularios **`ConversionLogin`** y **`ConversionVista`** utilizando el diseñador gráfico de **NetBeans (Palette / Drag & Drop)**, basado en la estructura de tu proyecto anterior y adaptado a este cliente SOAP (`CLIESC_CONVUNI_SOAP_GR08`).

---

## 1. Preparación de Recursos e Imágenes

Tu diseño de login utiliza un logotipo/imagen central (`jLabel1`).
1. Crea una carpeta de recursos si no existe en:
   `src/main/resources/ec/edu/monster/recursos/`
2. Coloca allí la imagen `monsterLogin.jpg` (o tu imagen preferida).
3. En Java se cargará automáticamente mediante:
   ```java
   getClass().getResource("/ec/edu/monster/recursos/monsterLogin.jpg")
   ```

---

## 2. Formulario 1: `ConversionLogin` (Pantalla de Acceso)

### 2.1. Creación del Formulario en NetBeans
1. Clic derecho sobre el paquete `ec.edu.monster.view` (o `ec.edu.monster.vista`).
2. Selecciona **New** $\rightarrow$ **JFrame Form...**
3. Nombre de la clase: `ConversionLogin`.
4. Clic en **Finish**.

### 2.2. Configuración del Contenedor Principal
1. En el panel **Navigator / Inspector** (abajo a la izquierda), haz clic derecho en `[JFrame]` $\rightarrow$ **Set Layout** $\rightarrow$ **Absolute Layout**.
2. Arrastra un **JPanel** (`jPanel1`) cubriendo toda la ventana (ancho: `660`, alto: `440`).
   - **Properties** $\rightarrow$ `background`: RGB `[44, 62, 80]` (Azul oscuro / Gris pizarra).
3. Arrastra un segundo **JPanel** (`jPanel2`) dentro de `jPanel1` centrado (ancho aprox `396`, alto `394`):
   - **Properties** $\rightarrow$ `background`: RGB `[255, 255, 255]` (Blanco).

### 2.3. Componentes a Arrastrar dentro de `jPanel2`

| Componente | Variable Name | Texto / Propiedades Clave |
| :--- | :--- | :--- |
| **JLabel** | `jLabel1` | `icon`: seleccionar `/ec/edu/monster/recursos/monsterLogin.jpg` |
| **JLabel** | `jLabel2` | `text`: `"BIENVENIDO"`, Font: `Segoe UI Bold 18`, Foreground: `[44, 62, 80]`, Aligned: `CENTER` |
| **JLabel** | `jLabel3` | `text`: `"Usuario"`, Font: `Segoe UI Plain 10`, Foreground: `[128, 128, 128]` |
| **JTextField** | `txtUsuario` *(o `jTextField1`)* | `text`: `""` |
| **JLabel** | `jLabel4` | `text`: `"Contraseña"`, Font: `Segoe UI Plain 10`, Foreground: `[128, 128, 128]` |
| **JPasswordField** | `txtPassword` *(o `jPasswordField1`)* | `text`: `""` |
| **JButton** | `btnIngresar` *(o `jButton1`)* | `text`: `"Ingresar"`, Font: `Tahoma Bold 18`, Background: `[41, 128, 185]` (Azul), Foreground: `[255, 255, 255]` |

### 2.4. Código Fuente y Eventos de `ConversionLogin`

Ve a la pestaña **Source** y agrega el atributo del controlador:

```java
package ec.edu.monster.view;

import ec.edu.monster.controller.AuthController;
import javax.swing.JOptionPane;

public class ConversionLogin extends javax.swing.JFrame {

    private final AuthController loginControlador;

    public ConversionLogin() {
        initComponents();
        this.loginControlador = new AuthController();
        this.setLocationRelativeTo(null); // Centrar en pantalla
    }
```

En la vista **Design**, haz doble clic sobre el botón **Ingresar** (`jButton1`) para generar su evento:

```java
private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    String usuario = jTextField1.getText().trim();
    String contrasena = new String(jPasswordField1.getPassword()).trim();

    if (usuario.isEmpty() || contrasena.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Llamada al método login del controlador (credenciales: MONSTER / MONSTER9)
    boolean autenticado = loginControlador.login(usuario, contrasena);

    if (autenticado) {
        JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
        this.dispose(); // Cerrar ventana actual

        // Abrir la ventana de conversiones
        ConversionVista conversionVista = new ConversionVista();
        conversionVista.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}                                        
```

---

## 3. Formulario 2: `ConversionVista` (Pantalla de Operaciones)

### 3.1. Creación del Formulario en NetBeans
1. Clic derecho sobre `ec.edu.monster.view`.
2. **New** $\rightarrow$ **JFrame Form...**
3. Nombre de la clase: `ConversionVista`.
4. Clic en **Finish**.

### 3.2. Estructura de Paneles y Colores (según tu código de referencia)

1. **`jPanel2` (Fondo exterior):**
   - Background: RGB `[204, 204, 204]` (Gris claro).
2. **`jPanel1` (Cabecera / Banner superior):**
   - Background: RGB `[57, 159, 214]` (Azul claro institucional).
   - Componente dentro: `jLabel1` con texto `"Conversión de Unidades"`, Font: `Segoe UI Bold 20`, Color: `Blanco`.
3. **`jPanel3` (Tarjeta blanca de contenido):**
   - Background: RGB `[255, 255, 255]` (Blanco).

### 3.3. Componentes a Arrastrar dentro de `jPanel3`

| Componente | Variable Name | Texto / Configuración |
| :--- | :--- | :--- |
| **JLabel** | `jLabel3` | `text`: `"Categoria:"`, Font: `Segoe UI Bold 12` |
| **JComboBox** | `cbCategoria` | Items iniciales: `masa`, `longitud`, `temperatura` |
| **JLabel** | `jLabel4` | `text`: `"Valor a Convertir:"`, Font: `Segoe UI Bold 12` |
| **JTextField** | `txtValor` | `horizontalAlignment`: `CENTER` |
| **JLabel** | `jLabel5` | `text`: `"Unidad Origen:"`, Font: `Segoe UI Bold 12` |
| **JComboBox** | `cbOrigen` | Unidades dinámicas según categoría |
| **JLabel** | `jLabel6` | `text`: `"Unidad Destino:"`, Font: `Segoe UI Bold 12` |
| **JComboBox** | `cbDestino` | Unidades dinámicas según categoría |
| **JButton** | `btnConvertir` | `text`: `"Convertir"`, Background: `[39, 174, 96]` (Verde), Foreground: `Blanco` |
| **JButton** | `btnLimpiar` | `text`: `"Limpiar"`, Background: `[149, 165, 166]` (Gris), Foreground: `Blanco` |
| **JLabel** | `jLabel7` | `text`: `"Resultado:"`, Font: `Segoe UI Bold 16` |
| **JLabel** | `lblResultado` | `text`: `""`, Font: `Segoe UI Bold 14` |

---

### 3.4. Código Fuente y Lógica Adaptada de `ConversionVista`

Pasa a la pestaña **Source** y define el controlador y la carga de unidades por categoría:

```java
package ec.edu.monster.view;

import ec.edu.monster.controller.ConversionController;
import ec.edu.monster.model.ConversionType;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class ConversionVista extends javax.swing.JFrame {

    private final ConversionController conversionControlador;

    public ConversionVista() {
        initComponents();
        this.conversionControlador = new ConversionController();
        this.setLocationRelativeTo(null); // Centrar en pantalla

        // Cargar categorías
        cbCategoria.setModel(new DefaultComboBoxModel<>(new String[] {
            "longitud", "temperatura", "masa"
        }));

        actualizarUnidadesPorCategoria();
    }

    private void actualizarUnidadesPorCategoria() {
        String categoria = cbCategoria.getSelectedItem().toString();
        cbOrigen.removeAllItems();
        cbDestino.removeAllItems();

        if ("longitud".equalsIgnoreCase(categoria)) {
            String[] unidades = {"cm", "in"};
            for (String u : unidades) {
                cbOrigen.addItem(u);
                cbDestino.addItem(u);
            }
            cbDestino.setSelectedItem("in");
        } else if ("temperatura".equalsIgnoreCase(categoria)) {
            String[] unidades = {"C", "F"};
            for (String u : unidades) {
                cbOrigen.addItem(u);
                cbDestino.addItem(u);
            }
            cbDestino.setSelectedItem("F");
        } else if ("masa".equalsIgnoreCase(categoria)) {
            String[] unidades = {"kg", "lb"};
            for (String u : unidades) {
                cbOrigen.addItem(u);
                cbDestino.addItem(u);
            }
            cbDestino.setSelectedItem("lb");
        }
    }
```

#### Evento al cambiar de Categoría (`cbCategoria`):
Haz doble clic en `cbCategoria` (o clic derecho $\rightarrow$ **Events** $\rightarrow$ **Item** $\rightarrow$ `itemStateChanged`):
```java
private void cbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {                                            
    actualizarUnidadesPorCategoria();
}                                           
```

#### Evento del Botón Convertir (`btnConvertir`):
Doble clic en el botón `btnConvertir`:
```java
private void btnConvertirActionPerformed(java.awt.event.ActionEvent evt) {                                             
    String valorTxt = txtValor.getText().trim();
    if (valorTxt.isEmpty()) {
        lblResultado.setText("Ingrese un valor");
        return;
    }

    try {
        float valor = Float.parseFloat(valorTxt);
        String origen = cbOrigen.getSelectedItem().toString();
        String destino = cbDestino.getSelectedItem().toString();

        if (origen.equalsIgnoreCase(destino)) {
            lblResultado.setText(String.format("%.4f %s", valor, destino));
            return;
        }

        // Mapear origen y destino al Enum ConversionType del servicio SOAP
        ConversionType tipo = null;
        if ("cm".equalsIgnoreCase(origen) && "in".equalsIgnoreCase(destino)) tipo = ConversionType.CM_A_IN;
        else if ("in".equalsIgnoreCase(origen) && "cm".equalsIgnoreCase(destino)) tipo = ConversionType.IN_A_CM;
        else if ("C".equalsIgnoreCase(origen) && "F".equalsIgnoreCase(destino)) tipo = ConversionType.C_A_F;
        else if ("F".equalsIgnoreCase(origen) && "C".equalsIgnoreCase(destino)) tipo = ConversionType.F_A_C;
        else if ("kg".equalsIgnoreCase(origen) && "lb".equalsIgnoreCase(destino)) tipo = ConversionType.KG_A_LB;
        else if ("lb".equalsIgnoreCase(origen) && "kg".equalsIgnoreCase(destino)) tipo = ConversionType.LB_A_KG;

        if (tipo == null) {
            JOptionPane.showMessageDialog(this, "Conversión no soportada entre " + origen + " y " + destino);
            return;
        }

        // Invocar el cliente SOAP a través del controlador
        float resultado = conversionControlador.convertir(tipo, valor);
        lblResultado.setText(String.format("%.4f %s", resultado, destino));

    } catch (NumberFormatException e) {
        lblResultado.setText("⚠ Ingrese un número válido");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al conectar con el servicio SOAP:\n" + e.getMessage(), 
                                      "Error de Conexión", JOptionPane.ERROR_MESSAGE);
    }
}                                            
```

#### Evento del Botón Limpiar (`btnLimpiar`):
Doble clic en el botón `btnLimpiar`:
```java
private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {                                           
    txtValor.setText("");
    lblResultado.setText("");
    cbCategoria.setSelectedIndex(0);
    actualizarUnidadesPorCategoria();
    txtValor.requestFocus();
}                                          
```

---

## 4. Conectar la Ejecución del Proyecto

En tu clase principal `CLIESC_CONVUNI_SOAP_GR08.java`:

```java
package ec.edu.monster.cliesc_convuni_soap_gr08;

import ec.edu.monster.view.ConversionLogin;
import javax.swing.SwingUtilities;

public class CLIESC_CONVUNI_SOAP_GR08 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ConversionLogin().setVisible(true);
        });
    }
}
```

---

## 5. Resumen de Ventajas del Diseño con NetBeans GUI Builder

1. **Sin código de diseño manual**: NetBeans genera automáticamente la sección `// <editor-fold defaultstate="collapsed" desc="Generated Code">` gestionando tamaños, alineaciones y layouts.
2. **Fácil personalización**: Colores exactos configurables en un clic desde la paleta de propiedades.
3. **Mantenimiento limpio**: La lógica sólo reside en los métodos `btnConvertirActionPerformed` y `jButton1ActionPerformed` delegando la lógica de negocio a los controladores `AuthController` y `ConversionController`.
