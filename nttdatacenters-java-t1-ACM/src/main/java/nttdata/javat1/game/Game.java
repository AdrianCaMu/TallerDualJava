package nttdata.javat1.game;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nttdata.javat1.dao.AbstractDAO;
import nttdata.javat1.interfaces.IGame;

/**
 * Clase Game
 * 
 * @author Adrián Cámara Muñoz
 *
 */
public class Game implements IGame {
	
	private static final Logger LOG = LoggerFactory.getLogger(Game.class);

	// Fields

	private List<Ball> ballList;
	private int round;
	private int score;
	private int power;
	private boolean isFinished;

	// Builder

	public Game() {
		this.ballList = new ArrayList<>();
		this.round = 0;
		this.score = 0;
		this.isFinished = false;
		initializeBalls();
	}

	/**
	 * inicializa las bolas de la lista
	 */
	private void initializeBalls() {
		for (int i = 0; i < 5; i++) {
			ballList.add(new Ball());
		}
	}

	// getters and setters

	public List<Ball> getBallList() {
		return ballList;
	}

	public boolean getIsFinished() {
		return isFinished;
	}

	public void setBallList(List<Ball> ballList) {
		this.ballList = ballList;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	// methods

	@Override
	public String Welcome() {
		LOG.info("Método: Game.Welcome() | Mensaje informacion");
		return "<html><body>Utiliza el botón New Ball para lanzar una bola nueva.<br> Elige la potencia entre 1 y 5 <br> Usa los botones right paddle y left paddle para tratar de golpear la bola</body></html>";
	}

	@Override
	public void startGame() {
		LOG.info("Método: Game.startGame() | Inicio");

		round++;

		if (ballList.size() > round) {
			generatePosition();
			LOG.info("Método: Game.startGame() | PosicionGenerada");

		} else {
			isFinished = true;
			LOG.info("Método: Game.startGame() | Finalizar game");

		}
		
		LOG.info("Método: Game.startGame() | Fin");

	}

	/**
	 * Genera posicion de Bola
	 */
	private void generatePosition() {
		LOG.info("Método: Game.generatePosition() | Inicio");

		Random r;
		try {
			r = SecureRandom.getInstanceStrong();
			int num = r.nextInt(9) + 1;
			ballList.get(round).setPosition(num);
			LOG.info("Método: Game.generatePosition() | numGenerado");


		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			LOG.error("Método: Game.generatePosition() | num no generado");

		}
		
		LOG.info("Método: Game.generatePosition() | Fin");


	}

	@Override
	public boolean usePaddle(int paddle) {
		LOG.info("Método: Game.usePaddle(paddle) | Inicio");
		boolean acierto = Boolean.FALSE;

		if (paddle == 1) {
			acierto = useLeftPaddle(acierto);
		}else if(paddle == 2){
			acierto = UseRightPaddle(acierto);
		}
		
		if(acierto) {
			LOG.info("Método: Game.usePaddle(paddle) | Acierto");
		}else {
			LOG.info("Método: Game.usePaddle(paddle) | Fallo");
		}
		
		LOG.info("Método: Game.usePaddle(paddle) | Fin");

		return acierto;
	}

	/**
	 * usar pala derecha
	 * @param acierto boolean comprobar si acierta
	 * @return true si acierta, false si no
	 */
	private boolean UseRightPaddle(boolean acierto) {
		LOG.info("Método: Game.useRightPaddle(acierto) | Inicio");

		if (ballList.get(round).getPosition() >= 5) {
			acierto = Boolean.TRUE;
		}
		
		LOG.info("Método: Game.useRightPaddle(acierto) | Fin");

		return acierto;
	}

	/**
	 * usar pala izquierda
	 * @param acierto boolean comprobar si acierta
	 * @return true si acierta, false si no
	 */
	private boolean useLeftPaddle(boolean acierto) {
		LOG.info("Método: Game.useLeftPaddle(acierto) | Inicio");

		if (ballList.get(round).getPosition() <= 5) {
			acierto = Boolean.TRUE;
		}
		
		LOG.info("Método: Game.useLeftPaddle(acierto) | Fin");

		return acierto;
	}

	/**
	 * obtener puntos dependiendo de la posicion
	 * 
	 * @return info sobre puntos obtenidos
	 */
	public String getPoints() {
		LOG.info("Método: Game.getPoints() | Inicio");

		generatePosition();
		String resultado = "";
		if (getBonus()) {
			resultado = "Bonus conseguido! ";
			LOG.info("Método: Game.getPoints() | BonusObtenido");

		}

		switch (ballList.get(round).getPosition()) {
		case 1:
			resultado += "Has perdido la bola!";
			break;
		case 2:
			score += 100;
			resultado += "Has obtenido 100 puntos!";
			break;
		case 3:
			score += 200;
			resultado += "Has obtenido 200 puntos!";
			break;
		case 4:
			score += 300;
			resultado += "Has obtenido 300 puntos!";
			break;
		case 5:
			score = 0;
			resultado += "Has perdido todos tus puntos!";
			break;
		case 6:
			score += 400;
			resultado += "Has obtenido 400 puntos!";
			break;
		case 7:
			score += 500;
			resultado += "Has obtenido 500 puntos!";
			break;
		case 8:
			score = score / 2;
			resultado += "Has perdido la mtiad de tus puntos!";
			break;
		case 9:
			score += score;
			resultado += "Has duplicado tus puntos!";
			break;
		case 10:
			ballList.add(new Ball());
			resultado += "Has conseguido una bola extra!";
			break;
		default:
			resultado = "No has conseguido puntos!";
			break;
		}

		LOG.info("Método: Game.getPoints() | Fin");

		return resultado;

	}

	/**
	 * comprobar si obtener bonus
	 * 
	 * @return true, bonus obtenido, false si no
	 */
	private boolean getBonus() {
		LOG.info("Método: Game.getBonus() | Inicio");

		if (ballList.get(round).getPosition() == power) {
			score = score + 1000;
			return true;
		}
		
		LOG.info("Método: Game.getBonus() | Fin");

		return false;
	}
}
