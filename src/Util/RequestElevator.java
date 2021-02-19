package Util;

import Model.ElevatorTypes;

public class RequestElevator {

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
