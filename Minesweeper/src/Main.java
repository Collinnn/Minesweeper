import java.awt.Color;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Main extends Application {
	public static final int WIDTH = 20;
	public static final int HEIGHT = 15;

	static int NoOfBombs = 20;
	public static timer time = new timer(); 
	public static Label label2 = new Label();
	public static boolean firstclicked = false;
	
	public static GridPane root = new GridPane();

	public static void main(String[] args) {
		initTiles();
		initBombs();
		time.timeline.pause();
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
		
		//time = new timer();
		
		BorderPane topItems = new BorderPane();
		
		
		HBox rightBox = new HBox();
		
		Label label1 = new Label("Bomb Counter");
		Button reset = new Button("reset");
		reset.setOnAction(e -> {
			Tile.reset();
			initTiles();
			initBombs();
			time.restartcounter();
			firstclicked = false;
			label2.setText("0");
		});
		
		label2.setText("0");
		Region rightPadderRegion = new Region();
		rightBox.getChildren().addAll(rightPadderRegion,label2);


		
		topItems.setLeft(label1);
		topItems.setCenter(reset);
		//topItems.setRight(tempBox);
		
		
		
		topItems.setRight(rightBox);
		
		
		
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
		rightPadderRegion.setPrefWidth(label1.getWidth()-label2.getWidth());
	}
}