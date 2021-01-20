import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class Board {
	public static GridPane grid = new GridPane();

	public static int width = 62;
	public static int height = 30;
	public static int winCounter;
	public static int timesClicked;
	public static int difficulty = 0;
	
	// Array for all tiles and tiles with bombs
	public static Tile[][] tiles = new Tile[height][width];
	public static ArrayList<Tile> bombTiles = new ArrayList<Tile>();
	

	// Random row and column index in tile grid
	private static Random rand = new Random();
	private static int randRow;
	private static int randCol; 
	
	public static int noOfBombs = 80;
	public static int bombsNotFound = noOfBombs;
	
	public static boolean firstclicked = false;
	
	public Board() {
		grid.setPadding(new Insets(2));
		initTiles();
		initBombs();
	}
	
	
	private static void initTiles() {
		for (int row = 0; row < height; row++) {
	        for (int col = 0; col < width; col++) {
	            new Tile(row, col);
	        }
	    }
		winCounter = (height*width)-noOfBombs;
		timesClicked = 0;
	}
	
	private static void initBombs() {
		int bombs = 0;
		while (bombs < noOfBombs) {
			randRow = rand.nextInt(height);
			randCol = rand.nextInt(width);
		    Tile tile = tiles[randRow][randCol];
		    if (!bombTiles.contains(tile)) {
		    	bombTiles.add(tile);	           
		    	bombs++;
		    }
		}
	}
	
	private static void initBombs(int noOfBombs, ArrayList<Tile> group) {
		if (group == null) {
			group = new ArrayList<Tile>();
		}
		int bombs = 0;
		while (bombs < noOfBombs) {
			randRow = rand.nextInt(height);
			randCol = rand.nextInt(width);
		    Tile tile = tiles[randRow][randCol];
		    if (!bombTiles.contains(tile) && !group.contains(tile)) {
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
					catch(ArrayIndexOutOfBoundsException e) {
					}
				}
			}
		}
		return neighbors;
	}
	
	public static HashSet<Tile> get_group(Tile tile) {
		HashSet<Tile> group = new HashSet<Tile>();
		group.add(tile);
		if (tile.get_value() == 0 && !bombTiles.contains(tile)) {
			tile.clicked = true;
			for (Tile neighbor : get_neighbors(tile)) {
				if (!group.contains(neighbor) && !neighbor.clicked) {
					neighbor.clicked = true;
					group.addAll(get_group(neighbor));
				}
			}
		}
		else {
			for (Tile neighbor : get_neighbors(tile)) {
				if (!tile.clicked && neighbor.get_value() == 0 && !bombTiles.contains(neighbor)) {
					group.addAll(get_group(neighbor));
				}
			}
		}
		tile.clicked = false;
		
		return group;
	}
	
	public static void firstClick(Tile tile) {
		firstclicked = true;
		Main.time.timeline.play();
		
		int badBombs = 0;
		ArrayList<Tile> group = get_neighbors(tile);
		group.add(tile);

		for (Tile groupMember : group) {
			if (bombTiles.contains(groupMember)) {
				bombTiles.remove(groupMember);
				badBombs++;
			}
		}
		initBombs(badBombs, group);
		
		tile.reveal_tile();
	}
	
	public void reset() {
		tiles = new Tile[height][width];
		grid.getChildren().clear();
		initTiles();
		initBombs();
		
		Main.time.restartcounter();
		firstclicked = false;
		TopBarLayout.labelTimer.setText("0");
		bombsNotFound = noOfBombs;
		TopBarLayout.labelBombCounter.setText(String.valueOf(bombsNotFound));
	}
}
