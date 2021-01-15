import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board {
	public static GridPane grid = new GridPane();

	public static int WIDTH = 15;
	public static int HEIGHT = 12;
	
	// Array for all tiles and tiles with bombs
	public static Tile[][] tiles = new Tile[Board.HEIGHT][Board.WIDTH];
	public static ArrayList<Tile> bombTiles = new ArrayList<Tile>();
	

	// Random integer
	private static Random randInt = new Random();

	
	public static int noOfBombs = 80;
	public static int bombsNotFound = noOfBombs;
	
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
		ArrayList<Tile> availableTiles = new ArrayList<Tile>();
		for (Tile[] tilerow : tiles) {
			availableTiles.addAll(Arrays.asList(tilerow));
		}
		availableTiles.removeAll(bombTiles);
		
		while (bombs < noOfBombs) {
			int listSize = availableTiles.size();
			Tile tile = availableTiles.get(randInt.nextInt(listSize));
		    availableTiles.remove(tile);
		    bombTiles.add(tile);         
		    bombs++;
		}
	}
	
	private static void initBombs(int noOfBombs, ArrayList<Tile> group) {
		int bombs = 0;
		ArrayList<Tile> availableTiles = new ArrayList<Tile>();
		for (Tile[] tilerow : tiles) {
			availableTiles.addAll(Arrays.asList(tilerow));
		}
		availableTiles.removeAll(group);
		availableTiles.removeAll(bombTiles);
		
		while (bombs < noOfBombs) {
			int listSize = availableTiles.size();
		    Tile tile = availableTiles.get(randInt.nextInt(listSize));
		    availableTiles.remove(tile);
		    bombTiles.add(tile);         
		    bombs++;
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
		if (tile.get_value() == 0 && !Board.bombTiles.contains(tile)) {
			tile.clicked = true;
			for (Tile neighbor : Board.get_neighbors(tile)) {
				if (!group.contains(neighbor) && !neighbor.clicked) {
					neighbor.clicked = true;
					group.addAll(get_group(neighbor));
				}
			}
		}
		else {
			for (Tile neighbor : Board.get_neighbors(tile)) {
				if (!tile.clicked && neighbor.get_value() == 0 && !Board.bombTiles.contains(neighbor)) {
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
		for(int i =0; i < HEIGHT; i++) {
			for(int j = 0; j < WIDTH; j++) {
				tiles[i][j].shape.setFill(Color.LIGHTGRAY);
			}
		}
		grid.getChildren().clear();
		initTiles();
		initBombs();
		
		Main.time.restartcounter();
		Board.firstclicked = false;
		TopBarLayout.labelTimer.setText("0");
		Board.bombsNotFound = noOfBombs;
		TopBarLayout.labelBombCounter.setText(String.valueOf(Board.bombsNotFound));
	}
}
