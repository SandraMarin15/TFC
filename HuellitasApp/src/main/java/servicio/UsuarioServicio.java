package servicio;

import java.sql.SQLException;
import java.util.List;

import dao.UsuarioDAO;
import modelo.Usuario;

public class UsuarioServicio {

	//UsuarioDAO -> UsuarioServicio para conectar con la base de datos sin sql
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	/**
	 * Crea un nuevo usuario en la base de datos.	
	 * Devuelve true si se ha insertado correctamente.
	 */
	public boolean crearUsuario(Usuario u) throws SQLException {

	    return usuarioDAO.insertarUsuario(u);
	}



	// Obtener lista con todos los usuarios de la BD.     
	public List<Usuario> listarUsuarios() throws SQLException {
		return usuarioDAO.listarUsuarios();
	}


	// Buscar usuario por su ID.     
	public Usuario buscarUsuarioPorId(int id) throws SQLException {
	    return usuarioDAO.obtenerUsuarioPorId(id);
	}



	 // Modifica un usuario existente.	
	public boolean modificarUsuario(Usuario u) throws SQLException {

		//Si existe -> Modifica el usuario
		return usuarioDAO.actualizarUsuario(u);
	}


	// Borra un usuario por su ID.     
	public boolean borrarUsuario(int id) throws SQLException {

		Usuario usuarioBD = usuarioDAO.obtenerUsuarioPorId(id);

		//Si no existe -> No puedo borrar
		if (usuarioBD == null) {
			return false;
		}

		//Si existe -> borro el usuario por id
		return usuarioDAO.eliminarUsuario(id);
	}
	
	public Usuario login(String email, String password) throws SQLException {
	    return usuarioDAO.login(email, password);
	}

}


