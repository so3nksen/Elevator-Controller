package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
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
		jp.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		// initiate grid
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		jp.setBackground(Color.WHITE);

		JLabel headline = new JLabel("Aufzugsinformationen");
		headline.setFont(new Font("Dosis", Font.BOLD, 36));
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		jp.add(headline, c);

		JLabel subHead = new JLabel("In diesem Bereich werden alle im System hinterlegten Aufzugsattribute angezeigt.");
		subHead.setFont(new Font("Dosis", Font.PLAIN, 18));
		subHead.setBorder(BorderFactory.createEmptyBorder(-10, 0, 0, 0));
		c.weightx = 1;
		c.weighty = 0.05;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 5;
		jp.add(subHead, c);

		@SuppressWarnings("serial")
		DefaultTableModel tableModel = new DefaultTableModel() {

			boolean[] canEdit = new boolean[] { false, false, false, false, false, true };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};

		JTable jt = new JTable(tableModel);
		jt.setBackground(Color.LIGHT_GRAY);
		tableModel.addColumn("Id");
		tableModel.addColumn("Typ");
		tableModel.addColumn("Aktuelles Stockwerk");
		tableModel.addColumn("Maximale Personen");
		tableModel.addColumn("Maximales Gewicht");
		tableModel.addColumn("Sonderattribut");

		for (Elevator e : ElevatorList.getElevatorList()) {
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
		c.weighty = 0.85;
		c.gridx = 0;
		c.gridy = 2;
		jp.add(jsp, c);

		return jp;

	}

}
