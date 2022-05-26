package nttdata.javat1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nttdata.javat1.game.User;

public class ScoreDAO extends AbstractDAO{
	/**
	 * Constructor de la BD que hereda de la clase Abstracta
	 */
	public ScoreDAO() {
		super();
	}
	
	/**
	 * registrar un nuevo score en nuestra base de datos
	 * 
	 * @param id id del usuario
	 * @param score puntos conseguidos
	 */
	public void newscore(int id, int score) {
		final String INSERT = "INSERT score (user_id,score,scoreDate) VALUES (" + id + "," + score + ", curdate());";
		try {
			stmt.executeUpdate(INSERT);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<User> getAll() {
		final String QUERY = "SELECT users.username, score.score FROM users inner join score on users.id=score.user_id order by score.score desc;";
		ArrayList<User> scorelist = new ArrayList<User>();
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				String username = rs.getString("username");
				int score = rs.getInt("score");
				
				User u = new User(username, score);	
				scorelist.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return scorelist;
	}
}
