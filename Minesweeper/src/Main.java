import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
	public static final int WIDTH = 20;
	public static final int HEIGHT = 15;

	static int NoOfBombs = 20;
	
	public static GridPane root = new GridPane();

	public static void main(String[] args) {
		initTiles();
		initBombs();		
		launch(args);
	}
	
	public static void initTiles() {
		for (int row = 0; row < HEIGHT; row++) {
	        for (int col = 0; col < WIDTH; col++) {
	            new Tile(row, col);
	        }
	    }
	}
	
	public static void initBombs() {
		Random rand = new Random();
		int bombs = 0;
		while (bombs < NoOfBombs) {
		    int randRow = rand.nextInt(HEIGHT);
		    int randCol = rand.nextInt(WIDTH);
		    Tile tile = Tile.tiles[randRow][randCol];
		    if (!Tile.bombTiles.contains(tile) && !tile.clicked) {
		    	Tile.bombTiles.add(tile);	           
		    	bombs++;
		    }
		}
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Minesweeper");
		HBox topItems = new HBox();
		
		topItems.setSpacing(50);
		
		Label label1 = new Label("Bomb Counter");
		Button reset = new Button("reset");
		Label label2 = new Label("Timer");
		
		topItems.setAlignment(Pos.CENTER);
		
		topItems.getChildren().addAll(label1,reset,label2);
		
		
		
		Menu fileMenu = new Menu("File");
		fileMenu.getItems().add(new MenuItem("Settings"));
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu);
		
		
		BorderPane topLevel = new BorderPane();
		BorderPane midLevel = new BorderPane();
		
		midLevel.setTop(topItems);
		midLevel.setCenter(root);
		
		topLevel.setTop(menuBar);
		topLevel.setCenter(midLevel);
		
		stage.setScene(new Scene(topLevel));
		root.setPadding(new Insets(2));		
		
		stage.show();
	}
}