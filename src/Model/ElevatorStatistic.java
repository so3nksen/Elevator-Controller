package Model;

public class ElevatorStatistic {

	ElevatorTypes type;
	int totalFloors;
	int totalPersonsOrWeight;

	public ElevatorTypes getType() {
		return type;
	}

	public void setType(ElevatorTypes type) {
		this.type = type;
	}

	public int getTotalFloors() {
		return totalFloors;
	}

	public void setTotalFloors(int totalFloors) {
		this.totalFloors = totalFloors;
	}

	public int getTotalPersonsOrWeight() {
		return totalPersonsOrWeight;
	}

	public void setTotalPersonsOrWeight(int totalPersonsOrWeight) {
		this.totalPersonsOrWeight = totalPersonsOrWeight;
	}

}
