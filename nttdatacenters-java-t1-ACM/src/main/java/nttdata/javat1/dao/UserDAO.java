package nttdata.javat1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Sentencias SQL relacionadas con la tabla Usuarios de la Base de Datos
 * 
 * @author Adrián Cámara Muñoz
 *
 */
public class UserDAO extends AbstractDAO {

	/**
	 * Constructor de la BD que hereda de la clase Abstracta
	 */
	public UserDAO() {
		super();
	}

	/**
	 * comprobar si un usuario se encuentra registrado en la BD
	 * 
	 * @param username username a comprobar
	 * @return true si existe, false si no.
	 */
	public boolean correoEncontrado(String username) {
		final String QUERY = "SELECT username FROM users where username = '" + username + "';";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * comprobar que un usuario puede logearse en la aplicacion
	 * 
	 * @param username username del usuario
	 * @param passwd contraseña hash del usuario
	 * @return true si coincide con la BD y está activado, false si no.
	 */
	public boolean login(String username, String passwd) {
		final String QUERY = "SELECT username, passwd FROM users where username = '" + username + "' and "
				+ "passwd = '" + passwd + "';";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * registrar un nuevo usuario en nuestra base de datos
	 * 
	 * @param username username del usuario
	 * @param passwd contraseña hash del usuario
	 */
	public void register(String username, String passwd) {
		final String INSERT = "INSERT INTO users (username,passwd)" + " VALUES ('" + username + "','" + passwd + "');";
		try {
			stmt.executeUpdate(INSERT);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * obtener id de un usuario
	 * 
	 * @param username username del usuario del que queremos obtenber la id
	 * @return id del usuario encontrado / -1 si no encuentra al usuario
	 */
	public int idUser(String username) {
		final String QUERY = "SELECT id FROM users WHERE username = '" + username + "';";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}