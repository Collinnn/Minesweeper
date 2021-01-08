import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;
import java.util.ArrayList;

public class Main extends Application implements EventHandler<ActionEvent> {
	Button button;
	private static int size = 40;
	static int width = 800;
	static int height = 600;
	static int xtile = width/size;
	static int ytile = height/size;
	static int NoOfBombs = 20;
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	public void pupulatebombs () {
		int bombs = 0;
		Random rand = new Random();
		while (bombs<NoOfBombs) {
			
		}
	}
	
	

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Minesweeper");
		GridPane root = new GridPane();
		
		
	   
	}
	
	
		
		
	
	
	@Override
	public void handle(ActionEvent tiles) {
		

	
}
}