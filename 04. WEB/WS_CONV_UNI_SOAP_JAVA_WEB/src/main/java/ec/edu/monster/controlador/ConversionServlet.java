package ec.edu.monster.controlador;

import ec.edu.monster.cliente.ws.WSConversionUnidades;
import ec.edu.monster.cliente.ws.WSConversionUnidades_Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConversionServlet", urlPatterns = {"/ConversionServlet"})
public class ConversionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String valorStr = request.getParameter("valor");
        String tipoConversion = request.getParameter("tipoConversion");

        try {
            float valor = Float.parseFloat(valorStr);

            WSConversionUnidades_Service service = new WSConversionUnidades_Service();
            WSConversionUnidades port = service.getWSConversionUnidadesPort();
            
            float resultado = 0.0f;
            String etiqueta = "";

            switch (tipoConversion) {
                case "cm_in":
                    resultado = port.centimetrosAPulgadas(valor);
                    etiqueta = String.format("%.2f cm = %.4f in", valor, resultado);
                    break;
                case "in_cm":
                    resultado = port.pulgadasACentimetros(valor);
                    etiqueta = String.format("%.2f in = %.4f cm", valor, resultado);
                    break;
                case "c_f":
                    resultado = port.celsiusAFahrenheit(valor);
                    etiqueta = String.format("%.2f °C = %.2f °F", valor, resultado);
                    break;
                case "f_c":
                    resultado = port.fahrenheitACelsius(valor);
                    etiqueta = String.format("%.2f °F = %.2f °C", valor, resultado);
                    break;
                case "kg_lb":
                    resultado = port.kilogramosALibras(valor);
                    etiqueta = String.format("%.2f kg = %.4f lb", valor, resultado);
                    break;
                case "lb_kg":
                    resultado = port.librasAKilogramos(valor);
                    etiqueta = String.format("%.2f lb = %.4f kg", valor, resultado);
                    break;
                default:
                    etiqueta = "Tipo de conversión no válido.";
                    break;
            }

            request.setAttribute("valor", valorStr);
            request.setAttribute("tipoSeleccionado", tipoConversion);
            request.setAttribute("resultado", etiqueta);

        } catch (Exception e) {
            request.setAttribute("resultado", "Error al conectar con el servicio SOAP: " + e.getMessage());
        }

        request.getRequestDispatcher("conversion.jsp").forward(request, response);
    }
}