package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import Model.Elevator;
import Model.ElevatorList;
import Model.ElevatorStatistic;
import Model.FreightElevator;
import Model.PersonElevator;
import Model.VipElevator;
import Properties.Props;

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
		saveStats();
		System.out.println("[STATUS] CSV-Data saved on program exit...");
	}

	/**
	 * Saves all person elevators in ElevatorList to personElevators.csv
	 */
	private void savePersonElevators() {

		StringBuilder sb = new StringBuilder();
		sb.append("id;maxPersons;maxWeight;music;currentFloor" + System.lineSeparator());
		for (Elevator e : ElevatorList.getElevatorList()) {
			if (e instanceof PersonElevator) {
				sb.append(e.getId() + ";");
				sb.append(e.getMaxPersons() + ";");
				sb.append(e.getMaxWeight() + ";");
				sb.append(((PersonElevator) e).getMusicPlaying() + ";");
				sb.append(e.getCurrentFloor() + System.lineSeparator());
			}
		}
		writeToFile(sb.toString().trim(), Props.PERSON_ELEVATOR);
	}

	/**
	 * Saves all freight elevators in ElevatorList to freightElevators.csv
	 */
	private void saveFreightElevators() {

		StringBuilder sb = new StringBuilder();
		sb.append("id;maxPersons;maxWeight;squareMeters;currentFloor" + System.lineSeparator());
		for (Elevator e : ElevatorList.getElevatorList()) {
			if (e instanceof FreightElevator) {
				sb.append(e.getId() + ";");
				sb.append(e.getMaxPersons() + ";");
				sb.append(e.getMaxWeight() + ";");
				sb.append(((FreightElevator) e).getSqaureMeters() + ";");
				sb.append(e.getCurrentFloor() + System.lineSeparator());

			}
		}
		writeToFile(sb.toString().trim(), Props.FREIGHT_ELEVATOR);
	}

	/**
	 * Saves all vip elevators in ElevatorList to vipElevators.csv
	 */
	private void saveVipElevators() {

		StringBuilder sb = new StringBuilder();
		sb.append("id;maxPersons;maxWeight;speed;currentFloor" + System.lineSeparator());
		for (Elevator e : ElevatorList.getElevatorList()) {
			if (e instanceof VipElevator) {
				sb.append(e.getId() + ";");
				sb.append(e.getMaxPersons() + ";");
				sb.append(e.getMaxWeight() + ";");
				sb.append(((VipElevator) e).getMaxSpeed() + ";");
				sb.append(e.getCurrentFloor() + System.lineSeparator());

			}
		}
		writeToFile(sb.toString().trim(), Props.VIP_ELEVATOR);
	}

	private void saveStats() {

		StringBuilder sb = new StringBuilder();
		sb.append("type;totalValue;totalFloors" + System.lineSeparator());
		for (ElevatorStatistic es : ElevatorList.getStatisticList()) {
			sb.append(es.getType() + ";");
			sb.append(es.getTotalPersonsOrWeight() + ";");
			sb.append(es.getTotalFloors() + System.lineSeparator());

		}
		System.out.println(sb.toString());
		writeToFile(sb.toString().trim(), Props.STATISTICS);
	}

	private void writeToFile(String string, String path) {

		try (PrintWriter writer = new PrintWriter(new File(Props.PARENT_PATH + path))) {

			writer.write(string);

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
