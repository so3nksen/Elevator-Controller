package Util;

import Model.Elevator;
import Model.ElevatorList;

/**
 * Util class moving elevator.
 * 
 * @author lasse
 *
 */
public class MoveElevator {

	/**
	 * Moving elevator.
	 * 
	 * @param id      -> id of elevator to move.
	 * @param toFloor -> floor elecator should move to.
	 */
	public void move(int id, int toFloor) {

		for (Elevator e : ElevatorList.getElevatorList()) {

			if (e.getId() == id) {
				System.out.println("[ELEVATOR] - Elevator #" + id + " now moving from your current Location to the "
						+ toFloor + "th floor.");
				e.setCurrentFloor(toFloor);
				break;
			}
		}
	}
}
