import java.util.Scanner;

public class Battle {
	
	/**
	 * The fight method. As of now, it only does turn by turn attacks.
	 * @param player // The player passed in.
	 */
	public static void start(Player player) {

		Enemy boss = Enemy.getRandomEnemy(player);
		//player.updateAttack();
		
		boolean playerEnd = false;
		boolean fight = true;
		Scanner kb = new Scanner(System.in);
		
		System.out.println("It is time to take the test. Hit any key to continue");
		kb.nextLine();

		do {
			playerEnd = false;
			do {
				decision(player,boss);
				kb.nextLine();
				playerEnd = true;
				fight = checkEnd(player, boss);
			} while (playerEnd == false);
			if (boss.getHealth() >= 1) {
				enemyTurn(player, boss);
				kb.nextLine();
				fight = checkEnd(player, boss);
			}
		} while (fight == true);
		checkWinner(player, boss);
	}
	
	private static void printSkill(Player p) {
		boolean ready;
		int counter = 3 - p.getSkillP();;
		
		if(p.getSkillP() > 3) {
			ready = true;
			System.out.println("Skill: Ready!");
		} else if (counter != 0) {
			ready = false;
			System.out.println("Skill: Not ready. (" + counter + " turn(s) until ready.)");
		} else if (counter == 0) {
			System.out.println("Skill: Ready next turn.");
		}
		
		
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
	public static boolean checkEnd(Player p, Enemy e) {
		if (p.getHealth() <= 0) {
			return false;
		} else if (e.getHealth() <= 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Get the player's decision for what to do.
	 */
	public static void decision(Player player, Enemy boss) {
		boolean valid = false;
		Scanner kb = new Scanner(System.in);
		boolean skill;
		
		player.setSkillP(player.getSkillP() + 1);
		if(player.getSkillP() <= 3) {
			skill = false;
		} else {
			skill = true;
		}
		
		do {
			System.out.println("You have " + player.getHealth() + " health, " + player.getAttack() + " attack, and " + player.getStat(1) + " defense.");
			printSkill(player);
			System.out.println("The test has " + boss.getHealth() + " health.");
			System.out.println("---------------------------------");
			System.out.println("What will you do?\n(a) Attack/Answer Question\n(b) Use Skill\n(c) Use Item");
			String decision = kb.nextLine();
			if (decision.toLowerCase().equals("a")) {
				player.attack(boss);
				valid = true;
			} else if (decision.toLowerCase().equals("b")) {
				if (skill == true) {
					//System.out.println("Choice two!");
					valid = player.skill(boss);
					if (valid == true) {
						player.setSkillP(0);
					}
					//player.deepBreath(boss);
				} else {
					System.out.println("Your skills are on cooldown!");
					kb.nextLine();
				}
			} else if (decision.toLowerCase().equals("c")) {
				//System.out.println("What item do you want to use");
				//player.checkItem()
				valid = true;
			}
		} while(valid == false);
	}
	
	/**
	 * The enemy's turn, it will act due to it's status.
	 * @param player
	 * @param boss
	 */
	public static void enemyTurn(Player player, Enemy boss) {
		int counter = boss.getsCounter();
		if (counter >= 3) {
			boss.setHeavyAttack(true);
		} else {
			boss.setsCounter(boss.getsCounter() + 1);
		}
		
		int defenseMod = boss.getMaxDefense() / 2;
		int attackMod = boss.getMaxAttack() / 2;
		
		int lowHealth = (int) (boss.getMaxHealth() * 0.50);
		int rand = (int) (Math.random() * 5 + 1); //Random check to see if the boss will increase attack.
		
		if (boss.getDefense() <= defenseMod) {
			boss.hardQ();
		} else if (boss.getAttack() <= attackMod) {
			boss.intimidate();
		} else if(boss.getHealth() <= lowHealth && boss.getHeavyAttack() == true) {
			boss.bigL(player);
		} else if (rand == 1) {
			boss.intimidate();
		} else {
			boss.enemyAttack(player);
		}
	}
	
}