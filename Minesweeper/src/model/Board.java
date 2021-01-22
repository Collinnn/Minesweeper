package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import main.Main;
import view.Tile;
import view.TopBarLayout;

// Main responsibility Jacob Martens
public class Board {
	public static GridPane grid = new GridPane();
	
	public static int width = 16;
	public static int height = 16;
	public static int winCounter; // Remaining not clicked tiles (excluding bombs)
	public static int timesClicked;
	public static int difficulty = 1;
	
	// Array (grid) for all tiles and array for tiles with bombs
	public static Tile[][] tiles = new Tile[height][width];
	public static HashSet<Tile> bombTiles = new HashSet<Tile>();
	
	private static Random rand = new Random();
	private static int randRow;
	private static int randCol; 
	
	public static int noOfBombs = 40;
	public static int bombsNotFound = noOfBombs;
	
	public static boolean firstclicked = false;
	
	public Board() {
		grid.setPadding(new Insets(2));
		grid.setAlignment(Pos.CENTER);
		initTiles();
		initBombs();
	}
	
	private static void initTiles() {
		/*
		 * Creates Tile objects for the board
		 * and sets the game counters.
		 */
		for (int row = 0; row < height; row++) {
	        for (int col = 0; col < width; col++) {
	            new Tile(row, col);
	        }
	    }
		winCounter = (height*width)-noOfBombs;
		timesClicked = 0;
	}
	
	private static void initBombs() {
		/*
		 * Places bombs on random tiles.
		 */
		
		// Creates array of all tiles
		ArrayList<Tile> allTiles = new ArrayList<Tile>();
		for (Tile[] tileRow : tiles) {
			allTiles.addAll(Arrays.asList(tileRow));
		}
		
		int groupSize = allTiles.size();
		int bombs = 0;
		// Add bombs to random available tiles
		while (bombs < noOfBombs) {
			bombTiles.add(allTiles.remove(rand.nextInt(groupSize-bombs)));
			bombs++;
		}
	}
	
	
	private static void initBombs(int noOfBombs, HashSet<Tile> group) {
		/*
		 * Places *noOfBombs* on random tiles which are not part of *group*.
		 * Used to move bombs away from the field created after first click.
		 */
		if (group == null) {
			group = new HashSet<Tile>();
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
	
	public static HashSet<Tile> getNeighbors(Tile tile) {
		/*
		 * Returns a HashSet with all tiles neighboring *tile*
		 */
		HashSet<Tile> neighbors = new HashSet<Tile>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				// Exclude *tile* from the set
				if (!(i == 0 && j == 0)) { 
					// Prevents error for tiles with less than 8 neighbors
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
	
	public static HashSet<Tile> getGroup(Tile tile) {
		/*
		 * Returns a HashSet with every tile belonging to the same group as *tile*.
		 * Note: A group is every tile being revealed when clicking on a tile.
		 * 		 Tiles neighboring blank tiles belong to that blank tile's group.
		 */
		HashSet<Tile> group = new HashSet<Tile>();
		group.add(tile);
		
		// Recursively adds blank tiles neighboring each other to the group
		if (tile.getValue() == 0 && !bombTiles.contains(tile)) {
			tile.tempUsed = true;  // Mark tile as added to group
			for (Tile neighbor : getNeighbors(tile)) {
				if (!group.contains(neighbor) && !neighbor.tempUsed) {
					neighbor.tempUsed = true;
					group.addAll(getGroup(neighbor));
				}
			}
		}
		// If *tile* isn't blank: Check if any neighbors are blank and call getGroup for such neighbor
		else {
			for (Tile neighbor : getNeighbors(tile)) {
				if (!tile.tempUsed && neighbor.getValue() == 0 && !bombTiles.contains(neighbor)) {
					group.addAll(getGroup(neighbor));
				}
			}
		}
		tile.tempUsed = false; // Resets value: Happens to all tiles in the group
		
		return group;
	}
	
	public static void disableAll() {
		/*
		 * Disables all tiles on the board by marking them clicked
		 */
		for (Tile[] tileRow : tiles) {
			for (Tile tile : tileRow) {
				tile.clicked = true;
			}
		}
	}
	
	
	public static void firstClick(Tile tile) {
		/*
		 * Starts the timer and removes any bombs neighboring the clicked tile
		 */
		firstclicked = true;
		Main.time.timeline.play();
		
		int badBombs = 0;
		HashSet<Tile> group = getNeighbors(tile);
		group.add(tile);
		
		// Counts number of bombs on neighboring tiles (including *tile*)
		for (Tile groupMember : group) {
			if (bombTiles.contains(groupMember)) {
				bombTiles.remove(groupMember);
				badBombs++;
			}
		}
		initBombs(badBombs, group); // Places bad bombs on new tiles
		
		tile.revealTile();
	}
	
	public void reset() {
		/*
		 * Resets the board and game counters/variables
		 */
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
