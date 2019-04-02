package codes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Rooms implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// room names should be the name of the room's picture
	private String[][] roomNames = {{"LB", "CB", "RB"}, {"LM", "CM", "RM"}, {"LT", "CT", "RT"}};
	private int[] roomCoord = new int[2]; 
	private ArrayList<Entities> roomContents = new ArrayList<Entities>();
	

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
	
	public int[] getRoomCoord() {
		return roomCoord;
	}
	
	public ArrayList<Entities> getRoomContents() {
		ArrayList<Entities> roomContentsToCopy = roomContents;
		return roomContentsToCopy;
	}
	
	public String getImageName() {
		return roomNames[roomCoord[0]][roomCoord[1]];
	}
	
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
