package juegosOlimpicos;

import java.util.ArrayList;

/**
 * Los equipos, quienes lo forman y a que juegan.
 * @author Reverte y Calvo
 **/

public class Equipo {

	private String nombre;
	private ArrayList<Disciplina> disciplinas;
	private Entrenador entrenador;
	private ArrayList<Deportista> deportistas;
	private Delegacion delegacionE;
	private ArrayList<Evento> eventos;
	private ArrayList<Medalla> medallas;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	public Entrenador getEntrenador() {
		return entrenador;
	}
	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}
	public ArrayList<Deportista> getDeportistas() {
		return deportistas;
	}
	public void setDeportistas(ArrayList<Deportista> deportistas) {
		this.deportistas = deportistas;
	}
	public Delegacion getDelegacionE() {
		return delegacionE;
	}
	public void setDelegacionE(Delegacion delegacionE) {
		this.delegacionE = delegacionE;
	}
	public ArrayList<Evento> getEventos() {
		return eventos;
	}
	public void setEventos(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}
	public ArrayList<Medalla> getMedallas() {
		return medallas;
	}
	public void setMedallas(ArrayList<Medalla> medallas) {
		this.medallas = medallas;
	}
	
	
	
}
