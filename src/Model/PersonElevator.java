package Model;

/**
 * Personelevator model class.
 * 
 * @author lasse
 *
 */
public class PersonElevator extends Elevator {

	// elevator music
	private String musicPlaying;

	// Getter u Setter
	public String getAufzugsMelodie() {
		return musicPlaying;
	}

	public void setAufzugsMelodie(String aufzugsMelodie) {
		this.musicPlaying = aufzugsMelodie;
	}
}
