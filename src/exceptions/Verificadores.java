package exceptions;

import daos.DeportistaDAOjdbc;
import daos.PaisDAOjdbc;
import juegosOlimpicos.Deportista;
import juegosOlimpicos.Pais;

public class Verificadores {

	public static void VerificarNoVacio(String elTexto) throws WrongException {
		// Verifica que se escribio algo
		if (elTexto.equals(""))
			throw new WrongException("'Vacio'");
	}

	public static void VerificarNumeros(String elTexto) throws WrongException {
		// Verifica que solo contenga numeros
		char[] chars = elTexto.toCharArray();
		for (char c : chars) {
			if (!Character.isDigit(c)) {
				throw new WrongException(elTexto);
			}
		}
	}

	public static void VerificarLetras(String elTexto) throws WrongException {
		// Verifica que solo contenga letras
		char[] chars = elTexto.toCharArray();
		for (char c : chars) {
			if (!Character.isLetter(c)) {
				throw new WrongException(elTexto);
			}
		}
	}

	public static void VerificarArroba(String elTexto) throws WrongException {
		// Verifica que el email tenga arroba
		char[] chars = elTexto.toCharArray();
		int i = 0;
		boolean loHay = false;
		while ((i < chars.length) && !loHay) {
			if (chars[i] == '@')
				loHay = true;
			i++;
		}
		if (!loHay)
			throw new WrongException(elTexto);
	}

	public static void VerificarNoRepetido(Deportista deportista) throws RepeatedException {
		// Verifica que no sea un deportista repetido
		if (new DeportistaDAOjdbc().isDeportista(deportista)) {
			throw new RepeatedException();
		}
	}

	public static void VerificarNoRepetido(Pais p) throws RepeatedException {
		// Verifica que el pais no sea repetido
		if (new PaisDAOjdbc().isPais(p)) {
			throw new RepeatedException();
		}

	}

}
