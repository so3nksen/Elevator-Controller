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
	private static List<ElevatorStatistic> elevatorStatistic = new ArrayList<ElevatorStatistic>();

	public static void addToElevatorList(Elevator e) {
		elevators.add(e);
	}

	public static List<Elevator> getElevatorList() {
		return elevators;
	}

	public static void addToStatistikList(ElevatorStatistic e) {
		elevatorStatistic.add(e);
	}

	public static List<ElevatorStatistic> getStatisticList() {
		return elevatorStatistic;
	}

}
