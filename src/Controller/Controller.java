package Controller;

import java.io.File;
import java.util.ArrayList;

import GUI.Gui;
import Model.Aufzug;
import Model.ElevatorTypes;
import Util.CsvReader;
import Util.ElevatorInfos;
import Util.MoveElevator;
import Util.NearestFloor;
import Util.Props;
import Util.SaveCsvData;
import Util.SearchElevator;

/**
 * Programm controller.
 * 
 * @author
 *
 */
public class Controller {

	Gui gui;

	public static void main(String[] args) {

		// set absolute path of java application
		Props.ABSOLUTE_PATH = new File("").getAbsolutePath();

		// start gui
		Gui gui = new Gui();
		gui.start();

		// read .csv on programm start
		CsvReader csv = new CsvReader();
		csv.read();

		//
		// ++++++++++++++++++
		// TESTING OF METHODS
		// ++++++++++++++++++
		// TEST OF SERACHING NEXT ELEVATOR
		int myfloor = 1;
		NearestFloor.serach(ElevatorTypes.FREIGHT_SMALL, myfloor);

		// TEST OF "MOVING ELEVATORS"
		MoveElevator m = new MoveElevator();
		m.move(1, 1);
		m.move(2, 1);
		m.move(3, 1);

		// TEST OF ELEVATOR SEARCH
		System.out.println(SearchElevator.search(1, 10, 10, 0, false));

		// TEST OF ELEVATOR INFOS
		ElevatorInfos i = new ElevatorInfos();
		Aufzug e = i.getInfos(49);

		// TEST OF SAVING .CSV
		SaveCsvData.save();

	}

	public void callElevator() {

		do

	}


}
