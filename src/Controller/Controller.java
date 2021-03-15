package Controller;

import java.io.File;
import java.io.IOException;

import javax.swing.SwingUtilities;

import GUI.SidebarGUI;
import Properties.Props;
import Util.CsvReader;

/**
 * Programm controller.
 * 
 * @author
 *
 */
public class Controller {

	public void start() {

		try {
			Props.PARENT_PATH = new File("").getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// start gui
				SidebarGUI gui = new SidebarGUI();
				gui.startUserInterface();
			}
		});

		// read .csv on programm start
		CsvReader csv = new CsvReader();
		csv.read();
	}

}
