package entrega3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import juegosOlimpicos.Pais;

import daos.*;
import exceptions.*;

public class FrameEditarPais extends FrameAgregarPais {
	JButton buttonEditar = new JButton("Editar");
	int id;

	public FrameEditarPais(Pais p) {
		super("Gestor de olimpiadas - EDITAR PAIS");
		this.id = p.getId();
		nombre.setText(p.getNombre());
		buttonEditar.addActionListener(new GuardarListener());
		this.add(buttonEditar);

	}

	private class GuardarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String elTexto = nombre.getText();
			elTexto = elTexto.replaceAll(" ", "");
			Pais p = new Pais(elTexto);

			try {
				exceptions.Verificadores.VerificarNoVacio(elTexto);
				exceptions.Verificadores.VerificarLetras(elTexto);
				exceptions.Verificadores.VerificarNoRepetido(p);
				PaisDAO conex = new PaisDAOjdbc();
				conex.editar(new Pais(elTexto, id));
				dispose();
				new FramePais();
			} catch (WrongException we) {
				errores.setText("Te equivocaste al rellenar los campos: " + we.getMessage());				
			} catch (RepeatedException re) {
				errores.setText("Pais repetido");
			}
		}
	}

}
