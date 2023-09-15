package daos;

import java.util.List;

import juegosOlimpicos.Disciplina;

public interface DisciplinaDAO {
	public List<Disciplina> getList();

	public Disciplina getDisciplina(int id);
}
