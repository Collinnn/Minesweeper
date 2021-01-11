import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Random;

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
	            new Tile(col, row);
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
		    if (!Tile.bombTiles.contains(tile)) {
		    	Tile.bombTiles.add(tile);	           
		    	bombs++;
		    }
		}
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Minesweeper");	
		stage.setScene(new Scene(root));
		root.setPadding(new Insets(2));		
		
		stage.show();
	}
}