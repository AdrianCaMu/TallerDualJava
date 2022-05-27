package nttdata.javat1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nttdata.javat1.game.User;

/**
 * Concetor con la tabla Score de la BD
 * 
 * @author Adián Cámara Muñoz
 *
 */
public class ScoreDAO extends AbstractDAO {

	private static final Logger LOG = LoggerFactory.getLogger(ScoreDAO.class);
	
	/**
	 * Constructor de la BD que hereda de la clase Abstracta
	 */
	public ScoreDAO() {
		super();
	}

	/**
	 * registrar un nuevo score en nuestra base de datos
	 * 
	 * @param id    id del usuario
	 * @param score puntos conseguidos
	 */
	public void newscore(int id, int score) {
		final String INSERT = "INSERT score (user_id,score,scoreDate) VALUES (" + id + "," + score + ", curdate());";
		try {
			stmt.executeUpdate(INSERT);
			LOG.info("Método: ScoreDAO.newscore(id, score) | nuevo score insertado");

		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error("Método: ScoreDAO.newscore(id, score) | nuevo score no insertado");
		}
	}

	/**
	 * obtener todos los scores de la base de datos
	 * 
	 * @return ArrayList<User> lista de los usuarios con más puntos
	 */
	public ArrayList<User> getAll() {
		final String QUERY = "SELECT users.username, score.score FROM users inner join score on users.id=score.user_id order by score.score desc limit 5;";
		ArrayList<User> scorelist = new ArrayList<User>();
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				String username = rs.getString("username");
				int score = rs.getInt("score");

				User u = new User(username, score);
				scorelist.add(u);
			}
			LOG.info("Método: ScoreDAO.getAll() | listaUsuarios obtenida");

		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error("Método: ScoreDAO.getAll() | listaUsuarios no obtenida");

		}
		
		return scorelist;
	}
}
