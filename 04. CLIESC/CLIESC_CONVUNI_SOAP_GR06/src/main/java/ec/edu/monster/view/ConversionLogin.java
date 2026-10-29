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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SOAP JAVA - Login");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(44, 62, 80));
        jPanel1.setLayout(null);
        jPanel1.setPreferredSize(new java.awt.Dimension(660, 440));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        java.net.URL logo = getClass().getResource("/ec/edu/monster/recursos/monsterLogin.jpg");
        if (logo != null) {
            jLabel1.setIcon(new javax.swing.ImageIcon(logo));
        } else {
            jLabel1.setText("MONSTER");
        }
        jPanel2.add(jLabel1);
        jLabel1.setBounds(98, 20, 200, 130);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel2.setForeground(new java.awt.Color(44, 62, 80));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("BIENVENIDO");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(40, 165, 316, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 10));
        jLabel3.setForeground(new java.awt.Color(128, 128, 128));
        jLabel3.setText("Usuario");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(50, 210, 100, 15);

        jTextField1.setText("");
        jPanel2.add(jTextField1);
        jTextField1.setBounds(50, 228, 296, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 10));
        jLabel4.setForeground(new java.awt.Color(128, 128, 128));
        jLabel4.setText("Contraseña");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(50, 270, 100, 15);

        jPasswordField1.setText("");
        jPanel2.add(jPasswordField1);
        jPasswordField1.setBounds(50, 288, 296, 30);

        jButton1.setBackground(new java.awt.Color(41, 128, 185));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Ingresar");
        jButton1.setOpaque(true);
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));
        jPanel2.add(jButton1);
        jButton1.setBounds(50, 330, 296, 38);

        jPanel2.setBounds(132, 23, 396, 394);
        jPanel1.add(jPanel2);

        getContentPane().setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>

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

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration
}
