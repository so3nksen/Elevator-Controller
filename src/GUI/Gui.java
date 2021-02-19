package GUI;

import static java.lang.Double.parseDouble;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gui {

	static String DEFINE_PASSENGER_TYPE = "Was möchten Sie transportieren?";

	// private ViewController viewController;
	// private ViewController.GuiListener guiListener;
	private JButton btnPersonen;
	private JButton btnGueter;

	private JTextField eingabe;
	private JTextArea anweisungen;

	// All widgets are added to the Frame already and will be set invisible to call
	// if needed.
	private void build() {
		JFrame frame = new JFrame();
		JPanel mainPane = new JPanel();
		JButton aufzugRufen = new JButton("Aufzug rufen");
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

		// Make these widgets invisible first, because we'll need them only, if the
		// elevator is called
		btnPersonen.setVisible(false);
		btnGueter.setVisible(false);
		eingabe.setVisible(false);
		anweisungen.setVisible(false);

		// Add action listener to widgets
		// btnGueter.addActionListener(guiListener);

		frame.add(mainPane);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	private void makePersonsOrGoodsVisible() {
		btnPersonen.setVisible(true);
		btnGueter.setVisible(true);

	}

	private double enterCarriageType(String personsOrGoods) {
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
		makePersonsOrGoodsVisible();
	}

	public class GuiListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnGueter) {
				double goodsWeight = enterCarriageType("goods");
				// if not above max, inform controller to call elevatr
			}

			if (e.getSource() == btnPersonen) {
				enterCarriageType("persons");
				// enter enter number of persons
			}

			if (e.getSource() == eingabe) {

			}
		}
	}

}
