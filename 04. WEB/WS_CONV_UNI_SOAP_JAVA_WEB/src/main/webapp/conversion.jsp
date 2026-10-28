<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session == null || session.getAttribute("usuarioLogueado") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Conversor de Unidades SOAP</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
        body { background: linear-gradient(135deg, #1e293b 0%, #0f172a 100%); min-height: 100vh; display: flex; align-items: center; justify-content: center; padding: 20px; }
        .card { background: #ffffff; width: 100%; max-width: 450px; padding: 35px 30px; border-radius: 16px; box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.3); }
        .user-bar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; font-size: 0.85rem; color: #64748b; font-weight: 600; border-bottom: 1px solid #e2e8f0; padding-bottom: 10px; }
        .btn-logout { color: #ef4444; text-decoration: none; font-weight: 700; }
        .btn-logout:hover { text-decoration: underline; }
        .card-header { display: flex; align-items: center; gap: 12px; margin-bottom: 25px; border-bottom: 2px solid #f1f5f9; padding-bottom: 15px; }
        .card-header img { width: 48px; height: 48px; border-radius: 50%; object-fit: cover; border: 2px solid #3b82f6; }
        .card-header h2 { font-size: 1.25rem; color: #0f172a; font-weight: 700; }
        .form-group { margin-bottom: 18px; }
        .form-group label { display: block; font-size: 0.875rem; font-weight: 600; color: #334155; margin-bottom: 6px; }
        .form-group input, .form-group select { width: 100%; padding: 12px 14px; font-size: 0.95rem; border: 1.5px solid #cbd5e1; border-radius: 8px; outline: none; background-color: #ffffff; }
        .form-group input:focus, .form-group select:focus { border-color: #3b82f6; box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.2); }
        .btn-submit { width: 100%; background-color: #10b981; color: #ffffff; padding: 12px; font-size: 1rem; font-weight: 600; border: none; border-radius: 8px; cursor: pointer; margin-top: 10px; }
        .btn-submit:hover { background-color: #059669; }
        .result-box { margin-top: 22px; padding: 16px; background-color: #f0fdf4; border: 1.5px solid #bbf7d0; border-radius: 8px; text-align: center; }
        .result-title { font-size: 0.8rem; text-transform: uppercase; color: #166534; font-weight: 700; margin-bottom: 4px; }
        .result-value { font-size: 1.25rem; color: #15803d; font-weight: 800; word-break: break-all; }
    </style>
</head>
<body>
    <div class="card">
        <div class="user-bar">
            <span>Bienvenido, <strong><%= session.getAttribute("usuarioLogueado") %></strong></span>
            <a href="LogoutServlet" class="btn-logout">Cerrar Sesión</a>
        </div>

        <div class="card-header">
            <img src="images/monster.jpg" alt="Monster Logo">
            <h2>Conversor de Unidades SOAP</h2>
        </div>

        <form action="ConversionServlet" method="POST">
            <div class="form-group">
                <label>Valor a convertir</label>
                <input type="number" step="any" name="valor" value="${valor != null ? valor : ''}" placeholder="Ingrese un número" required />
            </div>

            <div class="form-group">
                <label>Tipo de Conversión</label>
                <select name="tipoConversion">
                    <option value="cm_in" ${tipoSeleccionado == 'cm_in' ? 'selected' : ''}>Centímetros a Pulgadas (cm → in)</option>
                    <option value="in_cm" ${tipoSeleccionado == 'in_cm' ? 'selected' : ''}>Pulgadas a Centímetros (in → cm)</option>
                    <option value="c_f" ${tipoSeleccionado == 'c_f' ? 'selected' : ''}>Celsius a Fahrenheit (°C → °F)</option>
                    <option value="f_c" ${tipoSeleccionado == 'f_c' ? 'selected' : ''}>Fahrenheit a Celsius (°F → °C)</option>
                    <option value="kg_lb" ${tipoSeleccionado == 'kg_lb' ? 'selected' : ''}>Kilogramos a Libras (kg → lb)</option>
                    <option value="lb_kg" ${tipoSeleccionado == 'lb_kg' ? 'selected' : ''}>Libras a Kilogramos (lb → kg)</option>
                </select>
            </div>

            <button type="submit" class="btn-submit">Calcular Conversión</button>
        </form>

        <% 
            String resultado = (String) request.getAttribute("resultado");
            if (resultado != null) {
        %>
            <div class="result-box">
                <div class="result-title">Resultado obtenido</div>
                <div class="result-value"><%= resultado %></div>
            </div>
        <% } %>
    </div>
</body>
</html>