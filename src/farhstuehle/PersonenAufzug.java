package farhstuehle;

public class PersonenAufzug extends Aufzug {

	private boolean melodie;

	public PersonenAufzug(int personenZahl, int maxGewicht, int aktuellesStockwerk, boolean melodie) {
		super(personenZahl, maxGewicht, aktuellesStockwerk);
		this.melodie = melodie;
	}
	
	public boolean getMelodie() {
		return melodie;
	}

	public void setMelodie(boolean melodie) {
		this.melodie = melodie;
	}


}
