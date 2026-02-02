package servicio;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import dao.CitaDAO;
import modelo.Cita;



public class CitaServicio {
	
	//CitaDAO -> UsuarioServicio para conectar con la base de datos sin sql
			private CitaDAO  citaDAO = new CitaDAO();
			
			

			/*Creo una nueva cita en la BD
			 * Devuelve true si se ha insertado correctamente*/

			public boolean crearCita(Cita c) throws SQLException {

			    // Verificar que la mascota existe 
			    if (c.getMascota() == null) return false;

			    // Insertar 
			    return citaDAO.insertarCita(c);
			}


			//Obtener listado de las Citas de la BD
			public List<Cita>listarCita() throws SQLException{
				return citaDAO.listarCita();
			}

			//Buscar Cita por ID
			public Cita buscarCitaPorId(int cId) throws SQLException {
				return citaDAO.leerCitaPorId(cId);		
			}

			//Modificar Cita existente, con control de Objetos nulos para fechas
			public boolean modificarCita(Cita c) throws SQLException {
				
				if (c == null || c.getMascota() == null) return false;


			    Cita citaBD = citaDAO.leerCitaPorId(c.getCitaId());

			    if (citaBD == null) return false;

			    boolean hayCambios = false;

			    if (!citaBD.getCitaTipo().equalsIgnoreCase(c.getCitaTipo()))
			        hayCambios = true;
			    
			   //fecha
			    if (!Objects.equals(citaBD.getFechaHora(), c.getFechaHora()))
			        hayCambios = true;

			    if (citaBD.getMascota().getMascotaId() != c.getMascota().getMascotaId())
			        hayCambios = true;

			    if (!hayCambios) return false;

			    return citaDAO.actualizarCita(c);

			}
			
			//Borrar Cita por ID			
			public boolean borrarCita(int cId) throws SQLException {
				
				Cita citaBD = citaDAO.leerCitaPorId(cId);
				
				//Si no existe -> No borra
				if(citaBD == null) return false;
				
				//Si existe -> Borra
				return citaDAO.eliminarCita(cId);
			}


}
