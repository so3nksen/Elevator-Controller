package Model;

public class Lastenaufzug extends Aufzug {

    // Attribute, welche den Personenaufzug auszeichnen
    private double quadratmeter;
    private boolean kleinOderGross;

    //Getter u Setter
    public double getQuadratmeter() {
        return quadratmeter;
    }

    public void setQuadratmeter(double quadratmeter) {
        this.quadratmeter = quadratmeter;
    }

    public boolean isKleinOderGross() {
        return kleinOderGross;
    }

    public void setKleinOderGross(boolean kleinOderGross) {
        this.kleinOderGross = kleinOderGross;
    }

    // Angeben, welche Last der Aufzug momentan befördert
    private void lastAngeben() {

    }
}
