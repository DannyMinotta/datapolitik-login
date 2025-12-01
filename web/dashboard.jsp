<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="co.sena.datapolitik.model.Usuario"%>
<%
    // Se obtiene el usuario almacenado en sesión
    Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");

    // Si no hay usuario autenticado, se redirige al login
    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Dashboard - DataPolitik</title>
    </head>
    <body>
        <h1>Bienvenido a DataPolitik</h1>
        <p>Usuario autenticado: <strong><%= usuario.getNombreCompleto() %></strong></p>
        <p>Documento: <%= usuario.getDocumento() %></p>
        <p>Rol: <%= usuario.getRol() %></p>

        <p>Esta página es el punto de entrada después de un inicio de sesión exitoso.</p>
    </body>
</html>

