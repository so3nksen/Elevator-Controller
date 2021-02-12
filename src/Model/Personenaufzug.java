package Model;

public class Personenaufzug extends Aufzug {

	// Attribute, welche den Personenaufzug auszeichnen
	private String aufzugsMelodie;
	private boolean kleinOderGross;

	// Getter u Setter
	public String getAufzugsMelodie() {
		return aufzugsMelodie;
	}

	public void setAufzugsMelodie(String aufzugsMelodie) {
		this.aufzugsMelodie = aufzugsMelodie;
	}

	public boolean getRaumGroesse() {
		return kleinOderGross;
	}

	public void setRaumGroesse(boolean kleinOderGross) {
		this.kleinOderGross = kleinOderGross;
	}

	// Die Personenanzahl angeben, die sich im Aufzug befindet, sowie das Gewicht
	private void personenUndLastAngeben(int personenanzahl, double last) {

	}
}
