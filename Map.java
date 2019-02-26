import java.util.ArrayList;
import java.util.Scanner;

/**
 * TODO: - generate items in each room
 * 		 - method to check for items in the room, must print out description of items  | NEED ITEM CLASS
 *       - adding items to player inventory when user picks up an item                 | NEED PLAYER CLASS
 *       - deducting time for each interaction
 * 
 * @author TUT 06: G3
 *
 */
public class Map {
	private ArrayList<Item>[] room = new ArrayList[5];
	private static int timeLeft = 24;
	private static int currentRoom = 0;

	/**
	 * Generate random items to each room UNFINISHED!!!!!!!
	 */
	public void addRoomItems() {
		int randomNumber;
		for (int i = 0; i < room.length; i++) {
			randomNumber = (int) (Math.random() * 10) + 0;

		}
	}

	/**
	 * Scans the room and prints out the items in the room
	 * 
	 * @param roomNum
	 *            - room the player is currently in UNFINISHED!!!!
	 */
	public void printRoomContents(int roomNum) {
		System.out.println();
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
	 * Main menu method, user can either start the game or exit the program
	 * 
	 * @return the player's decision
	 */
	public static int mainMenu() {
		Scanner kb = new Scanner(System.in);
		ArrayList<String> currentOptions = new ArrayList();
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
	public static void gameScreen(int playerInput) {
		Scanner kb = new Scanner(System.in);
		ArrayList<String> currentOptions = new ArrayList();

		// if the player chose to play the game, start the game.
		if (playerInput != 2) {
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
					} else {
						// move to the desired room
						System.out.println("you decided to go to " + playerInput);
						currentRoom = moveTo(currentOptions.get(playerInput - 1));
					}
				} catch (Exception e) {
					System.out.println("That option does not exist");
				}

			}
		} // else do nothing and terminate the program
	}

	/**
	 * TODO: Call battle method after gameScreen
	 */
	public static void main(String[] args) {
		int playerInput;

		// menu
		playerInput = mainMenu();

		// game screen
		gameScreen(playerInput);
	}
}
