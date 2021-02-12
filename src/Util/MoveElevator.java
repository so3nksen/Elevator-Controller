package Util;

import Model.Aufzug;
import Model.AufzugList;

public class MoveElevator {

	public void move(int id, int toFloor) {

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
