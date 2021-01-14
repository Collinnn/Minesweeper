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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
	public static final int WIDTH = 20;
	public static final int HEIGHT = 15;

	static int NoOfBombs = 20;
	public static Timer time = new Timer(); 
	
	public static Label label1 = new Label();
	public static Label label2 = new Label();
	public static boolean firstclicked = false;
	public static int bombsNotFound;
	
	public static GridPane root = new GridPane();

	public static void main(String[] args) {
		bombsNotFound = NoOfBombs;
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
		
		
		BorderPane topItems = new BorderPane();
		
		HBox middleBox = new HBox();
		middleBox.setAlignment(Pos.CENTER);
		
		StackPane leftPane = new StackPane();
		StackPane rightPane = new StackPane();
		Rectangle left = new Rectangle(90,40);
		Rectangle right = new Rectangle(90,40);
		left.setFill(Color.BLACK);
		right.setFill(Color.BLACK);
		
		
		label1 = new Label(String.valueOf(bombsNotFound));
		label1.setTextFill(Color.WHITE);
		
		Button reset = new Button("reset");
		reset.setOnAction(e -> {
			Tile.reset();
			initTiles();
			initBombs();
			time.restartcounter();
			firstclicked = false;
			label2.setText("0");
			bombsNotFound = NoOfBombs;
			label1.setText(String.valueOf(bombsNotFound));
		});
		
		label2.setText("0");
		label2.setTextFill(Color.WHITE);
		
		
		leftPane.getChildren().addAll(left,label1);
		rightPane.getChildren().addAll(right,label2);
		
		middleBox.getChildren().add(reset);
		
		leftPane.setPadding(new Insets(10,0,10,20));
		rightPane.setPadding(new Insets(10,20,10,0));

		
		topItems.setLeft(leftPane);
		topItems.setRight(rightPane);
		topItems.setCenter(middleBox);
		
		
		
		Menu fileMenu = new Menu("File");
		
		MenuItem settings = new MenuItem("Settings");
		settings.setOnAction(e -> SettingsWindow.display());
		fileMenu.getItems().add(settings);
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