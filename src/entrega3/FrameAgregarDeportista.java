package entrega3;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;

import juegosOlimpicos.*;

import daos.*;

public abstract class FrameAgregarDeportista extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton buttonVolver = new JButton("Volver");
	JButton buttonGuardar = new JButton("Guardar");

	JTextField nombre = new JTextField("");
	JTextField apellido = new JTextField("");
	JTextField email = new JTextField("");
	JTextField telefono = new JTextField("");
	
	JComboBox<Pais> paises;
	JComboBox<Disciplina> disciplinas;
	JPanel panelInformacion;

	JLabel errores = new JLabel("");
	
	public FrameAgregarDeportista(String titulo) {
		super(titulo);
		this.setLayout(new FlowLayout());
		// BOTON VOLVER
		buttonVolver.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) { // REGRESA AL MENU
				dispose();
				new FrameDeportista();
			}
		});
		
		cargarComponent(); //carga los campos y el boton volver y guardar
		
		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/OLIM.GIF")).getImage());
		this.setPreferredSize(new Dimension(450, 250));
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	private void cargarComponent() {
		panelInformacion = new JPanel();
		panelInformacion.setLayout(new GridLayout(6, 2));

		JTextField texto = new JTextField("Nombre");
		texto.setEditable(false);
		panelInformacion.add(texto);
		panelInformacion.add(nombre);

		texto = new JTextField("e-mail");
		texto.setEditable(false);
		panelInformacion.add(texto);
		panelInformacion.add(email);

		texto = new JTextField("Apellido");
		texto.setEditable(false);
		panelInformacion.add(texto);
		panelInformacion.add(apellido);

		texto = new JTextField("Telefono");
		texto.setEditable(false);
		panelInformacion.add(texto);
		panelInformacion.add(telefono);

		texto = new JTextField("País");
		texto.setEditable(false);
		panelInformacion.add(texto);

		paises = new JComboBox<Pais>();

		actualizarComboPais(paises);
		panelInformacion.add(paises);

		texto = new JTextField("Disciplina");
		texto.setEditable(false);
		panelInformacion.add(texto);

		disciplinas = new JComboBox<Disciplina>();
		DisciplinaComboRender renderDisciplina = new DisciplinaComboRender();
		disciplinas.setRenderer(renderDisciplina);

		actualizarComboDisciplina(disciplinas);
		panelInformacion.add(disciplinas);

		errores.setPreferredSize(new Dimension(350, 30));
		errores.setForeground(Color.red);

		this.add(errores);

		this.add(panelInformacion);

		this.add(buttonVolver);

		this.add(buttonGuardar);
	}

	private void actualizarComboDisciplina(JComboBox<Disciplina> disciplinas) {
		List<Disciplina> lista = new DisciplinaDAOjdbc().getList();
		for (Disciplina e : lista) {
			disciplinas.addItem(e);
		}
	}

	private void actualizarComboPais(JComboBox<Pais> paises) {
		List<Pais> lista = new PaisDAOjdbc().getList();
		for (Pais e : lista) {
			paises.addItem(e);
		}
	}

	class PaisComboRender extends JLabel implements ListCellRenderer<Pais> {
		public PaisComboRender() {
			setOpaque(true);
			setHorizontalAlignment(CENTER);
			setVerticalAlignment(CENTER);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends Pais> list, Pais value, int index,
				boolean isSelected, boolean cellHasFocus) {
			// Get the selected index. (The index parameter isn't
			// always valid, so just use the value.)

			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}

			setText(value.getNombre());

			return this;
		}
	}

	class DisciplinaComboRender extends JLabel implements ListCellRenderer<Disciplina> {
		public DisciplinaComboRender() {
			setOpaque(true);
			setHorizontalAlignment(CENTER);
			setVerticalAlignment(CENTER);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends Disciplina> list, Disciplina value, int index,
				boolean isSelected, boolean cellHasFocus) {
			// Get the selected index. (The index parameter isn't
			// always valid, so just use the value.)

			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}

			setText(value.getNombre());

			return this;
		}
	}



	

}
