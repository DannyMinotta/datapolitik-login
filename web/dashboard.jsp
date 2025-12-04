<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="co.sena.datapolitik.model.Usuario"%>
<%@page import="co.sena.datapolitik.dao.UsuarioDAO"%>
<%@page import="co.sena.datapolitik.dao.impl.UsuarioDAOImpl"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Dashboard - DataPolitik</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #0B1020;
                color: #E3F2FD;
                margin: 0;
                padding: 0;
            }

            .container {
                max-width: 960px;
                margin: 40px auto;
                background-color: #111729;
                padding: 24px;
                border-radius: 8px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.4);
            }

            h1 {
                margin-top: 0;
                color: #90CAF9;
            }

            p {
                font-size: 14px;
                margin: 6px 0;
            }

            .label {
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <%
           Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    int totalUsuarios = 0;
    try {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        totalUsuarios = usuarioDAO.contarUsuarios();
    } catch (Exception e) {
        totalUsuarios = -1; // valor de seguridad en caso de error
    }
        %>

        <div class="container">
            <h1>Bienvenido a DataPolitik</h1>

            <p>
                <span class="label">Usuario autenticado:</span>
                <strong><%= usuario.getNombres() %> <%= usuario.getApellidos() %></strong>
            </p>

            <p>
                <span class="label">Documento:</span>
                <%= usuario.getNumeroDocumento() %>
            </p>

            <p>
                <span class="label">Correo electrónico:</span>
                <%= usuario.getCorreoElectronico() %>
            </p>
            
                        <p>
                <span class="label">Usuarios registrados en el sistema:</span>
                <%= totalUsuarios %>
            </p>

            <p>
                <a href="<%= request.getContextPath() %>/usuarios?accion=listar"
                   style="color: #90CAF9; text-decoration: none;">
                    Ver listado de usuarios
                </a>
            </p>


            <p>Esta página es el punto de entrada después de un inicio de sesión exitoso.</p>
        </div>
    </body>
</html>
