package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import Model.Aufzug;
import Model.AufzugList;
import Model.Lastenaufzug;
import Model.Personenaufzug;
import Model.VIPAufzug;

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
	public static void save() {

		savePersonElevators();
		saveFreightElevators();
		saveVipElevators();
	}

	/**
	 * Saves all person elevators in ElevatorList to personElevators.csv
	 */
	private static void savePersonElevators() {

		StringBuilder sb = new StringBuilder();
		sb.append("id;maxPersons;maxWeight;music;currentFloor" + System.lineSeparator());
		for (Aufzug e : AufzugList.getList()) {
			if (e instanceof Personenaufzug) {
				sb.append(e.getId() + ";");
				sb.append(e.getPersonenzahl() + ";");
				sb.append(e.getGesamtgewicht() + ";");
				sb.append(((Personenaufzug) e).getAufzugsMelodie() + ";");
				sb.append(e.getAktuellesStockwerk() + System.lineSeparator());
			}
		}

		writeToFile(sb.toString().trim(), Props.ABSOLUTE_PATH + Props.PERSON_ELEVATOR);
	}

	/**
	 * Saves all freight elevators in ElevatorList to freightElevators.csv
	 */
	private static void saveFreightElevators() {

		StringBuilder sb = new StringBuilder();
		sb.append("id;maxPersons;maxWeight;squareMeters;currentFloor" + System.lineSeparator());
		for (Aufzug e : AufzugList.getList()) {
			if (e instanceof Lastenaufzug) {
				sb.append(e.getId() + ";");
				sb.append(e.getPersonenzahl() + ";");
				sb.append(e.getGesamtgewicht() + ";");
				sb.append(((Lastenaufzug) e).getQuadratmeter() + ";");
				sb.append(e.getAktuellesStockwerk() + System.lineSeparator());

			}
		}
		writeToFile(sb.toString().trim(), Props.ABSOLUTE_PATH + Props.FREIGHT_ELEVATOR);
	}

	/**
	 * Saves all vip elevators in ElevatorList to vipElevators.csv
	 */
	private static void saveVipElevators() {

		StringBuilder sb = new StringBuilder();
		sb.append("id;maxPersons;maxWeight;speed;currentFloor" + System.lineSeparator());
		for (Aufzug e : AufzugList.getList()) {
			if (e instanceof VIPAufzug) {
				sb.append(e.getId() + ";");
				sb.append(e.getPersonenzahl() + ";");
				sb.append(e.getGesamtgewicht() + ";");
				sb.append(((VIPAufzug) e).getMaxGeschwindigkeit() + ";");
				sb.append(e.getAktuellesStockwerk() + System.lineSeparator());

			}
		}
		writeToFile(sb.toString().trim(), Props.ABSOLUTE_PATH + Props.VIP_ELEVATOR);
	}

	private static void writeToFile(String string, String path) {

		try (PrintWriter writer = new PrintWriter(new File(path))) {

			writer.write(string);

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

}
