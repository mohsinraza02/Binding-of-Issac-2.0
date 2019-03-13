package codes;

public class Map extends Main {
	// room names should be the name of the room's picture
	private String[][] roomNames = {{"room11", "room12"}, {"room21", "room22"}};
	
	public String getImageName(int x, int y) {
		return roomNames[y][x];
	}
	
	
}
