package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * ElevatorList contains the static arraylist of elevators reachable in whole
 * program.
 * 
 * @author lasse
 *
 */
public class ElevatorList {

	private static List<Elevator> elevators = new ArrayList<Elevator>();

	public static void add(Elevator e) {
		elevators.add(e);
	}

	public static List<Elevator> getList() {
		return elevators;
	}

}
