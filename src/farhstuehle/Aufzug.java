package farhstuehle;

public class Aufzug {
	
private int personenZahl;
private int maxGewicht;
private int aktuellesStockwerk;

public Aufzug(int personenZahl, int maxGewicht, int aktuellesStockwerk) {
	super();
	this.personenZahl = personenZahl;
	this.maxGewicht = maxGewicht;
	this.aktuellesStockwerk = aktuellesStockwerk;
}

public int getPersonenZahl() {
	return personenZahl;
}

public void setPersonenZahl(int personenZahl) {
	this.personenZahl = personenZahl;
}

public int getMaxGewicht() {
	return maxGewicht;
}

public void setMaxGewicht(int maxGewicht) {
	this.maxGewicht = maxGewicht;
}

public int getAktuellesStockwerk() {
	return aktuellesStockwerk;
}

public void setAktuellesStockwerk(int aktuellesStockwerk) {
	this.aktuellesStockwerk = aktuellesStockwerk;
}

}
