package codes;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class SetupScreen extends Scene {
	
	private static Pane root;
	
	private String saveFileName;
	Text welcome = new Text("Welcome");
	Text instr1 = new Text("Enter a name for this save file");
	
	
	public SetupScreen() {
		super(root = new Pane());
		root.setPrefSize(1200, 800);
		
	    this.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	            System.out.println("X: "+ event.getX());
	            System.out.println("Y: "+ event.getY());
	            System.out.println();
	        }
	    });
	}
}
