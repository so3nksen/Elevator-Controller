package Controller;

import java.io.File;

import GUI.MainGui;
import Model.Aufzug;
import Model.ElevatorTypes;
import Util.CsvReader;
import Util.ElevatorInfos;
import Util.MoveElevator;
import Util.NearestFloor;
import Util.Props;
import Util.SaveCsvData;

/**
 * Programm controller.
 * 
 * @author
 *
 */
public class Controller {

	public static void main(String[] args) {

		// set absolute path of java application
		Props.ABSOLUTE_PATH = new File("").getAbsolutePath();

		// start gui
		MainGui gui = new MainGui();
		gui.init();

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
		MoveElevator.move(1, 1);
		MoveElevator.move(2, 1);
		MoveElevator.move(3, 1);

		// TEST OF ELEVATOR SEARCH
		// System.out.println(SearchElevator.search(1, 10, 10, 0, false));

		// TEST OF ELEVATOR INFOS
		ElevatorInfos i = new ElevatorInfos();
		Aufzug e = i.getInfos(49);

		// TEST OF SAVING .CSV
		SaveCsvData.save();

	}

}
