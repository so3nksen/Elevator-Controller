package Util;

import Model.Elevator;
import Model.ElevatorList;
import Model.FreightElevator;
import Model.PersonElevator;
import Model.VipElevator;

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
	public Elevator getInfos(int id) {

		Elevator elevator = null;

		for (Elevator e : ElevatorList.getList()) {
			if (e.getId() == id) {
				elevator = e;

				StringBuilder sb = new StringBuilder();
				sb.append("###[ELEVATOR INFOS   (                    )] - ");
				sb.append("ID: " + elevator.getId() + " | ");
				sb.append("StockwerK: " + elevator.getCurrentFloor() + " | ");
				sb.append("Max Persoons: " + elevator.getMaxPersons() + " | ");
				sb.append("Max Weight: " + elevator.getMaxWeight() + " | ");

				if (e instanceof PersonElevator) {
					sb.append(("Music Playing: " + ((PersonElevator) e).getAufzugsMelodie()));
				} else if (e instanceof FreightElevator) {
					sb.append(("Square Meters: " + ((FreightElevator) e).getSqaureMeters()));
				} else if (e instanceof VipElevator) {
					sb.append(("Max Speed: " + ((VipElevator) e).getMaxSpeed()));
				}

				System.out.println(sb.toString());

				break;
			}
		}
		return elevator;
	}

}
