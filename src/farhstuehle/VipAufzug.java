package farhstuehle;

public class VipAufzug extends Aufzug{

	private int maxSpeed;

	public VipAufzug(int personenZahl, int maxGewicht, int aktuellesStockwerk, int maxKmh) {
		super(personenZahl, maxGewicht, aktuellesStockwerk);
		this.maxSpeed = maxKmh;
	}

	public int getMaxKmh() {
		return maxSpeed;
	}

	public void setMaxKmh(int maxKmh) {
		this.maxSpeed = maxKmh;
	}
	
}
