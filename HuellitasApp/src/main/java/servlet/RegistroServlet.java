package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Persona;
import modelo.RolUsuario;
import modelo.Usuario;
import servicio.PersonaServicio;
import servicio.UsuarioServicio;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioServicio usuarioServicio = new UsuarioServicio();
	private PersonaServicio personaServicio = new PersonaServicio();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String email = request.getParameter("email");
			String telefono = request.getParameter("telefono");
			String password = request.getParameter("password");

			//Crear persona
			Persona p = new Persona();
			p.setNombre(nombre);
			p.setApellidos(apellidos);
			p.setEmail(email);
			p.setTelefono(telefono);

			//personaServicio.crearPersona(p);

			//Crear usuario
			Usuario u = new Usuario();
			
			//int personaId = personaServicio.crearPersona(p);
			int personaId = personaServicio.crearPersonaYDevolverId(p);

			if(personaId == -1){
			    response.sendRedirect("registro.html");
			    return;
			}

			p.setPersonaId(personaId);
			u.setPersona(p);



			// Rol por defecto 
			RolUsuario r = new RolUsuario();
			r.setRolUsuarioId(21);
			u.setRolUsuario(r);
			u.setPassword(password);


			usuarioServicio.crearUsuario(u);

			response.sendRedirect("login.html");

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("registro.html");
		}
	}
}
