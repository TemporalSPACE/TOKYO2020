package daos;

import java.util.List;

import juegosOlimpicos.Deportista;

public interface DeportistaDAO {
	public List<Deportista> getList();

	public int getFilas();
	
	public Deportista getDeportista(int id);
	
	public boolean isDeportista(Deportista D );
	
	public void editar(Deportista d);
	
	public void eliminar(int id);
	
	/**
	 * @param d
	 * @return retorna el id del deportista
	 */
	public int guardar(Deportista d);
	
	public void exportCSV();
}
