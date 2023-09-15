package entrega3;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;


public abstract class ButtonColumna extends AbstractCellEditor
		implements TableCellRenderer, TableCellEditor, ActionListener {	JTable table;
	JButton renderButton;
	JButton editButton;
	String text;

	public ButtonColumna(JTable table, String column,String text) {
			super();
			this.table = table;
			renderButton = new JButton();
			this.text = text;

			editButton = new JButton();
			editButton.setFocusPainted(false);
			editButton.addActionListener(this);

			table.getColumn(column).setCellRenderer(this);
			table.getColumn(column).setCellEditor(this);
		}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (hasFocus) {
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		} else if (isSelected) {
			renderButton.setForeground(table.getSelectionForeground());
			renderButton.setBackground(table.getSelectionBackground());
		} else {
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		}

		renderButton.setText((value == null) ? this.text : value.toString());
		return renderButton;
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		text = (value == null) ? this.text : value.toString();
		editButton.setText(text);
		return editButton;
	}

	public Object getCellEditorValue() {
		return text;
	}

	public abstract void actionPerformed(ActionEvent e) ;

}
