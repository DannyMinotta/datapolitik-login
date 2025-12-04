<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar usuario - DataPolitik</title>
</head>
<body>
    <h1>Registrar nuevo usuario</h1>

    <p>
        <a href="<%= request.getContextPath() %>/usuarios?accion=listar">Volver al listado</a>
    </p>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <div style="color: red;"><%= error %></div>
    <%
        }
    %>

    <form action="<%= request.getContextPath() %>/usuarios" method="post">
        <input type="hidden" name="accion" value="guardar"/>

        <p>
            <label>Tipo de documento:</label>
            <select name="tipoDocumento" required>
                <option value="CC">Cédula de ciudadanía</option>
                <option value="TI">Tarjeta de identidad</option>
                <option value="CE">Cédula de extranjería</option>
            </select>
        </p>

        <p>
            <label>Número de documento:</label>
            <input type="text" name="numeroDocumento" required/>
        </p>

        <p>
            <label>Nombres:</label>
            <input type="text" name="nombres" required/>
        </p>

        <p>
            <label>Apellidos:</label>
            <input type="text" name="apellidos" required/>
        </p>

        <p>
            <label>Correo electrónico:</label>
            <input type="email" name="correoElectronico" required/>
        </p>

        <p>
            <label>Teléfono:</label>
            <input type="text" name="telefono"/>
        </p>

        <p>
            <label>Contraseña:</label>
            <input type="password" name="password" required/>
        </p>

        <p>
            <button type="submit">Guardar usuario</button>
        </p>
    </form>

</body>
</html>
