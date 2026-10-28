package ec.edu.monster.cliesc_convuni_soap_gr06;

import ec.edu.monster.view.ConversionLogin;
import javax.swing.SwingUtilities;

public class CLIESC_CONVUNI_SOAP_GR06 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ConversionLogin().setVisible(true);
        });
    }
}
