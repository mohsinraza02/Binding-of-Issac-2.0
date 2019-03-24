package codes;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * @author TUT 06, GROUP 03 
 */
public class Main extends Application {

	private final int WIDTH = 1200;
	private final int HEIGHT = 800;
	
	private ArrayList<Scene> scenes = new ArrayList<>(); // 0 = menu screen, 1 = game screen.
	
	private Button startGameButton = new Button("CONTINUE");
	private Button newGameButton = new Button("NEW GAME");
	private Button backToMenuButton = new Button("Main menu");

	@Override
	public void start(Stage stage) throws Exception {
		createScreens();
		
		stage.setScene(scenes.get(0));
		
		((MenuScreen) scenes.get(0)).addNode(startGameButton);
		startGameButton.setOnMouseClicked(e -> {stage.setScene(scenes.get(1)); });
		startGameButton.setTranslateX(500);
		startGameButton.setTranslateY(500);
		startGameButton.setPrefSize(200, 100);
		
		((MenuScreen) scenes.get(0)).addNode(newGameButton);
		newGameButton.setOnMouseClicked(e -> {newGame(); stage.setScene(scenes.get(1)); });
		newGameButton.setTranslateX(500);
		newGameButton.setTranslateY(300);
		newGameButton.setPrefSize(200, 100);
		
		backToMenuButton.setOnMouseClicked(e -> {stage.setScene(scenes.get(0));});
		backToMenuButton.setTranslateX(100);
		backToMenuButton.setTranslateY(100);
		backToMenuButton.setPrefSize(100, 80);
		
		stage.setTitle("BEST PROJECT IN CPSC 233 LECTURE 02");
		stage.show();
	}
	
	public void createScreens() {
		scenes.add(new MenuScreen(WIDTH, HEIGHT));
		scenes.add(new GameScreen());
	}
	
	public void newGame() {
		if(scenes.get(1) != null) {
			// ask user if they want to overwrite save data
		}
		scenes.set(1, new GameScreen());
		((GameScreen) scenes.get(1)).addNode(backToMenuButton);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
