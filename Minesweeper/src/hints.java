import javafx.scene.paint.Color;
import java.util.ArrayList; 
public class hints {
	
	public ArrayList<Tile> GenerateBitmap() {
		ArrayList<Tile> bitmap = new ArrayList<Tile>();
		for (int row = 0; row < Board.HEIGHT;row++) {
			for (int col = 0; col <Board.WIDTH;col++) {
				Tile tile = Board.tiles[row][col];
				if(tile.clicked) {
					bitmap.add(tile);
				}
				else {
					bitmap.add(null);
				}
			}
		}
		return bitmap; 
	}
	
	public void gethint () {
		for (Tile[] tilerow: Board.tiles) {
			for (Tile tile: tilerow) {
				if(tile.clicked && tile.get_value()==Board.get_neighbors(tile).size()) {   
					for(Tile neighbor: findNeighbors(tile)) {
						neighbor.set_highlight(Color.GOLD);
					}
					
				}
			}
		}
	}

	private ArrayList<Tile> findNeighbors(Tile tile) {
		ArrayList<Tile> notclick = new ArrayList<Tile>();
		for(Tile neighbor: Board.get_neighbors(tile)) {
			if(!neighbor.clicked) {
				notclick.add(neighbor); 
			}
		}
		
		return notclick; 
	}
	
	
	
	/*
	  	
	private int getNeighbors(int row, int col) {
		ArrayList<Tile> neighbors = new ArrayList<Tile>();
		int n=0; 
		for(Tile neighbor : Board.get_neighbors(this)) {
			
				
			}
		
		}
		return null; 
		 
	}
	 */
	
	
	
	
	
}
