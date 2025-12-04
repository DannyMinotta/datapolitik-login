<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="co.sena.datapolitik.model.Usuario"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Usuarios - DataPolitik</title>
</head>
<body>
    <h1>Listado de usuarios</h1>

    <p>
        <a href="<%= request.getContextPath() %>/usuarios?accion=nuevo">Registrar nuevo usuario</a>
        |
        <a href="<%= request.getContextPath() %>/dashboard.jsp">Volver al dashboard</a>
    </p>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <div style="color: red;"><%= error %></div>
    <%
        }
    %>

    <%
        List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
        if (usuarios == null || usuarios.isEmpty()) {
    %>
        <p>No hay usuarios registrados.</p>
    <%
        } else {
    %>
        <table border="1" cellpadding="5" cellspacing="0">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Documento</th>
                    <th>Nombres</th>
                    <th>Apellidos</th>
                    <th>Correo electrónico</th>
                    <th>Teléfono</th>
                </tr>
            </thead>
            <tbody>
            <%
                for (Usuario u : usuarios) {
            %>
                <tr>
                    <td><%= u.getIdUsuario() %></td>
                    <td><%= u.getTipoDocumento() %> <%= u.getNumeroDocumento() %></td>
                    <td><%= u.getNombres() %></td>
                    <td><%= u.getApellidos() %></td>
                    <td><%= u.getCorreoElectronico() %></td>
                    <td><%= u.getTelefono() %></td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    <%
        }
    %>

</body>
</html>
