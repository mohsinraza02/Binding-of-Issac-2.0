package codes;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * For now, the objects will be a rectangle. this will be changed once we got the sprites drawn (and once i figured
 * out how to add pictures)
 * @author Kean Arguelles
 * TODO: Make this abstract
 *
 */
public class Entities extends Rectangle {
	private String entityType;
	private Image objectImage;
	
	/**
	 * This constructor creates an object and sets its position in the map, size, its type, and its sprite. 
	 * @param x - x position of the object
	 * @param y - y position of the object
	 * @param w - width of the object 
	 * @param h - height of the object
	 * @param type - type of the object (Player/Enemy/items(collectable/instant))
	 * @param image - filename of the object's sprite.
	 */
	public Entities(int x, int y, int w, int h, String type, String image) {
		// creating a new rectangle
		super(w, h);
		
		this.entityType = type;
		
		// if object has a sprite, add sprite.
		updateImage(image);
		
		setPosition(x, y);
	}
	
	/**
	 * This constructor creates an object, except it doesn't set its position and its sprite at the time of its creation.
	 * @param w
	 * @param h
	 * @param type
	 */
	public Entities(int w, int h, String type) {
		super(w, h);
		this.entityType = type;
	}
	
	public void updateImage(String imageName) {
		if (imageName != null) {
			this.objectImage = new Image(imageName);
			super.setFill(new ImagePattern(this.objectImage));
		}
	}
	
	public void setPosition(int x, int y) {
		setTranslateX(x);
		setTranslateY(y);
	}
	
	public int[] getRandomPos() {
		Random rand = new Random();
		// size of the wall is roughly 25 pixels
		int x = rand.nextInt(1100) + 50;
		int y = rand.nextInt(700) + 50;
		int[] pos = {x,y};
		return pos;
	}
	
	// When we change Entities to abstract, make this function abstract
	public String getName() {
		return entityType;
	}
	
	public String getEntityType() {
		return entityType;
	}
}
