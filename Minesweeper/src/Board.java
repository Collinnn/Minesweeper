import java.util.ArrayList;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board {
	public static GridPane grid = new GridPane();

	// Array for all tiles and tiles with bombs
	public static Tile[][] tiles = new Tile[Board.HEIGHT][Board.WIDTH];
	public static ArrayList<Tile> bombTiles = new ArrayList<Tile>();
	
	public static final int WIDTH = 20;
	public static final int HEIGHT = 15;

	static int NoOfBombs = 20;
	
	public Board() {
		grid.setPadding(new Insets(2));
		initTiles();
		initBombs();
	}
	
	
	private static void initTiles() {
		for (int row = 0; row < HEIGHT; row++) {
	        for (int col = 0; col < WIDTH; col++) {
	            new Tile(row, col);
	        }
	    }
	}
	
	private static void initBombs() {
		Random rand = new Random();
		int bombs = 0;
		while (bombs < NoOfBombs) {
		    int randRow = rand.nextInt(HEIGHT);
		    int randCol = rand.nextInt(WIDTH);
		    Tile tile = tiles[randRow][randCol];
		    if (!bombTiles.contains(tile) && !tile.clicked) {
		    	bombTiles.add(tile);	           
		    	bombs++;
		    }
		}
	}
	
	public static ArrayList<Tile> get_neighbors(Tile tile) {
		ArrayList<Tile> neighbors = new ArrayList<Tile>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (!(i == 0 && j == 0)) {
					try {
						neighbors.add(tiles[tile.row+i][tile.col+j]);
					}
					catch(Exception e) {
					}
				}
			}
		}
		return neighbors;
	}
	
	public void reset() {
		for(int i =0; i < HEIGHT; i++) {
			for(int j = 0; j < WIDTH; j++) {
				tiles[i][j].shape.setFill(Color.LIGHTGRAY);
			}
		}
		initTiles();
		initBombs();
		
		Main.time.restartcounter();
		Main.firstclicked = false;
		TopBarLayout.labelTimer.setText("0");
		Main.bombsNotFound = NoOfBombs;
		TopBarLayout.labelBombCounter.setText(String.valueOf(Main.bombsNotFound));
	}
	
}
