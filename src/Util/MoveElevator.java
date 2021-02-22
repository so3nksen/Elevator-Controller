package Util;

import Model.Aufzug;
import Model.AufzugList;

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
	public static void move(int id, int toFloor) {

		for (Aufzug e : AufzugList.getList()) {

			if (e.getId() == id) {
				if (e.getAktuellesStockwerk() != toFloor) {
					System.out.println("###[ELEVATOR STATUS  (   MOVEMENT START   )] - Elevator #" + id
							+ " now moving from " + e.getAktuellesStockwerk() + "th to the " + toFloor + "th floor.");
					e.setAktuellesStockwerk(toFloor);
					System.out.println("###[ELEVATOR STATUS  (    MOVEMENT END    )] - Elevator #" + id
							+ " reached the " + e.getAktuellesStockwerk() + "th floor.");
					break;
				} else {
					System.out.println("###[ELEVATOR STATUS  (NO MOVEMENT REQUIRED)] - Elevator #" + id
							+ " already in the " + e.getAktuellesStockwerk() + "th floor.");
				}
			}

		}

	}

}
