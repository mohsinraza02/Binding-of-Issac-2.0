import javax.xml.stream.events.EndElement;
import java.util.*;
import java.util.Random;
import java.util.Scanner;

public class Player {
	private int[] stats = new int[2];
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private int health = 100;// default health
	private int attack = 50;// default attack damage
	private int currentRoom;

	public Player(int health, int attack) {
		this.health = health;
		this.attack = attack;
	}

	public int getStat(int index) {
		int[] copyStats = this.stats.clone();
		return copyStats[index];
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health += health;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	/*
	 * Generate 5 random stats for each category: Strength, Defense, Attack, Luck,
	 * Speed
	 */
	public void generateStats() {
		Random randStat = new Random();
		for (int i = 0; i < 2; i++) {
			int randomStat = randStat.nextInt(10);
			randomStat += 1;
			stats[i] = randomStat;

		}
	}

	public void printStats(int[] stats) {
		System.out.println("Attack:" + stats[0]);
		System.out.println("Defense:" + stats[1]);
		// System.out.println("Speed:"+stats[2]);
		// System.out.println("Luck:"+stats[3]);
		// System.out.println("Strength:"+stats[4]);
	}

	public void addItemToInventory(Item item) {
		System.out.println("you picked up: " + item.getName());
		inventory.add(item);
		stats[item.getType()] += item.getValue();

	}

	// public void addItemStatToPlayer(Item itemValue){

	// }

	// public void updateHealth() {
	// // Adds health depending on the stats that you get in the beginning
	// int newHealth = health + (stats[1] * 10);
	// }

	public int updateAttack() {
		// Adds damage depending on the stats that you get in the beginning
		// int newAttack = attack + (stats[0]);
		return attack + (stats[0]);
	}

	public void attack(Enemy enemy) {
		// enemy.setHealth(getHealth() - );
		enemy.setHealth(enemy.getHealth() - updateAttack());
		System.out.println("You did " + attack + " damage to the " + enemy.getName());
		System.out.println("The " + enemy.getName() + " has " + enemy.getHealth() + " health left.");
	}

	public int getCurrentRoom() {
		return this.currentRoom;
	}

	public void setCurrentRoom(int currentRoom) {
		this.currentRoom = currentRoom;
	}

}