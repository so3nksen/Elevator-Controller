package GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Elevator;
import Model.ElevatorList;
import Model.FreightElevator;
import Model.PersonElevator;
import Model.VipElevator;
import Properties.Props;

/**
 * ElevatorInfoPanel class.
 * 
 * @author lasse
 *
 */
public class ElevatorInfoPanel {

	/**
	 * Method returning infopanel where all important elevator infos are shown in
	 * jtable.
	 * 
	 * @return JPanel -> panel to show.
	 */

	public JPanel get() {

		// create root jpanel
		JPanel jp = new JPanel(new GridBagLayout());
		jp.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		// initiate grid
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		jp.setBackground(Color.WHITE);

		// upper headline
		JLabel headline = new JLabel(
				"<html><span style='font-size: 2.4em'><b>Aufzugsinformationen</b></span><br><span style='font-size: 1.56em'>Übersicht aller im System hinterlegten Aufzugsattribute.&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></html>");
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		jp.add(headline, c);

		// create customized table model
		@SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel() {

			// only last column editable
			boolean[] canEdit = new boolean[] { false, false, false, false, false, true };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}

			// handle change of specialattribute
			public void fireTableCellUpdated(int row, int column) {
				for (Elevator e : ElevatorList.getElevatorList()) {
					if (e.getId() == row + 1) {
						if (e instanceof PersonElevator) {
							((PersonElevator) e).setMusicPlaying(getValueAt(row, 5).toString());
						} else if (e instanceof FreightElevator) {
							((FreightElevator) e).setSquareMeters(getValueAt(row, 5).toString());
						} else if (e instanceof VipElevator) {
							((VipElevator) e).setMaxSpeed(getValueAt(row, 5).toString());
						}
						ImageIcon icon = new ImageIcon(Props.ABSOLUTE_PATH + Props.CHECK_ICON);
						Image scaled = icon.getImage().getScaledInstance(42, 42, 0);
						JOptionPane.showMessageDialog(null, "Attribut erfolgreich geändert!", "Änderung vorgenommen",
								JOptionPane.OK_OPTION, new ImageIcon(scaled));
					}
				}
			}
		};

		// create jtable with specific table model
		JTable jt = new JTable(tableModel);
		jt.setBackground(Color.LIGHT_GRAY);
		tableModel.addColumn("Id");
		tableModel.addColumn("Typ");
		tableModel.addColumn("Aktuelles Stockwerk");
		tableModel.addColumn("Maximale Personen");
		tableModel.addColumn("Maximales Gewicht");
		tableModel.addColumn("Sonderattribut (Musik, Quadratmeter, Geschwindigkeit)");

		// create table data object
		for (Elevator e : ElevatorList.getElevatorList()) {
			Object[] rowData = null;
			if (e instanceof PersonElevator) {
				rowData = new Object[] { e.getId(), "Personenaufzug", e.getCurrentFloor(), e.getMaxPersons(), "-",
						((PersonElevator) e).getMusicPlaying() };
			} else if (e instanceof FreightElevator) {
				rowData = new Object[] { e.getId(), "Frachtaufzug", e.getCurrentFloor(), "-", e.getMaxWeight(),
						((FreightElevator) e).getSqaureMeters() };
			} else if (e instanceof VipElevator) {
				rowData = new Object[] { e.getId(), "VIP-Aufzug", e.getCurrentFloor(), e.getMaxPersons(), "-",
						((VipElevator) e).getMaxSpeed() };
			}
			tableModel.addRow(rowData);
		}

		// scrollpane for table
		JScrollPane jsp = new JScrollPane(jt);
		c.weightx = 1;
		c.weighty = 0.9;
		c.gridx = 0;
		c.gridy = 1;
		jp.add(jsp, c);

		return jp;

	}

}
