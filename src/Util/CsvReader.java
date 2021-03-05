package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Model.ElevatorList;
import Model.ElevatorStatistic;
import Model.ElevatorTypes;
import Model.FreightElevator;
import Model.PersonElevator;
import Model.VipElevator;

/**
 * Util class managing reading all .csv data on programm start
 * 
 * @author lasse
 *
 */
public class CsvReader {

	/**
	 * init reading process of all .csv documents.
	 */
	public void read() {
		// console status
		System.out.println("Reading csv...");
		// read data for every .csv file
		readPersonElevators();
		readFreightElevators();
		readVIPElevators();
		readStats();

	}

	/**
	 * Reads statistics from statistics.csv
	 */
	private void readStats() {
		try (FileReader reader = new FileReader(Props.ABSOLUTE_PATH + Props.STATISTICS);
				BufferedReader br = new BufferedReader(reader)) {

			int i = 0;
			String line;
			while ((line = br.readLine()) != null) {

				if (i != 0) {

					ElevatorStatistic es = new ElevatorStatistic();
					String[] splitted = line.split(";");

					System.out.println(splitted[0]);

					if (splitted[0].equals("person")) {
						es.setType(ElevatorTypes.PERSON);
					} else if (splitted[0].equals("freight")) {
						es.setType(ElevatorTypes.FREIGHT);
					} else if (splitted[0].equals("vip")) {
						es.setType(ElevatorTypes.VIP);
					}
					es.setTotalPersonsOrWeight(Integer.parseInt(splitted[1]));
					es.setTotalFloors(Integer.parseInt(splitted[2]));
					ElevatorList.addToStatistikList(es);
				}
				i++;
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

	/**
	 * Reads all elevators in personElevators.csv
	 */
	private void readPersonElevators() {

		try (FileReader reader = new FileReader(Props.ABSOLUTE_PATH + Props.PERSON_ELEVATOR);
				BufferedReader br = new BufferedReader(reader)) {

			int i = 0;
			String line;
			while ((line = br.readLine()) != null) {

				if (i != 0) {

					PersonElevator p = new PersonElevator();
					String[] splitted = line.split(";");

					p.setId(Integer.parseInt(splitted[0]));
					p.setMaxPersons(Integer.parseInt(splitted[1]));
					p.setMaxWeight(Integer.parseInt(splitted[2]));
					p.setMusicPlaying(splitted[3]);
					p.setCurrentFloor(Integer.parseInt(splitted[4]));

					ElevatorList.addToElevatorList(p);
				}
				i++;
			}

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

	/**
	 * Reads all elevators in freightElevators.csv
	 */
	private void readFreightElevators() {

		try (FileReader reader = new FileReader(Props.ABSOLUTE_PATH + Props.FREIGHT_ELEVATOR);
				BufferedReader br = new BufferedReader(reader)) {

			int i = 0;
			String line;
			while ((line = br.readLine()) != null) {

				if (i != 0) {

					FreightElevator f = new FreightElevator();
					String[] splitted = line.split(";");

					f.setId(Integer.parseInt(splitted[0]));
					f.setMaxPersons(Integer.parseInt(splitted[1]));
					f.setMaxWeight(Integer.parseInt(splitted[2]));
					f.setSquareMeters(Integer.parseInt(splitted[3]));
					f.setCurrentFloor(Integer.parseInt(splitted[4]));

					ElevatorList.addToElevatorList(f);
				}
				i++;
			}

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

	/**
	 * Reads all elevators in vipElevators.csv
	 */
	private void readVIPElevators() {

		try (FileReader reader = new FileReader(Props.ABSOLUTE_PATH + Props.VIP_ELEVATOR);
				BufferedReader br = new BufferedReader(reader)) {

			int i = 0;
			String line;
			while ((line = br.readLine()) != null) {

				if (i != 0) {

					VipElevator v = new VipElevator();
					String[] splitted = line.split(";");

					v.setId(Integer.parseInt(splitted[0]));
					v.setMaxPersons(Integer.parseInt(splitted[1]));
					v.setMaxWeight(Integer.parseInt(splitted[2]));
					v.setMaxSpeed(Integer.parseInt(splitted[3]));
					v.setCurrentFloor(Integer.parseInt(splitted[4]));

					ElevatorList.addToElevatorList(v);
				}
				i++;
			}

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

	}

}
