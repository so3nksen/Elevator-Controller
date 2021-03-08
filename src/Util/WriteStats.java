package Util;

import java.util.List;

import Model.ElevatorList;
import Model.ElevatorStatistic;
import Model.ElevatorTypes;

public class WriteStats {

	/**
	 * Write stats to ElevatorStats list.
	 * 
	 * @param type           -> type of elevator.
	 * @param fromFloor      -> floor to move to
	 * @param toFloor        ->
	 * @param weightOrPerson
	 */
	public void write(ElevatorTypes type, int fromFloor, int toFloor, int weightOrPerson) {

		List<ElevatorStatistic> list = ElevatorList.getStatisticList();

		// check for elevator type
		if (type == ElevatorTypes.PERSON) {
			ElevatorStatistic obj = list.get(0);
			obj.setTotalFloors(obj.getTotalFloors() + Math.abs(fromFloor - toFloor));
			obj.setTotalPersonsOrWeight(obj.getTotalPersonsOrWeight() + weightOrPerson);

		} else if (type == ElevatorTypes.FREIGHT) {
			ElevatorStatistic obj = list.get(1);
			obj.setTotalFloors(obj.getTotalFloors() + Math.abs(fromFloor - toFloor));
			obj.setTotalPersonsOrWeight(obj.getTotalPersonsOrWeight() + weightOrPerson);

		} else if (type == ElevatorTypes.VIP) {
			ElevatorStatistic obj = list.get(2);
			obj.setTotalFloors(obj.getTotalFloors() + Math.abs(fromFloor - toFloor));
			obj.setTotalPersonsOrWeight(obj.getTotalPersonsOrWeight() + weightOrPerson);

		}

		System.out.println(list);

	}
}
