package daos;

import juegosOlimpicos.Disciplina;

public interface DisciplinaDeportistaDAO {
	public void eliminarDeportista(int idDeportista);
	
	public void editar(int idDeportista, int idDisciplina);
	
	public void guardar(int idDeportista, int idDisciplina);
	
	public Disciplina getDisciplina(int idDeportista);
}
