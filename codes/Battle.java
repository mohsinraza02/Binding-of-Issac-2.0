package codes;

import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Battle extends Scene {
	
	private static Pane root;
	private final int WIDTH = 1200;
	private final int HEIGHT = 800;
	
	public Battle() {
		super(root = new Pane());
		root = initialContent();
		
	}
	
	/**
	 * This adds all the stuff to the root when the battle starts.
	 * You don't really need this but it's good to have so everything isn't in the constructor.
	 * UPDATE: I removed this method in my gameScreen cause it only needs 1 constructor. therefore theres no point of using this.
	 * @return the root after adding some stuff in it
	 */
	private Pane initialContent() {
		root.setPrefSize(WIDTH, HEIGHT); // makes the screen 1200x800px
		
		/*
		 * To add objects, use root.getChildren().add(Node n) ".remove(Node n) to remove it"
		 * To move objects, use n.setTranslateX(int x) or n.setTranslateY(int y)
		 * To get their position in the map, use n.getTranslate X or Y ()
		 * 
		 *  WHen creating an object, you can use the Entities class. Give it a look to see how it works.
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
	 * This changes the background of the battle screen to the image passed in the parameter
	 * note that you dont need to make an image object, you just pass the filename of the background image.
	 * @param imageName
	 */
	private void changeBackground(String imageName) {
		imageName = "/src/sprites/" + imageName + ".png";
		root.setStyle("-fx-background-image: url(" + imageName + "); " + "-fx-background-size: cover;");
	}
	
}
