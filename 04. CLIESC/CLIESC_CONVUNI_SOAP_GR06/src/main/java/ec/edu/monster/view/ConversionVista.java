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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbOrigen = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbDestino = new javax.swing.JComboBox<>();
        btnConvertir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lblResultado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SOAP JAVA - Conversión de Unidades");
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(null);
        jPanel2.setPreferredSize(new java.awt.Dimension(660, 560));

        jPanel1.setBackground(new java.awt.Color(57, 159, 214));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Conversión de Unidades");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(30, 20, 600, 32);

        jPanel1.setBounds(0, 0, 660, 70);
        jPanel2.add(jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel3.setText("Categoria:");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(40, 30, 200, 20);

        cbCategoria.setModel(new DefaultComboBoxModel<>(new String[] { "masa", "longitud", "temperatura" }));
        cbCategoria.addActionListener(evt -> cbCategoriaActionPerformed(evt));
        jPanel3.add(cbCategoria);
        cbCategoria.setBounds(40, 52, 500, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel4.setText("Valor a Convertir:");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(40, 95, 200, 20);

        txtValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValor.setText("");
        jPanel3.add(txtValor);
        txtValor.setBounds(40, 117, 500, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel5.setText("Unidad Origen:");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(40, 160, 230, 20);

        cbOrigen.setModel(new DefaultComboBoxModel<>(new String[] { }));
        jPanel3.add(cbOrigen);
        cbOrigen.setBounds(40, 182, 230, 30);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel6.setText("Unidad Destino:");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(310, 160, 230, 20);

        cbDestino.setModel(new DefaultComboBoxModel<>(new String[] { }));
        jPanel3.add(cbDestino);
        cbDestino.setBounds(310, 182, 230, 30);

        btnConvertir.setBackground(new java.awt.Color(39, 174, 96));
        btnConvertir.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnConvertir.setForeground(new java.awt.Color(255, 255, 255));
        btnConvertir.setText("Convertir");
        btnConvertir.setOpaque(true);
        btnConvertir.addActionListener(evt -> btnConvertirActionPerformed(evt));
        jPanel3.add(btnConvertir);
        btnConvertir.setBounds(40, 230, 235, 40);

        btnLimpiar.setBackground(new java.awt.Color(149, 165, 166));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setOpaque(true);
        btnLimpiar.addActionListener(evt -> btnLimpiarActionPerformed(evt));
        jPanel3.add(btnLimpiar);
        btnLimpiar.setBounds(305, 230, 235, 40);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16));
        jLabel7.setText("Resultado:");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(40, 290, 200, 25);

        lblResultado.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblResultado.setText("");
        jPanel3.add(lblResultado);
        lblResultado.setBounds(40, 320, 500, 50);

        jPanel3.setBounds(40, 95, 580, 430);
        jPanel2.add(jPanel3);

        getContentPane().setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>

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

    private void cbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {
        actualizarUnidadesPorCategoria();
    }

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

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        txtValor.setText("");
        lblResultado.setText("");
        cbCategoria.setSelectedIndex(0);
        actualizarUnidadesPorCategoria();
        txtValor.requestFocus();
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnConvertir;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JComboBox<String> cbDestino;
    private javax.swing.JComboBox<String> cbOrigen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblResultado;
    private javax.swing.JTextField txtValor;
    // End of variables declaration
}
