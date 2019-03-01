
import java.util.Scanner;

public class Battle {
	
	/**
	 * The fight method. As of now, it only does turn by turn attacks.
	 * @param player // The player passed in.
	 */
	public static void start(Player player) {

		Enemy boss = Enemy.getRandomEnemy();
		player.setAttack(player.updateAttack());
		
		boolean playerEnd = false;
		boolean fight = true;
		Scanner kb = new Scanner(System.in);
		
		System.out.println("It is time to take the test. Hit any key to continue");
		kb.nextLine();

		do {
			playerEnd = false;
			do {
				System.out.println("You have " + player.getHealth() + " health, " + player.getAttack() + " attack, and " + player.getStat(1) + " defense.");
				System.out.println("The test has " + boss.getHealth() + " health.");
				System.out.println("---------------------------------");
				System.out.println("What will you do?\n(a) Attack/Answer Question");
				String decision = kb.nextLine();
				if (decision.equals("a")) { // Then attack
					player.attack(boss);
					kb.nextLine();
					playerEnd = true;
					fight = checkFailOrPass(player, boss);
				}
			} while (playerEnd == false);
			if (boss.getHealth() >= 1) {
				System.out.println("The test inflicts its difficulty upon you.");
				kb.nextLine();
				boss.enemyAttack(player);
				kb.nextLine();
				fight = checkFailOrPass(player, boss);
			}
		} while (fight == true);
		checkWinner(player, boss);
	}
	
	/**
	 * Check if the player won or the enemy won.
	 * @param p //Player passed in
	 * @param e //Enemy passed in
	 */
	public static void checkWinner(Player p, Enemy e) {
		if (p.getHealth() <= 0) {
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
	public static boolean checkFailOrPass(Player p, Enemy e) {
		if (p.getHealth() <= 0) {
			return false;
		} else if (e.getHealth() <= 0) {
			return false;
		} else {
			return true;
		}
	}
}
