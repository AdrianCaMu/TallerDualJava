package nttdata.javat1.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Obtener credenciales del fichero json
 * 
 * @author Adrián Cámara Muñoz
 *
 */
public class Credentials {
	
	private Credentials() {
		super();
	}

	/**
	 * Leer del fichero json las credenciales
	 * 
	 * @param keyword nombre de la credencial a leer
	 * @return credencial buscada
	 */
	private static String readFromFile(String keyword) {
		List<String> list;

		try {
			list = Files.readAllLines(new File(Constants.SETTINGS).toPath());
			StringBuilder  appsettingsContent = new StringBuilder();
			for (var l : list) {
				appsettingsContent.append(l);
			}
			JsonObject jsonObject = JsonParser.parseString(appsettingsContent.toString()).getAsJsonObject();

			return jsonObject.get(keyword).getAsString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// distintos tipos de credenciales guardadas

	public static String getPassDB() {
		return readFromFile("passwordBD");
	}

	public static String getUserDB() {
		return readFromFile("userBD");
	}

	public static String getUrlDB() {
		return readFromFile("urlDB");
	}
}