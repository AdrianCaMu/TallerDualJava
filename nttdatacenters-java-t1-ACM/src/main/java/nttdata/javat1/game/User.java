package nttdata.javat1.game;

/**
 * Clase User
 * 
 * @author Adrián Cámara Muñoz
 *
 */
public class User {

	// Fields
	private String username;
	private int score;

	// Builder
	public User(String username, int score) {
		this.username = username;
		this.score = score;
	}

	// Getters and Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
