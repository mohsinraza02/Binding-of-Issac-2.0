package codes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class RerollScreen extends Scene {
	private final int WIDTH = 1200;
	private final int HEIGHT = 800;
	private Player player;
	private static Pane root;
	private Text[] statsText = new Text[4];
	private Text[] descText = new Text[4];
//	private HUDobjects stats = new HUDobjects(150, -100, 900, 900, "logo.png");
	private HUDobjects logo = new HUDobjects(530, 100, 150, 150, "logo.png");

	public RerollScreen(Player p) {
		super(root = new Pane());

		this.player = p; // ryan this is what changed. im referencing player to p (p is the player from
							// gamescreen)
		root.setPrefSize(WIDTH, HEIGHT);
//		root.getChildren().add(stats);
		root.getChildren().add(logo);

		changeBackground("");

		player.generateStats();

		for (int i = 0; i < 4; i++) {
			statsText[i] = new Text();
			statsText[i].setFont(new Font("Times", 38));
			statsText[i].setTranslateX(500);
			statsText[i].setTranslateY(300 + (i * 100)); // 30 is the gap between the stats
		}
		statsText = player.updateStatsText(statsText);

		for (int i = 0; i < 4; i++) {
			root.getChildren().add(statsText[i]);
		}

		for (int a = 0; a < 4; a++) {
			descText[a] = new Text();
			descText[a].setFont(new Font("Times", 20));
			descText[a].setTranslateX(500);
			descText[a].setTranslateY(325 + (a * 100));
		}

		descText = player.descStat(descText);
		for (int a = 0; a < 4; a++) {
			root.getChildren().add(descText[a]);
		}

	}

	private void remove() {

		for (int i = 0; i < 4; i++) {
			root.getChildren().remove(statsText[i]);
		}

		for (int a = 0; a < 4; a++) {
			root.getChildren().remove(descText[a]);
		}
	}

	public void reroll() {
		player.generateStats();

		System.out.println(player.getStat(3));
		System.out.println(player.getStat(2));
		System.out.println(player.getStat(1));
		System.out.println(player.getStat(0));
		remove();
		for (int i = 0; i < 4; i++) {
			statsText[i] = new Text();
			statsText[i].setFont(new Font("Times", 38));
			statsText[i].setTranslateX(500);
			statsText[i].setTranslateY(300 + (i * 100)); // 30 is the gap between the stats
		}
		statsText = player.updateStatsText(statsText);

		for (int i = 0; i < 4; i++) {
			root.getChildren().add(statsText[i]);
		}

		for (int a = 0; a < 4; a++) {
			descText[a] = new Text();
			descText[a].setFont(new Font("Times", 20));
			descText[a].setTranslateX(500);
			descText[a].setTranslateY(325 + (a * 100));
		}

		descText = player.descStat(descText);
		for (int a = 0; a < 4; a++) {
			root.getChildren().add(descText[a]);
		}
	}

	private void changeBackground(String imageName) {
		imageName = "/src/sprites/" + imageName + ".png";
		root.setStyle("-fx-background-image: url(" + imageName + "); " + "-fx-background-size: cover;");
	}

	public void addNode(Node n) {
		root.getChildren().add(n);
	}

	public Player getPlayer() {
		return player;
	}
	

}
