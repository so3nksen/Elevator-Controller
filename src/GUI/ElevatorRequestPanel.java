package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Model.ElevatorTypes;
import Util.Props;

public class ElevatorRequestPanel implements ActionListener {

	private ElevatorTypes elevatorType = ElevatorTypes.PERSON;
	private int personsOrWeight = 0;
	private int fromFloor = 0;
	private int tofloor = 1;

	private static final String prefixTypeLabel = "Aufzugsart";
	private static final String prefixPersonWeightLabel = "Einheiten";
	private static final String prefixStartFloorLabel = "Startstockwerk";
	private static final String prefixEndFloorLabel = "Endstockwerk";
	private static final String undefindedLabel = "keine Angabe";

	private JPanel requestPanel;
	private JLabel typeSideLabel;
	private JLabel personWeightSideLabel;
	private JLabel startFloorSideLabel;
	private JLabel endFloorSideLabel;
	private JButton confirmBtn;

	private enum Steps {
		ONE, TWO, THREE, SUCCESS, NEW
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

		JLabel headline = new JLabel("Aufzugsauftrag");
		headline.setFont(new Font("Dosis", Font.BOLD, 36));
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		jp.add(headline, c);

		JLabel subHead = new JLabel(
				"Nachfolgend können Aufzugsfahrten mit Angabe relevanter Attribute beauftragt werden. ");
		subHead.setFont(new Font("Dosis", Font.PLAIN, 18));
		subHead.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		c.weightx = 0.7;
		c.weighty = 0.05;
		c.gridx = 0;
		c.gridy = 1;
		jp.add(subHead, c);

		requestPanel = createRequestPanel();
		c.weightx = 0.65;
		c.weighty = 0.85;
		c.gridx = 0;
		c.gridy = 2;
		jp.add(requestPanel, c);

		JPanel spacer = new JPanel();
		spacer.setBackground(Color.WHITE);
		c.weightx = 0.05;
		c.weighty = 0.85;
		c.gridx = 1;
		c.gridy = 2;
		jp.add(spacer, c);

		c.weightx = 0.3;
		c.weighty = 0.85;
		c.gridx = 2;
		c.gridy = 2;
		jp.add(createConfirmPanel(), c);

		return jp;
	}

	/**
	 * creating left sided request panel.
	 * 
	 * @return -> the panel to show
	 */
	private JPanel createRequestPanel() {

		JPanel jp = new JPanel(new GridBagLayout());
		jp.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		jp.add(stepOne());

		return jp;
	}

	/**
	 * creating right sided confirm panel.
	 * 
	 * @return -> the panel to show
	 */
	private JPanel createConfirmPanel() {

		JPanel jp = new JPanel(new GridBagLayout());
		jp.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		// initiate grid
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;

		JLabel confirmPanelPlaceholder = new JLabel(
				"<html><div style='text-align: center; font-size: 1.12em'><b>Auftragsübersicht</b></div></html>");
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		jp.add(confirmPanelPlaceholder, c);

		typeSideLabel = new JLabel(createLabelText(prefixTypeLabel, undefindedLabel));
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		jp.add(typeSideLabel, c);

		personWeightSideLabel = new JLabel(createLabelText(prefixPersonWeightLabel, undefindedLabel));
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		jp.add(personWeightSideLabel, c);

		startFloorSideLabel = new JLabel(createLabelText(prefixStartFloorLabel, undefindedLabel));
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		jp.add(startFloorSideLabel, c);

		endFloorSideLabel = new JLabel(createLabelText(prefixEndFloorLabel, undefindedLabel));
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		jp.add(endFloorSideLabel, c);

		JLabel btnInfoLabel = new JLabel(
				"<html><div style='text-align: left;'><span style='font-size: 0.76em'>Vervollständige deine Anfrage,<br> um den Auftrag bestätigen zu können.</span></div></html>");
		btnInfoLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		jp.add(btnInfoLabel, c);

		confirmBtn = new JButton("<html><div style='text-align: center;'>Auftrag<br>bestätigen</div></html>");
		confirmBtn.setEnabled(false);
		confirmBtn.addActionListener(this);
		confirmBtn.setActionCommand(Steps.SUCCESS.name());
		c.gridx = 0;
		c.gridy = 6;
		c.weightx = 1;
		c.weighty = 0.5;
		c.gridwidth = 1;
		jp.add(confirmBtn, c);

		JButton newBtn = new JButton("<html><div style='text-align: center;'>Neuer Auftrag</div></html>");
		newBtn.addActionListener(this);
		newBtn.setActionCommand(Steps.NEW.name());
		c.gridx = 0;
		c.gridy = 7;
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridwidth = 1;
		jp.add(newBtn, c);

		return jp;
	}

	private JPanel stepOne() {
		JPanel jp = new JPanel(new GridBagLayout());
		// initiate grid
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;

		JLabel label = new JLabel(
				"<html><div style='text-align: center;'><span style='font-size: 2em'>Was möchten Sie transportieren?"
						+ "</span></div></html>");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		jp.add(label, c);

		ButtonGroup group = new ButtonGroup();

		JRadioButton radioPerson = new JRadioButton("Personen", true);
		radioPerson.addActionListener(this);
		radioPerson.setActionCommand(ElevatorTypes.PERSON.name());
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		jp.add(radioPerson, c);

		JRadioButton radioFreight = new JRadioButton("Fracht");
		radioFreight.addActionListener(this);
		radioFreight.setActionCommand(ElevatorTypes.FREIGHT.name());
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		jp.add(radioFreight, c);

		JRadioButton radioVip = new JRadioButton("VIP Auftrag");
		radioVip.addActionListener(this);
		radioVip.setActionCommand(ElevatorTypes.VIP.name());
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		jp.add(radioVip, c);

		group.add(radioPerson);
		group.add(radioFreight);
		group.add(radioVip);

		JButton nextBtn = new JButton("Weiter");
		nextBtn.addActionListener(this);
		nextBtn.setActionCommand(Steps.ONE.name());
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		jp.add(nextBtn, c);

		return jp;
	}

	/**
	 * Step two of request process.
	 * 
	 * @return JPanel -> The panel that inclued all elements for step two.
	 */
	private JPanel stepTwo() {
		JPanel jp = new JPanel(new GridBagLayout());
		// initiate grid
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		JLabel label = null;

		if (this.elevatorType == ElevatorTypes.PERSON) {
			label = new JLabel(
					"<html><div style='text-align: center;'><span style='font-size: 2em'>Wie viel Personen wollen Sie befördern?</span></div></html>");
			String[] numbers = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
			JComboBox numbersCombo = new JComboBox(numbers);
			numbersCombo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					personsOrWeight = Integer.parseInt((String) numbersCombo.getSelectedItem());
					personWeightSideLabel
							.setText(createLabelText(prefixPersonWeightLabel, String.valueOf(personsOrWeight)));
				}
			});
			numbersCombo.setSelectedIndex(0);
			c.gridx = 0;
			c.gridy = 1;
			jp.add(numbersCombo, c);
		} else if (this.elevatorType == ElevatorTypes.FREIGHT) {
			label = new JLabel(
					"<html><div style='text-align: center;'><span style='font-size: 2em'>Wie viel Gewicht soll befördert werden?</span></div></html>");
			String[] numbers = { "1000", "2000", "3000", "4000", "5000" };
			JComboBox numbersCombo = new JComboBox(numbers);
			numbersCombo.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					personsOrWeight = Integer.parseInt((String) numbersCombo.getSelectedItem());
					personWeightSideLabel
							.setText(createLabelText(prefixPersonWeightLabel, String.valueOf(personsOrWeight)));
				}
			});
			numbersCombo.setSelectedIndex(0);
			c.gridx = 0;
			c.gridy = 1;
			jp.add(numbersCombo, c);
		} else if (this.elevatorType == ElevatorTypes.VIP) {
			label = new JLabel(
					"<html><div style='text-align: center;'><span style='font-size: 2em'>Wie viele VIPs sollen befördert werden?"
							+ "</span></div></html>");
			String[] numbers = { "1", "2", "3", "4", "5" };
			JComboBox numbersCombo = new JComboBox(numbers);
			numbersCombo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					personsOrWeight = Integer.parseInt((String) numbersCombo.getSelectedItem());
					personWeightSideLabel
							.setText(createLabelText(prefixPersonWeightLabel, String.valueOf(personsOrWeight)));
				}
			});
			numbersCombo.setSelectedIndex(0);
			c.gridx = 0;
			c.gridy = 1;
			jp.add(numbersCombo, c);
		}
		c.gridx = 0;
		c.gridy = 0;
		jp.add(label, c);

		JButton nextBtn = new JButton("Weiter");
		nextBtn.addActionListener(this);
		nextBtn.setActionCommand(Steps.TWO.name());
		c.gridx = 0;
		c.gridy = 2;
		jp.add(nextBtn, c);

		return jp;
	}

	private JPanel stepThree() {
		JPanel jp = new JPanel(new GridBagLayout());
		// initiate grid
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;

		JLabel label = new JLabel(
				"<html><div style='text-align: center;'><span style='font-size: 2em'>Wo soll der Aufzug starten und wo enden?"
						+ "</span></div></html>");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		jp.add(label, c);

		JLabel confirmLabel = new JLabel(
				"<html><div style='text-align: center;'><span style='font-size: 1em'>Bitte überprüfe und bestätige deinen Auftrag in der Auftragsübersicht rechts.</span></div></html>");
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		jp.add(confirmLabel, c);

		String[] floors = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
				"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33",
				"34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
				"51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67",
				"68", "69", "70", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83",
				"84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100" };
		JComboBox startFloorCombo = new JComboBox(floors);
		startFloorCombo.setSelectedIndex(0);
		startFloorCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fromFloor = Integer.parseInt((String) startFloorCombo.getSelectedItem());
				if (fromFloor != tofloor) {
					startFloorSideLabel.setText(createLabelText(prefixStartFloorLabel, String.valueOf(fromFloor)));
					System.out.println(fromFloor);
				} else {

				}
			}
		});
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		jp.add(startFloorCombo, c);

		JComboBox endFloorCombo = new JComboBox(floors);
		endFloorCombo.setSelectedIndex(1);
		endFloorCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tofloor = Integer.parseInt((String) endFloorCombo.getSelectedItem());
				if (tofloor != fromFloor) {
					endFloorSideLabel.setText(createLabelText(prefixEndFloorLabel, String.valueOf(tofloor)));
					System.out.println("das geht nicht");
				}
			}
		});
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		jp.add(endFloorCombo, c);

		return jp;
	}

	/**
	 * JPanel to show when everything is alright.
	 * 
	 * @return -> JPanel to show.
	 */
	private JPanel stepSuccess() {
		JPanel jp = new JPanel(new GridBagLayout());
		// initiate grid
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 0;
		JPanel iconPanel = new JPanel(new GridBagLayout());
		try {
			// Show success button
			BufferedImage img = ImageIO.read(new File(Props.ABSOLUTE_PATH + Props.CHECK_ICON));
			Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(scaled);
			JLabel imgLabel = new JLabel(icon);
			imgLabel.setBackground(Color.WHITE);
			iconPanel.add(imgLabel);

			Icon gif = new ImageIcon(Props.ABSOLUTE_PATH + Props.MOVING_ELEVATOR);

			//Image scaledGif = gif.getScaledInstance(350, 550, Image.SCALE_SMOOTH);

			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					turnImgIntoGif(imgLabel, gif);
				}
			}, 2000);



		} catch (IOException e) {
			e.printStackTrace();
		}

		jp.add(iconPanel, c);


		JLabel label = new JLabel(
				"<html><div style='text-align: center;'><span style='font-size: 2em'>Der Aufzug ist auf dem Weg zu Dir.<br> In Kürze wird die Steuerung resetted.");
		c.gridx = 0;
		c.gridy = 1;

		jp.add(label, c);
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				resetPanels();
			}
		}, 5000);



		return jp;
	}

	private JPanel arrivingElevator() {
		// show the moving elevator gif :-)
		JPanel jp = new JPanel(new GridBagLayout());
		// initiate grid
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;

		c.gridx = 0;
		c.gridy = 0;

		JPanel iconPanel = new JPanel(new GridBagLayout());

		try {
			BufferedImage gif = ImageIO.read(new File(Props.ABSOLUTE_PATH + Props.MOVING_ELEVATOR));
			ImageIcon gifIcon = new ImageIcon(gif);
			JLabel imgLabel = new JLabel(gifIcon);
			imgLabel.setBackground(Color.WHITE);
			iconPanel.add(imgLabel);
		} catch (IOException e) {
			e.printStackTrace();
		}

		jp.add(iconPanel, c);
		jp.setVisible(true);

		// timer to reset panels
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				resetPanels();
			}
		}, 5000);

		return jp;
	}

	private void turnImgIntoGif(JLabel labelToChange, Icon icon_gifToChangeInto) {
		labelToChange.setIcon(icon_gifToChangeInto);
	}

	/**
	 * action perform events of sidebar btns. -> working with
	 */
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();

		// to step two
		if (action.equals(Steps.ONE.name())) {
			this.requestPanel.removeAll();
			this.requestPanel.add(stepTwo());
			this.requestPanel.revalidate();
			this.requestPanel.repaint();
			this.typeSideLabel.setText(createLabelText(prefixTypeLabel, elevatorType.name()));

		}
		// to step three
		else if (action.equals(Steps.TWO.name())) {
			this.requestPanel.removeAll();
			this.requestPanel.add(stepThree());
			this.requestPanel.revalidate();
			this.requestPanel.repaint();
			this.confirmBtn.setEnabled(true);
		}
		// cancel order and start new
		else if (action.equals(Steps.NEW.name())) {
			resetPanels();
		}
		// all is successful
		else if (action.equals(Steps.SUCCESS.name())) {

			this.requestPanel.removeAll();
			this.requestPanel.add(stepSuccess());
			this.requestPanel.revalidate();
			this.requestPanel.repaint();
			this.confirmBtn.setEnabled(false);



		}
		// person radio btn is clicked
		else if (action.equals(ElevatorTypes.PERSON.name())) {
			this.elevatorType = ElevatorTypes.PERSON;
		}
		// freight radio btn is clicked
		else if (action.equals(ElevatorTypes.FREIGHT.name())) {
			this.elevatorType = ElevatorTypes.FREIGHT;
		}
		// vip radio btn is clicked
		else if (action.equals(ElevatorTypes.VIP.name())) {
			this.elevatorType = ElevatorTypes.VIP;
		}

	}


	private void resetPanels() {
		// reset all vars in case of new order
		this.elevatorType = ElevatorTypes.PERSON;
		this.personsOrWeight = 0;
		this.fromFloor = 0;
		this.tofloor = 0;

		// reset panel
		this.requestPanel.removeAll();
		this.requestPanel.add(stepOne());
		this.requestPanel.revalidate();
		this.requestPanel.repaint();
		this.confirmBtn.setEnabled(false);

		// reset side text
		this.typeSideLabel.setText(createLabelText(prefixTypeLabel, undefindedLabel));
		this.personWeightSideLabel.setText(createLabelText(prefixPersonWeightLabel, undefindedLabel));
		this.startFloorSideLabel.setText(createLabelText(prefixStartFloorLabel, undefindedLabel));
		this.endFloorSideLabel.setText(createLabelText(prefixEndFloorLabel, undefindedLabel));
	}

	private String createLabelText(String prefix, String value) {
		StringBuilder s = new StringBuilder();
		s.append("<html><div style='text-align:center'>");
		s.append("<span style='font-size: 1.2em'><b>" + prefix + "</b></span><br>");
		s.append("<span style='font-size: 1em'><i>" + value + "</i></span>");
		s.append("</div></html>");
		return s.toString();
	}

}
