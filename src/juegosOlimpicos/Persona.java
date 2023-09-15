package juegosOlimpicos;

public class Persona {
		private String nombre;
		private String apellido;
		private int edad;
		private String email;
		private long telefono;
		private int id;
		private Pais pais;
		
		public Persona(String nombre, String apellido, String email, long telefono) {
			super();
			this.setId(-1);// -1 es que no lo coloco la base de datos
			this.email = email;
			this.nombre = nombre;
			this.apellido = apellido;
			this.telefono = telefono;
		}
		
		public Persona(int id, String nombre, String apellido, String email, long telefono) {
			this(nombre,apellido,email,telefono);
			this.setId(id);
		}
		
		public Persona( String nombre, String apellido, String email,long telefono, Pais p) {
			this(nombre, apellido, email, telefono);
			this.setPais(p);
		}
		
		public Persona(int id, String nombre, String apellido, String email,long telefono, Pais p) {
			this(id,nombre, apellido, email, telefono);
			this.setPais(p);
		}
		
		
		public Persona() {
			super();
		}

		public String getEmail() {
			return email;
		}





		public void setEmail(String email) {
			this.email = email;
		}





		public long getTelefono() {
			return telefono;
		}





		public void setTelefono(long telefono) {
			this.telefono = telefono;
		}	
		
		
		
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getApellido() {
			return apellido;
		}
		public void setApellido(String apellido) {
			this.apellido = apellido;
		}
		public int getEdad() {
			return edad;
		}
		public void setEdad(int edad) {
			this.edad = edad;
		}

		public Pais getPais() {
			return pais;
		}

		public void setPais(Pais pais) {
			this.pais = pais;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		

}
