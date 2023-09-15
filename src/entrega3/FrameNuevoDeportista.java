package entrega3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import daos.DeportistaDAO;
import daos.DeportistaDAOjdbc;
import exceptions.RepeatedException;
import exceptions.WrongException;
import juegosOlimpicos.Deportista;
import juegosOlimpicos.Disciplina;
import juegosOlimpicos.Pais;

public class FrameNuevoDeportista extends FrameAgregarDeportista {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FrameNuevoDeportista() {
		super("Gestor de olimpiadas - NUEVO DEPORTISTA");
		buttonGuardar.addActionListener(new GuardarListener());
	}

	private class GuardarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				String elNombre = nombre.getText();

				elNombre = elNombre.replaceAll(" ", "");
				exceptions.Verificadores.VerificarNoVacio(elNombre);
				exceptions.Verificadores.VerificarLetras(elNombre);

				String elEmail = email.getText();
				elEmail.replaceAll(" ", "");
				exceptions.Verificadores.VerificarNoVacio(elEmail);
				exceptions.Verificadores.VerificarArroba(elEmail);

				String elTelefono = telefono.getText();
				elTelefono.replaceAll(" ", "");
				exceptions.Verificadores.VerificarNumeros(elTelefono);

				String elApellido = apellido.getText();
				elApellido = elApellido.replaceAll(" ", "");
				exceptions.Verificadores.VerificarNoVacio(elApellido);
				exceptions.Verificadores.VerificarLetras(elApellido);

				DeportistaDAO conex = new DeportistaDAOjdbc();

				Pais p = (Pais) paises.getSelectedItem();

				Disciplina d = (Disciplina) disciplinas.getSelectedItem();

				Deportista nuevo = new Deportista(elNombre, elApellido, elEmail, Long.valueOf(elTelefono), p, d);

				exceptions.Verificadores.VerificarNoRepetido(nuevo);

				conex.guardar(nuevo);

				dispose();
				new FrameDeportista();
			} catch (WrongException we) {
				errores.setText("Te equivocaste en algo: " + we.getMessage());
			} catch (RepeatedException re) {
				errores.setText("Ya existe");
			}
		}

	}

}
