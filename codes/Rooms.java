package codes;

import java.util.ArrayList;
import java.util.Random;

public class Rooms {
	// room names should be the name of the room's picture
	private String[][] roomNames = {{"LB", "CB", "RB"}, {"LM", "CM", "RM"}, {"LT", "CT", "RT"}};
	private int[] roomCoord = new int[2]; 
	private ArrayList<Entities> roomContents = new ArrayList<Entities>();
	
	/**
         * This constructor takes 2 parameter values. The constructor takes the parameters and creates the rooms
	 * based on the coordinates. The generateItems() method is called in this constructor so that Items can be placed
	 * in the rooms.
	 *
	 * @param x
	 * 
	 * @param y
	 */ 
	public Rooms(int x, int y) {
		if (x < 3 && y < 3) {
			roomCoord[0] = y;
			roomCoord[1] = x;			
		} else {
			roomCoord[0] = 0;
			roomCoord[1] = 0;
		}
		generateItems();
	}
	/**
	 * The method getRoomCoord returns the instance variable roomCoord of type int [].
	 * 
	 * @return instance variable roomCoord
	 */
	
	public int[] getRoomCoord() {
		return roomCoord;
	}
	/**
	 * The method getRoomContents returns a copy of the instance variable roomContents of type ArrayList<Entities>.
	 * 
	 * @return roomContentsToCopy
	 */
	
	public ArrayList<Entities> getRoomContents() {
		ArrayList<Entities> roomContentsToCopy = roomContents;
		return roomContentsToCopy;
	}
	/**
	 * The method getImageName returns the instance variable roomNames of type String[][].
	 * 
	 * @return instance variable roomNames
	 */
	public String getImageName() {
		return roomNames[roomCoord[0]][roomCoord[1]];
	}
	
	/**
	 * The method generateItems randomly generates items from the
	 * Instant class and Collectables class and adds them to the rooms randomly.
	 */
	
	public void generateItems() {
		Random rand = new Random();
		int numOfItems = rand.nextInt(9) + 1;
		int itemType;
		int itemIndex;
		int instantSkillIndex;
		
		for (int i = 0; i < numOfItems; i++) {
			itemType = rand.nextInt(2); // 0 if instant, 1 if collectible
			itemIndex = rand.nextInt(8); // pick a number between 0 and 7
			if(itemType == 0) {
				instantSkillIndex = (itemIndex + 4) % 4;
				roomContents.add(new Instant(itemIndex, 10.0, instantSkillIndex, 4));
			} else {
				roomContents.add(new Collectable(itemIndex, 10.0, 1));
			}
		}
	}
	
}
