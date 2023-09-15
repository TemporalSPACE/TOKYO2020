package juegosOlimpicos;

import java.util.ArrayList;

/**
 * La delegacion de cada pais, la forman Deportistas y equipos.
 * @author Reverte y Calvo
 **/

public class Delegacion {
	private int id;
	private Pais pais;
	private ArrayList<Deportista> deportistas;
	private ArrayList<Equipo> equiposE;
	
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public ArrayList<Deportista> getDeportistas() {
		return deportistas;
	}
	public void setDeportistas(ArrayList<Deportista> deportistas) {
		this.deportistas = deportistas;
	}
	public ArrayList<Equipo> getEquiposE() {
		return equiposE;
	}
	public void setEquiposE(ArrayList<Equipo> equiposE) {
		this.equiposE = equiposE;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
