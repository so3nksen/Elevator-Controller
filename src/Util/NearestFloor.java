package Util;

import Model.Elevator;
import Model.ElevatorList;
import Model.ElevatorTypes;
import Model.FreightElevator;
import Model.PersonElevator;
import Model.VipElevator;

/**
 * Util class to find closest elevator.
 * 
 * @author lasse
 *
 */
public class NearestFloor {

	/**
	 * Search closest elevator of specific type to actual position in building.
	 * 
	 * @param type    -> type of elevator to look for
	 * @param myFloor -> the position in building.
	 * @return
	 */
	public int serach(ElevatorTypes type, int myFloor) {

		int distanceBetween = 999999999;
		int nearestElevatorInFloor = 0;
		int idOfElevator = -1;

		// check for the closest personElevator
		if (type == ElevatorTypes.PERSON_SMALL || type == ElevatorTypes.PERSON_BIG) {

			for (Elevator e : ElevatorList.getElevatorList()) {
				if (e instanceof PersonElevator) {
					if (type == ElevatorTypes.PERSON_BIG) {
						if (e.getMaxPersons() > 15) {
							int d = Math.abs(myFloor - e.getCurrentFloor());
							if (d < distanceBetween) {
								distanceBetween = d;
								nearestElevatorInFloor = e.getCurrentFloor();
								idOfElevator = e.getId();
							}
						}
					} else {
						int d = Math.abs(myFloor - e.getCurrentFloor());
						if (d < distanceBetween) {
							distanceBetween = d;
							nearestElevatorInFloor = e.getCurrentFloor();
							idOfElevator = e.getId();
						}
					}
				}
			}
		}

		// check for the closest freightElevator
		else if (type == ElevatorTypes.FREIGHT_SMALL || type == ElevatorTypes.FREIGHT_BIG) {

			for (Elevator e : ElevatorList.getElevatorList()) {
				if (e instanceof FreightElevator) {
					if (type == ElevatorTypes.FREIGHT_BIG) {
						if (e.getMaxWeight() > 5000) {
							int d = Math.abs(myFloor - e.getCurrentFloor());
							if (d < distanceBetween) {
								distanceBetween = d;
								nearestElevatorInFloor = e.getCurrentFloor();
								idOfElevator = e.getId();
							}
						}
					} else {
						int d = Math.abs(myFloor - e.getCurrentFloor());
						if (d < distanceBetween) {
							distanceBetween = d;
							nearestElevatorInFloor = e.getCurrentFloor();
							idOfElevator = e.getId();
						}
					}
				}
			}
		}

		// check for closest vip elevator
		else if (type == ElevatorTypes.VIP) {

			for (Elevator e : ElevatorList.getElevatorList()) {
				if (e instanceof VipElevator) {
					int d = Math.abs(myFloor - e.getCurrentFloor());
					if (d < distanceBetween) {
						distanceBetween = d;
						nearestElevatorInFloor = e.getCurrentFloor();
						idOfElevator = e.getId();
					}
				}
			}
		}
		System.out.println("[ELEVATOR] - Nearest elevator to your current location is in the " + nearestElevatorInFloor
				+ "th floor.");
		return idOfElevator;
	}

}
