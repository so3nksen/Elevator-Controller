package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Model.AufzugList;
import Model.Lastenaufzug;
import Model.Personenaufzug;
import Model.VIPAufzug;

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

					Personenaufzug p = new Personenaufzug();
					String[] splitted = line.split(";");

					p.setId(Integer.parseInt(splitted[0]));
					p.setPersonenzahl(Integer.parseInt(splitted[1]));
					p.setGesamtgewicht(Integer.parseInt(splitted[2]));
					p.setAufzugsMelodie(splitted[3]);
					p.setAktuellesStockwerk(Integer.parseInt(splitted[4]));

					AufzugList.add(p);
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

					Lastenaufzug f = new Lastenaufzug();
					String[] splitted = line.split(";");

					f.setId(Integer.parseInt(splitted[0]));
					f.setPersonenzahl(Integer.parseInt(splitted[1]));
					f.setGesamtgewicht(Integer.parseInt(splitted[2]));
					f.setQuadratmeter(Integer.parseInt(splitted[3]));
					f.setAktuellesStockwerk(Integer.parseInt(splitted[4]));

					AufzugList.add(f);
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

					VIPAufzug v = new VIPAufzug();
					String[] splitted = line.split(";");

					v.setId(Integer.parseInt(splitted[0]));
					v.setPersonenzahl(Integer.parseInt(splitted[1]));
					v.setGesamtgewicht(Integer.parseInt(splitted[2]));
					v.setMaxGeschwindigkeit(Integer.parseInt(splitted[3]));
					v.setAktuellesStockwerk(Integer.parseInt(splitted[4]));

					AufzugList.add(v);
				}
				i++;
			}

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

	}

}
