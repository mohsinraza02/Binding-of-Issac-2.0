package cpsc233;

public class Character {
	

	
	int health; // Health points stat
	int attack; // Attack points stat
	
	/**
	 * This method is just a setter for creating a new player.
	 * @param health // Players health
	 * @param attack // Players attack
	 */
	public Character(int health, int attack) {
		this.health = health;
		this.attack = attack;
	}
	
	/**
	 * This method will run when the player attacks an enemy in the Room class.
	 * @param enemy // The enemy passed in is the enemy getting attacked
	 */
	public void attack(Enemy enemy) {
		enemy.health = enemy.health - attack;
		System.out.println("You answered " + attack + " questions on the " + enemy.name);
		System.out.println("The " + enemy.name + " has " + enemy.health + " questions left.");
	}

}
