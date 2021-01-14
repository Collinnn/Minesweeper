import java.util.ArrayList;
import java.util.HashSet;
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

	// Random row and column index in tile grid
	private static Random rand = new Random();
	private static int randRow = rand.nextInt(HEIGHT);
	private static int randCol = rand.nextInt(WIDTH);
	
	public static int NoOfBombs = 20;
	public static int bombsNotFound = NoOfBombs;
	
	public static boolean firstclicked = false;
	
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
		int bombs = 0;
		while (bombs < NoOfBombs) {
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
	
	public static HashSet<Tile> generateGroup(Tile tile) {
		HashSet<Tile> group = new HashSet<Tile>();
		group.add(tile);
		if (tile.get_value() == 0 && !Board.bombTiles.contains(tile)) {
			tile.clicked = true;
			for (Tile neighbor : Board.get_neighbors(tile)) {
				if (!group.contains(neighbor) && !neighbor.clicked) {
					neighbor.clicked = true;
					group.addAll(generateGroup(neighbor));
				}
			}
		}
		else {
			for (Tile neighbor : Board.get_neighbors(tile)) {
				if (!tile.clicked && neighbor.get_value() == 0 && !Board.bombTiles.contains(neighbor)) {
					group.addAll(generateGroup(neighbor));
				}
			}
		}
		tile.clicked = false;
		
		return group;
	}
	
	public static void firstClick(Tile tile) {
		firstclicked = true;
		Main.time.timeline.play();
		for (Tile groupMember : generateGroup(tile)) {
			if (bombTiles.contains(groupMember)) {
				
			}
		}
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
		Board.firstclicked = false;
		TopBarLayout.labelTimer.setText("0");
		Board.bombsNotFound = NoOfBombs;
		TopBarLayout.labelBombCounter.setText(String.valueOf(Board.bombsNotFound));
	}
	
}
