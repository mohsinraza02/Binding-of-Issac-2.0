package codes;

import java.util.Scanner;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Battle extends Scene {

	
	Enemy enemy;
	//= new Enemy("CPSC Test", 10, 10, 10, 10, true, 10);
	Player player;
	
	private static Pane root;
	private final int WIDTH = 1200;
	private final int HEIGHT = 800;

	// Texts
	private Text enemyHealth = new Text("");
	private Text playerHealth = new Text("");

	private Text text1 = new Text(
			"This is the current text in the box. This will be universal so we can change it easier.");
	private Text text2 = new Text();

	private Text bossPrompt = new Text(
			"We'll use this universal text for when the player does something to the boss, to prompt the player.");

	// HUD stuff
	HUDobjects textBox = new HUDobjects(0, 600, 1200, 200, "textbox.PNG"); // Textbox
	HUDobjects enemyText = new HUDobjects(375, 25, 400, 200, "textbox.PNG"); // Textbox
	HUDobjects playerBattle = new HUDobjects(0, 300, 600, 400, "PC-battle.PNG"); // Player sprite in battle
	HUDobjects testBoss = new HUDobjects(750, 25, 350, 400, "monsterboi2.png");
	HUDobjects background = new HUDobjects(0, 0, 1200, 800, "background.png");
	HUDobjects platform = new HUDobjects(730, 355, 400, 130, "label-small.png");
	HUDobjects platform2 = new HUDobjects(150, 500, 450, 200, "label-small.png");
	HUDobjects playerText = new HUDobjects(0, 242, 470, 100, "textbox.PNG");

	// Event Listeners
	private EventHandler<KeyEvent> mainPrompt;
	private EventHandler<KeyEvent> skillPrompt;
	private EventHandler<KeyEvent> continuePrompt;
	private EventHandler<KeyEvent> counterPrompt;
	

	/**
	 * NOT FINISHED!!! IT'LL BREAK!!! Just saying. - Josh
	 */
	public Battle(Player player) {
		super(root = new Pane());
		this.player = player;
		root = initialContent();
		
		//Initialize the enemy here because we need the player to be passed in first.
		enemy = new Enemy("CPSC Test",(int)(25 * player.getAttack()) , (int) (1.5 * player.getStat(1)), (int)(0.5 * player.getAttack()), 35, true, 10);
		text1.setText(enemy.getName() + " has appeared. Good luck.");
		enemyHealth.setText("Boss Test\nHealth: " + enemy.getHealth());
		
		mainPrompt = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case A:
					attack();	
					changeListener(counterPrompt, mainPrompt);
					break;
				case B:
					skill();
					changeListener(skillPrompt, mainPrompt);
					break;
				case C:
					item();
					changeListener(counterPrompt, mainPrompt);
					break;
				default:
					break;
				}
			}
		};

		// SET UP THE SKILL PROMPT LISTENER
		skillPrompt = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case A:
					breathe();
					changeListener(counterPrompt, skillPrompt);
					break;
				case B:
					stretch();
					changeListener(counterPrompt, skillPrompt);
					break;
				case C:
					cheat();
					changeListener(counterPrompt, skillPrompt);
					break;
				case D:
					cry();
					changeListener(counterPrompt, skillPrompt);
					break;

				default:
					break;
				}
			}
		};
		
		counterPrompt = new EventHandler<KeyEvent>() {
			// Note: when a text shows up and the user needs to hit enter to continue
			// remove the current listener and add this one

			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case ENTER:
					enemyAttack();
					changeListener(continuePrompt, counterPrompt);
					break;
				default:
					break;
				}
			}
		};
		
		//Set up the items prompt listener
		/*itemPrompt = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case A:
					
				default:
					break;
				}
			}
		};*/

		// SET UP THE CONTINUE PROMPT
		continuePrompt = new EventHandler<KeyEvent>() {
			// Note: when a text shows up and the user needs to hit enter to continue
			// remove the current listener and add this one

			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case ENTER:
					changeSkippableText();
					removeBossP();
					break;
				default:
					break;
				}
			}
		};

		this.addEventHandler(KeyEvent.KEY_PRESSED, continuePrompt);

	}

	private void updatePlayerHealth() {
		root.getChildren().remove(playerHealth);
		playerHealth.setText("Your Health: " + player.getHealth());
		root.getChildren().add(playerHealth);
	}
	
	/**
	 * Change the listeners
	 */
	private void changeListener(EventHandler<KeyEvent> change, EventHandler<KeyEvent> old) {
		this.removeEventHandler(KeyEvent.KEY_PRESSED, old);
		this.addEventHandler(KeyEvent.KEY_PRESSED, change);
	}

	/**
	 * Alternates between two skippable texts
	 */
	private void changeSkippableText() {
		// if text 1 is showing, remove it and show text2
		if (root.getChildren().contains(text1)) {
			
			if(player.getHealth() <= 0 || enemy.getHealth() <= 0) {
				
			}

			root.getChildren().remove(text1);
			root.getChildren().add(text2);

			changeListener(mainPrompt, continuePrompt);
			

			// or the other way around
		} else if (root.getChildren().contains(text2)) {

			if(player.getHealth() <= 0 || enemy.getHealth() <= 0) {
				
			}
			
			root.getChildren().remove(text2);
			root.getChildren().add(text1);

			changeListener(mainPrompt, continuePrompt);
		}
	}
	
	/**
	 * Remove text in the box.
	 */
	private void remove() {
		root.getChildren().remove(text1);
		root.getChildren().remove(text2);
	}
	
	private void bossHealth() {
		root.getChildren().remove(enemyHealth);
		enemyHealth.setText("Boss Test\nHealth: " + enemy.getHealth());
		root.getChildren().add(enemyHealth);
		}
	
	
	
	/**
	 * Remove the boss prompt
	 */
	private void removeBossP() {
		root.getChildren().remove(bossPrompt);
	}

	/**
	 * When the player attacks - LOGIC DONE
	 */
	private void attack() {
		
		
		player.attack(enemy);
		
		//enemy.setHealth(enemy.getHealth() - player.getAttack());
		remove();

		// root.getChildren().remove(current);
		text1.setText("You attack the test. You have dealt\n" + player.getAttack() + " damage.");
		
		bossHealth();
		
		root.getChildren().add(text1);

		text1.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 42.00));
		text1.setX(50);
		text1.setY(665);
		text1.setFill(Color.BLACK);

	}

	/**
	 * Text prompting the player to use a skill - NOT DONE!! DO THIS!!! TODO
	 */
	private void skill() {
		remove();
		text1.setText("What skill do you want to use?\n(a) Breathe (b) Stretch\n(c) Cheat (d) Cry");
		root.getChildren().add(text1);
		text1.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 42.00));
		text1.setX(50);
		text1.setY(665);
		text1.setFill(Color.BLACK);
		
		String input = "c";
		boolean pick = false; 
		boolean valid = false;
		
		if (input.toLowerCase().equals("a")) {
			player.deepBreath(enemy);
			pick = true;
			valid = true;
		} else if(input.toLowerCase().equals("b")) {
			player.stretch();
			pick = true;
			valid = true;

		}else if (input.toLowerCase().equals("c")){
			player.cheat(enemy);
			pick = true;
			valid = true;

		}else if (input.toLowerCase().equals("d")){
			player.cry(enemy);
			pick = true;
			valid = true;
		}
	}
	
	private void item() {
		remove();
		
		if (player.getInventory().size() != 0) {
			player.setHealth(player.getHealth()+ Collectable.getValue());
			if (player.getHealth() > 100) {
				player.setHealth(100.0);
			}
			updatePlayerHealth();
			text1.setText("You used "+ player.getLastItem() +" to restore your health.");			
			bossHealth();
		} else {
			text1.setText("You open your backpack, but it is empty.");
		}
		
		root.getChildren().add(text1);

		text1.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 42.00));
		text1.setX(50);
		text1.setY(665);
		text1.setFill(Color.BLACK);
	}

	/**
	 * Use crying skill - LOGIC DONE
	 */
	private void cry() {

		remove();
		text1.setText(
				"You cry...a lot, everyone around starts looking\nat you, but luckily there is a kind soul who \nslides his scantron to the side of his desk \nfor you to look at.");
		bossPrompt.setText("The test's attack was\nlowered due to your tears");
		root.getChildren().add(text1);
		root.getChildren().add(bossPrompt);

		text1.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 38.00));
		text1.setX(50);
		text1.setY(655);
		text1.setFill(Color.BLACK);

		bossPrompt.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 22.00));
		bossPrompt.setX(410);
		bossPrompt.setY(170);
		bossPrompt.setFill(Color.RED);
		
		int damageMod = (int)(player.getStat(1) * 0.20);
		enemy.setAttack(enemy.getAttack() - damageMod);
		if (damageMod == 0) {
			enemy.setAttack(enemy.getAttack() - 1);
		}
		else{

		}
	}

	/**
	 * Player uses stretch skill - LOGIC DONE
	 */
	private void stretch() {
		int healMod = (int) ((player.getStat(1) * 0.25) + (player.getHealthCap() * 0.15));
		if ((player.getHealth() + healMod) > player.getHealthCap()) {
			player.setHealth(player.getHealthCap());
		} else {
			player.setHealth(player.getHealth() + healMod);
		}
		
		remove();
		text1.setText(
				"You stretch a little, telling yourself\nit'll be okay. Hopefully.\nYou heal yourself for" + healMod + " health.");
		root.getChildren().add(text1);

		text1.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 38.00));
		text1.setX(50);
		text1.setY(655);
		text1.setFill(Color.BLACK);
		
	}

	/**
	 * Player uses cheat skill - LOGIC DONE
	 */
	private void cheat() {
		
		int attackMod = (int)(player.getAttack() * 2.5);
		enemy.setHealth(enemy.getHealth() - attackMod);
		bossHealth();
		
		remove();
		text1 = new Text(
				"You use the eagle vision technique,\nand look around with amazing accuracy.\nBOOM! Critical Strike!!\nYou have dealt " + attackMod + " damage to the test.");
		root.getChildren().add(text1);

		text1.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 38.00));
		text1.setX(50);
		text1.setY(655);
		text1.setFill(Color.BLACK);
	}

	/**
	 * Player uses breathe skill - LOGIC DONE
	 */
	private void breathe() {
		
		Scanner skip = new Scanner(System.in);
		int defenseMod = (int) (player.getStat(1) * 0.20);
		//System.out.println(defenseMod);
		enemy.setDefense(enemy.getDefense() - defenseMod);
		if (defenseMod == 0) {
			enemy.setDefense(enemy.getDefense() - 1);
		} else {
		}
		
		root.getChildren().remove(text1);
		bossPrompt = new Text("The test's defense was\nlowered.");
		text1 = new Text("You take a deep breath, clearing your mind.");
		root.getChildren().add(text1);
		root.getChildren().add(bossPrompt);

		text1.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 38.00));
		text1.setX(50);
		text1.setY(655);
		text1.setFill(Color.BLACK);

		bossPrompt.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 22.00));
		bossPrompt.setX(410);
		bossPrompt.setY(170);
		bossPrompt.setFill(Color.RED);
	}

	/**
	 * When enemy attacks player - LOGIC DONE BUT ERROR HERE!!!
	 */
	private void enemyAttack() {
		
		enemy.enemyAttack(player);
		remove();
		text1 = new Text("The boss inflicts its difficulty on you!\nYou take " + enemy.getAttack() + " damage.");
		root.getChildren().add(text1);
		updatePlayerHealth();

		text1.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 38.00));
		text1.setX(50);
		text1.setY(655);
		text1.setFill(Color.BLACK);
		
	}

	/**
	 * Boss uses buff defense skill - LOGIC DONE
	 */
	private void hardQ() {
		
		int defenseMod = (int) (enemy.getMaxDefense() * 0.50);
		enemy.setDefense(defenseMod + enemy.getDefense());
		
		remove();
		bossPrompt = new Text("The test gained defense.");
		text1 = new Text(
				"The test seems to have a question you haven't\nstudied for!\nThe test seems more unanswerable now.");
		root.getChildren().add(text1);
		root.getChildren().add(bossPrompt);

		text1.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 38.00));
		text1.setX(50);
		text1.setY(655);
		text1.setFill(Color.BLACK);

		bossPrompt.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 22.00));
		bossPrompt.setX(410);
		bossPrompt.setY(170);
		bossPrompt.setFill(Color.RED);
	}

	/**
	 * Boss hurts the player big time. - LOGIC DONE
	 */
	private void bigL() {
		
		enemy.setsCounter(0);
		enemy.setHeavyAttack(false);
		int attackMod = (int) ((enemy.getMaxAttack() * 0.50) + enemy.getMaxAttack());
		player.setHealth(player.getHealth() - attackMod);
		
		remove();

		text1.setText(
				"The test suddenly reveals a hidden backside!!\nIT UNLEASHES IT'S WRITTEN RESPONSE ON YOU!!\nYou take " + attackMod + " damage.");
		root.getChildren().add(text1);
		root.getChildren().add(bossPrompt);

		text1.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 38.00));
		text1.setX(50);
		text1.setY(655);
		text1.setFill(Color.BLACK);
	}

	/**
	 * Boss raises own attack - LOGIC DONE
	 */
	private void intimidate() {
		
		int attackMod = (int)((enemy.getAttack() * 0.25) + enemy.getAttack());
		if (attackMod >= enemy.getMaxAttack()) {
			attackMod = enemy.getMaxAttack();
		}
		enemy.setAttack(attackMod);
		
		remove();

		bossPrompt.setText("Test attack has increased.");
		text1.setText(
				"The test's next page seems to have a lot of\nquestions and reading...\nThe test seems more frightening.\nIt will hit harder now.");
		root.getChildren().add(text1);
		root.getChildren().add(bossPrompt);

		text1.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 38.00));
		text1.setX(50);
		text1.setY(655);
		text1.setFill(Color.BLACK);

		bossPrompt.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 22.00));
		bossPrompt.setX(410);
		bossPrompt.setY(170);
		bossPrompt.setFill(Color.RED);
	}

	/**
	 * This adds all the stuff to the root when the battle starts. You don't really
	 * need this but it's good to have so everything isn't in the constructor.
	 * UPDATE: I removed this method in my gameScreen cause it only needs 1
	 * constructor. therefore theres no point of using this.
	 * 
	 * @return the root after adding some stuff in it
	 */
	private Pane initialContent() {
		root.setPrefSize(WIDTH, HEIGHT); // makes the screen 1200x800px
		
		playerHealth.setText("Your Health: " + player.getHealth());
		
		player.updateAttack();
		enemyHealth.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 42.00));
		enemyHealth.setX(410);
		enemyHealth.setY(80);
		enemyHealth.setFill(Color.RED);
		
		playerHealth.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 42.00));
		playerHealth.setX(10);
		playerHealth.setY(300);
		playerHealth.setFill(Color.RED);

		//text1.setText(enemy.getName() + " has appeared. Good luck.");

		text1.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 48.00));
		text1.setX(50);
		text1.setY(665);
		text1.setFill(Color.BLACK);

		root.getChildren().add(background);
		root.getChildren().add(platform);
		root.getChildren().add(platform2);
		root.getChildren().add(playerBattle);
		root.getChildren().add(textBox);
		root.getChildren().add(text1);
		root.getChildren().add(testBoss);
		root.getChildren().add(enemyText);
		root.getChildren().add(enemyHealth);
		root.getChildren().add(playerText);
		root.getChildren().add(playerHealth);

		text2.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 42.00));
		text2.setX(50);
		text2.setY(665);
		text2.setFill(Color.BLACK);
		text2.setText("What would you like to do?\n(a) Attack		(c) Items\n(b) Skills");

		// text1.setText(enemy.getName() + " has appeared. Good luck.");

		/*
		 * To add objects, use root.getChildren().add(Node n)
		 * ".remove(Node n) to remove it" To move objects, use n.setTranslateX(int x) or
		 * n.setTranslateY(int y) To get their position in the map, use n.getTranslate X
		 * or Y ()
		 * 
		 * WHen creating an object, you can use the Entities class. Give it a look to
		 * see how it works.
		 */

		// I created and started the animation here. but you can move it somewhere else
		// This pretty much works the same way as a javascript timer
		// the method "handle" gets called every frame of the animation timer
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				// handle can call any # of methods. but for now it only calls update
				// i used the "update" method to store all of the basic animations
				// and i created separate functions for other animations
				update();
			}

		};
		timer.start();
		return root;
	}

	/**
	 * put all the basic animations here
	 */
	private void update() {

	}

	/**
	 * This changes the background of the battle screen to the image passed in the
	 * parameter note that you dont need to make an image object, you just pass the
	 * filename of the background image.
	 * 
	 * @param imageName
	 */
	private void changeBackground(String imageName) {
		imageName = "/src/sprites/" + imageName + ".png";
		root.setStyle("-fx-background-image: url(" + imageName + "); " + "-fx-background-size: cover;");
	}
	
	/**
	 * This method shows a skippable text. It uses text1 or text2 depending on what
	 * is currently being show in the textbox EDIT: realized that this isnt what
	 * enter class needed, so i commented it out. sry josh pls ignore this
	 * 
	 * @param textToShow
	 *            - the text that will be shown in the textbox
	 */
	// private void printContinueText(String textToShow) {
	//
	// // if text1 is currently showing, remove text1 and use text2 to show the text
	// if (root.getChildren().contains(text1)) {
	// text2.setText(textToShow);
	//
	// root.getChildren().remove(text1);
	// root.getChildren().add(text2);
	//
	// // same thing but works the other way around
	// } else if (root.getChildren().contains(text2)) {
	// text1.setText(textToShow);
	//
	// root.getChildren().remove(text2);
	// root.getChildren().add(text1);
	//
	// // if nothing is showing (i.e mainPrompt is showing). use Text1
	// } else {
	// text1.setText(textToShow);
	//
	// root.getChildren().add(text1);
	// }
	// }


}
