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

public class FrameEditarDeportista extends FrameAgregarDeportista {
	Deportista d; // guardo el deportista que recibo para poder pasalo al listener

	public FrameEditarDeportista(Deportista d) {
		super("Gestor de olimpiadas - EDITAR DEPORTISTA");

		this.d = d;
		
		nombre.setText(d.getNombre());
		apellido.setText(d.getApellido());
		email.setText(d.getEmail());
		telefono.setText(Long.toString(d.getTelefono()));

		buttonGuardar.addActionListener(new EditarListener());

		paises.setSelectedItem(d.getPais());
		disciplinas.setSelectedItem(d.getDisciplina());
	}

	private class EditarListener implements ActionListener {

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

				Pais pais = (Pais) paises.getSelectedItem();
				Disciplina disciplina = (Disciplina) disciplinas.getSelectedItem();
				d.setApellido(elApellido);
				d.setDisciplina(disciplina);
				d.setNombre(elNombre);
				d.setTelefono(Long.valueOf(elTelefono));
				d.setPais(pais);
				d.setEmail(elEmail);
				
				exceptions.Verificadores.VerificarNoRepetido(d);

				DeportistaDAO conex = new DeportistaDAOjdbc();
				
				conex.editar(d);

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
