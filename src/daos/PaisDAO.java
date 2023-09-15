package daos;

import java.util.List;

import exceptions.ExceptionBaseDatos;
import juegosOlimpicos.Pais;

public interface PaisDAO {
	public List<Pais> getList();

	public void guardar(Pais d);

	public int getFilas();

	public void eliminar(int id) throws ExceptionBaseDatos;

	public Pais getPais(int id);

	public void editar(Pais p);
}
