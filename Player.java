package cpsc233;

import java.util.ArrayList;

public class Player {
	
	public static ArrayList<Character> party = new ArrayList<Character>();
	
	public static void characterInit() {
		party.add(new Character(50,4));
	}
}
