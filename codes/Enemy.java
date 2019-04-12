package codes;
import java.util.Scanner;

public class Enemy {
	private String name;
	private double health;
	private int attack, defense, maxAttack, maxDefense, maxHealth, sCounter;
	boolean heavyAttack;
	
	/**
	 * The method getName returns the instance variable name of type String.
	 * 
	 * @return instance variable name
	 */
	public String getName() {
		return name;
	}
	/**
	 * The method setName takes the parameter name of type String
	 * and sets it to instance variable name.
	 *
	 * @param name  set to instance variable name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * The method getHealth returns the instance variable health of type double.
	 * 
	 * @return instance variable health
	 */
	public double getHealth() {
		return this.health;
	}
	/**
	 * The method setHealth takes the parameter d of type double
	 * and sets it to instance variable health.
	 *
	 * @param d  set to instance variable health.
	 */
	public void setHealth(double d) {
		this.health = d;
	}
	/**
	 * The method getAttack returns the instance variable attack of type int.
	 * 
	 * @return instance variable attack
	 */
	public int getAttack() {
		return attack;
	}
	/**
	 * The method setAttack takes the parameter attack of type int
	 * and sets it to instance variable attack.
	 *
	 * @param attack set to instance variable attack.
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}
	/**
	 * The method setDefense takes the parameter d of type int
	 * and sets it to instance variable defense.
	 *
	 * @param d set to instance variable defense.
	 */
	public void setDefense(int d) {
		this.defense = d;
	}
	/**
	 * The method getDefense returns the instance variable defense of type int.
	 * 
	 * @return instance variable defense
	 */
	public int getDefense() {
		return this.defense;
	}
	/**
	 * The method getMaxAttack returns the instance variable maxAttack of type int.
	 * 
	 * @return instance variable maxAttack
	 */
	public int getMaxAttack() {
		return maxAttack;
	}
	/**
	 * The method setMaxAttack takes the parameter maxAttack of type int
	 * and sets it to instance variable maxAttack.
	 *
	 * @param maxAttack set to instance variable maxAttack.
	 */
	public void setMaxAttack(int maxAttack) {
		this.maxAttack = maxAttack;
	}
	/**
	 * The method getMaxDefense returns the instance variable maxDefense of type int.
	 * 
	 * @return instance variable maxDefense
	 */
	public int getMaxDefense() {
		return maxDefense;
	}
	/**
	 * The method setMaxDefense takes the parameter maxDefense of type int
	 * and sets it to instance variable maxDefense.
	 *
	 * @param maxDefense set to instance variable maxDefense.
	 */
	public void setMaxDefense(int maxDefense) {
		this.maxDefense = maxDefense;
	}
	/**
	 * The method getMaxHealth returns the instance variable maxHealth of type int.
	 * 
	 * @return instance variable maxHealth
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	/**
	 * The method setMaxHealth takes the parameter maxHealth of type int
	 * and sets it to instance variable maxHealth.
	 *
	 * @param maxHealth set to instance variable maxHealth.
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	/**
	 * The method getsCounter returns the instance variable sCounter of type int.
	 * 
	 * @return instance variable sCounter
	 */
	public int getsCounter() {
		return sCounter;
	}
	/**
	 * The method setsCounter takes the parameter sCounter of type int
	 * and sets it to instance variable sCounter.
	 *
	 * @param sCounter set to instance variable sCounter.
	 */
	public void setsCounter(int sCounter) {
		this.sCounter = sCounter;
	}
	/**
	 * The method getHeavyAttack returns the instance variable heavyAttack of type boolean.
	 * 
	 * @return instance variable heavyAttack
	 */
	public boolean getHeavyAttack() {
		return this.heavyAttack;
	}
	/**
	 * The method setHeavyAttack takes the parameter set of type boolean
	 * and sets it to instance variable heavyAttack.
	 *
	 * @param set set to instance variable heavyAttack.
	 */
	public void setHeavyAttack(boolean set) {
		this.heavyAttack = set;
	}
	
	/**
         * This constructor takes 7 parameter values and sets them to the instance variables 
	 * name, health, attack, defense, maxAttack, heavyAttack and sCounter.
	 * 
	 * @param name // Name of enemy
	 * 
	 * @param health // Health of enemy
	 *
	 * @param attack //Attack of enemy
	 *
	 * @param defense //Defense of enemy
	 *
	 * @param maxAttack //Max attack of enemy
	 *
	 * @param heavyAttack //Boolean to see if the enemy has their special attack up or not
	 *
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
	 * The method getRandomEnemy takes the parameter player of type Player and 
	 * returns a randomly generated 'Enemy' a.k.a. the test for the Boss Battle.
	 * 
	 * @return a randomly generated Enemy 
	 *
	 * @param player
	 */
	
	public Enemy getRandomEnemy(Player player) {
		int n = (int) (Math.random() * 3 + 1);
		
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
	 * The method enemyAttack takes the parameter player of type Player. 
	 * In this method the Enemy attacks and inflicts damage on the player.
	 * 
	 * @param player
	 */
	public void enemyAttack(Player player) {
		//Scanner skip = new Scanner(System.in);
		// defense reduces the boss's base damage
		System.out.println("The test inflicts its difficulty upon you!!");
		//skip.nextLine();
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
	 * In the method hardQ the Enemy a.k.a. the test raises it's defense during the battle making it harder
	 * for the player to inflict damage.
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
	 * The method bigL takes the parameter player of type Player. 
	 * In this method the Enemy attacks and inflicts a considerable amount of damage on the player.
	 * 
	 * @param player
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
	 * In the method intimidate the Enemy a.k.a. the test raises it's attack during the battle making it 
	 * more difficult for the player to win.
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
