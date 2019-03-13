package codes;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author TUT 06, GROUP 03 TODO: Move key listeners to Movements.java, Add
 *         comments, organize variables
 * 
 */
public class Main extends Application {

	private Pane root = new Pane();
	int WIDTH = 1200;
	int HEIGHT = 800;

	// the following are used for event listeners
	private boolean goLeft = false;
	private boolean goRight = false;
	private boolean goUp = false;
	private boolean goDown = false;
	
	// Entities
	private Player player = new Player(500, 500, 100, 150, "player", Color.BLUE, "player.png");
	private Entities itemExample = new Entities(870, 500, 30, 40, "interaction", Color.BLUE, null);
	private Entities statsUndropped = new Entities(0, 0, WIDTH, HEIGHT, "stats1", Color.BLACK, "stats1.png");
	private Entities statsDropped = new Entities(0, 0, WIDTH, HEIGHT, "stats2", Color.BLACK, "stats2.png");
	
	private Map map;
	
	private int[][] playerCurrentRoom = {{0}, {0}};
	
	private Entities bubble;
	private Text t = new Text("press <space> to interact");

	/**
	 * We pass this parameter when we create a new scene.
	 * This simplifies our code and allows us to add more nodes in the beginning
	 * and other actions
	 * @return
	 */
	private Parent createContent() {
		root.setPrefSize(WIDTH, HEIGHT);
		// adding the player, and enemies?
		root.getChildren().add(player);
		// maybe move adding npc to the map
		root.getChildren().add(itemExample);
		//add HUDS
		root.getChildren().add(statsUndropped);
		// An animation timer is used for smoother movements
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
			}
		};

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
			playerCurrentRoom[0][0] = player.getCurrentRoomX();
			playerCurrentRoom[1][0] = player.getCurrentRoomY();
			
			changeRoom(player.getCurrentRoomX(), player.getCurrentRoomY());
		}
		
		// if player is intersecting with the npc, show a dialogue
		if (player.getBoundsInParent().intersects(itemExample.getBoundsInParent())) {
			// nested loop so the text doesn't flicker
			if (!root.getChildren().contains(t)) {
				t.setX(itemExample.getTranslateX());
				t.setY(itemExample.getTranslateY());
				root.getChildren().add(t);
			}
		} else {
			root.getChildren().remove(t);
		}
		
		

	}
	
	/**
	 * when called, an interaction with an npc shows up
	 * in this case, a text bubble shows up when the space bar is pressed
	 */
	private void interact() {
		// check if player can interact with npc (in range)
		if (player.getBoundsInParent().intersects(itemExample.getBoundsInParent())) {
			if (!root.getChildren().contains(bubble)) {
				bubble = new Entities((int) itemExample.getTranslateX(), (int) itemExample.getTranslateY() - 80, 80, 80, "bubble",
						Color.BLACK, "text.png");
				root.getChildren().add(bubble);
			} else {
				root.getChildren().remove(bubble);
			}

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
		} else {
			root.getChildren().remove(statsDropped);
			root.getChildren().add(statsUndropped);
		}
	}
	
	/**
	 * Changes the backround image to match the currentRoom
	 * @param x - column of the room
	 * @param y - row of the room
	 */
	private void changeRoom(int x, int y) {
		String roomName = "'" + map.getImageName(x, y) + ".jpg'";
		
		root.setStyle("-fx-background-image: url("
				+ roomName
				+ "); " + "-fx-background-size: cover;");
	}
	
	/**
	 * yall know what goes down here
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(createContent());
		map = new Map();

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
		
		// render the first room at start up
		changeRoom(0,0);
		stage.setScene(scene);
		stage.setTitle("BEST PROJECT IN CPSC 233 LECTURE 02");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
