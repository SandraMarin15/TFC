<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="modelo.Usuario"%>

<%
Usuario u = (Usuario) session.getAttribute("usuario");

if (u == null) {
    response.sendRedirect("login.html");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>

    <h2>
        Hola,
        <%= u.getPersona().getNombre() %>
    </h2>

    <a href="LogoutServlet">Cerrar sesión</a>

</body>
</html>
