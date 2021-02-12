package Model;

public class VIPAufzug extends Aufzug {

	// Attribute, welche den VIP-Aufzug auszeichnen
	private String passwort;
	private int maxGeschwindigkeit;

	// Getter u Setter
	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public int getMaxGeschwindigkeit() {
		return maxGeschwindigkeit;
	}

	public void setMaxGeschwindigkeit(int maxGeschwindigkeit) {
		this.maxGeschwindigkeit = maxGeschwindigkeit;
	}

	// Die Personenanzahl angeben, die sich im Aufzug befindet, sowie das Gewicht
	private void personenUndLastAngeben(int personenanzahl, double last) {

	}
}
