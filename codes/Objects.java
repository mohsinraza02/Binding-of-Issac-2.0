package codes;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * For now, the objects will be a rectangle. this will be changed once we got the sprites drawn (and once i figured
 * out how to add pictures)
 * @author Kean Arguelles
 *
 */
public class Objects extends Rectangle {
	private boolean isVisible = false;
	private String type;
	private int speed;
	private Image objectImage;
	
	/**
	 * This constructor creates an object that will be rendered in the game
	 * @param x - x position of the object
	 * @param y - y position of the object
	 * @param w - width of the object 
	 * @param h - height of the object
	 * @param type - type of the object (Player/npc/items)
	 */
	public Objects(int x, int y, int w, int h, String type, Color color, String image) {
		// creating a new rectangle
		super(w, h, color);
		
		this.type = type;
		// if object has a sprite, add sprite.
		if (image != null) {
			this.objectImage = new Image(image);
			super.setFill(new ImagePattern(this.objectImage));
		}
		
		setTranslateX(x);
		setTranslateY(y);
		
		// if npcs can't move, remove this
		switch(type) {
		case "player":
			this.speed = 4;
			break;
		case "npc":
			this.speed = 3;
			break;
		case "enemy":
			this.speed = 4;
			break;
		default:
			this.speed = 0;
			break;
		}
		
	}
	
	/**
	 * dont know where to put event listeners so it will be here for now
	 */
	public void moveLeft() {
		// the if statements checks if the player is going past the visible area
		if (getTranslateX() < 0)
			setTranslateX(1200);
		setTranslateX(getTranslateX() - speed);
	}
	
	public void moveRight() {
		if (getTranslateX() > 1200)
			setTranslateX(-39);
		setTranslateX(getTranslateX() + speed);
	}
	
	public void moveUp() {
		if (getTranslateY() < 0)
			setTranslateY(800);
		setTranslateY(getTranslateY() - speed);
	}
	
	public void moveDown() {
		if (getTranslateY() > 800)
			setTranslateY(-79);
		setTranslateY(getTranslateY() + speed); // in javafx, the axis starts from the top left therefore up is - and down is +
	}
}
