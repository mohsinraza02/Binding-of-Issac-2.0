package codes;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
	private Button startGame = new Button("START!");
	private BackgroundImage bImage = new BackgroundImage(new Image("/codes/sprites/button.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(newGameButton.getWidth(), newGameButton.getHeight(), true, true, true, false));
	private Background buttonBg = new Background(bImage);
	//	private Background buttonbg = new Background(new BackgroundImage(new Image("/codes/sprites/button.png"), null, null, null, null));

	@Override
	public void start(Stage stage) throws Exception {
		createScreens();
		
		stage.setScene(scenes.get(0));
		
		((MenuScreen) scenes.get(0)).addNode(startGameButton);
		startGameButton.setOnMouseClicked(e -> {stage.setScene(scenes.get(1)); });
		startGameButton.setTranslateX(100);
		startGameButton.setTranslateY(550);
		startGameButton.setPrefSize(250, 100);
		startGameButton.setBackground(buttonBg);
		
		((MenuScreen) scenes.get(0)).addNode(newGameButton);
		newGameButton.setOnMouseClicked(e -> {newGame(); stage.setScene(scenes.get(1)); });
		newGameButton.setTranslateX(100);
		newGameButton.setTranslateY(400);
		newGameButton.setPrefSize(250, 100);
		newGameButton.setBackground(buttonBg);
		
		backToMenuButton.setOnMouseClicked(e -> {stage.setScene(scenes.get(0));});
		backToMenuButton.setTranslateX(950);
		backToMenuButton.setTranslateY(730);
		backToMenuButton.setPrefSize(100, 60);
		backToMenuButton.setBackground(buttonBg);
		
		Button debug = new Button("debugBattle");
        debug.setTranslateX(120);
        debug.setTranslateY(700);
        debug.setPrefSize(150, 40);
        debug.setOnMouseClicked(e -> {stage.setScene(scenes.get(3));});
        ((MenuScreen) scenes.get(0)).addNode(debug);
		
		stage.setResizable(false);
		stage.setTitle("CRAM before the EXAM");
		stage.show();
	}
	
	
	public void createScreens() {
		scenes.add(new MenuScreen(WIDTH, HEIGHT));
		scenes.add(new SetupScreen());
		scenes.add(new GameScreen());
		scenes.add(new Battle());
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
