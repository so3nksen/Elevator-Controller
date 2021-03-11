package Controller;

import java.io.File;

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

		// set absolute path of java application
		Props.ABSOLUTE_PATH = new File("").getAbsolutePath();

		// start gui
		SidebarGUI gui = new SidebarGUI();
		gui.startUserInterface();

		// read .csv on programm start
		CsvReader csv = new CsvReader();
		csv.read();

	}

}
