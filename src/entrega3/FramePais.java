package entrega3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import daos.*;
import exceptions.ExceptionBaseDatos;
import juegosOlimpicos.Pais;

public class FramePais extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton buttonNuevo;
	private JButton buttonVolver;
	private JTable table;
	private ArrayList<Integer> rowId; //array adicional para mantener la la relación filas id  

	Object[][] data;

	public FramePais() {
		super("Gestor de olimpiadas - PAIS");

		this.setLayout(new FlowLayout());
		JPanel botones = new JPanel(new GridLayout(0, 2));
		
		// BOTON NUEVO
		buttonNuevo = new JButton("Nuevo");
		buttonNuevo.addMouseListener(new MouseAdapter() { // REGRESA AL MENU
			public void mouseClicked(MouseEvent e) {
				dispose();
				new FrameNuevoPais();
			}
		});

		// BOTON VOLVER
		buttonVolver = new JButton("Volver");
		buttonVolver.addMouseListener(new MouseAdapter() { // REGRESA AL MENU
			public void mouseClicked(MouseEvent e) {
				dispose();
				new FrameMenu();
			}
		});
		
		// TABLA
		String[] columnNames = { "Id", "País", "Editar", "Eliminar" };
		PaisDAO conex = new PaisDAOjdbc(); // USO ESTA CONEXION PARA SABER EL TAMAÑO DE LA TABLA
		data = new Object[conex.getFilas()][];
		rowId = new ArrayList<Integer>();
		int i = 0;
		for (Pais e : new PaisDAOjdbc().getList()) {
			Object[] f = { e.getId(), e.getNombre() };
			rowId.add(e.getId());
			data[i] = f;
			i++;
		}
		
		table = new JTable(new ModeloPaises(data, columnNames){
		    public boolean isCellEditable(int row, int column)
		    {
		    	if( (column == 2) || (column == 3) ) {
		    		return true;
		    	}
		    	return false;
		    	
		    }
		  });

		new ButtonEliminar(table, "Eliminar");
		new ButtonEditar(table, "Editar");
		JPanel PanelPais = new JPanel();
		PanelPais.add(new JScrollPane(table));
		PanelPais.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Países",
				TitledBorder.CENTER, TitledBorder.TOP));

		botones.add(buttonNuevo);
		botones.add(buttonVolver);
		
		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/OLIM.GIF")).getImage());
		this.add(botones);
		this.add(PanelPais);
		this.setPreferredSize(new Dimension(550, 600));
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public class ModeloPaises extends DefaultTableModel {
		private static final long serialVersionUID = 8164505737793198161L;

		public ModeloPaises(final Object[][] datos, final String[] titulos) {
			super(datos, titulos);
		}
	}

	// MOSTRAR BOTONES

	// Boton ELIMINAR
	class ButtonEliminar extends ButtonColumna{

		public ButtonEliminar(JTable table, String column) {
			super(table,column,"Eliminar");
		}
		
		public void actionPerformed(ActionEvent e) {
			fireEditingStopped();

			// Te hace saltar una ventanita para confirmar la eliminacion
			if (JOptionPane.showConfirmDialog(null, "¿Seguro queres eliminar?", "Confirmar eliminar",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
				PaisDAOjdbc conex = new PaisDAOjdbc();
				int row = table.getSelectedRow();
				if (row != -1) {
					// Busca el id de la fila y lo elimina de la base de datos
					try {
						conex.eliminar((int) ((DefaultTableModel) table.getModel()).getValueAt(row, 0));
						rowId.remove(row);
						((DefaultTableModel) table.getModel()).removeRow(row); // elimina la fila
					} catch (ExceptionBaseDatos e1) {
						System.out.print("errror");
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			}
		}
	}

	// Boton EDITAR
	class ButtonEditar extends ButtonColumna{
		public ButtonEditar(JTable table, String column) {
			super(table,column,"Editar");
		}
		
		public void actionPerformed(ActionEvent e) { // Te cambia a FRAMEEDITARPAIS
			fireEditingStopped();
			int row = table.getSelectedRow();
			dispose();
			Pais p = new PaisDAOjdbc().getPais(rowId.get(row));
			new FrameEditarPais(p);
		}
	}
}