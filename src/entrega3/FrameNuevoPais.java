package entrega3;

import javax.swing.*;

import juegosOlimpicos.Pais;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import daos.*;
import exceptions.*;

public class FrameNuevoPais extends FrameAgregarPais {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton buttonGuardar = new JButton("Guardar");

	public FrameNuevoPais() {
		super("Gestor de olimpiadas - NUEVO PAIS");
		buttonGuardar.addActionListener(new GuardarListener());
		this.add(buttonGuardar);
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
				conex.guardar(new Pais(elTexto));
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
