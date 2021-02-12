package Model;

public class VIPAufzug extends Aufzug {

    // Attribute, welche den VIP-Aufzug auszeichnen
    private String passwort;
    private double maxGeschwindigkeit;


    // Getter u Setter
    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public double getMaxGeschwindigkeit() {
        return maxGeschwindigkeit;
    }

    public void setMaxGeschwindigkeit(double maxGeschwindigkeit) {
        this.maxGeschwindigkeit = maxGeschwindigkeit;
    }


    // Die Personenanzahl angeben, die sich im Aufzug befindet, sowie das Gewicht
    private void personenUndLastAngeben(int personenanzahl, double last) {

    }
}
