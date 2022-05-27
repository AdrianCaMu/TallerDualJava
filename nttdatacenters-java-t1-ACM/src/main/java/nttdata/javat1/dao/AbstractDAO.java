package nttdata.javat1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nttdata.javat1.ui.LoginView;
import nttdata.javat1.utils.Credentials;

/**
 * Clase Abstacta que conecta con la base de datos
 * 
 * @author Adrián Cámara Muñoz
 *
 */
public class AbstractDAO {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractDAO.class);

	// Fields

	private final String DB_URL = Credentials.getUrlDB();
	private final String USER = Credentials.getUserDB();
	private final String PASS = Credentials.getPassDB();
	protected Connection conn;
	protected Statement stmt;

	// Builder

	/**
	 * Constructor de la clase que realiza la conexión con la Base de datos
	 */
	public AbstractDAO() {
		try {
			this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
			this.stmt = conn.createStatement();
			LOG.info("Método: AbstractDAO() | Conexion BD correcta");

		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error("Método: AbstractDAO() | Conexion BD fallida");

		}
	}
}