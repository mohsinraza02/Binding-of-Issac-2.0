package cpsc233;

import java.util.Scanner;

public class Room {

	public Scanner kb = new Scanner(System.in);

	/**
	 * Create a new room, and if time is up then the battle method will run Time is
	 * not up, then
	 * 
	 * @param timeOut
	 *            // Boolean to see if time is out.
	 */
	Room(boolean timeOut) {
		System.out.println("You walk into the next room.");
		kb.nextLine();
		if (timeOut == true) {
			battle();
		} else {
			roomThings();
		}
	}

	/**
	 * When there is no battle in the room, look for items or events
	 */
	public void roomThings() {
		System.out.println("There are items in the room...");
		System.out.println("More text");
		System.out.println("More text");
	}

	/**
	 * When time is up, this battle will commence.
	 */
	public void battle() {

		Enemy boss = Enemy.getRandomEnemy();
		boolean playerEnd = false;
		boolean fight = true;

		System.out.println("It is time to take the test.");
		kb.nextLine();

		do {
			playerEnd = false;
			do {
				System.out.println("What will you do?\n(a) Attack/Answer Question");
				String decision = kb.nextLine();
				if (decision.equals("a")) { // Then attack
					Player.party.get(0).attack(boss);
					kb.nextLine();
					playerEnd = true;
					fight = checkFailOrPass(Player.party.get(0), boss);
				}
			} while (playerEnd == false);
			if (boss.health >= 1) {
				System.out.println("The test inflicts it's difficulty upon you!!");
				kb.nextLine();
				boss.enemyAttack(Player.party.get(0));
				kb.nextLine();
				fight = checkFailOrPass(Player.party.get(0), boss);
			}
		} while (fight == true);

		checkWinner(Player.party.get(0), boss);
	}

	/**
	 * Check to see who the "winner of the battle is"
	 * 
	 * @param c
	 *            Character passed in
	 * @param e
	 *            Enemy passed in
	 */
	private void checkWinner(Character c, Enemy e) {
		if (c.health <= 0) {
			System.out.println("You have failed the exam. Prepare better next time.");
		} else {
			System.out.println("You have passed the exam. Well done.");
		}

	}

	/**
	 * Check to see if anyone is defeated yet.
	 * 
	 * @param c
	 *            Character passed in
	 * @param e
	 *            Enemy passed in
	 * @return boolean if the fight needs to be stopped.
	 */
	public boolean checkFailOrPass(Character c, Enemy e) {
		if (c.health <= 0) {
			return false;
		} else if (e.health <= 0) {
			return false;
		} else {
			return true;
		}
	}
}
