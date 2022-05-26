package nttdata.javat1.game;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import nttdata.javat1.interfaces.IGame;

public class Game implements IGame {
	
	// Fields
	
	private ArrayList<Ball> ballList;
	private int round;
	private int score;
	private int power;
	private boolean isFinished;

	// Builder
	
	public Game() {
		this.ballList = new ArrayList<Ball>();
		this.round = 0;
		this.score = 0;
		this.isFinished = false;
		for (int i = 0; i < 5; i++) {
			ballList.add(new Ball());
		}
	}
	
	//getters and setters
	
	
	
	public ArrayList<Ball> getBallList() {
		return ballList;
	}

	public boolean getIsFinished() {
		return isFinished;
	}

	public void setBallList(ArrayList<Ball> ballList) {
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
	
	//methods

	@Override
	public String Welcome() {
		return "<html><body>Utiliza el botón New Ball para lanzar una bola nueva.<br> Elige la potencia entre 1 y 5 <br> Usa los botones right paddle y left paddle para tratar de golpear la bola</body></html>"; 
	}

	@Override
	public void startGame() {
		round++;

		if(ballList.size() > round) {
			generatePosition();
		}else {
			isFinished = true;
		}
	}
	
	private void generatePosition() {
		Random r;
		try {
			r = SecureRandom.getInstanceStrong();
			int num = r.nextInt(9)+1;
			ballList.get(round).setPosition(num);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public boolean usePaddle(int paddle) {
		if(paddle == 1) {
			if(ballList.get(round).getPosition() <= 5) {
				return true;
			}
		}else {
			if(ballList.get(round).getPosition() >= 5) {
				return true;
			}
		}
		return false;
	}

	public String getPoints() {
		generatePosition();
		String resultado = "";
		if(getBonus()) {
			resultado = "Bonus conseguido! ";
		}

		switch (ballList.get(round).getPosition()){
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
				score = score/2;
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
		
		return resultado;
				
	}

	private boolean getBonus() {
		if(ballList.get(round).getPosition() == power) {
			score = score + 1000;
			return true;
		}
		return false;
	}
}
