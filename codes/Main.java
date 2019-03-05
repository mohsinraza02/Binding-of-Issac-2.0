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

	// the following are used for event listeners
	boolean goLeft = false;
	boolean goRight = false;
	boolean goUp = false;
	boolean goDown = false;

	private Objects player = new Objects(500, 500, 50, 100, "player", Color.BLUE, "player.png");
	private Objects npc = new Objects(870, 500, 30, 40, "interaction", Color.BLUE, null);
	private Objects bubble;

	private Text t = new Text("press <space> to interact");

	/**
	 * We pass this parameter when we create a new scene.
	 * This simplifies our code and allows us to add more nodes in the beginning
	 * and other actions
	 * @return
	 */
	private Parent createContent() {
		root.setPrefSize(1200, 800);
		// adding the player, and enemies?
		root.getChildren().add(player);
		// maybe move adding npc to the map
		root.getChildren().add(npc);

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
		
		// if player is intersecting with the npc, show a dialogue
		if (player.getBoundsInParent().intersects(npc.getBoundsInParent())) {
			// nested loop so the text doesn't flicker
			if (!root.getChildren().contains(t)) {
				t.setX(npc.getTranslateX());
				t.setY(npc.getTranslateY());
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
		if (player.getBoundsInParent().intersects(npc.getBoundsInParent())) {
			if (!root.getChildren().contains(bubble)) {
				bubble = new Objects((int) npc.getTranslateX(), (int) npc.getTranslateY() - 80, 80, 80, "bubble",
						Color.BLACK, "text.png");
				root.getChildren().add(bubble);
			} else {
				root.getChildren().remove(bubble);
			}

		}
	}
	
	/**
	 * yall know what goes down here
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(createContent());

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
		
		// Changing the CSS of the pane for an easier way to set the background 
		// how ever, using online pictures takes very long to render
		// so TODO: download pictures/ have the hard copy of pictures
		root.setStyle("-fx-background-image: url("
				+ "'https://s3.amazonaws.com/cartoonsmartstreaming/wp-content/uploads/2017/05/30123103/Castle-top-down-royalty-free-game-art.jpg'"
				+ "); " + "-fx-background-size: cover;");
		stage.setScene(scene);
		stage.setTitle("BEST PROJECT IN CPSC 233 LECTURE 02");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
