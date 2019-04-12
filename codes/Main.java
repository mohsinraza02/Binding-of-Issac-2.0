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

	private Button continueGameButton = new Button("CONTINUE");
	private Button newGameButton = new Button("NEW GAME");
	private Button backToMenuButton = new Button("Main menu");
	private Button battleButton = new Button("Take the exam");
	private Button rerollButton = new Button("Reroll");
	private Button submitButton = new Button("Submit");

	
	private BackgroundImage bImage = new BackgroundImage(new Image("/codes/sprites/button.png"),
			BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
			new BackgroundSize(newGameButton.getWidth(), newGameButton.getHeight(), true, true, true, false));
	private Background buttonBg = new Background(bImage);

	@Override
	public void start(Stage stage) throws Exception {
		createScreens();

		stage.setScene(scenes.get(0));

		((MenuScreen) scenes.get(0)).addNode(continueGameButton);
		continueGameButton.setOnMouseClicked(e -> {
			stage.setScene(scenes.get(1));
		});
		continueGameButton.setTranslateX(100);
		continueGameButton.setTranslateY(550);
		continueGameButton.setPrefSize(250, 100);
		continueGameButton.setBackground(buttonBg);

		((MenuScreen) scenes.get(0)).addNode(newGameButton);
		newGameButton.setOnMouseClicked(e -> {
			newGame();
			stage.setScene(scenes.get(2));
		});
		newGameButton.setTranslateX(100);
		newGameButton.setTranslateY(400);
		newGameButton.setPrefSize(250, 100);
		newGameButton.setBackground(buttonBg);

		backToMenuButton.setOnMouseClicked(e -> {
			stage.setScene(scenes.get(0));
		});
		backToMenuButton.setTranslateX(950);
		backToMenuButton.setTranslateY(730);
		backToMenuButton.setPrefSize(100, 60);
		backToMenuButton.setBackground(buttonBg);

		battleButton.setTranslateX(275);
		battleButton.setTranslateY(460);
		battleButton.setPrefSize(250, 80);
		battleButton.setBackground(buttonBg);

		battleButton.setOnMouseClicked(e -> {
			stage.setScene(((GameScreen) scenes.get(1)).getBattle());
		});

		rerollButton.setTranslateX(500);
		rerollButton.setTranslateY(650);
		rerollButton.setPrefSize(100, 60);
		rerollButton.setBackground(buttonBg);
		
		submitButton.setTranslateX(600);
		submitButton.setTranslateY(650);
		submitButton.setPrefSize(100, 60);
		submitButton.setBackground(buttonBg);
		
		
		rerollButton.setOnMouseClicked(e ->{
			((RerollScreen) scenes.get(2)).reroll();
		});
		
		submitButton.setOnMouseClicked(e ->{
			((GameScreen) scenes.get(1)).updateStats();
			stage.setScene(scenes.get(1));
		});

		stage.setResizable(false);
		stage.setTitle("CRAM before the EXAM");
		stage.show();
	}

	public void createScreens() {
		scenes.add(new MenuScreen(WIDTH, HEIGHT));
		scenes.add(new GameScreen());
		Player p = ((GameScreen) scenes.get(1)).getPlayer();
		scenes.add(new RerollScreen(p));
		((RerollScreen) scenes.get(2)).addNode(rerollButton);
		((RerollScreen) scenes.get(2)).addNode(submitButton);
	}

	public void newGame() {
		scenes.set(1, new GameScreen());
		((GameScreen) scenes.get(1)).setBackToMain(backToMenuButton);
		((GameScreen) scenes.get(1)).addNode(backToMenuButton);
		((GameScreen) scenes.get(1)).setBattleButton(battleButton);
		
		Player p = ((GameScreen) scenes.get(1)).getPlayer();
		scenes.set(2, new RerollScreen(p));
		((RerollScreen) scenes.get(2)).addNode(rerollButton);
		((RerollScreen) scenes.get(2)).addNode(submitButton);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
