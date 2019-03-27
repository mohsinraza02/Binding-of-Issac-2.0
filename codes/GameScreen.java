package codes;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameScreen extends Scene {

	private static Pane root;
//	private Main m = new Main();

	private final int WIDTH = 1200;
	private final int HEIGHT = 800;
	private AnimationTimer timer;

	// the following are used for event listeners
	private boolean goLeft = false;
	private boolean goRight = false;
	private boolean goUp = false;
	private boolean goDown = false;
	private int intersectingWith = -1; // -1 means intersecting with nothing
	private double timeLeft = 12;
	private boolean timeUp = false;

	// DECLARING NEW ENTITIES (What is shown in the game)
	private Player player = new Player(100.0, 0.0, 100, 50);
	private HUDobjects statsUndropped = new HUDobjects(0, 0, WIDTH, HEIGHT, "stats1.png");
	private HUDobjects statsDropped = new HUDobjects(0, 0, WIDTH, HEIGHT, "stats2.png");
	private HUDobjects clock = new HUDobjects(900, 120, 220, 120, "clock.png");
	private HUDobjects timesUpWindow = new HUDobjects(600, 400, 600, 400, "timesUpWindow.png");
	
	private ArrayList<Entities> itemsInCurrentRoom = new ArrayList<Entities>();
	
	//TODO: 3x3
	private Rooms[][] room = new Rooms[3][3];

	private int[][] playerCurrentRoom = { { 0 }, { 0 } };

	private Text[] statsText = new Text[4];
	private Text itemName = new Text();
	private Text itemDesc = new Text();
	private Text timeLeftText = new Text(Double.toString(timeLeft));
	// END OF ENTITIES

	/**
	 * Creates a new game screen that shows the main game
	 */
	public GameScreen() {
		super(root = new Pane());

		// Key pressed listener. To bind a new key, add a new case statement
		
		this.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case W:
				goUp = true;
				break;
			case S:
				goDown = true;
				break;
			case A:
				goLeft = true;
				break;
			case D:
				goRight = true;
				break;
			case E:
				interact();
				break;
			case P:
				showStats();
				break;
				
			default:
				break;
			}
		});

		// Key release listener
		this.setOnKeyReleased(e -> {
			switch (e.getCode()) {
			case W:
				goUp = false;
				break;
			case S:
				goDown = false;
				break;
			case A:
				goLeft = false;
				break;
			case D:
				goRight = false;
				break;
				
			default:
				break;
			}
		});
		
		// set the size of our Pane layout
				root.setPrefSize(WIDTH, HEIGHT);
				
				// create new room objects
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						room[i][j] = new Rooms(i, j);
					}
				}

				itemName.setFont(new Font("Comic Sans MS", 18));
				itemDesc.setFont(new Font("Comic Sans MS", 16));
				itemDesc.setFill(Color.RED);
				
				timeLeftText.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 38.00));
				timeLeftText.setX(950);
				timeLeftText.setY(200);
				timeLeftText.setFill(Color.GREEN);
				
				player.generateStats();
				
				for (int i = 0; i < 4; i++) {
					statsText[i] = new Text();

					statsText[i].setFont(new Font("Comic Sans MS", 22));
					statsText[i].setTranslateX(960);
					statsText[i].setTranslateY(110 + (i * 40)); // 30 is the gap between the stats
				}
				statsText = player.updateStatsText(statsText);

				// render the first room at start up
				changeRoom(0, 0);
				// adding the player, and enemies?
				root.getChildren().add(player);

				// add HUDS
				root.getChildren().add(statsUndropped);
				root.getChildren().add(clock);
				root.getChildren().add(timeLeftText);

				// An animation timer is used for smoother movements
				timer = new AnimationTimer() {
					@Override
					public void handle(long now) {
						update();
						checkForItemIntersection();
						// walk(timer)
					}
				};

				timer.start();

	}

	
	/**
	 * Main function for animations. This function gets called every frame of
	 * AnimationTimer so its very fast. To add animations, simply add them here.
	 */
	private void update() {
		// checks if keys are pressed and call these functions
		// without using booleans, only 1 key presses will be accepted
		if (goUp) {
			player.moveUp();
		}
		if (goDown) {
			player.moveDown();
		}
		if (goLeft) {
			player.moveLeft();
		}
		if (goRight) {
			player.moveRight();
		}
		
		// if player room moves to a different room, update playerCurrentRoom and change
		// the background
		if (playerCurrentRoom[0][0] != player.getCurrentRoomX()
				|| playerCurrentRoom[1][0] != player.getCurrentRoomY()) {
			changeRoom(player.getCurrentRoomX(), player.getCurrentRoomY());
		}

	}

	/**
	 * Checks if the player is intersecting with an item. For now, all it does is
	 * when the player is on the item, show the item's name
	 */
	private void checkForItemIntersection() {

		// Will loop depending on how many items there are in the current room
		for (int i = 0; i < itemsInCurrentRoom.size(); i++) {

			// If the player is intersecting with an item...
			if (player.getBoundsInParent().intersects(itemsInCurrentRoom.get(i).getBoundsInParent())) {
				intersectingWith = i; // store the index

				// Show the item name, if its already showing, then ignore.
				if (!root.getChildren().contains(itemName)) {
					itemName.setX(itemsInCurrentRoom.get(i).getTranslateX());
					itemName.setY(itemsInCurrentRoom.get(i).getTranslateY() - 25);
					itemName.setText(itemsInCurrentRoom.get(i).getName());
					
					itemDesc.setX(itemsInCurrentRoom.get(i).getTranslateX());
					itemDesc.setY(itemsInCurrentRoom.get(i).getTranslateY());
					itemDesc.setText(itemsInCurrentRoom.get(i).getDesc());
					
					root.getChildren().add(itemName);
					root.getChildren().add(itemDesc);
				}
				// stop the loop so it doesn't check the rest of the items
				break;
			} else {
				// if there are no item intersection, remove the item name.
				// NOTE: this step will be skipped if there is an intersection
				root.getChildren().remove(itemName);
				root.getChildren().remove(itemDesc);
				intersectingWith = -1;
			}
		}
	}

	/**
	 * This function handles interaction with items/npcs
	 */
	private void interact() {

		// if the player is intersecting with an item, pick up the item
		if (intersectingWith != -1) {
			Entities item = itemsInCurrentRoom.get(intersectingWith);

			// Check if the item is a collectable or an instant
			if (item.getEntityType() == "Collectable") {
				player.addCollectableToInventory((Collectable) item);
			} else {
				Instant itemToInstant = (Instant) item;
				player.interactWithItem(itemToInstant);
				timeLeft -= itemToInstant.getTime()-(player.getStat(2)*0.05);
				if (timeLeft <= 0.0) {
					timeLeft = 0.00;
					timeLeftText.setFill(Color.RED);
				} else {					
					timeLeft = Math.round(timeLeft * 100.0) / 100.0;
				}
				timeLeftText.setText(Double.toString(timeLeft));
			}

			// update the player's stats after the interaction
			player.updateStatsText(statsText);

			// remove the item from the map and remove it from the screen
			root.getChildren().remove(item);
			itemsInCurrentRoom.remove(intersectingWith);
			// remove the item's name from the screen
			root.getChildren().remove(itemName);
			root.getChildren().remove(itemDesc);
			// reset index of intersection
			intersectingWith = -1;
		}
	}
	

	/**
	 * Opens or closes the stats box
	 */
	private void showStats() {

		// if it is closed, remove the unopened node and add the opened node
		if (root.getChildren().contains(statsUndropped)) {
			root.getChildren().remove(statsUndropped);
			root.getChildren().add(statsDropped);
			for (int i = 0; i < 4; i++) {
				root.getChildren().add(statsText[i]);
			}
		} else {
			root.getChildren().remove(statsDropped);
			for (int i = 0; i < 4; i++) {
				root.getChildren().remove(statsText[i]);
			}
			root.getChildren().add(statsUndropped);
		}
	}

	/**
	 * Changes the background image to match the currentRoom
	 * 
	 * @param x
	 *            - column of the room
	 * @param y
	 *            - row of the room
	 */
	private void changeRoom(int x, int y) {
		String roomName = "'/codes/sprites/" + room[x][y].getImageName() + ".png'";

		root.setStyle("-fx-background-color: DarkSlateGray; -fx-background-image: url(" + roomName + "); " + "-fx-background-size: 800px 800px;" + "-fx-background-repeat: no-repeat;"); // cover to fill

		ArrayList<Entities> oldRoomItems = itemsInCurrentRoom;
		// remove all the items in the old room
		for (int i = 0; i < oldRoomItems.size(); i++) {
			root.getChildren().remove(oldRoomItems.get(i));
		}

		itemsInCurrentRoom = room[x][y].getRoomContents();
		// add all the new items from the new room
		for (int i = 0; i < itemsInCurrentRoom.size(); i++) {
			root.getChildren().add(itemsInCurrentRoom.get(i));
		}
		playerCurrentRoom[0][0] = player.getCurrentRoomX();
		playerCurrentRoom[1][0] = player.getCurrentRoomY();

		// Opening and reopening stats so items don't spawn over it when user leaves it
		// open when changing rooms
//		 showStats();
//		 showStats();
	}
	
	public void addNode(Node n) {
		root.getChildren().add(n);
	}

}
