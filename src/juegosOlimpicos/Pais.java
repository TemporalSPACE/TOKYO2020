package juegosOlimpicos;

public class Pais {

	private String nombre;
	private int id;

	public Pais(String nombre) {
		this.nombre = nombre;
		this.id = -1;
	}

	public Pais(String nombre, int id) {
		this.nombre = nombre;
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() == this.getClass()) {
			return this.getId() == ((Pais)obj).getId();
		}
		return super.equals(obj);
	}
	
	

}
