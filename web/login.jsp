<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Login - DataPolitik</title>
        <style>
            /* Estilos simples para la evidencia.
               Luego se puede ajustar al prototipo visual completo. */

            body {
                font-family: Arial, sans-serif;
                background-color: #0B1020; /* tono oscuro tipo DataPolitik */
                color: #E3F2FD;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                margin: 0;
            }

            .login-container {
                background-color: #111729;
                padding: 24px;
                border-radius: 8px;
                width: 100%;
                max-width: 380px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.4);
            }

            h1 {
                margin-top: 0;
                margin-bottom: 4px;
                font-size: 22px;
                text-align: center;
            }

            h2 {
                margin-top: 0;
                font-size: 16px;
                text-align: center;
                color: #90CAF9;
            }

            label {
                display: block;
                margin-top: 12px;
                margin-bottom: 4px;
                font-size: 14px;
            }

            input[type="text"],
            input[type="password"] {
                width: 100%;
                padding: 8px;
                border-radius: 4px;
                border: 1px solid #3949AB;
                background-color: #0B1020;
                color: #E3F2FD;
                box-sizing: border-box;
            }

            input[type="submit"] {
                margin-top: 16px;
                width: 100%;
                padding: 10px;
                border-radius: 4px;
                border: none;
                background-color: #2979FF;
                color: #FFFFFF;
                font-weight: bold;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #1565C0;
            }

            .links {
                margin-top: 12px;
                font-size: 13px;
                display: flex;
                justify-content: space-between;
            }

            .links a {
                color: #90CAF9;
                text-decoration: none;
            }

            .links a:hover {
                text-decoration: underline;
            }

            .error {
                margin-top: 10px;
                padding: 8px;
                border-radius: 4px;
                background-color: #C62828;
                color: #FFFFFF;
                font-size: 13px;
            }
        </style>
    </head>
    <body>
        <%
            String mensajeError = (String) request.getAttribute("mensajeError");
            String documentoIngresado = (String) request.getAttribute("documentoIngresado");
            if (documentoIngresado == null) {
                documentoIngresado = "";
            }
        %>

        <div class="login-container">
            <h1>DataPolitik</h1>
            <h2>Iniciar sesión</h2>

            <!-- Mensaje de error, si existe -->
            <% if (mensajeError != null) { %>
                <div class="error"><%= mensajeError %></div>
            <% } %>

            <!-- Formulario de inicio de sesión -->
<form action="login" method="post">
    <!-- Número de documento -->
    <input type="text" name="numeroDocumento" placeholder="Número de documento" required>

    <!-- Contraseña -->
    <input type="password" name="password" placeholder="Contraseña" required>

    <!-- Botón -->
    <button type="submit">Ingresar</button>

    <!-- Mensaje de error (opcional) -->
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <div style="color: #ff8080; margin-top: 10px;">
            <%= error %>
        </div>
    <%
        }
    %>
</form>


<div class="links">
    <a href="recuperar.jsp">¿Olvidó su contraseña?</a>
    <a href="<%= request.getContextPath() %>/usuarios?accion=nuevo">Registrarse</a>
</div>

        </div>
    </body>
</html>

