package entrega3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import daos.*;

public class FrameMenu extends JFrame {
	private JButton[] botones;

	public FrameMenu() {
		super("Gestor de olimpiadas");
		this.setLayout(new GridBagLayout());
		this.botones = new JButton[7];

		// BOTON DEPORTISTA
		this.botones[0] = new JButton("Deportista");
		this.botones[0].setIcon(new ImageIcon(this.getClass().getResource("/images/DEP.GIF")));
		this.botones[0].addMouseListener(new MouseAdapter() { // ABRE DEPORTISTAS
			public void mouseClicked(MouseEvent e) {
				if (MiConnection.isSeHizoConexion()) { // Verifico si se logeo
					System.out.println("Abriendo 'Deportistas'");
					dispose();
					new FrameDeportista();
				} else {
					dispose();
					new FrameCredenciales();
				}
			}
		});

		// BOTON PAIS
		this.botones[1] = new JButton("País");
		this.botones[1].setIcon(new ImageIcon(this.getClass().getResource("/images/FLAG.GIF")));
		this.botones[1].addMouseListener(new MouseAdapter() { // ABRE PAIS
			public void mouseClicked(MouseEvent e) {
				if (MiConnection.isSeHizoConexion()) { // Verifico si se logeo
					System.out.println("Abriendo 'Pais'");
					dispose();
					new FramePais();
				} else {
					dispose();
					new FrameCredenciales();
				}
			}
		});

		// BOTON DISCIPLINA
		this.botones[2] = new JButton("Disciplina");
		this.botones[2].setIcon(new ImageIcon(this.getClass().getResource("/images/PEL.GIF")));
		this.botones[2].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("Abriendo 'Disciplina'");
			}
		});

		// BOTON CONF
		this.botones[6] = new JButton();
		this.botones[6].setIcon(new ImageIcon(this.getClass().getResource("/images/CONF.GIF")));
		this.botones[6].setPreferredSize(new Dimension(30, 30));
		this.botones[6].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) { // ABRE CONFIGURACIONES
				System.out.println("Abriendo 'Configuraciones'");
				dispose();
				new FrameCredenciales();
			}
		});

		// OTROS BOTONES
		this.botones[3] = new JButton("Sin definir");
		this.botones[4] = new JButton("Sin definir");
		this.botones[5] = new JButton("Sin definir");

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		this.add(this.botones[3], c);

		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		this.add(this.botones[4], c);

		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 1;
		this.add(this.botones[5], c);

		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		this.add(this.botones[0], c);

		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		this.add(this.botones[1], c);

		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		this.add(this.botones[2], c);

		c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0.5;
		c.gridx = 6;
		c.gridy = 0;
		this.add(this.botones[6], c);

		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/OLIM.GIF")).getImage());
		this.setVisible(true);
		this.setPreferredSize(new Dimension(500, 200));
		this.pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
