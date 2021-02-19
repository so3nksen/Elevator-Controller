package farhstuehle;

public class Aufzugfahrt {

	private String aufzugTyp;
	private int fahrtGewicht;
	private int personenZahl;
	private int gefahreneStockwerke;
	
	public Aufzugfahrt(String aufzugTyp, int fahrtGewicht, int personenZahl, int gefahreneStockwerke) {
		super();
		this.aufzugTyp = aufzugTyp;
		this.fahrtGewicht = fahrtGewicht;
		this.personenZahl = personenZahl;
		this.gefahreneStockwerke = gefahreneStockwerke;
	}

	public String getAufzugTyp() {
		return aufzugTyp;
	}

	public void setAufzugTyp(String aufzugTyp) {
		this.aufzugTyp = aufzugTyp;
	}

	public int getFahrtGewicht() {
		return fahrtGewicht;
	}

	public void setFahrtGewicht(int fahrtGewicht) {
		this.fahrtGewicht = fahrtGewicht;
	}

	public int getPersonenZahl() {
		return personenZahl;
	}

	public void setPersonenZahl(int personenZahl) {
		this.personenZahl = personenZahl;
	}

	public int getGefahreneStockwerke() {
		return gefahreneStockwerke;
	}

	public void setGefahreneStockwerke(int gefahreneStockwerke) {
		this.gefahreneStockwerke = gefahreneStockwerke;
	}
	
}
