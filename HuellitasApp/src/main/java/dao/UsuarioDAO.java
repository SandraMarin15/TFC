package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Persona;
import modelo.RolUsuario;
import modelo.Usuario;
import util.ConexionOracle;

public class UsuarioDAO {

	/*UsuarioDAO: Clase para el acceso a los datos del Objeto.	 
		- Incluye los métodos CRUD y control de campos nulos sin son necesarios.*/



	//Método para insertar usuarios	
	public boolean insertarUsuario(Usuario usuario) throws SQLException {

		String sql = "INSERT INTO usuarios (persona_id, rol_usuario_id, password) "
				+ "VALUES (?, ?, ?)";

		try (
				Connection conexionBd = ConexionOracle.getConnection();
				PreparedStatement ps = conexionBd.prepareStatement(sql)
				) {

			// FK Persona
			ps.setInt(1, usuario.getPersona().getPersonaId());

			// FK Rol
			ps.setInt(2, usuario.getRolUsuario().getRolUsuarioId());
			
			//Para un escenario real cambiar a clave encriptada
			ps.setString(3, usuario.getPassword());
			
			
			int rows = ps.executeUpdate();

			return rows == 1;
		}
	}



	//Método para listar usuarios
	public List<Usuario> listarUsuarios() throws SQLException {

		List<Usuario> lista = new ArrayList<>();

		String sql = "SELECT u.usuario_id, "
				+ "p.persona_id, p.nombre, p.apellidos, p.telefono, p.email, "
				+ "r.rol_usuario_id, r.rol_nombre "
				+ "FROM usuarios u "
				+ "JOIN personas p ON u.persona_id = p.persona_id "
				+ "JOIN roles_usuarios r ON u.rol_usuario_id = r.rol_usuario_id";

		try (
				Connection conexionBd = ConexionOracle.getConnection();
				PreparedStatement ps = conexionBd.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()
				) {

			while (rs.next()) {

				// Objetos
				Usuario u = new Usuario();
				Persona p = new Persona();
				RolUsuario r = new RolUsuario();

				// Persona
				p.setPersonaId(rs.getInt("persona_id"));
				p.setNombre(rs.getString("nombre"));
				p.setApellidos(rs.getString("apellidos"));
				p.setTelefono(rs.getString("telefono"));
				p.setEmail(rs.getString("email"));

				// Rol
				r.setRolUsuarioId(rs.getInt("rol_usuario_id"));
				r.setRolNombre(rs.getString("rol_nombre"));

				// Usuario
				u.setUsuarioId(rs.getInt("usuario_id"));
				u.setPersona(p);
				u.setRolUsuario(r);

				lista.add(u);
			}
		}

		return lista;
	}

	//Método para buscar usuario por id
	public Usuario obtenerUsuarioPorPersonaId(int personaId) throws SQLException {

	    Usuario u = null;

	    String sql = "SELECT u.usuario_id, "
	            + "p.persona_id, p.nombre, p.apellidos, p.telefono, p.email, "
	            + "r.rol_usuario_id, r.rol_nombre "
	            + "FROM usuarios u "
	            + "JOIN personas p ON u.persona_id = p.persona_id "
	            + "JOIN roles_usuarios r ON u.rol_usuario_id = r.rol_usuario_id "
	            + "WHERE p.persona_id = ?";

	    try (Connection con = ConexionOracle.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, personaId);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {

	                Persona p = new Persona();
	                RolUsuario r = new RolUsuario();
	                u = new Usuario();

	                p.setPersonaId(rs.getInt("persona_id"));
	                p.setNombre(rs.getString("nombre"));
	                p.setApellidos(rs.getString("apellidos"));
	                p.setTelefono(rs.getString("telefono"));
	                p.setEmail(rs.getString("email"));

	                r.setRolUsuarioId(rs.getInt("rol_usuario_id"));
	                r.setRolNombre(rs.getString("rol_nombre"));

	                u.setUsuarioId(rs.getInt("usuario_id"));
	                u.setPersona(p);
	                u.setRolUsuario(r);
	            }
	        }
	    }

	    return u;
	}

	// Buscar usuario por usuario_id
	public Usuario obtenerUsuarioPorId(int usuarioId) throws SQLException {

	    Usuario u = null;

	    String sql =
	        "SELECT u.usuario_id, " +
	        "p.persona_id, p.nombre, p.apellidos, p.telefono, p.email, " +
	        "r.rol_usuario_id, r.rol_nombre " +
	        "FROM usuarios u " +
	        "JOIN personas p ON u.persona_id = p.persona_id " +
	        "JOIN roles_usuarios r ON u.rol_usuario_id = r.rol_usuario_id " +
	        "WHERE u.usuario_id = ?";

	    try (Connection con = ConexionOracle.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, usuarioId);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            Persona p = new Persona();
	            RolUsuario r = new RolUsuario();
	            u = new Usuario();

	            p.setPersonaId(rs.getInt("persona_id"));
	            p.setNombre(rs.getString("nombre"));
	            p.setApellidos(rs.getString("apellidos"));
	            p.setTelefono(rs.getString("telefono"));
	            p.setEmail(rs.getString("email"));

	            r.setRolUsuarioId(rs.getInt("rol_usuario_id"));
	            r.setRolNombre(rs.getString("rol_nombre"));

	            u.setUsuarioId(rs.getInt("usuario_id"));
	            u.setPersona(p);
	            u.setRolUsuario(r);
	        }
	    }

	    return u;
	}


	//Método para modificar un usuario

	public boolean actualizarUsuario(Usuario usuario) throws SQLException {

		//Query
		String sql = "UPDATE usuarios SET rol_usuario_id=? WHERE usuario_id=?";

		//Conexión BD
		try
		(Connection conexionBd = ConexionOracle.getConnection();
				PreparedStatement ps = conexionBd.prepareStatement(sql)
				){

			//Preparar y ejecutar sql
			ps.setInt(1, usuario.getRolUsuario().getRolUsuarioId());
	        ps.setInt(2, usuario.getUsuarioId());

			return ps.executeUpdate() ==1;
		}

	}

	// Método para eliminar una usuario existente en la BD
	public boolean eliminarUsuario(int usuarioId) throws SQLException {

		//Query
		String sql = "DELETE FROM usuarios WHERE usuario_id = ?";

		//Conexión BD
		try (
				Connection conexionBd = ConexionOracle.getConnection();
				PreparedStatement ps = conexionBd.prepareStatement(sql)
				) {

			//Preparar y ejecutar sql	
			ps.setInt(1, usuarioId);		 
			return ps.executeUpdate() == 1;

		}	    
	}
	
	// Login por email y password
	public Usuario login(String email, String password) throws SQLException {

	    String sql = "SELECT u.usuario_id, u.password, " +
	                 "p.persona_id, p.nombre, p.apellidos, p.email, " +
	                 "r.rol_usuario_id, r.rol_nombre " +
	                 "FROM usuarios u " +
	                 "JOIN personas p ON u.persona_id = p.persona_id " +
	                 "JOIN roles_usuarios r ON u.rol_usuario_id = r.rol_usuario_id " +
	                 "WHERE p.email = ? AND u.password = ?";

	    try (
	        Connection con = ConexionOracle.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	    ) {

	        ps.setString(1, email);
	        ps.setString(2, password);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            Usuario u = new Usuario();
	            Persona p = new Persona();
	            RolUsuario r = new RolUsuario();

	            u.setUsuarioId(rs.getInt("usuario_id"));
	            u.setPassword(rs.getString("password"));

	            p.setPersonaId(rs.getInt("persona_id"));
	            p.setNombre(rs.getString("nombre"));
	            p.setApellidos(rs.getString("apellidos"));
	            p.setEmail(rs.getString("email"));

	            r.setRolUsuarioId(rs.getInt("rol_usuario_id"));
	            r.setRolNombre(rs.getString("rol_nombre"));

	            u.setPersona(p);
	            u.setRolUsuario(r);

	            return u;
	        }

	        return null;
	    }
	}

}
