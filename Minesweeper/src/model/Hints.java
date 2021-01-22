package model;
import java.util.HashSet;

import javafx.scene.paint.Color;
import view.Tile; 

public class Hints {
	public static void getHint () {
		for (Tile[] tilerow: Board.tiles) {
			for (Tile tile: tilerow) {
				if (tile.clicked && tile.getValue()==getUnclickedNeighbors(tile).size()) {   
					for(Tile neighbor : getUnclickedNeighbors(tile)) {
						if (!neighbor.flagged) {
							neighbor.setHighlight(Color.RED);
						}
					}
				}
			}
		}
	}

	private static HashSet<Tile> getUnclickedNeighbors(Tile tile) { 
		HashSet<Tile> notClicked = new HashSet<Tile>();
		for(Tile neighbor: Board.getNeighbors(tile)) {
			if(!neighbor.clicked) {
				notClicked.add(neighbor); 
			}
		}
		return notClicked; 
	}	
}
