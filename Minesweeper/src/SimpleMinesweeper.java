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
	// JavaFX button object used for the tiles
	Button button;
	
	static int tileSize = 40; // width and height of tiles/button object
	static int rowSize = 15; // Number of tiles in a row
	static int colSize = 10; // Number of tiles in a column
	static int NoOfBombs = 25; // Number of bombs
	
	// Button array/matrix: Stores all tiles
	static Button[][] tiles = new Button[rowSize][colSize];
	
	// Button array list: Stores all tiles containing a bomb/mine
	static ArrayList<Button> bombTiles = new ArrayList<Button>();
	
	// Loads bomb image
	static Image img = new Image("bomb1.png");
	
	// GridPane object for storing the tiles in a grid-layout
	static GridPane grid = new GridPane();
	
	public static void main(String[] args) {
		try {
			rowSize = Integer.valueOf(args[0]);
			colSize = Integer.valueOf(args[1]);
			NoOfBombs = Integer.valueOf(args[2]);
		}
		catch(Exception e) {
		}
		
		// initializes the board with mines and sets 
		// an action to be performed when clicking a tile
		init_tiles();
		init_bombs();
		set_tileAction();
		
		// Launches the main game loop
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Minesweeper");
		
		// Creates a scene in the main window with the grid layout
		stage.setScene(new Scene(grid));
	    
	    stage.show();
	}
	
	public static void init_tiles() {
		/*
		 * Initializes the board by adding tiles
		 * to the grid layout
		 */
		for (int row = 0; row < rowSize; row++) {
	        for (int col = 0; col < colSize; col++) {
	            Button button = new Button();
	            button.setPrefSize(tileSize, tileSize); //sets the size of the tile
	            grid.add(button, row, col); // adds the tile to the grid layout at the given row and column
            	tiles[row][col] = button; // Stores the tile in the tile array at the given row and column
	        }
	    }
	}
	
	public static void init_bombs() {
		/*
		 * Randomly adds bombs to a number of tiles given by the NoOfBombs-variable
		 */
		
		Random rand = new Random();
		// Bomb counter: Counts number of bombs added to the game
		int bombs = 0; 
	    
		while (bombs < NoOfBombs) {
			
			// Selects a random row and column
	    	int randRow = rand.nextInt(rowSize);
	    	int randCol = rand.nextInt(colSize);
	    	
	    	// Assigns the tile at the random row and column to a variable
	    	Button tile = tiles[randRow][randCol];
	    	
	    	// Adds a bomb to the assigned tile unless 
	    	// the tile already contains a bomb
	    	if (!bombTiles.contains(tile)) {
	    		bombTiles.add(tile);
	    		
	    		// ImageView object: Image of bomb which will be shown when 
	    		// clicking on a tile.
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
		
		// loops over every row and column in the tile grid
		for (int row = 0; row < rowSize; row++) {
	    	for (int col = 0; col < colSize; col++) {
	    		
	    		// Variable stores number of bombs next to a tile
	    		int neighborBombs = 0; 
	    		
	    		// loops over every tile lying next to the tile at the specified row and column
	    		for (Button tile : get_neighbors(row, col)) {
		    		if (bombTiles.contains(tile)) {
		    			// increments the variable if a neighboring tile is a bomb
		    			neighborBombs++;
		    		}
		    	}
	    		
	    		// Assigns the tile/button at the specified row and column to a variable
	    		Button tile = tiles[row][col];
	    		
	    		if (!bombTiles.contains(tile) && neighborBombs != 0) {
	    			/*
	    			 * If the tile is not a bomb and at least one of the neighboring 
	    			 * tiles has a bomb:
	    			 * Set the tile's on-click action to display the number of neighboring bombs.
	    			*/  
		    		String finalNeighborBombs = Integer.toString(neighborBombs);
		    		tiles[row][col].setOnAction(e -> tile.setText(finalNeighborBombs));
	    		}
	    	}
	    }
	}
	
	public static ArrayList<Button> get_neighbors(int row, int col) {
		/*
		 * Parameters: Row and column from the grid of tiles (to select a tile)
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
