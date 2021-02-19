package GUI;

import Util.SearchElevator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Double.parseDouble;


public class Gui {

	static String DEFINE_PASSENGER_TYPE = "Was möchten Sie transportieren?";


	private boolean elevatorCalled;
	private GuiListener guiListener;

	private JButton aufzugRufen;
	private JButton btnPersonen;
	private JButton btnGueter;

	private JTextField eingabe;
	private JTextArea anweisungen;


	private int momentanesStockwerk;
	private int gesuchtesStockwerk;
	private int angegebenePersonen;
	private int angegebenesGewicht;
	private boolean vip;

	// All widgets are added to the Frame already and will be set invisible to call if needed.
	private void build() {
		JFrame frame = new JFrame();
		JPanel mainPane = new JPanel();
		aufzugRufen = new JButton("Aufzug rufen");
		btnPersonen = new JButton("Personen");
		btnGueter = new JButton("Güter");
		eingabe = new JTextField();
		anweisungen = new JTextArea();

		mainPane.setPreferredSize(new Dimension(700, 200));
		mainPane.add(aufzugRufen);
		mainPane.add(eingabe);
		mainPane.add(anweisungen);
		mainPane.add(btnPersonen);
		mainPane.add(btnGueter);

		//Make these widgets invisible first, because we'll need them only, if the elevator is called
		btnPersonen.setVisible(false);
		btnGueter.setVisible(false);
		eingabe.setVisible(false);
		anweisungen.setVisible(false);

		// Add action listener to widgets
		btnGueter.addActionListener(guiListener);

		frame.add(mainPane);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);


	}

	private void makePersonsOrGoodsVisible() {
		btnPersonen.setVisible(true);
		btnGueter.setVisible(true);

	}

	private int enterCarriageType(String personsOrGoods) {
		 double userInput = 0;

		anweisungen.setVisible(true);

		// Define whether persons or goods are carried
		if (personsOrGoods.equals("persons")) {
			anweisungen.setText("Geben Sie an, wie viele Personen befördert werden sollen");
			userInput = parseDouble(eingabe.getText());
		} else if (personsOrGoods.equals("goods")) {
			anweisungen.setText("Geben Sie das Gewicht der Fracht an.");
			userInput = parseDouble(eingabe.getText());

		}



		return userInput;
	}

	public void start() {
		build();
		System.out.println("Gui starting...");
	}

	// Returns necessary information for Controller to call screen
	public void elevatorCallingScreen() {
		makePersonsOrGoodsVisible();
		SearchElevator.search(angegebenesGewicht, angegebenePersonen, momentanesStockwerk, gesuchtesStockwerk, vip)
		// wait for userinput in 'eingabe'

	}

	public boolean isElevatorCalled() {
		return elevatorCalled;
	}


	public class GuiListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnGueter) {
				angegebenesGewicht = enterCarriageType("goods");
				// if not above max, inform controller to call elevatr
			}

			if (e.getSource() == btnPersonen) {
				angegebenePersonen = enterCarriageType("persons");
				// enter enter number of persons
			}

			if (e.getSource() == aufzugRufen) {
				elevatorCalled = true;

			}
		}
	}

}
