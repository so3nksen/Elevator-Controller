package GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.ElevatorList;
import Model.ElevatorStatistic;
import Model.ElevatorTypes;
import Properties.Props;

/**
 * Class managing the gui stats panel.
 * 
 * @author lasse
 *
 */
public class ElevatorStatsPanel {

	// enum for set label text type
	private enum KindoOfInfo {
		FLOORS, PERSONS_WEIGHT
	}

	/**
	 * Method returning the jpanel to show in stats section.
	 * 
	 * @return JPanel -> panel to show.
	 */
	public JPanel get() {

		// create root jpanel
		JPanel jp = new JPanel(new GridBagLayout());
		jp.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		jp.setBackground(Color.WHITE);

		// upper headline
		JLabel headline = new JLabel(
				"<html><span style='font-size: 2.4em'><b>Aufzugsstatistiken</b></span><br><span style='font-size: 1.56em'>Nachfolgend werden die gesamten Systemstatistiken des Aufzug-Controllers angezeigt.</span></html>");
		c.weightx = 1;
		c.weighty = 0.2;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		jp.add(headline, c);

		// person elevator child panel.
		c.weightx = 0.3;
		c.weighty = 0.85;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		jp.add(createChildPanel(getInfosFromStats(ElevatorTypes.PERSON, KindoOfInfo.FLOORS),
				getInfosFromStats(ElevatorTypes.PERSON, KindoOfInfo.PERSONS_WEIGHT), "Personen",
				Props.ABSOLUTE_PATH + Props.PERSON_ELEVATOR_ICON), c);

		// spacer for vertical spacing between panels.
		JPanel spacerOne = new JPanel();
		spacerOne.setBackground(Color.WHITE);
		c.weightx = 0.05;
		c.weighty = 0.85;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		jp.add(spacerOne, c);

		// freight elevator child panel.
		c.weightx = 0.3;
		c.weighty = 0.85;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		jp.add(createChildPanel(getInfosFromStats(ElevatorTypes.FREIGHT, KindoOfInfo.FLOORS),
				getInfosFromStats(ElevatorTypes.FREIGHT, KindoOfInfo.PERSONS_WEIGHT), "Fracht (kg)",
				Props.ABSOLUTE_PATH + Props.FREIGHT_ELEVATOR_ICON), c);

		// spacer for vertical spacing between panels.
		JPanel spacerTwo = new JPanel();
		spacerTwo.setBackground(Color.WHITE);
		c.weightx = 0.05;
		c.weighty = 0.85;
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		jp.add(spacerTwo, c);

		// vip elevator child panel.
		c.weightx = 0.3;
		c.weighty = 0.85;
		c.gridx = 4;
		c.gridy = 1;
		c.gridwidth = 1;
		jp.add(createChildPanel(getInfosFromStats(ElevatorTypes.VIP, KindoOfInfo.FLOORS),
				getInfosFromStats(ElevatorTypes.VIP, KindoOfInfo.PERSONS_WEIGHT), "VIP's",
				Props.ABSOLUTE_PATH + Props.VIP_ELEVATOR_ICON), c);

		return jp;

	}

	/**
	 * Method creating childpanel that shows stats for specific elevator type.
	 * 
	 * @param totalFloorsTravelled -> total floors stats.
	 * @param totalPersonsOrWeight -> total weight or persons.
	 * @param prefix               -> specifies if persons, freight or vip.
	 * @param pathToImage          -> path to elevator type icon.
	 * @return JPanel -> panel to show.
	 */
	private JPanel createChildPanel(int totalFloorsTravelled, int totalPersonsOrWeight, String prefix,
			String pathToImage) {

		// childpanel root
		JPanel jp = new JPanel(new GridBagLayout());
		jp.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		GridBagConstraints c = new GridBagConstraints();

		// elevator icon
		JPanel iconPanel = new JPanel(new GridBagLayout());
		try {
			BufferedImage img = ImageIO.read(new File(pathToImage));
			Image scaled = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(scaled);
			JLabel imgLabel = new JLabel(icon);
			iconPanel.add(imgLabel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		c.weightx = 1;
		c.weighty = 0.33;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		jp.add(iconPanel, c);

		// total floors travelled label
		JLabel floorsTravelled = new JLabel(
				"<html><div style='text-align: center;'><span style='font-size: 1.44em;'>Stockwerke zurückgelegt: </span><br> <b><span style='font-size: 5em;'>"
						+ totalFloorsTravelled + "</span></b></div></html>)");
		c.weightx = 1;
		c.weighty = 0.33;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		jp.add(floorsTravelled, c);

		// total persons or weight moved label
		JLabel totalPersonsWeight = new JLabel(
				"<html><div style='text-align: center;'><span style='font-size: 1.44em;'>" + prefix
						+ " befördert:</span><br> <b><span style='font-size: 4em;'>" + totalPersonsOrWeight
						+ "</span></b></div></html>");
		c.weightx = 1;
		c.weighty = 0.33;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		jp.add(totalPersonsWeight, c);

		return jp;
	}

	/**
	 * Method returning stats for given elevator and infotype.
	 * 
	 * @param type -> type of elevator to get stats for.
	 * @param info -> kind of info for elevator (floors or persons/weight)
	 * @return int -> value of statistic.
	 */
	private int getInfosFromStats(ElevatorTypes type, KindoOfInfo info) {

		int information = 0;

		// get floor stats for type// freight elevator child panel.
		if (info == KindoOfInfo.FLOORS) {
			for (ElevatorStatistic es : ElevatorList.getStatisticList()) {
				if (es.getType() == type) {
					information = es.getTotalFloors();
				}
			}
		}

		// get person/weight stats
		else if (info == KindoOfInfo.PERSONS_WEIGHT) {
			for (ElevatorStatistic es : ElevatorList.getStatisticList()) {
				if (es.getType() == type) {
					information = es.getTotalPersonsOrWeight();
				}
			}
		}
		return information;
	}

}
