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

		for (Elevator e : ElevatorList.getList()) {

			if (e.getId() == id) {
				if (e.getCurrentFloor() != toFloor) {
					System.out.println("###[ELEVATOR STATUS  (   MOVEMENT START   )] - Elevator #" + id
							+ " now moving from " + e.getCurrentFloor() + "th to the " + toFloor + "th floor.");
					e.setCurrentFloor(toFloor);
					System.out.println("###[ELEVATOR STATUS  (    MOVEMENT END    )] - Elevator #" + id
							+ " reached the " + e.getCurrentFloor() + "th floor.");
					break;
				} else {
					System.out.println("###[ELEVATOR STATUS  (NO MOVEMENT REQUIRED)] - Elevator #" + id
							+ " already in the " + e.getCurrentFloor() + "th floor.");
				}
			}
		}
	}
}
