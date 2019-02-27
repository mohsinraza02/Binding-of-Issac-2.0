package cpsc233;

import java.util.Scanner;

public class Battle {
	
	public Scanner kb = new Scanner(System.in);
	
	/**
	 * The fight method. As of now, it only does turn by turn attacks.
	 * @param player // The player passed in.
	 */
	public void start(Player player) {

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
					Player.attack(boss);
					kb.nextLine();
					playerEnd = true;
					fight = checkFailOrPass(player, boss);
				}
			} while (playerEnd == false);
			if (boss.health >= 1) {
				System.out.println("The test inflicts it's difficulty upon you.");
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
	public void checkWinner(Player p, Enemy e) {
		if (p.health <= 0) {
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
	public boolean checkFailOrPass(Player p, Enemy e) {
		if (p.health <= 0) {
			return false;
		} else if (e.health <= 0) {
			return false;
		} else {
			return true;
		}
	}
}
