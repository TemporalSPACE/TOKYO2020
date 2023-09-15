package exceptions;

public class WrongException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mensaje;

	public WrongException(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public String getMessage() {
		return this.mensaje;
	}

}
