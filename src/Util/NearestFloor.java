package Util;

import Model.Aufzug;
import Model.AufzugList;
import Model.ElevatorTypes;
import Model.Lastenaufzug;
import Model.Personenaufzug;
import Model.VIPAufzug;

public class NearestFloor {

	public static int serach(ElevatorTypes type, int myFloor) {

		int distanceBetween = 999999999;
		int nearestElevatorInFloor = 0;
		int elevatorId = 0;

		// check for the closest personElevator
		if (type == ElevatorTypes.PERSON_SMALL || type == ElevatorTypes.PERSON_BIG) {

			for (Aufzug aufzug : AufzugList.getList()) {
				if (aufzug instanceof Personenaufzug) {
					if (type == ElevatorTypes.PERSON_BIG) {
						if (aufzug.getPersonenzahl() > 15) {
							int d = Math.abs(myFloor - aufzug.getAktuellesStockwerk());
							if (d < distanceBetween) {
								distanceBetween = d;
								nearestElevatorInFloor = aufzug.getAktuellesStockwerk();
								elevatorId = aufzug.getId();
							}
						}
					} else {
						int d = Math.abs(myFloor - aufzug.getAktuellesStockwerk());
						if (d < distanceBetween) {
							distanceBetween = d;
							nearestElevatorInFloor = aufzug.getAktuellesStockwerk();
							elevatorId = aufzug.getId();

						}
					}
				}
			}
		}

		// check for the closest freightElevator
		else if (type == ElevatorTypes.FREIGHT_SMALL || type == ElevatorTypes.FREIGHT_BIG) {

			for (Aufzug aufzug : AufzugList.getList()) {
				if (aufzug instanceof Lastenaufzug) {
					if (type == ElevatorTypes.FREIGHT_BIG) {
						if (aufzug.getGesamtgewicht() > 5000) {
							int d = Math.abs(myFloor - aufzug.getAktuellesStockwerk());
							if (d < distanceBetween) {
								distanceBetween = d;
								nearestElevatorInFloor = aufzug.getAktuellesStockwerk();
								elevatorId = aufzug.getId();

							}
						}
					} else {
						int d = Math.abs(myFloor - aufzug.getAktuellesStockwerk());
						if (d < distanceBetween) {
							distanceBetween = d;
							nearestElevatorInFloor = aufzug.getAktuellesStockwerk();
							elevatorId = aufzug.getId();

						}
					}
				}
			}
		}

		// check for closest vip elevator
		else if (type == ElevatorTypes.VIP) {

			for (Aufzug aufzug : AufzugList.getList()) {
				if (aufzug instanceof VIPAufzug) {
					int d = Math.abs(myFloor - aufzug.getAktuellesStockwerk());
					if (d < distanceBetween) {
						distanceBetween = d;
						nearestElevatorInFloor = aufzug.getAktuellesStockwerk();
						elevatorId = aufzug.getId();

					}
				}
			}
		}
		System.out.println(
				"###[ELEVATOR NEAREST (                    )] - Nearest elevator to your current location is in the "
						+ nearestElevatorInFloor + "th floor.");
		return elevatorId;
	}

}
