package juegosOlimpicos;

import java.util.ArrayList;
import java.util.Date;

/**
 * Cada evento, donde, cuando y que.
 * @author Reverte y Calvo
 **/

public class Evento {
	
	private Date fecha;
	private Sede sede;
	private Disciplina disciplina;
	private Tipo_Competencia tipo;
	private ArrayList<Equipo> equiposP;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public Tipo_Competencia getTipo() {
		return tipo;
	}
	public void setTipo(Tipo_Competencia tipo) {
		this.tipo = tipo;
	}
	public ArrayList<Equipo> getEquiposP() {
		return equiposP;
	}
	public void setEquiposP(ArrayList<Equipo> equiposP) {
		this.equiposP = equiposP;
	}

	
}
