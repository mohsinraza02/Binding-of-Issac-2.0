package codes;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * This is the item class. It will have the functions that are required for an items
 * TODO: 1) Create generateItem which generates an item to a specified room
 * 		 2) Create the instance variables
 * 		 4) Create getPicture(String name) which takes the picture of the item
 * 		 5) getters and setters!!!
 *
 */
public class Item extends Entities{
	private boolean isVisible; // if the item is visible
	private int roonNumX; // row # of the room to which the item is located
	private int roomNumY; // column # of the room to which the item is located
	
	public Item(int x, int y, int w, int h, String name) {
		super(x, y, w, h, "item", Color.BLACK, name);
		// TODO add something to this constructor if items needs to do something when they are created
	}

	private Image getPicture(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
