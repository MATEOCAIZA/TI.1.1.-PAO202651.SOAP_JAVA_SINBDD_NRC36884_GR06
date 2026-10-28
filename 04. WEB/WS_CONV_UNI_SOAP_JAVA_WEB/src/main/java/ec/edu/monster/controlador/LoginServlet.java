package ec.edu.monster.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");

        // Credenciales de prueba
        if ("MONSTER".equals(usuario) && "MONSTER69".equals(clave)) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuario);
            response.sendRedirect("conversion.jsp");
        } else {
            request.setAttribute("errorLogin", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}