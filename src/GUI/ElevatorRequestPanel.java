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

public class ElevatorRequestPanel {

	public JPanel get() {

		JPanel jp = new JPanel(new GridBagLayout());
		jp.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		// initiate grid
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		jp.setBackground(Color.WHITE);

		JLabel headline = new JLabel("Aufzugsauftrag");
		headline.setFont(new Font("Dosis", Font.BOLD, 36));
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		jp.add(headline, c);

		JLabel subHead = new JLabel(
				"Nachfolgend können Aufzugsfahrten mit Angabe relevanter Attribute beauftragt werden. ");
		subHead.setFont(new Font("Dosis", Font.PLAIN, 18));
		subHead.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		c.weightx = 1;
		c.weighty = 0.05;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 5;
		jp.add(subHead, c);

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
			Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
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

		JLabel floorsTravelled = new JLabel("<html><div style='text-align: center;'>Stockwerke zurückgelegt: <br> <b>"
				+ totalFloorsTravelled + "</b></div></html>)");
		c.weightx = 1;
		c.weighty = 0.33;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		jp.add(floorsTravelled, c);

		JLabel totalPersonsWeight = new JLabel("<html><div style='text-align: center;'>" + prefix
				+ " befördert: <br> <b>" + totalPersonsOrWeight + "</b></div></html>)");
		c.weightx = 1;
		c.weighty = 0.33;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		jp.add(totalPersonsWeight, c);

		return jp;
	}

}
