package nttdata.javat1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nttdata.javat1.ui.LoginView;

/**
 * Clase principal que inicia el programa
 * 
 * @author Adián Cámara Muñoz
 *
 */
public class T1MainACM {

	private static final Logger LOG = LoggerFactory.getLogger(T1MainACM.class);

	/**
	 * método principal del programa
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LOG.info("Método: main() | TRAZA INICIO");
		new LoginView();
		LOG.info("Método: main() | TRAZA FIN");

	}
}
