package nttdata.javat1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sentencias SQL relacionadas con la tabla Usuarios de la Base de Datos
 * 
 * @author Adrián Cámara Muñoz
 *
 */
public class UserDAO extends AbstractDAO {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);

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
	public boolean usernameFind(String username) {
		final String QUERY = "SELECT username FROM users where username = '" + username + "';";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			LOG.info("Método: UserDAO.correoEncontrado(username) | existe el usuario");
			return rs.next();

		} catch (SQLException e) {
			LOG.error("Método: UserDAO.correoEncontrado(username) | no se pudo realizar la consulta");
			e.printStackTrace();
		}
		LOG.debug("Método: UserDAO.correoEncontrado(username) | no existe el usuario");
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
			LOG.info("Método: UserDAO.login(username, passwd) | login correcto");
			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error("Método: UserDAO.login(username, passwd) | login incorrecto");
		}
		LOG.debug("Método: UserDAO.login(username, passwd) | login incorrecto");
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
			LOG.info("Método: UserDAO.register(username, passwd) | registro correcto");

		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error("Método: UserDAO.register(username, passwd) | registro incorrecto");

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
				LOG.info("Método: UserDAO.idUser(username) | userId obtenido");
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error("Método: UserDAO.idUser(username) | userId no obtenido");

		}
		LOG.debug("Método: UserDAO.idUser(username) | userId no obtenido");
		return -1;
	}
}