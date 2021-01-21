import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;
import java.util.ArrayList;

/*
 * The SimpleMinesweeper class contains 
 * the entire simple Minesweeper program.
 */

public class SimpleMinesweeper extends Application {
	Button button;
	
	static int tileSize = 40; // Width and height of tiles
	static int rowSize = 15; 
	static int colSize = 10; 
	static int NoOfBombs = 25;
	
	// Arrays stores all tiles and tiles containing bombs
	static Button[][] tiles;
	static ArrayList<Button> bombTiles = new ArrayList<Button>();
	
	static Image img = new Image("bomb1.png");
	
	static GridPane grid = new GridPane();
	
	public static void main(String[] args) {
		try {
			rowSize = Integer.valueOf(args[0]);
			colSize = Integer.valueOf(args[1]);
			NoOfBombs = Integer.valueOf(args[2]);
			
			if (rowSize < 4 || colSize < 4 || rowSize > 100 || colSize > 100) {
				throw new Exception("Invalid argument(s):\n\t-The grid size must be between 4x4 and 100x100");
			}
			if (NoOfBombs> rowSize*colSize-1) {
				throw new Exception("Invalid argument(s):\n\t-The amount of bombs must be at least 1 smaller than the height*width");
			}
			
		}
		catch(Exception exception) {
			System.out.println(exception);
			System.exit(0);
		}
		
		// initializes the board
		tiles = new Button[rowSize][colSize];
		init_tiles();
		init_bombs();
		set_tileAction();
		
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Minesweeper");
		stage.setScene(new Scene(grid));
	    
	    stage.show();
	}
	
	public static void init_tiles() {
		/*
		 * Initializes the board by adding tiles to the grid layout
		 */
		for (int row = 0; row < rowSize; row++) {
	        for (int col = 0; col < colSize; col++) {
	            Button button = new Button();
	            button.setPrefSize(tileSize, tileSize); 
	            grid.add(button, row, col); 
            	tiles[row][col] = button; 
	        }
	    }
	}
	
	public static void init_bombs() {
		/*
		 * Randomly adds bombs to a number of tiles given by the NoOfBombs-variable
		 */
		
		Random rand = new Random();
		int bombs = 0; 
	    
		while (bombs < NoOfBombs) {
			// Selects a random tile
	    	int randRow = rand.nextInt(rowSize);
	    	int randCol = rand.nextInt(colSize);
	       	Button tile = tiles[randRow][randCol];
	    	
	    	// Adds a bomb to the assigned tile unless the tile already contains a bomb
	    	if (!bombTiles.contains(tile)) {
	    		bombTiles.add(tile);
	    		
	    		// Adds bomb image to tile
	    		ImageView view = new ImageView(img);
	            view.setFitWidth(20);
	        	view.setPreserveRatio(true);
	        	
	        	// Sets the action of the button to show the bomb image
	            tiles[randRow][randCol].setOnAction(e ->tiles[randRow][randCol].setGraphic(view));
	           
	    		bombs++;
	    	}
	    }
	}
	
	public static void set_tileAction() {
		/*
		 * Initializes all tiles (except bomb-tiles) with a number showing nearby bombs.
		 * The number will be shown when the tile i clicked.
		 */
		
		for (int row = 0; row < rowSize; row++) {
	    	for (int col = 0; col < colSize; col++) {
	    		int neighborBombs = 0; 
	    		for (Button tile : get_neighbors(row, col)) {
		    		if (bombTiles.contains(tile)) {
		    			neighborBombs++;
		    		}
		    	}
	    		
	    		Button tile = tiles[row][col];
	    		
	    		// Applies number/value to all tiles except bombs and blank tiles
	    		if (!bombTiles.contains(tile) && neighborBombs != 0) {
		    		String finalNeighborBombs = Integer.toString(neighborBombs);
		    		tiles[row][col].setOnAction(e -> tile.setText(finalNeighborBombs));
	    		}
	    	}
	    }
	}
	
	public static ArrayList<Button> get_neighbors(int row, int col) {
		/*
		 * Returns: ArrayList containing the selected tile's surrounding 8 tiles
		 */
		
		ArrayList<Button> neighbors = new ArrayList<Button>();
		// Loops over the 8 tiles surrounding the selected tile
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (!(i == 0 && j == 0)) { // excludes the selected tile from the loop
					// Try-statements prevents errors for tiles with less than 8 neighbors
					try {
						// Adds the neighboring tile to the neighbors-ArrayList
						neighbors.add(tiles[row+i][col+j]);
					}
					catch(Exception e) {
					}
				}
			}
		}
		return neighbors;
	}
}
