
public class Enemy {
	private String name;
	private int health;
	private int attack;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return this.health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public Enemy(String name, int health, int attack) {
		this.name = name;
		this.health = health;
		this.attack = attack;
	}
	
	public static Enemy getRandomEnemy() {
		int n = (int) (Math.random() * 3 + 1);
		if (n == 1) {
			return new Enemy("Math Test", 50, 4);
		} else if (n == 2) {
			return new Enemy("CPSC Test", 45, 5);
		} else if (n == 3) {
			return new Enemy("Science Test", 65, 2);
		} else {
			return new Enemy("Neverpick", 999,999);
		}
	}
	
	public void enemyAttack(Player player) {
		player.setHealth(player.getHealth() - (this.attack - player.getStat(1)));
		System.out.println("The test attacks your sanity for " + this.attack + " damage!");
		System.out.println("You have " + player.getHealth() + " sanity left.");
	}
	
}
