package servicio;

import java.sql.SQLException;
import java.util.List;

import dao.MascotaDAO;
import modelo.Mascota;

public class MascotaServicio {
	
	
	//MascotaDAO -> UsuarioServicio para conectar con la base de datos sin sql
		private MascotaDAO  mascotaDAO = new MascotaDAO();

		/*Creo una nueva mascota en la BD
		 * Devuelve true si se ha insertado correctamente*/

		/*public boolean crearMascota(Mascota m) throws SQLException {
			Mascota mascotaBD = mascotaDAO.obtenerMascotaPorId(m.getMascotaId());

			//Si ya existe -> No crea
			if(mascotaBD != null) return false;

			//Si no existe se inserta la nueva mascota
			return mascotaDAO.insertarMascota(m);
		}
*/
		public boolean crearMascota(Mascota m) throws SQLException {
		    return mascotaDAO.insertarMascota(m);
		}

		//Obtener listado de las mascotas de la BD
		public List<Mascota>listarMascota() throws SQLException{
			return mascotaDAO.listarMascota();
		}

		//Buscar Mascota por ID
		public Mascota buscarMascotaPorId(int mId) throws SQLException {
			return mascotaDAO.obtenerMascotaPorId(mId);		
		}

		
		//Modificar por id
		public boolean modificarMascotaPorId(Mascota m) throws SQLException {
		    Mascota mascotaBD = mascotaDAO.obtenerMascotaPorId(m.getMascotaId());
		    if (mascotaBD == null) return false;
		    return mascotaDAO.actualizarMascota(m);
		}


		
		//Borrar mascota por ID
		
		public boolean borrarMascota(int pId) throws SQLException {
			
			Mascota MascotaBD = mascotaDAO.obtenerMascotaPorId(pId);
			
			//Si no existe -> No borra
			if(MascotaBD == null) return false;
			
			//Si existe -> Borra
			return mascotaDAO.eliminarMascota(pId);
		}

}
