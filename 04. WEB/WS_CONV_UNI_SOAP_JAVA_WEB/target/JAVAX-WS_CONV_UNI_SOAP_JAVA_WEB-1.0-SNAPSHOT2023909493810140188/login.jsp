<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión - Conversor SOAP</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
        body { background: linear-gradient(135deg, #1e293b 0%, #0f172a 100%); min-height: 100vh; display: flex; align-items: center; justify-content: center; padding: 20px; }
        .card { background: #ffffff; width: 100%; max-width: 400px; padding: 35px 30px; border-radius: 16px; box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.3); }
        .card-header { text-align: center; margin-bottom: 25px; border-bottom: 2px solid #f1f5f9; padding-bottom: 15px; }
        .card-header img { width: 70px; height: 70px; border-radius: 50%; object-fit: cover; border: 3px solid #3b82f6; margin-bottom: 10px; }
        .card-header h2 { font-size: 1.3rem; color: #0f172a; font-weight: 700; }
        .form-group { margin-bottom: 18px; }
        .form-group label { display: block; font-size: 0.875rem; font-weight: 600; color: #334155; margin-bottom: 6px; }
        .form-group input { width: 100%; padding: 12px 14px; font-size: 0.95rem; border: 1.5px solid #cbd5e1; border-radius: 8px; outline: none; background-color: #ffffff; }
        .form-group input:focus { border-color: #3b82f6; box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.2); }
        .btn-submit { width: 100%; background-color: #3b82f6; color: #ffffff; padding: 12px; font-size: 1rem; font-weight: 600; border: none; border-radius: 8px; cursor: pointer; margin-top: 10px; }
        .btn-submit:hover { background-color: #2563eb; }
        .error-box { margin-top: 15px; padding: 10px; background-color: #fef2f2; border: 1px solid #fecaca; color: #dc2626; border-radius: 8px; text-align: center; font-size: 0.875rem; font-weight: 600; }
    </style>
</head>
<body>
    <div class="card">
        <div class="card-header">
            <img src="images/monster.jpg" alt="Monster Logo">
            <h2>Acceso al Sistema SOAP</h2>
        </div>

        <form action="LoginServlet" method="POST">
            <div class="form-group">
                <label>Usuario</label>
                <input type="text" name="usuario" placeholder="Ej: admin" required />
            </div>

            <div class="form-group">
                <label>Contraseña</label>
                <input type="password" name="clave" placeholder="••••••••" required />
            </div>

            <button type="submit" class="btn-submit">Ingresar</button>
        </form>

        <% 
            String errorLogin = (String) request.getAttribute("errorLogin");
            if (errorLogin != null) {
        %>
            <div class="error-box"><%= errorLogin %></div>
        <% } %>
    </div>
</body>
</html>