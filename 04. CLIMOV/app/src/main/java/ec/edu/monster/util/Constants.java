package ec.edu.monster.util;

public class Constants {
    public static final String NAMESPACE = "http://ws.monster.edu.ec/";
    
    // Para emulador usar: 10.0.2.2
    // Para dispositivo físico usar la IP de tu PC donde corre el servidor
    // Ejemplo: "http://192.168.100.5:8080/WS_ConUni_SOAPJAVA_GR06/WSConversionUnidades"
    // Por USB con "adb reverse tcp:8080 tcp:8080" se puede usar localhost
    // Para encontrar tu IP en Windows: ipconfig en CMD y busca "Dirección IPv4"
    public static final String URL = "http://172.20.10.2:8080/WS_ConUni_SOAPJAVA_GR06/WSConversionUnidades";
}
