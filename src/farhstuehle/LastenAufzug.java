package farhstuehle;

public class LastenAufzug extends Aufzug{
	
	private int qm;

	public LastenAufzug(int personenZahl, int maxGewicht, int aktuellesStockwerk, int qm) {
		super(personenZahl, maxGewicht, aktuellesStockwerk);
		this.qm = qm;
	}

	public int getQm() {
		return qm;
	}

	public void setQm(int qm) {
		this.qm = qm;
	}
	
}
