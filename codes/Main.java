package codes;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author TUT 06, GROUP 03 
 * TODO: STORE ITEMS' COORDINATES SO WE CAN CHECK INTERSECTION
 * 
 */
public class Main extends Application {

	private Pane root = new Pane();
	private final int WIDTH = 1200;
	private final int HEIGHT = 800;

	// the following are used for event listeners
	private boolean goLeft = false;
	private boolean goRight = false;
	private boolean goUp = false;
	private boolean goDown = false;
	private int intersectingWith = -1; // -1 means intersecting with nothing
	
	// Entities
	private Player player = new Player(100.0, 0.0,100,50);
	private Entities statsUndropped = new Entities(0, 0, WIDTH, HEIGHT, "stats1", "stats1.png");
	private Entities statsDropped = new Entities(0, 0, WIDTH, HEIGHT, "stats2", "stats2.png");
	private Entities mainMenu = new Entities(0, 0, WIDTH, HEIGHT, "mainMenu", "mainMenu.png");
	private ArrayList<Entities> itemsInCurrentRoom = new ArrayList<Entities>();
	
	private Rooms[][] room = new Rooms[2][2];
	
	private int[][] playerCurrentRoom = {{0}, {0}};
	
	private Text[] statsText = new Text[4];
	private Text itemName = new Text();

	/**
	 * This function modifies our Pane before its passed down as a parameter when we create a new Scene
	 * It eliminates clutter in our code and allows us to add a lot of things to root before it shows up in the Window
	 * 
	 * @return returns the instance variable Pane 
	 */
	private Parent initialGameContent() {
		// set the size of our Pane layout
		root.setPrefSize(WIDTH, HEIGHT);
		
		// create new room objects
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				room[i][j] = new Rooms(i, j);
			}
		}
		
		itemName.setFont(new Font("Comic Sans MS", 18));
		
		player.generateStats();
		for (int i = 0; i < 4; i++) {
			statsText[i] = new Text();
			
			statsText[i].setFont(new Font("Comic Sans MS", 22));
			statsText[i].setTranslateX(960);
			statsText[i].setTranslateY(110 + (i*40)); // 30 is the gap between the stats
		}
		statsText = player.updateStatsText(statsText);
		
		
		// render the first room at start up
		changeRoom(0,0);
		// adding the player, and enemies?
		root.getChildren().add(player);

		//add HUDS
		root.getChildren().add(statsUndropped);
		
		// An animation timer is used for smoother movements
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
			}
		};
		
		//add mainMenu
		root.getChildren().add(mainMenu);

		timer.start();
		
		return root;
	}

	/**
	 * Updates every frame of the animation (idk how animation timer works yet so idk what the fps is)
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
		
		// if player room moves to a different room, update playerCurrentRoom and change the background
		if (playerCurrentRoom[0][0] != player.getCurrentRoomX() || playerCurrentRoom[1][0] != player.getCurrentRoomY()) {
			changeRoom(player.getCurrentRoomX(), player.getCurrentRoomY());
		}
		
		checkForItemIntersection();
	}
	
	private void checkForItemIntersection() {
		for(int i = 0; i < itemsInCurrentRoom.size(); i++) {
			if (player.getBoundsInParent().intersects(itemsInCurrentRoom.get(i).getBoundsInParent())) {
				intersectingWith = i;
				// nested loop so the text doesn't flicker
				if (!root.getChildren().contains(itemName)) {
					itemName.setX(itemsInCurrentRoom.get(i).getTranslateX());
					itemName.setY(itemsInCurrentRoom.get(i).getTranslateY());
					itemName.setText(itemsInCurrentRoom.get(i).getName());
					root.getChildren().add(itemName);
				}
				break;
			} else {
				root.getChildren().remove(itemName);
				intersectingWith = -1;
			}
		}
	}
	
	/**
	 * Handles interaction with items/npcs
	 */
	private void interact() {
		// if the player is intersecting with an item, pick up the item
		if (intersectingWith != -1) {
			Entities item = itemsInCurrentRoom.get(intersectingWith);
			
			if (item.getEntityType() == "Collectable") {
				player.addCollectableToInventory((Collectable) item);
			} else {
				player.interactWithItem((Instant) item);
			}
			player.updateStatsText(statsText);
			
			root.getChildren().remove(item);
			itemsInCurrentRoom.remove(intersectingWith);
			intersectingWith = -1;
			root.getChildren().remove(itemName);
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
			root.getChildren().add(statsText[0]);
			root.getChildren().add(statsText[1]);
			root.getChildren().add(statsText[2]);
			root.getChildren().add(statsText[3]);
		} else {
			root.getChildren().remove(statsDropped);
			root.getChildren().remove(statsText[0]);
			root.getChildren().remove(statsText[1]);
			root.getChildren().remove(statsText[2]);
			root.getChildren().remove(statsText[3]);
			root.getChildren().add(statsUndropped);
		}
	}
	
	/**
	 * Changes the backround image to match the currentRoom
	 * @param x - column of the room
	 * @param y - row of the room
	 */
	private void changeRoom(int x, int y) {
		String roomName = "'" + room[x][y].getImageName() + ".jpg'";
		
		
		root.setStyle("-fx-background-image: url("
				+ roomName
				+ "); " + "-fx-background-size: cover;");
		
		ArrayList<Entities> oldRoomItems = itemsInCurrentRoom;
		// remove all the items in the old room
		for(int i = 0; i < oldRoomItems.size(); i++) {
			root.getChildren().remove(oldRoomItems.get(i));
		}
		
		itemsInCurrentRoom = room[x][y].getRoomContents();
		// add all the new items from the new room
		for(int i = 0; i < itemsInCurrentRoom.size(); i++) {
			root.getChildren().add(itemsInCurrentRoom.get(i));
		}
		playerCurrentRoom[0][0] = player.getCurrentRoomX();
		playerCurrentRoom[1][0] = player.getCurrentRoomY();
		
		// Opening and reopening stats so items don't spawn over it when user leaves it open when changing rooms
//		showStats();
//		showStats();
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Scene scene = new Scene(initialGameContent());
		
		// Key pressed listener. To bind a new key, add a new case statement
		scene.setOnKeyPressed(e -> {
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
			case SPACE:
				interact();
				break;
			case P:
				showStats();
				break;
			case DIGIT1:
				if(root.getChildren().contains(mainMenu))
					root.getChildren().remove(mainMenu);
			}	
		});

		// Key release listener
		scene.setOnKeyReleased(e -> {
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
			}
		});
		
		
		stage.setScene(scene);
		stage.setTitle("BEST PROJECT IN CPSC 233 LECTURE 02");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
