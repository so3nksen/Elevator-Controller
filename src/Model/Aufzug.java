package Model;

public class Aufzug {

    //Notwendige Kriterien eines jeden Aufzugs
    private int personenzahl;
    private double gesamtgewicht;
    private int aktuellesStockwerk;


    // Getter u. Setter
    public int getPersonenzahl() {
        return personenzahl;
    }

    public void setPersonenzahl(int personenzahl) {
        this.personenzahl = personenzahl;
    }

    public double getGesamtgewicht() {
        return gesamtgewicht;
    }

    public void setGesamtgewicht(double gesamtgewicht) {
        this.gesamtgewicht = gesamtgewicht;
    }

    public int getAktuellesStockwerk() {
        return aktuellesStockwerk;
    }

    public void setAktuellesStockwerk(int aktuellesStockwerk) {
        this.aktuellesStockwerk = aktuellesStockwerk;
    }


    //Die Methoden, die jede Aufzugart besitzt
    private void hochfahren() {

    }

    private void runterfahren() {

    }

    private void auslesen() {

    }

    private void speichern() {

    }

}
