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
	public void request(int fromFloor, int toFloor, int persons, int weight, ElevatorTypes type,
			boolean isVipRequested) {

		System.out.println(
				"########################################################################################################");
		System.out.println("###[ELEVATOR REQUEST (    REQUEST DATA    )] - From Floor: " + fromFloor + " | To Floor: "
				+ toFloor + " | Persons: " + persons + " | Weight: " + weight);

		// get required elevator type
		SearchElevator se = new SearchElevator();
		ElevatorTypes typeOfElevator = se.search(weight, persons, fromFloor, toFloor, type, isVipRequested);

		// get id of the closest elevator
		NearestFloor nf = new NearestFloor();
		int idOfClosestElevator = nf.serach(typeOfElevator, fromFloor);

		// move elevator to floor required
		MoveElevator m = new MoveElevator();
		m.move(idOfClosestElevator, toFloor);

		// write order values to statistics
		WriteStats ws = new WriteStats();
		ws.write(type, fromFloor, toFloor, persons);

	}
}
