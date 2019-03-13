package codes;

import javafx.scene.paint.Color;

public class Player extends Entities{
	
	private int speed;
	// the map is 2d. Therefore, we need to know the column and the row number of the room.
	public int currentRoomX = 0;
	public int currentRoomY = 0;
	public int timeLft = 0;

	public Player(int x, int y, int w, int h, String type, Color color, String image) {
		super(x, y, w, h, type, color, image);
		// TODO Auto-generated constructor stub
		this.speed = 6;
	}
	
	// Getters for the room's coordinates
	public int getCurrentRoomX() {
		return currentRoomX;
	}
	
	public int getCurrentRoomY() {
		return currentRoomY;
	}
	
	/**
	 * Moves the to the left
	 * TODO: Implement walking animation
	 */
	public void moveLeft() {
		// check if the user is hitting the left edge
		if (getTranslateX() < 0) {
			// check if user is in the very left room. If they're not, move to the left room.
			if (currentRoomX != 0) {
				setTranslateX(1200);
				currentRoomX--;
				setTranslateX(getTranslateX() - speed);
			}
		} else {
			setTranslateX(getTranslateX() - speed);
		}
	}
	
	/**
	 * Moves the player to the right
	 */
	public void moveRight() {
		if (getTranslateX() > 1150) {
			if (currentRoomX != 1) {
				setTranslateX(-39);
				currentRoomX++;
				setTranslateX(getTranslateX() + speed);
			}
		} else {
			setTranslateX(getTranslateX() + speed);
		}
	}
	
	/**
	 * Moves the player up
	 */
	public void moveUp() {
		if (getTranslateY() < 0) {
			if (currentRoomY != 1) {
				setTranslateY(800);
				currentRoomY++;
				setTranslateY(getTranslateY() - speed);
			}
		} else {
			setTranslateY(getTranslateY() - speed);
		}
	}
	
	/**
	 * Moves the player down
	 */
	public void moveDown() {
		if (getTranslateY() > 700) {
			if (currentRoomY != 0) {
				setTranslateY(-79);
				currentRoomY--;
				setTranslateY(getTranslateY() + speed);
			}
		} else {
			setTranslateY(getTranslateY() + speed);			
		}
	}

}
