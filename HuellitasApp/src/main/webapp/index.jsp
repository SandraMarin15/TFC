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

<title>Clínica Veterinaria Huellitas</title>

<!-- Librería icons -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<!-- ESTILOS -->
<link rel="stylesheet" href="css/base.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/icons.css">
<link rel="stylesheet" href="css/boton.css">

</head>
<body>


	<div class="app-container">

		<!-- HEADER DINÁMICO -->
		<div id="header"></div>


		<!-- ================= HERO ================= -->
		<section class="hero">

			<h1>Cuidamos a quienes más quieres</h1>
			<p>Gestión de citas, mascotas y clientes desde una única
				plataforma.</p>
			<a class="btn" href="login.html">Acceder</a>

		</section>


		<!-- FOOTER DINÁMICO -->
		<div id="footer"></div>

	</div>

	<!-- JS -->
	<script src="js/loadComponents.js"></script>



</body>
</html>