package codes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MenuScreen extends Scene {

	static Pane root = new Pane();
	
	/**
	 * Create a menu screen object that will show until the player decides to start a new game/ (load game?)/ or exit
	 * TODO: Make a quit game button, and a load screen?
	 */
	public MenuScreen(int width, int height) {
		super(root);
		root.setPrefSize(width, height);
		
		// changing the background
		root.setStyle("-fx-background-image: url(/sprites/mainMenu.png); " 
				+ "-fx-background-size: cover;");
		
	}
	
	public void addNode(Node n) {
		root.getChildren().add(n);
	}
	
}
