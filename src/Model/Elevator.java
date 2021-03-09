package Model;

/**
 * Elevator superclass.
 * 
 * @author lasse
 *
 */
public class Elevator {

	// Notwendige Kriterien eines jeden Aufzugs
	private int id;
	private int maxPersons;
	private int maxWeight;
	private int currentFloor;

	// Getter u. Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxPersons() {
		return maxPersons;
	}

	public void setMaxPersons(int maxPersons) {
		this.maxPersons = maxPersons;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
}
