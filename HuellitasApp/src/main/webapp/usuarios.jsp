<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="modelo.Usuario"%>

<%
List<Usuario> lista = (List<Usuario>)request.getAttribute("listaUsuarios");

if (lista == null) {
    response.sendRedirect("index.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Usuarios</title>

 <!-- Librería icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">


<link rel="stylesheet" href="css/icons.css">
<link rel="stylesheet" href="css/tablas.css">

</head>

<body>

<h2 style="text-align:center;">Listado de Usuarios</h2>

<table>
<tr>
    <th>ID</th>
    <th>Persona</th>
    <th>Rol</th>
    <th>Acciones</th>
</tr>

<%
for (Usuario u : lista) {
	if(u == null)continue;
%>

<tr>
    <td><%= u.getUsuarioId() %></td>

    <td><%= u.getPersona().getNombre() %></td>

    <td><%= u.getRolUsuario().getRolNombre() %></td>

    <td>

        <a href="BorrarUsuarioServlet?id=<%=u.getUsuarioId()%>">
            <span class="material-icons">delete</span>
        </a>

        |

        <a href="EditarUsuarioServlet?id=<%=u.getUsuarioId()%>">
            <span class="material-icons">edit</span>
        </a>

    </td>
</tr>



<%
}
%>

</table>

</body>
</html>
