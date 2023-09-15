package entrega3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class FrameAgregarPais extends JFrame {
	JButton buttonVolver = new JButton("Volver");
	JTextField nombre = new JTextField("");
	JLabel errores = new JLabel("");

	public FrameAgregarPais(String titulo) {
		super(titulo);
		this.setLayout(new FlowLayout());
		buttonVolver.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) { // REGRESA AL MENU
				dispose();
				new FramePais();
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		JTextField texto = new JTextField("Nombre");
		texto.setEditable(false);
		panel.add(texto);
		panel.add(nombre);
		panel.setPreferredSize(new Dimension(320, 30));
		errores.setPreferredSize(new Dimension(320, 30));
		errores.setForeground(Color.red);

		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/OLIM.GIF")).getImage());
		this.add(errores);
		this.add(panel);
		this.add(buttonVolver);
		this.setPreferredSize(new Dimension(400, 200));
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
