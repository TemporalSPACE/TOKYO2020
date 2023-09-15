package entrega3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import juegosOlimpicos.Deportista;

import daos.*;

public class FrameDeportista extends JFrame {
	JButton buttonVolver = new JButton("Volver");
	JButton buttonNuevo = new JButton("+Nuevo");
	JButton buttonExportar = new JButton("Exportar CSV");
	JTable table;
	ArrayList<Integer> rowId; //array adicional para mantener la la relación filas id  
	Object[][] data ;

	public FrameDeportista() {
		super("Gestor de olimpiadas - Deportista");
		
		//BOTON EXPORTARCSV
		buttonExportar.addMouseListener(new MouseAdapter() { 
			public void mouseClicked(MouseEvent e) {
				System.out.println("Exportar");
				new DeportistaDAOjdbc().exportCSV();
			}
		});

		// BOTON NUEVO
		buttonNuevo.addMouseListener(new MouseAdapter() { //TE LLEVA A HACER UN NUEVO PAIS
			public void mouseClicked(MouseEvent e) {
				dispose();
				new FrameNuevoDeportista();
			}
		});

		// BOTON VOLVER
		buttonVolver.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) { // REGRESA AL MENU
				dispose();
				new FrameMenu();
			}
		});

		String[] columnNames = { "Nombre", "País", "Disciplina", "Editar", "Eliminar" };

		DeportistaDAO conex = new DeportistaDAOjdbc(); // USO ESTA CONEXION PARA SABER EL TAMAÑO DE LA TABLA

		int filas = conex.getFilas();
		data = new Object[filas][];
		rowId = new ArrayList<Integer>();
		int i = 0;
		for (Deportista d : new DeportistaDAOjdbc().getList()) {
			Object[] f = { d.getNombre(), d.getPais().getNombre(), d.getDisciplina().getNombre(), null, null };
			rowId.add(d.getId());//agrega los id
			data[i] = f;
			i++;
		}

		table = new JTable(new DefaultTableModel(data, columnNames){
		    public boolean isCellEditable(int row, int column)
		    {
		    	if( (column == 3) || (column == 4) ) {
		    		return true;
		    	}
		    	return false;
		    	
		    }
		  });
		new ButtonEliminar(table, "Eliminar");
		new ButtonEditar(table, "Editar");

		Container pane = this.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

		JPanel botones = new JPanel(new FlowLayout());
		botones.add(buttonNuevo);
		botones.add(buttonExportar);
		botones.add(buttonVolver);
		pane.add(botones);

		JPanel panelTable = new JPanel();
		panelTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Deportistas",
				TitledBorder.CENTER, TitledBorder.TOP));
		panelTable.add(new JScrollPane(table));
		pane.add(panelTable);

		this.setIconImage(new ImageIcon(this.getClass().getResource("/images/OLIM.GIF")).getImage());
		this.setPreferredSize(new Dimension(550, 575));
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// Boton ELIMINAR
	class ButtonEliminar extends ButtonColumna {
		
		public ButtonEliminar(JTable table, String column) {
			super(table,column,"Eliminar");
		}

		public void actionPerformed(ActionEvent e) {
			fireEditingStopped();
			// Te hace saltar una ventanita para confirmar la eliminacion
			if (JOptionPane.showConfirmDialog(null, "¿Seguro queres eliminar?", "Confirmar eliminar",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
				DeportistaDAO conex = new DeportistaDAOjdbc();
				int row = table.getSelectedRow();
				if (row != -1) {
					// Busca el id de la fila y lo elimina de la base de datos
					conex.eliminar(rowId.get(row));
					rowId.remove(row); //actualizo el array
					((DefaultTableModel) table.getModel()).removeRow(row); // elimina la fila
				}
			}
		}
	}

	// Boton EDITAR
	class ButtonEditar extends ButtonColumna {
		
		public ButtonEditar(JTable table, String column) {
			super(table,column,"Editar");
		}

		public void actionPerformed(ActionEvent e) { // Te cambia a FRAMEEDITARPAIS
			fireEditingStopped();
			int row = table.getSelectedRow();
			dispose();
			Deportista d = new DeportistaDAOjdbc().getDeportista(rowId.get(row));
			new FrameEditarDeportista(d);
		}
	}

}
