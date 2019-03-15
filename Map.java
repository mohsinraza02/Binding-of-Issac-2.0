
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TODO: - generate items in each room - method to check for items in the room,
 * must print out description of items | NEED ITEM CLASS - adding items to
 * player inventory when user picks up an item | NEED PLAYER CLASS - deducting
 * time for each interaction
 * 
 * @author TUT 06: G3
 *
 */
public class Map {

	
	private static ArrayList<Instant>[] room = new ArrayList[5];
//	private static ArrayList<ArrayList<Item>> room = new ArrayList<ArrayList<Item>>();
	private static double timeLeft = 12; //
	private static int currentRoom = 0;
	private static Instant[] itemList = new Instant[8];

	// private Stats[]

	/**
	 * Generate random items to each room
	 */
	public static void addRoomItems() {
		int randomNumber;
		int pickItem;
		
		// fill the item list with items so we can use it to fill the room with items
		for (int i = 0; i < itemList.length; i++) {
			// the type parameter wants a 0 or a 1, luckily the itemList is sorted so i
			// could convert the index into either 1 or 2 by
			// taking the mod of i
			itemList[i] = new Instant(i, 10.0, ((i+4) % 4), 3.00);
		}
		// fill all 5 rooms
		for (int i = 0; i < room.length; i++) {
			// add between 0 and 4 items in a room
			randomNumber = (int) (Math.random() * 4) + 1;
			room[i] = new ArrayList<Instant>();
			for (int j = 0; j < randomNumber; j++) {
				pickItem = (int) (Math.random() * 8) + 0;
				room[i].add(itemList[pickItem]);
			}
		}
	}

	/**
	 * Scans the room and prints out the items in the room
	 * 
	 * @param roomNum
	 *            - room the player is currently in UNFINISHED!!!!
	 */
	public static boolean searchRoom(ArrayList<Instant> room) {
		System.out.println("You search the room for items..\n");
		if (room.size() == 0) {
			System.out.println("This room is empty\n");
			return false;
		} else {
			System.out.print("You found ");
			for (int i = 0; i < room.size(); i++) {
				if (i == room.size() - 1) {
					if (room.size() > 1) {
						System.out.print("and ");
					}
					System.out.println("a " + room.get(i).getName() + ".");
				} else {
					System.out.print("a " + room.get(i).getName() + ", ");
				}
			}
			return true;
		}
	}

	/**
	 * Moves the player to a room according to their input
	 * 
	 * @param choice
	 *            - the choice picked the the user (1 to 5)
	 * @return the room number based on the user's decision
	 */
	public static int moveTo(String choice) {
		// takes the last letter in the string
		// EXAMPLE: player choices "Go to room 3". It takes the last letter "3" and
		// converts it to a number
		return Integer.parseInt(choice.substring(choice.length() - 1));
	}

	/**
	 * prints out a menu of choices
	 * 
	 * @param choices
	 *            - list of choices the player can make
	 */
	public static void printMenu(ArrayList<String> choices) {
		// loops n number of times depending on the size of the choices list
		for (int i = 0; i < choices.size(); i++) {
			// adds a number at the beginning to organize the choices
			System.out.println((i + 1) + ") " + choices.get(i));
		}
	}

	/**
	 * creates choices for the player to pick which room to go to
	 * 
	 * @param roomNum
	 *            - number of the room player is currently in
	 * @return list of rooms the player could go to
	 */
	public static ArrayList<String> roomOptions(int roomNum) {
		ArrayList<String> temp = new ArrayList<String>();
		// starts counting at -1 because the first option should always be "Search the
		// room"
		for (int i = -1; i < 5; i++) {
			if (i == -1) {
				temp.add("Search the room");
			} else if (roomNum != i) { // skips the room the player is currently in
				temp.add(("Go to room " + i));
			}
		}
		return temp;
	}

	/**
	 * Picks up the item chosen by the player. Or does nothing when player changes
	 * their mind.
	 * 
	 * @param room
	 *            - current room the player is in
	 */
	public static void pickUpItem(ArrayList<Instant> room, Player player) {
		String input;
		int InstantIndex;
		Scanner kb = new Scanner(System.in);
		
		while (true) {
			input = kb.nextLine();
			InstantIndex = Integer.parseInt(input) - 1;
			if (room.size() + 1 != Integer.parseInt(input)) {
				try {
					System.out.println("-------------------------------------------");
					player.interactWithItem(room.get(InstantIndex));
					System.out.println("You gained " + room.get(InstantIndex).getDesc() + "\n");
					
					timeLeft -= room.get(InstantIndex).getTime()-(player.getStat(2)*0.10);
					room.remove(InstantIndex);
					player.printStats();
					break;
				} catch (Exception e) {
					System.out.println("Please enter a different number.");
				}
			} else {
				break;
			}
		}
	}

	/**
	 * Main menu method, user can either start the game or exit the program
	 * 
	 * @return the player's decision
	 */
	public static int mainMenu() {
		Scanner kb = new Scanner(System.in);
		ArrayList<String> currentOptions = new ArrayList<String>();
		int playerInput;

		// add two options "play game and quit"
		currentOptions.add("Play Game");
		currentOptions.add("quit");
		do { // do until user picks 1 or 2
			System.out.println("-------------------------------------------");
			System.out.println("WELCOME TO THE UNIVERSITY GAME");
			System.out.println("-------------------------------------------");
			printMenu(currentOptions);
			playerInput = Integer.parseInt(kb.nextLine());
			if (playerInput > 2)
				System.out.println("Please enter 1 or 2");
		} while (playerInput > 2);

		return playerInput;
	}

	/**
	 * Game screen, handles moving into different rooms and printing out choices the
	 * player can make
	 * 
	 * @param playerInput
	 */
	public static void gameScreen(Player player) {
		Scanner kb = new Scanner(System.in);
		ArrayList<String> currentOptions = new ArrayList<String>();
		int playerInput;
		
		addRoomItems();
		// will run until the player doesn't have any time left
		while (timeLeft > 0) {
			System.out.println("-------------------------------------------");
			System.out.println("You are currently in ROOM " + currentRoom + "   | There are " + timeLeft
					+ " hours left before the exam starts");
			currentOptions = roomOptions(currentRoom);
			printMenu(currentOptions);

			// catches exceptions when user picks an option that doesn't exist
			try {
				playerInput = Integer.parseInt(kb.nextLine());
				// TODO: finish room checking
				if (playerInput == 1) {
					// check room for items and print it out
					if (searchRoom(room[currentRoom])) {
						currentOptions.clear();
						for (int i = 0; i < room[currentRoom].size(); i++) {
							currentOptions.add("Pick up " + room[currentRoom].get(i).getName());
						}
						currentOptions.add("Nevermind, go back.");
						printMenu(currentOptions);
						pickUpItem(room[currentRoom], player);
					}
				} else {
					// move to the desired room
					currentRoom = moveTo(currentOptions.get(playerInput - 1));
					System.out.println("you decided to go to room " + currentRoom);
				}
			} catch (Exception e) {
				System.out.println("That option does not exist");
			}

		}
	}

	
	public static void statScreen(Player player) {
		int [] stats = player.generateStats();
		System.out.println("-------------------------------------------");
		System.out.println("Your stats are:");
		System.out.println("Attack:" + stats[0]);
		System.out.println("Defense:" + stats[1]);
		System.out.println("Speed:"+stats[2]);
		System.out.println("Luck:"+stats[3]);
	}
	
	
	public static void main(String[] args) {
		int playerInput;
		Player player = new Player(100.0, 0.0,100,50);
		// menu
		playerInput = mainMenu();

		// game screen
		// if the player chose to play the game, start the game.
		if (playerInput != 2) {
			statScreen(player);
			gameScreen(player);
			Battle.start(player);
		} // else do nothing and terminate the program

		// Item Creation
		// Strength [0]
		// Item book = new Item("Book", 1, 0, 2.0);
		// Item ta = new Item("TA", 3, 0, 5.0);
		// // Defence [1]
		// Item games = new Item("Games", 1, 1, 3.5);
		// Item Sleep = new Item("Sleep", 5, 1, 8.0);
		// // Luck [2]
		// Item sacrifice = new Item("Sacifice to the Gods!", 5, 2, 5.5);
		// Item charm = new Item("Charm", 3, 2, 0.5);
		// // Attack [3]
		// Item sharpener = new Item("Sharpener", 2, 3, 0.5);
		// // Speed [4]
		// Item cheatSheet = new Item("Totaly not Cheating", 5, 4, 2.5);
	}

	// public Player getPlayer() {
	// return player;
	// }
}