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
	public void request(int fromFloor, int toFloor, int persons, int weight, ElevatorTypes type) {

		System.out.println(
				"########################################################################################################");
		System.out.println("###[ELEVATOR REQUEST (    REQUEST DATA    )] - From Floor: " + fromFloor + " | To Floor: "
				+ toFloor + " | Persons: " + persons + " | Weight: " + weight);

		ElevatorTypes typeOfElevator = search(weight, persons, fromFloor, toFloor, type);

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

	/**
	 * Method checking for fitable elevator type with given values.
	 * 
	 * @param weight    -> weight should be transported.
	 * @param persons   -> persons should be transported.
	 * @param fromFloor -> startfloor
	 * @param toFloor   -> endfloor
	 * @param type      -> elevatortype
	 * @return
	 */
	private ElevatorTypes search(int weight, int persons, int fromFloor, int toFloor, ElevatorTypes type) {

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
