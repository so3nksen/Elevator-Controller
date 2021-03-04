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

		// check for the closest personElevator
		if (type == ElevatorTypes.PERSON_SMALL || type == ElevatorTypes.PERSON_BIG) {

			for (Elevator aufzug : ElevatorList.getList()) {
				if (aufzug instanceof PersonElevator) {
					if (type == ElevatorTypes.PERSON_BIG) {
						if (aufzug.getMaxPersons() > 15) {
							int d = Math.abs(myFloor - aufzug.getCurrentFloor());
							if (d < distanceBetween) {
								distanceBetween = d;
								nearestElevatorInFloor = aufzug.getCurrentFloor();
							}
						}
					} else {
						int d = Math.abs(myFloor - aufzug.getCurrentFloor());
						if (d < distanceBetween) {
							distanceBetween = d;
							nearestElevatorInFloor = aufzug.getCurrentFloor();
						}
					}
				}
			}
		}

		// check for the closest freightElevator
		else if (type == ElevatorTypes.FREIGHT_SMALL || type == ElevatorTypes.FREIGHT_BIG) {

			for (Elevator aufzug : ElevatorList.getList()) {
				if (aufzug instanceof FreightElevator) {
					if (type == ElevatorTypes.FREIGHT_BIG) {
						if (aufzug.getMaxWeight() > 5000) {
							int d = Math.abs(myFloor - aufzug.getCurrentFloor());
							if (d < distanceBetween) {
								distanceBetween = d;
								nearestElevatorInFloor = aufzug.getCurrentFloor();
							}
						}
					} else {
						int d = Math.abs(myFloor - aufzug.getCurrentFloor());
						if (d < distanceBetween) {
							distanceBetween = d;
							nearestElevatorInFloor = aufzug.getCurrentFloor();
						}
					}
				}
			}
		}

		// check for closest vip elevator
		else if (type == ElevatorTypes.VIP) {

			for (Elevator aufzug : ElevatorList.getList()) {
				if (aufzug instanceof VipElevator) {
					int d = Math.abs(myFloor - aufzug.getCurrentFloor());
					if (d < distanceBetween) {
						distanceBetween = d;
						nearestElevatorInFloor = aufzug.getCurrentFloor();
					}
				}
			}
		}
		System.out.println(
				"###[ELEVATOR NEAREST (                    )] - Nearest elevator to your current location is in the "
						+ nearestElevatorInFloor + "th floor.");
		return nearestElevatorInFloor;
	}

}
