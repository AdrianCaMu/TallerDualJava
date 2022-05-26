package nttdata.javat1.game;

import nttdata.javat1.interfaces.IBall;

public class Ball implements IBall {
	private int position;
	
	public Ball() {
		super();
		this.position = 0;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
}
