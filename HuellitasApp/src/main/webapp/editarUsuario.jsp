<%@ page import="modelo.Usuario"%>

<%
Usuario u = (Usuario) request.getAttribute("usuario");

if(u == null){
    response.sendRedirect("ListarUsuariosServlet");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Usuario</title>

<!-- ESTILOS TABLAS -->
<link rel="stylesheet" href="css/tablas.css">
<link rel="stylesheet" href="css/icons.css">
<link rel="stylesheet" href="css/boton.css">

</head>
<body>

<h2>Editar Rol Usuario</h2>

<form action="ActualizarUsuarioServlet" method="post">

    <!-- ID real oculto -->
    <input type="hidden" name="id" value="<%=u.getUsuarioId()%>">

    <!-- ID visible -->
    <p>
        <strong>ID Usuario:</strong>
        <%=u.getUsuarioId()%>
    </p>

    <p>
        <strong>Persona:</strong>
        <%=u.getPersona().getNombre()%>
    </p>

    <p>
        <strong>Rol actual:</strong>
        <%=u.getRolUsuario().getRolNombre()%>
    </p>

    <label>Nuevo Rol (id)</label>
    <input type="number" name="rolId" required>

    <br><br>

    <button type="submit" class="btn">
        <span class="material-icons">Guardar</span>
       
    </button>

</form>

</body>
</html>
