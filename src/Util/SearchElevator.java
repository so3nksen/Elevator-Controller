package Util;

import Model.ElevatorTypes;

public class SearchElevator {

	/**
	 * Method looks for the needed elevator type with given params.
	 * 
	 * @param weight
	 * @param persons
	 * @param fromFloor
	 * @param toFloor
	 * @param type
	 * @return -> The elevator type that fits.
	 */
	public ElevatorTypes search(int weight, int persons, int fromFloor, int toFloor, ElevatorTypes type) {

		ElevatorTypes expected = null;
		if (type == ElevatorTypes.PERSON) {
			if (persons > 15) {
				expected = ElevatorTypes.PERSON_BIG;
			} else {
				expected = ElevatorTypes.PERSON_SMALL;
			}
		} else if (type == ElevatorTypes.FREIGHT) {
			if (weight > 5000) {
				expected = ElevatorTypes.FREIGHT_BIG;
			} else {
				expected = ElevatorTypes.FREIGHT_SMALL;
			}
		} else if (type == ElevatorTypes.VIP) {
			expected = ElevatorTypes.VIP;
		}
		return expected;
	}
}