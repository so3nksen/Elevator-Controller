package GUI;
import javax.swing.*;
import java.awt.*;


public class Gui {

	static String DEFINE_PASSENGER_TYPE = "Was möchten Sie transportieren?";

	private JButton btnPersonen;

	private void build() {
		JFrame frame = new JFrame();
		JPanel mainPane = new JPanel();
		JButton aufzugRufen = new JButton("Aufzug rufen");
		btnPersonen = new JButton("Personen");
		JButton btnGueter = new JButton("Güter");
		JTextField eingabe = new JTextField();
		JTextArea anweisungen = new JTextArea();

		mainPane.setPreferredSize(new Dimension(700, 200));
		mainPane.add(aufzugRufen);
		mainPane.add(eingabe);
		mainPane.add(anweisungen);
		mainPane.add(btnPersonen);
		mainPane.add(btnGueter);

		//Make these buttons invisible first, because we'll need them only, if the elevator is called
		btnPersonen.setVisible(false);
		btnGueter.setVisible(false);

		frame.add(mainPane);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);


	}

	private void makePersonsOrGoodsVisible() {
		btnPersonen.setVisible(true);
	}

	public void start() {
		build();
		System.out.println("Gui starting...");
		makePersonsOrGoodsVisible();
	}

}
