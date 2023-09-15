package exceptions;

public class ExceptionBaseDatos extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	

	public ExceptionBaseDatos(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public String getMessage() {
		return this.mensaje;
	}
		
}
