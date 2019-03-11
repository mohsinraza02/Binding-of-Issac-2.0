import java.util.Scanner;
import javax.xml.stream.events.EndElement;
import java.util.*;
import java.util.Random;
import java.util.Scanner;

public class Player {
	private int[] stats = new int[4];
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private double health = 100.0;// default health
	private double attack = 50.0;// default attack damage

	public Player(double health, double attack) {
		this.health = health;
		this.attack = attack;
	}

	public int getStat(int index) {
		return this.stats[index];
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double d) {
		this.health = d;
	}

	public double getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	/*
	 * Generate 4 random stats for each category: Strength, Defense, Attack, Luck,
	 * Speed
	 * The sum of the stats that is possible is 30 because it would be too unbalanced to get perfect
	 * rolls. The sum of the stats will always be 30 as well, so when you start the game it will always
	 * be perfectly balanced.
	 * Added a reroll method (credit to Josh)
	 */
	public int[] generateStats() {
		
        Random randStat = new Random();
		int maxStat = 30;
		Scanner kb = new Scanner(System.in);
		
		boolean reroll;
		do {
		reroll = false;
		for (int i = 0; i < 4; i++) {
			int randomStat = randStat.nextInt(10);
			randomStat += 1;
			stats[i] = randomStat;
			maxStat -= randomStat;
			}
		System.out.println("Attack:" + stats[0]);
		System.out.println("Defense:" + stats[1]);
		System.out.println("Speed:"+stats[2]);
		System.out.println("Luck:"+stats[3]);
		System.out.println("Do you want to reroll your stats? (y/n)");
		String input = kb.nextLine();
		if (input.equals("y")) {
			reroll = true;
		}
		
		if(maxStat != 0) {
			maxStat = 30;
		}
		
		}while(maxStat != 0 && reroll == true);
		
	
	return stats;
	}

		
	
	
	public void addStat(Item item) {
		int index = item.getType();
		double value = item.getValue();
		stats[index] += value;
	}

	public void printStats() {
		System.out.println("Attack:" + this.stats[0]);
		System.out.println("Defense:" + this.stats[1]);
		System.out.println("Speed:"+this.stats[2]);
		System.out.println("Luck:"+this.stats[3]);
		
	}

	public void addItemToInventory(Item item) {
		System.out.println("you picked up: " + item.getName());
		inventory.add(item);
		stats[item.getType()] += item.getValue();

	}


	// public void updateHealth() {
	// // Adds health depending on the stats that you get in the beginning
	// int newHealth = health + (stats[1] * 10);
	// }

	public void updateAttack() {
		// Adds damage depending on the stats that you get in the beginning
		// int newAttack = attack + (stats[0]);
		this.attack += (stats[0]);
	}

	public void attack(Enemy enemy) {
		// enemy.setHealth(getHealth() - );
		enemy.setHealth(enemy.getHealth() - this.attack);
		System.out.println("You did " + attack + " damage to the " + enemy.getName());
		System.out.println("The " + enemy.getName() + " has " + enemy.getHealth() + " health left.");
	}

}