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
