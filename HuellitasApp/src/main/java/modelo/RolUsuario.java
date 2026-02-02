package modelo;

/*Rol usuario define:
 Lasfuncionalidades disponibles dentro del sistema de la clínica*/

public class RolUsuario {
	
	// Atributos	
	private int rolUsuarioId;
	private String rolNombre;
	
	
	// Constructores
	public RolUsuario() {}


	public RolUsuario(int rolUsuarioId, String rolNombre) {
		this.rolUsuarioId = rolUsuarioId;
		this.rolNombre = rolNombre;
	}

	
	// Getters y Setters

	public int getRolUsuarioId() {
		return rolUsuarioId;
	}


	public void setRolUsuarioId(int rolUsuarioId) {
		this.rolUsuarioId = rolUsuarioId;
	}


	public String getRolNombre() {
		return rolNombre.trim() ;
	}


	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre.toUpperCase().replace('Á', 'A').replace('É', 'E').replace('Í', 'I').replace('Ó', 'O').replace('Ú', 'U');
	}


	//Método sobrescrito
	@Override
	public String toString() {
		return "Rol Usuario ID =" + this.rolUsuarioId + "\n" + 
				"Rol Nombre =" + this.rolNombre + "\n";
	}
	
	
	
	
	
	
	
	
	
	

}
