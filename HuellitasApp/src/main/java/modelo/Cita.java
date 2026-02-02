package modelo;

//Cada Cita está asociada a una mascota de la clínica

import java.sql.Timestamp;

public class Cita {
	
	// Atributos
	private int citaId;
	private Mascota mascota;
	private String citaTipo;
	private Timestamp fechaHora;
	
	//Constructores
	public Cita() {	}

	public Cita(int citaId, Mascota mascota, String citaTipo, Timestamp fechaHora) {
		
		this.citaId = citaId;
		this.mascota = mascota;
		this.citaTipo = citaTipo;
		this.fechaHora = fechaHora;
	}

	public int getCitaId() {
		return citaId;
	}

	public void setCitaId(int citaId) {
		this.citaId = citaId;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public String getCitaTipo() {
		return citaTipo;
	}

	public void setCitaTipo(String citaTipo) {
		this.citaTipo = citaTipo;
	}

	public Timestamp getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Timestamp fechaHora) {
		this.fechaHora = fechaHora;
	}

	@Override
	public String toString() {
		return "Cita ID = " + this.citaId + "\n"
				+"Mascota ID = " + (mascota != null ? mascota.getMascotaId() : "null") + "\n" 
				+ "Tipo de cita = " + this.citaTipo + "\n"
				+ "Fecha y hora de la cita = " + (this.fechaHora != null ? this.fechaHora : " - ") + "\n";
	}
	

}
