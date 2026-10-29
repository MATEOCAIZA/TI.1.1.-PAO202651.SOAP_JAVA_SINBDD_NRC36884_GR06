<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BIENVENIDO</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }
        body {
            background-color: #34495e;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .login-card {
            background: #ffffff;
            width: 450px;
            padding: 40px;
            text-align: center;
        }
        .avatar-img {
            width: 220px;
            height: 150px;
            object-fit: cover;
            margin-bottom: 20px;
        }
        .login-title {
            font-size: 1.5rem;
            color: #2c3e50;
            font-weight: bold;
            margin-bottom: 25px;
            letter-spacing: 1px;
        }
        .form-group {
            text-align: left;
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            font-size: 0.85rem;
            color: #7f8c8d;
            margin-bottom: 5px;
        }
        .form-group input {
            width: 100%;
            padding: 10px;
            font-size: 0.95rem;
            border: 1px solid #7f8c8d;
            outline: none;
            background-color: #ffffff;
        }
        .btn-submit {
            width: 100%;
            background-color: #2980b9;
            color: #ffffff;
            padding: 12px;
            font-size: 1.1rem;
            font-weight: bold;
            border: none;
            cursor: pointer;
            margin-top: 10px;
        }
        .btn-submit:hover {
            background-color: #21618c;
        }
        .error-message {
            margin-top: 15px;
            color: #c0392b;
            font-size: 0.9rem;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="login-card">
        <img src="images/monster.jpg" alt="Monster Logo" class="avatar-img">
        <h1 class="login-title">BIENVENIDO</h1>

        <form action="LoginServlet" method="POST">
            <div class="form-group">
                <label>Usuario</label>
                <input type="text" name="usuario" required />
            </div>

            <div class="form-group">
                <label>Contraseña</label>
                <input type="password" name="clave" required />
            </div>

            <button type="submit" class="btn-submit">Ingresar</button>
        </form>

        <% 
            String error = (String) request.getAttribute("errorLogin");
            if (error != null) {
        %>
            <div class="error-message"><%= error %></div>
        <% } %>
    </div>
</body>
</html>