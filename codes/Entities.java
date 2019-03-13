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
public class Entities extends Rectangle {
	private boolean isVisible = false;
	private String type;
	private Image objectImage;
	
	/**
	 * This constructor creates an object that will be rendered in the game
	 * @param x - x position of the object
	 * @param y - y position of the object
	 * @param w - width of the object 
	 * @param h - height of the object
	 * @param type - type of the object (Player/npc/items)
	 */
	public Entities(int x, int y, int w, int h, String type, Color color, String image) {
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
	}
	
	
}
