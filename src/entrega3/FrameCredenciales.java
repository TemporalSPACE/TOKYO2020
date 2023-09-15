package entrega3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import daos.*;

public class FrameCredenciales extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton buttonVolver = new JButton("Volver");
	JButton buttonGuardar = new JButton("Guardar");
	static JTextField usuario = new JTextField();
	static JTextField contraseña = new JTextField();

	public FrameCredenciales() {
		super("Gestor de olimpiadas - CONFIGURACION");
		this.setLayout(new FlowLayout());
		buttonVolver.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) { // REGRESA AL MENU
				dispose();
				new FrameMenu();
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));

		JTextField texto = new JTextField("Usuario");
		texto.setEditable(false);
		panel.add(texto);
		panel.add(usuario);

		texto = new JTextField("Contraseña");
		texto.setEditable(false);
		panel.add(texto);
		panel.add(contraseña);

		panel.setPreferredSize(new Dimension(320, 30));
		buttonGuardar.addActionListener(new GuardarListener());
		this.add(panel);
		this.add(buttonGuardar);
		this.add(buttonVolver);
		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/OLIM.GIF")).getImage());
		this.setPreferredSize(new Dimension(400, 120));
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private class GuardarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			MiConnection.realizarConexion(usuario.getText(), contraseña.getText());
			dispose();
			new FrameMenu();
		}
	}

}
