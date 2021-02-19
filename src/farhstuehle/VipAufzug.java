package farhstuehle;

public class VipAufzug extends Aufzug{

	private int maxKmh;

	public VipAufzug(int personenZahl, int maxGewicht, int aktuellesStockwerk, int maxKmh) {
		super(personenZahl, maxGewicht, aktuellesStockwerk);
		this.maxKmh = maxKmh;
	}

	public int getMaxKmh() {
		return maxKmh;
	}

	public void setMaxKmh(int maxKmh) {
		this.maxKmh = maxKmh;
	}
	
}
