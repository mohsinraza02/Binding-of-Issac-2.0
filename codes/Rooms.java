package codes;

import java.util.ArrayList;
import java.util.Random;

public class Rooms {
	// room names should be the name of the room's picture
	private String[][] roomNames = {{"room11", "room12"}, {"room21", "room22"}};
	private int[] roomCoord = new int[2]; 
	private ArrayList<Entities> roomContents = new ArrayList();
	
	public ArrayList<Entities> getRoomContents() {
		ArrayList<Entities> roomContentsToCopy = roomContents;
		return roomContentsToCopy;
	}

	public Rooms(int x, int y) {
		roomCoord[0] = y;
		roomCoord[1] = x;
		generateItems();
	}
	
	public String getImageName() {
		return roomNames[roomCoord[0]][roomCoord[1]];
	}
	
	public void generateItems() {
		Random rand = new Random();
		int numOfItems = rand.nextInt(9) + 1;
		int itemType;
		int itemIndex;
		
		for (int i = 0; i < numOfItems; i++) {
			itemType = rand.nextInt(2);
			itemIndex = rand.nextInt(8);
			if(itemType == 0) {
				roomContents.add(new Instant(itemIndex, 10.0, 0, 4));
			} else {
				roomContents.add(new Collectable(itemIndex, 10.0, 1));
			}
		}
	}
	
	public void removeItem(int index) {
		roomContents.remove(index);
	}
	
}
