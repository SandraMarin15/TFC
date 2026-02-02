package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Cita;
import modelo.Mascota;
import util.ConexionOracle;

public class CitaDAO {

	/*CitaDAO: Clase para el acceso a los datos del Objeto.	 
		- Incluye los métodos CRUD y control de campos nulos sin son necesarios.
		
		 -- Esta clase esta asociada a la clase Mascota.*/	


	//Método para insetar citas en la bbdd
	public boolean insertarCita(Cita cita) throws SQLException {

		//Query
		String sql = "INSERT INTO citas (mascota_id, cita_tipo, fecha_hora) "
				+ "VALUES (?, ?, ?) ";

		try (//Conexion bbdd
				Connection conexionBd = ConexionOracle.getConnection();

				//Preparar sql
				PreparedStatement ps = conexionBd.prepareStatement(sql)){

			//Rellenar bbdd			
			ps.setInt(1, cita.getMascota().getMascotaId());
			ps.setString(2, cita.getCitaTipo());

			//Control de null Oracle
			if (cita.getFechaHora() != null)ps.setTimestamp(3, cita.getFechaHora());
			else ps.setNull(3, java.sql.Types.TIMESTAMP);

			//Ejecutar sql
			int rows = ps.executeUpdate();
			System.out.println("Filas insertadas: " + rows);


			//Retorna si hay filas afectadas
			return rows == 1;

		}			
	}

	//Método para la listar todas las citas que tenga la Clínica	
	public List<Cita> listarCita() throws SQLException{

		//Nuevo objeto
		List<Cita> lista = new ArrayList<>();

		//Query
		String sql = "SELECT c.cita_id, c.cita_tipo, c.fecha_hora, "
				+ "m.mascota_id "
				+ "FROM citas c "
				+ "JOIN mascotas m ON c.mascota_id = m.mascota_id ";

		try(//Conexion bbdd
				Connection conexionBd = ConexionOracle.getConnection();

				//Preparar y ejecutar sql
				PreparedStatement ps = conexionBd.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){

			//Ejecutar al menos una vez
			while(rs.next()){

				//Objetos
				Cita c = new Cita();
				Mascota m = new Mascota();

				//Leer columnas y rellenar los Objetos
				c.setCitaId(rs.getInt("cita_id"));
				c.setCitaTipo(rs.getString("cita_tipo"));
				c.setFechaHora(rs.getTimestamp("fecha_hora"));
				m.setMascotaId(rs.getInt("mascota_id"));
				c.setMascota(m);


				//Añado cita a la lista
				lista.add(c);

			}

		}

		return lista;
	}


	//Método para leer una cita por ID	
	public Cita leerCitaPorId(int id) throws SQLException{

		// Objeto Cita con resultado null, se rellena en el nuevo Objeto
		Cita c = null;

		//Query
		String sql = "SELECT c.cita_id, c.cita_tipo, c.fecha_hora, "
				+ "m.mascota_id "
				+ "FROM citas c "
				+ "JOIN mascotas m ON c.mascota_id = m.mascota_id "
				+ "WHERE c.cita_id = ? ";

		//Conexión BD
		try(				
				Connection conexionBd = ConexionOracle.getConnection();
				PreparedStatement ps = conexionBd.prepareStatement(sql)
				) {
			
			//Leer por id
			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {

				//Solo una fila (id)
				if(rs.next()) {

					//Objetos
					c = new Cita();
					Mascota m = new Mascota();

					//Leer columnas y rellenar los Objetos
					c.setCitaId(rs.getInt("cita_id"));
					c.setCitaTipo(rs.getString("cita_tipo"));
					c.setFechaHora(rs.getTimestamp("fecha_hora"));
					m.setMascotaId(rs.getInt("mascota_id"));
					c.setMascota(m);

				}

			}

		}
		return c;

	}

	//Método para modificar una cita existente
	public boolean actualizarCita(Cita cita) throws SQLException {

		//Query
		String sql = "UPDATE citas "
				+ "SET cita_tipo = ?, fecha_hora = ?, mascota_id = ? "
				+ "WHERE cita_id = ?";
		
		//Conexión BD
		try (
				Connection conexionBd = ConexionOracle.getConnection();
				PreparedStatement ps = conexionBd.prepareStatement(sql)
				) {

			//Preparar y ejecutar sql
			ps.setString(1, cita.getCitaTipo());
			
			//Constrol null Oracle
			if(cita.getFechaHora() != null)
			ps.setTimestamp(2, cita.getFechaHora());
			else ps.setNull(2, java.sql.Types.TIMESTAMP);
			
			ps.setInt(3, cita.getMascota().getMascotaId());
			ps.setInt(4, cita.getCitaId());
			
			
			return ps.executeUpdate() ==1;
		}	

	}

	// Método para eliminar una cita existente en la BD
	public boolean eliminarCita(int citaId) throws SQLException {

		//Query
		String sql = "DELETE FROM citas WHERE cita_id = ?";

		//Conexión BD
		try (
				Connection conexionBd = ConexionOracle.getConnection();
				PreparedStatement ps = conexionBd.prepareStatement(sql)
				) {
			
			//Preparar y ejecutar sql	
			ps.setInt(1, citaId);		    
			return ps.executeUpdate() == 1;

		}	    
	}


}
