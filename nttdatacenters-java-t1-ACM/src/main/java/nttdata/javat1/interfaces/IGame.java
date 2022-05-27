package nttdata.javat1.interfaces;

public interface IGame {
	/**
	 * Mensaje de bienvenida
	 * @return reglas del juego
	 */
	public String Welcome();
	
	/**
	 * comenzar nueva ronda
	 */
	public void startGame();
	
	/**
	 * utilizar pala
	 * @param paddle 1 para usar izquierda y 2 para usar derecha
	 * @return true si golpea bola, false si no
	 */
	public boolean usePaddle(int paddle);
}
