package GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import Model.ElevatorTypes;
import Properties.Props;
import Util.RequestElevator;

public class ElevatorRequestPanel implements ActionListener {

	// declaration of standard request parameters
	private ElevatorTypes elevatorType = ElevatorTypes.PERSON;
	private int personsOrWeight = 1;
	private int fromFloor = 0;
	private int tofloor = 1;

	// prefixes for easier label management
	private static final String prefixTypeLabel = "Aufzugsart";
	private static final String prefixPersonWeightLabel = "Einheiten";
	private static final String prefixStartFloorLabel = "Startstockwerk";
	private static final String prefixEndFloorLabel = "Endstockwerk";
	private static final String undefindedLabel = "keine Angabe";

	// initialize panels and buttons needed in whole class
	private JPanel requestPanel;
	private JLabel typeSideLabel;
	private JLabel personWeightSideLabel;
	private JLabel startFloorSideLabel;
	private JLabel endFloorSideLabel;
	private JButton confirmBtn;

	// enum representing steps of request process
	private enum Steps {
		ONE, TWO, THREE, SUCCESS, NEW
	}

	/**
	 * Method returning the jpanel to show in request section.
	 * 
	 * @return JPanel -> panel to show.
	 */
	public JPanel get() {

		// create root panel
		JPanel jp = new JPanel(new GridBagLayout());
		jp.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		jp.setBackground(Color.WHITE);

		// upper headline
		JLabel headline = new JLabel("<html><span style='font-size: 2.4em'><b>Aufzugsauftrag "
				+ "</b></span><br><span style='font-size: 1.56em'>Erstellen Sie mittels des Formulars unten einen neuen Beförderungsauftrag.</span></html>");

		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		jp.add(headline, c);

		// request child panel.
		requestPanel = createRequestPanel();
		c.weightx = 0.65;
		c.weighty = 0.9;
		c.gridx = 0;
		c.gridy = 1;
		jp.add(requestPanel, c);

		// spacer for vertical spacing between panels.
		JPanel spacer = new JPanel();
		spacer.setBackground(Color.WHITE);
		c.weightx = 0.05;
		c.weighty = 0.85;
		c.gridx = 1;
		c.gridy = 1;
		jp.add(spacer, c);

		// right sided confirm child panel.
		c.weightx = 0.3;
		c.weighty = 0.85;
		c.gridx = 2;
		c.gridy = 1;
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

		// root panel of child.
		JPanel jp = new JPanel(new GridBagLayout());
		jp.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;

		// upper headline of sidepanel
		JLabel confirmPanelPlaceholder = new JLabel(
				"<html><div style='text-align: center; font-size: 1.12em'><b>Auftragsübersicht</b></div></html>");
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		jp.add(confirmPanelPlaceholder, c);

		// type label of sidepanel
		typeSideLabel = new JLabel(createLabelText(prefixTypeLabel, undefindedLabel));
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		jp.add(typeSideLabel, c);

		// units label of sidepanel
		personWeightSideLabel = new JLabel(createLabelText(prefixPersonWeightLabel, undefindedLabel));
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		jp.add(personWeightSideLabel, c);

		// startfloor label of sidepanel
		startFloorSideLabel = new JLabel(createLabelText(prefixStartFloorLabel, undefindedLabel));
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		jp.add(startFloorSideLabel, c);

		// endfloor label of sidepanel
		endFloorSideLabel = new JLabel(createLabelText(prefixEndFloorLabel, undefindedLabel));
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		jp.add(endFloorSideLabel, c);

		// info label of sidepanel
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

		// confirm btn of sidepanel
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

		// reset/new btn of sidepanel
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

	/**
	 * Method creating childpanel that shows shows step one of request.
	 * 
	 * @return JPanel -> panel to show in step one.
	 */
	private JPanel stepOne() {

		// root of step two
		JPanel jp = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;

		JLabel label = new JLabel(
				"<html><div style='text-align: center;'><span style='font-size: 2em'>Was möchten Sie transportieren?<br>&nbsp;"
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

		JPanel spacer = new JPanel();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		jp.add(spacer, c);

		JButton nextBtn = new JButton("Weiter");
		nextBtn.addActionListener(this);
		nextBtn.setActionCommand(Steps.ONE.name());
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;
		jp.add(nextBtn, c);

		return jp;
	}

	/**
	 * Method creating childpanel that shows shows step two of request.
	 * 
	 * @return JPanel -> panel to show in step one.
	 */
	private JPanel stepTwo() {

		// root of step two
		JPanel jp = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		JLabel label = null;

		if (this.elevatorType == ElevatorTypes.PERSON) {
			label = new JLabel(
					"<html><div style='text-align: center;'><span style='font-size: 2em'>Wie viel Personen wollen Sie befördern?<br>&nbsp;</span></div></html>");
			// all possible values of combobox for elevatortype person
			String[] numbers = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
					"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" };
			@SuppressWarnings({ "unchecked", "rawtypes" })
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
					"<html><div style='text-align: center;'><span style='font-size: 2em'>Wie viel Gewicht soll befördert werden?<br>&nbsp;</span></div></html>");
			// all possible values of combobox for elevatortype freight
			String[] numbers = { "1000", "2000", "3000", "4000", "5000", "6000", "7000", "8000", "9000", "10000" };
			@SuppressWarnings({ "rawtypes", "unchecked" })
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
					"<html><div style='text-align: center;'><span style='font-size: 2em'>Wie viele VIPs sollen befördert werden?<br>&nbsp;"
							+ "</span></div></html>");
			// all possible values of combobox for elevatortype vip
			String[] numbers = { "1", "2", "3", "4", "5" };
			@SuppressWarnings({ "rawtypes", "unchecked" })
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

		JPanel spacer = new JPanel();
		c.gridx = 0;
		c.gridy = 2;
		jp.add(spacer, c);

		JButton nextBtn = new JButton("Weiter");
		nextBtn.addActionListener(this);
		nextBtn.setActionCommand(Steps.TWO.name());
		c.gridx = 0;
		c.gridy = 3;
		jp.add(nextBtn, c);

		return jp;
	}

	/**
	 * Method creating childpanel that shows shows step three of request.
	 * 
	 * @return JPanel -> panel to show in step one.
	 */
	private JPanel stepThree() {

		// root of step three
		JPanel jp = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;

		JLabel label = new JLabel(
				"<html><div style='text-align: center;'><span style='font-size: 2em'>Wo soll der Aufzug starten und wo enden?<br>&nbsp;"
						+ "</span></div></html>");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		jp.add(label, c);

		JLabel endLabel = new JLabel(
				"<html><div style='text-align: center;'><span style='font-size: 1.44em'>Start</span></div></html>");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		jp.add(endLabel, c);

		String[] floors = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
				"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33",
				"34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50",
				"51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67",
				"68", "69", "70", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83",
				"84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100" };
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox startFloorCombo = new JComboBox(floors);
		startFloorCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fromFloor = Integer.parseInt((String) startFloorCombo.getSelectedItem());
				if (fromFloor != tofloor) {
					startFloorSideLabel.setText(createLabelText(prefixStartFloorLabel, String.valueOf(fromFloor)));
				} else {

				}
			}
		});
		startFloorCombo.setSelectedIndex(0);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		jp.add(startFloorCombo, c);

		JLabel startLabel = new JLabel(
				"<html><div style='text-align: center;'><span style='font-size: 1.44em'>Ende</span></div></html>");
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		jp.add(startLabel, c);

		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox endFloorCombo = new JComboBox(floors);
		endFloorCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tofloor = Integer.parseInt((String) endFloorCombo.getSelectedItem());
				if (tofloor != fromFloor) {
					endFloorSideLabel.setText(createLabelText(prefixEndFloorLabel, String.valueOf(tofloor)));
				}
			}
		});
		endFloorCombo.setSelectedIndex(1);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		jp.add(endFloorCombo, c);

		JPanel spacer = new JPanel();
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;
		jp.add(spacer, c);

		JLabel confirmLabel = new JLabel(
				"<html><div style='text-align: center;'><span style='font-size: 1.2em'>Bitte überprüfe und bestätige deinen Auftrag in der Auftragsübersicht rechts.</span></div></html>");
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		jp.add(confirmLabel, c);

		return jp;
	}

	/**
	 * Method creating childpanel that shows success message in final step of
	 * request.
	 * 
	 * @return JPanel -> panel to show in step one.
	 */
	private JPanel stepSuccess() {

		// root of success step
		JPanel jp = new JPanel(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;

		JPanel iconPanel = new JPanel(new GridBagLayout());
		iconPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		try {
			// Show success button
			BufferedImage img = ImageIO.read(ElevatorRequestPanel.class.getResource(Props.MOVING_ELEVATOR));
			Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(scaled);
			JLabel imgLabel = new JLabel(icon);
			imgLabel.setBackground(Color.WHITE);
			iconPanel.add(imgLabel);

			Icon gif = new ImageIcon(ElevatorRequestPanel.class.getResource(Props.MOVING_ELEVATOR));
			imgLabel.setIcon(gif);

		} catch (IOException e) {
			e.printStackTrace();
		}

		jp.add(iconPanel, c);

		JLabel label = new JLabel(
				"<html><div style='text-align: center;'><span style='font-size: 1.4em'><b>Ein Aufzug ist auf dem Weg zu Dir.</b><<br><br><span style='font-size: 1em'><i>In Kürze wird die Steuerung resetted.</i></span>");
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 1;

		jp.add(label, c);
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				resetPanels();
			}
		}, 7000);

		return jp;
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

			int persons = 0;
			int weight = 0;

			if (elevatorType == ElevatorTypes.PERSON) {
				persons = this.personsOrWeight;
			} else if (elevatorType == ElevatorTypes.FREIGHT) {
				weight = this.personsOrWeight;
			} else if (elevatorType == ElevatorTypes.VIP) {
				persons = this.personsOrWeight;
			}

			RequestElevator r = new RequestElevator();
			r.request(this.fromFloor, this.tofloor, persons, weight, this.elevatorType);
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
			String password = JOptionPane.showInputDialog("Bitte Passwort eingeben.");
			if (password != null) {
				if (password.equals("Passwort")) {
					JOptionPane.showMessageDialog(requestPanel, "Passwort korrekt!");
					this.elevatorType = ElevatorTypes.VIP;
					this.typeSideLabel.setText(createLabelText(prefixTypeLabel, elevatorType.name()));
					this.requestPanel.removeAll();
					this.requestPanel.add(stepTwo());
					this.requestPanel.revalidate();
					this.requestPanel.repaint();
					this.confirmBtn.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(requestPanel, "Passwort inkorrekt!");
					resetPanels();
				}
			} else {
				resetPanels();
			}
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
