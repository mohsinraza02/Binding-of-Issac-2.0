package cpsc233;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		boolean timeOut = true; // Testing to see if time is out
		Player.characterInit();
		Room room = new Room(timeOut); // Create a new room and pass in the boolean to see if time is out.

	}

}
