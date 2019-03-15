package cpsc233;
import java.util.Scanner;

public class Enemy {
	private String name;
	private double health;
	private int attack, defense, maxAttack, maxDefense, maxHealth, sCounter;
	boolean heavyAttack;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHealth() {
		return this.health;
	}

	public void setHealth(double d) {
		this.health = d;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public void setDefense(int d) {
		this.defense = d;
	}
	
	public int getDefense() {
		return this.defense;
	}

	public int getMaxAttack() {
		return maxAttack;
	}
	
	public void setMaxAttack(int maxAttack) {
		this.maxAttack = maxAttack;
	}
	
	public int getMaxDefense() {
		return maxDefense;
	}
	
	public void setMaxDefense(int maxDefense) {
		this.maxDefense = maxDefense;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public int getsCounter() {
		return sCounter;
	}
	
	public void setsCounter(int sCounter) {
		this.sCounter = sCounter;
	}
	
	public boolean getHeavyAttack() {
		return this.heavyAttack;
	}
	
	public void setHeavyAttack(boolean set) {
		this.heavyAttack = set;
	}
	
	/**
	 * Enemy constructor
	 * @param name // Name of enemy
	 * @param health // Health of eneny
	 * @param attack //Attack of enemy
	 * @param defense //Defense of enemy
	 * @param maxAttack //Max attack of enemy
	 * @param heavyAttack //Boolean to see if the enemy has their special attack up or not
	 * @param sCounter // Special attack counter
	 */
	public Enemy(String name, int health, int attack, int defense, int maxAttack, boolean heavyAttack, int sCounter) {
		this.name = name;
		this.health = health; 
		this.attack = attack; 
		this.defense = defense; 
		this.maxAttack = maxAttack;
		this.maxDefense = defense;
		this.maxHealth = health;
		this.sCounter = sCounter;
	}

	/**
	 * health, attack, defense, maxAttack, heavyAttack, sCounter
	 * @return
	 */
	public static Enemy getRandomEnemy(Player player) {
		int n = (int) (Math.random() * 1 + 1);
		
		int playerAttack = (int) player.getStat(0);
		
		if (n == 1) {
			return new Enemy("Math Test", (13 * playerAttack), 16, 7, 28, true, 3);
		} else if (n == 2) {
			return new Enemy("CPSC Test", (16 * playerAttack), 14, 4, 24, true, 3);
		} else if (n == 3) {
			return new Enemy("Science Test", (int)(18.5 * playerAttack), 9, 10, 22, true, 3);
		} else {
			return new Enemy("Neverpick", 999, 999, 999, 9999, true, 0);
		}
	}

	/**
	 * Basic enemy attack on the player.
	 * @param player
	 */
	public void enemyAttack(Player player) {
		Scanner skip = new Scanner(System.in);
		// defense reduces the boss's base damage
		System.out.println("The test inflicts its difficulty upon you!!");
		skip.nextLine();
		int attackMod = this.attack -= this.attack * (player.getStat(1) / 100);
		
		if (player.getStat(1) == 0) {
			player.setHealth(player.getHealth() - this.attack);
		} else {
			player.setHealth(player.getHealth() - attackMod);
		}
		
		System.out.println("The test attacks your health for " + this.attack + " damage!");
		System.out.println("You have " + player.getHealth() + " health left.");
	}
	
	/**
	 * The test raises it's defense
	 * @param player
	 */
	public void hardQ() {
		Scanner skip = new Scanner(System.in);
		int defenseMod = (int) (this.maxDefense * 0.50);
		System.out.println("The test seems to have a question you haven't studied for!");
		skip.nextLine();
		this.setDefense(defenseMod + this.getDefense());
		System.out.println("The test seems more unanswerable now. It has gained defense.");
		System.out.println("Test defense:" + this.getDefense());
	}
	
	/**
	 * When the player takes the biggest L.
	 */
	public void bigL(Player player) {
		this.setsCounter(0);
		this.setHeavyAttack(false);
		Scanner skip = new Scanner(System.in);
		int attackMod = (int) ((this.maxAttack * 0.50) + this.attack);
		System.out.println("The test suddenly reveals a written response backside you didn't know of!!");
		skip.nextLine();
		System.out.println("IT STARTS TO UNLEASH IT'S WRITTEN RESPONSE ON YOU!!");
		player.setHealth(player.getHealth() - attackMod);
		skip.nextLine();
		System.out.println("You take heavy damage! You start to feel dizzy too!");
		System.out.println("You take " + attackMod + " damage and have " + player.getHealth() + " health.");
	}

	/**
	 * The boss will raise it's attack.
	 */
	public void intimidate() {
		int attackMod = (int)((this.getAttack() * 0.25) + this.getAttack());
		if (attackMod >= this.maxAttack) {
			attackMod = this.maxAttack;
		}
		Scanner skip = new Scanner(System.in);
		System.out.println("The test's next page seems to have a lot of questions and reading...");
		skip.nextLine();
		this.setAttack(attackMod);
		System.out.println("The test seems more frightening. It will hit harder now.");
	}
	



}
