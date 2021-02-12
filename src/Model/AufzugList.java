package Model;

import java.util.ArrayList;
import java.util.List;

public class AufzugList {

	private static List<Aufzug> elevators = new ArrayList<Aufzug>();

	public static void add(Aufzug e) {
		elevators.add(e);
	}

	public static List<Aufzug> getList() {
		return elevators;
	}

}
