package juegosOlimpicos;

import java.util.ArrayList;

/**
 * La clase "central", su objetivo es el manejo de las otras clases
 * @author Reverte y Calvo
 **/

public class Juegos {
	
	private int año;
	private Pais pais;
	private ArrayList<Disciplina> disciplinas;
	private ArrayList<Sede> sedes;
	private ArrayList<Delegacion> delegaciones;
	private Mascota mascota;
	private Tipo_Juegos tipo;
	
	
	public int getAño() {
		return año;
	}
	public void setAño(int año) {
		this.año = año;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public ArrayList<Sede> getSedes() {
		return sedes;
	}
	public void setSedes(ArrayList<Sede> sedes) {
		this.sedes = sedes;
	}
	public ArrayList<Delegacion> getDelegaciones() {
		return delegaciones;
	}
	public void setDelegaciones(ArrayList<Delegacion> delegaciones) {
		this.delegaciones = delegaciones;
	}
	public Mascota getMascota() {
		return mascota;
	}
	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}
	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	public Tipo_Juegos getTipo() {
		return tipo;
	}
	public void setTipo(Tipo_Juegos tipo) {
		this.tipo = tipo;
	}
	
	
	

}
