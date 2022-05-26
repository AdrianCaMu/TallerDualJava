package nttdata.javat1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import nttdata.javat1.utils.Credentials;

/**
 * Clase Abstacta que conecta con la base de datos
 * 
 * @author Adrián Cámara Muñoz
 *
 */
public class AbstractDAO {
	private final String DB_URL = Credentials.getUrlDB();
	private final String USER = Credentials.getUserDB();
	private final String PASS = Credentials.getPassDB();
	protected Connection conn;
	protected Statement stmt;

	// Builders

	/**
	 * Constructor de la clase que realiza la conexión con la Base de datos
	 */
	public AbstractDAO() {
		try {
			this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
			this.stmt = conn.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}