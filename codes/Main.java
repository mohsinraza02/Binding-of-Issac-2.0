package codes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
public class Main extends Application implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private boolean saveExist = false;
	private String fileName = "saveFile.save";
	
	
	private final int WIDTH = 1200;
	private final int HEIGHT = 800;

	private ArrayList<Scene> scenes = new ArrayList<>(); // 0 = menu screen, 1 = game screen.

	private Button loadGameButton = new Button("Load Game");
	private Button newGameButton = new Button("New Game");
	private Button backToMenuButton = new Button("Main menu");
	private Button startGame = new Button("START!");
	private Button battleButton = new Button("Take the exam");
	private Button saveGameButton = new Button("Save");

	private BackgroundImage bImage = new BackgroundImage(new Image("/codes/sprites/button.png"),
			BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
			new BackgroundSize(newGameButton.getWidth(), newGameButton.getHeight(), true, true, true, false));
	private Background buttonBg = new Background(bImage);
	// private Background buttonbg = new Background(new BackgroundImage(new
	// Image("/codes/sprites/button.png"), null, null, null, null));

	@Override
	public void start(Stage stage) throws Exception {
		createScreens();

		stage.setScene(scenes.get(0));


		((MenuScreen) scenes.get(0)).addNode(newGameButton);
		newGameButton.setOnMouseClicked(e -> {
			createNewGame();
			stage.setScene(scenes.get(1));
		});
		newGameButton.setTranslateX(100);
		newGameButton.setTranslateY(400);
		newGameButton.setPrefSize(250, 100);
		newGameButton.setBackground(buttonBg);
		
		((MenuScreen) scenes.get(0)).addNode(loadGameButton);
		loadGameButton.setOnMouseClicked(e -> {
//			stage.setScene(scenes.get(1));
			loadGame();
		});
		loadGameButton.setTranslateX(100);
		loadGameButton.setTranslateY(550);
		loadGameButton.setPrefSize(250, 100);
		loadGameButton.setBackground(buttonBg);

		backToMenuButton.setOnMouseClicked(e -> {
			stage.setScene(scenes.get(0));
		});
		backToMenuButton.setTranslateX(1000);
		backToMenuButton.setTranslateY(730);
		backToMenuButton.setPrefSize(100, 60);
		backToMenuButton.setBackground(buttonBg);
		
		saveGameButton.setOnMouseClicked(e -> {
			saveGame();
		});
		saveGameButton.setTranslateX(900);
		saveGameButton.setTranslateY(730);
		saveGameButton.setPrefSize(100, 60);
		saveGameButton.setBackground(buttonBg);
		
		
		battleButton.setTranslateX(275);
		battleButton.setTranslateY(460);
		battleButton.setPrefSize(250, 80);
		battleButton.setBackground(buttonBg);
		
		battleButton.setOnMouseClicked(e -> {
			stage.setScene(((GameScreen) scenes.get(1)).getBattle());
		});

		stage.setResizable(false);
		stage.setTitle("CRAM before the EXAM");
		stage.show();
	}

	public void createScreens() {
		scenes.add(new MenuScreen(WIDTH, HEIGHT));
		scenes.add(new GameScreen());
	}

	public void createNewGame() {
		scenes.set(1, new GameScreen());
		((GameScreen) scenes.get(1)).addNode(backToMenuButton);
		((GameScreen) scenes.get(1)).addNode(saveGameButton);
		((GameScreen) scenes.get(1)).setBattleButton(battleButton);
	}
	
	public void loadGame() {
		System.out.println("Loading saved game...");
		// TODO: finish set up screen and show this in the GUI
		FileInputStream streamIn = null; 
		ObjectInputStream objectinputstream = null;
		
		try {
			streamIn = new FileInputStream(fileName);
			objectinputstream = new ObjectInputStream(streamIn);
			
			GameScreen savedObject =  (GameScreen) objectinputstream.readObject();
			
			if (savedObject == null) {
				System.out.println("Corrupted/empty save file");
				createNewGame();
			} else {
				System.out.println("Loaded! Welcome back!");
				scenes.set(1, savedObject); 
			}
		} catch (Exception e) {
			System.out.println("No save game found");
			e.printStackTrace();
			
		} finally {
			try {
				objectinputstream.close(); // always close this after you're done
			} catch (Exception e) {
				System.out.println("ERROR: " + e.getMessage());
			}
		}
	}
	
	public void saveGame() {
		System.out.print("Saving game...");
		FileOutputStream fout;
		ObjectOutputStream oos = null;
		try {
			fout = new FileOutputStream(fileName); // create a new save file
			oos = new ObjectOutputStream(fout); // use the save file to save an object of a clas
			
			oos.writeObject(scenes.get(1)); // write the gameScreen to the file
			System.out.println("Saved!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR SAVING: " + e.toString());
		}
		try {
			oos.close(); // always close ObjectOutputStream once you're done
		} catch (Exception e) {
			System.out.println("ERROR CLOSING: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
