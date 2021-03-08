package GUI;

import java.awt.Color;
import java.awt.Font;
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
import Util.Props;

public class ElevatorStatsPanel {

	private enum KindoOfInfo {
		FLOORS, PERSONS_WEIGHT
	}

	public JPanel get() {

		JPanel jp = new JPanel(new GridBagLayout());
		jp.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		// initiate grid
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		jp.setBackground(Color.WHITE);

		JLabel headline = new JLabel("Aufzugsstatistiken");
		headline.setFont(new Font("Dosis", Font.BOLD, 36));
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		jp.add(headline, c);

		JLabel subHead = new JLabel(
				"Nachfolgend werden die gesamten Systemstatistiken des Aufzug-Controllers angezeigt.");
		subHead.setFont(new Font("Dosis", Font.PLAIN, 18));
		subHead.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		c.weightx = 1;
		c.weighty = 0.05;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 5;
		jp.add(subHead, c);

		c.weightx = 0.3;
		c.weighty = 0.85;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		jp.add(createChildPanel(getInfosFromStats(ElevatorTypes.PERSON, KindoOfInfo.FLOORS),
				getInfosFromStats(ElevatorTypes.PERSON, KindoOfInfo.PERSONS_WEIGHT), "Personen",
				Props.ABSOLUTE_PATH + Props.PERSON_ELEVATOR_ICON), c);

		JPanel spacerOne = new JPanel();
		spacerOne.setBackground(Color.WHITE);
		c.weightx = 0.05;
		c.weighty = 0.85;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		jp.add(spacerOne, c);

		c.weightx = 0.3;
		c.weighty = 0.85;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		jp.add(createChildPanel(getInfosFromStats(ElevatorTypes.FREIGHT, KindoOfInfo.FLOORS),
				getInfosFromStats(ElevatorTypes.FREIGHT, KindoOfInfo.PERSONS_WEIGHT), "Fracht (kg)",
				Props.ABSOLUTE_PATH + Props.FREIGHT_ELEVATOR_ICON), c);

		JPanel spacerTwo = new JPanel();
		spacerTwo.setBackground(Color.WHITE);
		c.weightx = 0.05;
		c.weighty = 0.85;
		c.gridx = 3;
		c.gridy = 2;
		c.gridwidth = 1;
		jp.add(spacerTwo, c);

		c.weightx = 0.3;
		c.weighty = 0.85;
		c.gridx = 4;
		c.gridy = 2;
		c.gridwidth = 1;
		jp.add(createChildPanel(getInfosFromStats(ElevatorTypes.VIP, KindoOfInfo.FLOORS),
				getInfosFromStats(ElevatorTypes.VIP, KindoOfInfo.PERSONS_WEIGHT), "VIP's",
				Props.ABSOLUTE_PATH + Props.VIP_ELEVATOR_ICON), c);

		return jp;

	}

	private JPanel createChildPanel(int totalFloorsTravelled, int totalPersonsOrWeight, String prefix,
			String pathToImage) {
		JPanel jp = new JPanel(new GridBagLayout());
		jp.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		jp.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();

		JPanel iconPanel = new JPanel(new GridBagLayout());
		iconPanel.setBackground(Color.WHITE);
		// try to load request image
		try {
			BufferedImage img = ImageIO.read(new File(pathToImage));
			Image scaled = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(scaled);
			JLabel imgLabel = new JLabel(icon);
			imgLabel.setBackground(Color.WHITE);
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

		JLabel floorsTravelled = new JLabel(
				"<html><div style='text-align: center;'><span style='font-size: 1.44em;'>Stockwerke zurückgelegt: </span><br> <b><span style='font-size: 5em;'>"
						+ totalFloorsTravelled + "</span></b></div></html>)");
		c.weightx = 1;
		c.weighty = 0.33;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		jp.add(floorsTravelled, c);

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

	private int getInfosFromStats(ElevatorTypes type, KindoOfInfo info) {
		int information = 0;

		if (info == KindoOfInfo.FLOORS) {
			for (ElevatorStatistic es : ElevatorList.getStatisticList()) {
				if (es.getType() == type) {
					information = es.getTotalFloors();
				}
			}
		} else if (info == KindoOfInfo.PERSONS_WEIGHT) {
			for (ElevatorStatistic es : ElevatorList.getStatisticList()) {
				if (es.getType() == type) {
					information = es.getTotalPersonsOrWeight();
				}
			}
		}
		return information;
	}

}
