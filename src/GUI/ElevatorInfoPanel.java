package GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Elevator;
import Model.ElevatorList;
import Model.FreightElevator;
import Model.PersonElevator;
import Model.VipElevator;

/**
 * ElevatorInfoPanel class.
 * 
 * @author lasse
 *
 */
public class ElevatorInfoPanel {

	/**
	 * Method returning infopanel where all important elevator infos are shown
	 * inside jtable.
	 * 
	 * @return JPanel -> panel to show.
	 */

	public JPanel get() {

		JPanel jp = new JPanel(new GridBagLayout());
		// initiate grid
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		jp.setBackground(Color.WHITE);

		JLabel l = new JLabel("Aufzugsinformationen");
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		jp.add(l, c);

		DefaultTableModel tableModel = new DefaultTableModel();
		JTable jt = new JTable(tableModel);
		tableModel.addColumn("Id");
		tableModel.addColumn("Typ");
		tableModel.addColumn("Aktuelles Stockwerk");
		tableModel.addColumn("Maximale Personen");
		tableModel.addColumn("Maximales Gewicht");
		tableModel.addColumn("Sonderattribut");

		for (Elevator e : ElevatorList.getList()) {
			Object[] rowData = null;
			if (e instanceof PersonElevator) {
				rowData = new Object[] { e.getId(), "Personenaufzug", e.getCurrentFloor(), e.getMaxPersons(), "-",
						"Musik: " + ((PersonElevator) e).getMusicPlaying() };
			} else if (e instanceof FreightElevator) {
				rowData = new Object[] { e.getId(), "Frachtaufzug", e.getCurrentFloor(), "-", e.getMaxWeight(),
						"Quadratmeter: " + ((FreightElevator) e).getSqaureMeters() };
			} else if (e instanceof VipElevator) {
				rowData = new Object[] { e.getId(), "VIP-Aufzug", e.getCurrentFloor(), e.getMaxPersons(), "-",
						"Geschwindigkeit: " + ((VipElevator) e).getMaxSpeed() };
			}
			tableModel.addRow(rowData);
		}

		JScrollPane jsp = new JScrollPane(jt);
		c.weightx = 1;
		c.weighty = 0.9;
		c.gridx = 0;
		c.gridy = 1;
		jp.add(jsp, c);

		return jp;

	}

}
