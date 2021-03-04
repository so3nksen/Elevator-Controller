package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import Model.ElevatorList;
import Model.Elevator;
import Model.FreightElevator;
import Model.PersonElevator;
import Model.VipElevator;

/**
 * Class saving elements of ElevatorList to specific .csv data on programm exit.
 * 
 * @author
 *
 */
public class SaveCsvData {

	/**
	 * init the saving process
	 */
	public void save() {
		savePersonElevators();
		saveFreightElevators();
		saveVipElevators();
	}

	/**
	 * Saves all person elevators in ElevatorList to personElevators.csv
	 */
	private void savePersonElevators() {

		StringBuilder sb = new StringBuilder();
		sb.append("id;maxPersons;maxWeight;music;currentFloor" + System.lineSeparator());
		for (Elevator e : ElevatorList.getList()) {
			if (e instanceof PersonElevator) {
				sb.append(e.getId() + ";");
				sb.append(e.getMaxPersons() + ";");
				sb.append(e.getMaxWeight() + ";");
				sb.append(((PersonElevator) e).getAufzugsMelodie() + ";");
				sb.append(e.getCurrentFloor() + System.lineSeparator());
			}
		}
		writeToFile(sb.toString().trim(), Props.ABSOLUTE_PATH + Props.PERSON_ELEVATOR);
	}

	/**
	 * Saves all freight elevators in ElevatorList to freightElevators.csv
	 */
	private void saveFreightElevators() {

		StringBuilder sb = new StringBuilder();
		sb.append("id;maxPersons;maxWeight;squareMeters;currentFloor" + System.lineSeparator());
		for (Elevator e : ElevatorList.getList()) {
			if (e instanceof FreightElevator) {
				sb.append(e.getId() + ";");
				sb.append(e.getMaxPersons() + ";");
				sb.append(e.getMaxWeight() + ";");
				sb.append(((FreightElevator) e).getSqaureMeters() + ";");
				sb.append(e.getCurrentFloor() + System.lineSeparator());

			}
		}
		writeToFile(sb.toString().trim(), Props.ABSOLUTE_PATH + Props.FREIGHT_ELEVATOR);
	}

	/**
	 * Saves all vip elevators in ElevatorList to vipElevators.csv
	 */
	private void saveVipElevators() {

		StringBuilder sb = new StringBuilder();
		sb.append("id;maxPersons;maxWeight;speed;currentFloor" + System.lineSeparator());
		for (Elevator e : ElevatorList.getList()) {
			if (e instanceof VipElevator) {
				sb.append(e.getId() + ";");
				sb.append(e.getMaxPersons() + ";");
				sb.append(e.getMaxWeight() + ";");
				sb.append(((VipElevator) e).getMaxSpeed() + ";");
				sb.append(e.getCurrentFloor() + System.lineSeparator());

			}
		}
		writeToFile(sb.toString().trim(), Props.ABSOLUTE_PATH + Props.VIP_ELEVATOR);
	}

	private void writeToFile(String string, String path) {

		try (PrintWriter writer = new PrintWriter(new File(path))) {

			writer.write(string);

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

}
