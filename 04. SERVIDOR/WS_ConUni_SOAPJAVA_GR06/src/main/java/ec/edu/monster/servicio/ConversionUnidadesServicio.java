package ec.edu.monster.servicio;

import java.util.Map;

/**
 *
 * @author danie
 */
public class ConversionUnidadesServicio {

    // Método auxiliar para redondear a 2 decimales
    private float redondear2Decimales(float valor) {
        return Math.round(valor * 100f) / 100f;
    }

    // Conversión de longitud
    public float centimetrosAPulgadas(float centimetros) {
        return redondear2Decimales(centimetros / 2.54f);
    }

    public float pulgadasACentimetros(float pulgadas) {
        return redondear2Decimales(pulgadas * 2.54f);
    }

    // Conversión de temperatura
    public float celsiusAFahrenheit(float celsius) {
        return redondear2Decimales((celsius * 9 / 5) + 32);
    }

    public float fahrenheitACelsius(float fahrenheit) {
        return redondear2Decimales((fahrenheit - 32) * 5 / 9);
    }

    // Conversión de masa
    public float kilogramosALibras(float kilogramos) {
        return redondear2Decimales(kilogramos * 2.20462f);
    }

    public float librasAKilogramos(float libras) {
        return redondear2Decimales(libras / 2.20462f);
    }

    // Conversión general: cualquier par de unidades de la misma categoría
    private static final Map<String, Double> LONGITUD = Map.of(   // factor a metros
            "m", 1.0, "cm", 0.01, "km", 1000.0, "in", 0.0254, "ft", 0.3048);
    private static final Map<String, Double> MASA = Map.of(       // factor a kilogramos
            "kg", 1.0, "g", 0.001, "lb", 0.45359237, "oz", 0.028349523125, "t", 1000.0);

    public double convertir(String categoria, String origen, String destino, double valor) {
        switch (categoria.toLowerCase()) {
            case "longitud":
                return redondear4Decimales(valor * factor(LONGITUD, origen) / factor(LONGITUD, destino));
            case "masa":
                return redondear4Decimales(valor * factor(MASA, origen) / factor(MASA, destino));
            case "temperatura":
                return redondear4Decimales(desdeCelsius(aCelsius(valor, origen), destino));
            default:
                throw new IllegalArgumentException("Categoría no válida: " + categoria);
        }
    }

    private static double factor(Map<String, Double> tabla, String unidad) {
        Double f = tabla.get(unidad);
        if (f == null) throw new IllegalArgumentException("Unidad no válida: " + unidad);
        return f;
    }

    private static double aCelsius(double v, String u) {
        switch (u) {
            case "C": return v;
            case "F": return (v - 32) * 5 / 9;
            case "K": return v - 273.15;
            case "R": return (v - 491.67) * 5 / 9;
            default: throw new IllegalArgumentException("Unidad no válida: " + u);
        }
    }

    private static double desdeCelsius(double c, String u) {
        switch (u) {
            case "C": return c;
            case "F": return c * 9 / 5 + 32;
            case "K": return c + 273.15;
            case "R": return (c + 273.15) * 9 / 5;
            default: throw new IllegalArgumentException("Unidad no válida: " + u);
        }
    }

    private static double redondear4Decimales(double valor) {
        return Math.round(valor * 10000d) / 10000d;
    }
}
