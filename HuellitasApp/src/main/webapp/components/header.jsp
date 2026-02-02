<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="modelo.Usuario"%>

<%
Usuario u = (Usuario) session.getAttribute("usuario");
%>

<header class="navbar">

	<div class="logo-container">
		<img src="./img/logoTransBco.png" alt="Logo Huellitas" class="logo">
		<span class="brand-name">Clínica Veterinaria</span>
	</div>

	<div class="user-info">
		<% if (u != null) { %>
		
		<%=u.getRolUsuario().getRolNombre()%>
		<%= "-" %>
		<%=u.getPersona().getNombre()%>
		
		
		<% } %>
	</div>


	<div class="hamburger" id="hamburger">
		<span class="material-icons">menu</span>
	</div>

	<!--   Admin ve opciones de administración
		 Usuario normal ve solo opciones básicas-->
	<div class="mobile-menu" id="mobileMenu">

		<!-- SOLO ADMIN -->
		<% if(u != null && u.getRolUsuario().getRolNombre().equalsIgnoreCase("ADMIN")) { %>
		<a href="ListarUsuariosServlet">Gestión Usuarios</a> 
		<a href="#">Gestión Mascotas</a> 
		<a href="#">Gestión Citas</a>
		<% } %>

		<!-- SOLO USUARIO -->
		<% if(u != null && u.getRolUsuario().getRolNombre().equalsIgnoreCase("USUARIO")) { %>
		<a href="#">Mis Mascotas</a>
		 <a href="#">Mis Citas</a>
		<% } %>

		<!-- COMUN -->
		<a href="#">Servicios</a> <a href="#"
			onclick="location.href='/HuellitasApp/LogoutServlet'"> Cerrar
			sesión </a>
		<!--  <a href="/HuellitasApp/LogoutServlet">Cerrar sesión</a> -->

	</div>

</header>