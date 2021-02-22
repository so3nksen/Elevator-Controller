package Util;

import Model.ElevatorTypes;

/**
 * Util class to request elevator.
 * 
 * @author lasse
 *
 */
public class RequestElevator {

	/**
	 * Request elevator with given values. Method calls further utils. Only
	 * connection to "frontend" gui.
	 * 
	 * @param fromFloor      -> floor elevator should start from.
	 * @param toFloor        -> floor elevator should move to.
	 * @param persons        -> number of persons to transport.
	 * @param weight         -> weight to transport.
	 * @param type           -> type of elevator
	 * @param isVipRequested -> should it be a vip elevator?
	 */
	public static void reuqest(int fromFloor, int toFloor, int persons, int weight, ElevatorTypes type,
			boolean isVipRequested) {

		System.out.println(
				"########################################################################################################");
		System.out.println("###[ELEVATOR REQUEST (    REQUEST DATA    )] - From Floor: " + fromFloor + " | To Floor: "
				+ toFloor + " | Persons: " + persons + " | Weight: " + weight);

		// get required elevator type
		ElevatorTypes typeOfElevator = SearchElevator.search(weight, persons, fromFloor, toFloor, type, isVipRequested);

		// get id of the closest elevator
		int idOfClosestElevator = NearestFloor.serach(typeOfElevator, fromFloor);

		// move elevator to floor required
		MoveElevator.move(idOfClosestElevator, toFloor);

	}
}
