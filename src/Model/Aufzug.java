package Model;

public class Aufzug {

	// Notwendige Kriterien eines jeden Aufzugs
	private int id;
	private int personenzahl;
	private int gesamtgewicht;
	private int aktuellesStockwerk;

	// Getter u. Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPersonenzahl() {
		return personenzahl;
	}

	public void setPersonenzahl(int personenzahl) {
		this.personenzahl = personenzahl;
	}

	public int getGesamtgewicht() {
		return gesamtgewicht;
	}

	public void setGesamtgewicht(int gesamtgewicht) {
		this.gesamtgewicht = gesamtgewicht;
	}

	public int getAktuellesStockwerk() {
		return aktuellesStockwerk;
	}

	public void setAktuellesStockwerk(int aktuellesStockwerk) {
		this.aktuellesStockwerk = aktuellesStockwerk;
	}

	// Die Methoden, die jede Aufzugart besitzt
	private void hochfahren() {

	}

	private void runterfahren() {

	}

	private void auslesen() {

	}

	private void speichern() {

	}

}
