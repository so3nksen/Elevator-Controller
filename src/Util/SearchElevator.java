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
	 * @param isVipRequest
	 * @return -> The elevator type that fits.
	 */
	public ElevatorTypes search(int weight, int persons, int fromFloor, int toFloor, ElevatorTypes type,
			boolean isVipRequest) {

		ElevatorTypes expected = null;

		if (!isVipRequest) {

			// check if weight and/or the number of persons is valid
			// if ((weight != 0 && weight <= 10000 && weight > 0) && (persons != 0 &&
			// persons <= 30 && persons > 0)) {

			// check if the combination of start- and endfloor is valid
			// if ((fromFloor >= 0 && fromFloor <= 100) && (toFloor >= 0 && toFloor <=
			// 100) && (fromFloor != toFloor)) {

			// if person transportation is required only personElevator can be used

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
			}

			// } else
			// System.out.println(
			// "###[ELEVATOR SEARCH ( INVALID REQUEST )] - Query has invalid values for
			// startfloor and/or endfloor.");

			// } else
			// System.out.println(
			// "###[ELEVATOR SEARCH ( INVALID REQUEST )] - Query has invalid values for
			// weight and/or persons.");

		} else {

			// vip elevator requested
			expected = ElevatorTypes.VIP;

		}

		return expected;
	}

}