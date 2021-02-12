package Util;

import Model.Aufzug;
import Model.AufzugList;
import Model.Lastenaufzug;
import Model.Personenaufzug;
import Model.VIPAufzug;

/**
 * Util class to get Elevator infos.
 * 
 * @author
 *
 */
public class ElevatorInfos {

	/**
	 * returns elevator object with given id.
	 * 
	 * @param id -> id of elevator
	 * @return object of elevator
	 */
	public Aufzug getInfos(int id) {

		Aufzug elevator = null;

		for (Aufzug e : AufzugList.getList()) {
			if (e.getId() == id) {
				elevator = e;

				StringBuilder sb = new StringBuilder();
				sb.append("###[ELEVATOR INFOS   (                    )] - ");
				sb.append("ID: " + elevator.getId() + " | ");
				sb.append("StockwerK: " + elevator.getAktuellesStockwerk() + " | ");
				sb.append("Max Persoons: " + elevator.getPersonenzahl() + " | ");
				sb.append("Max Weight: " + elevator.getGesamtgewicht() + " | ");

				if (e instanceof Personenaufzug) {
					sb.append(("Music Playing: " + ((Personenaufzug) e).getAufzugsMelodie()));
				} else if (e instanceof Lastenaufzug) {
					sb.append(("Square Meters: " + ((Lastenaufzug) e).getQuadratmeter()));
				} else if (e instanceof VIPAufzug) {
					sb.append(("Max Speed: " + ((VIPAufzug) e).getMaxGeschwindigkeit()));
				}

				System.out.println(sb.toString());

				break;
			}
		}
		return elevator;
	}

}
