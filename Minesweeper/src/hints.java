import java.util.ArrayList;

public class hints {
	
	public ArrayList<Tile> GenerateBitmap() {
		ArrayList<Tile> bitmap = new ArrayList<Tile>();
		for (int row = 0; row < Main.HEIGHT;row++) {
			for (int col = 0; col <Main.WIDTH;col++) {
				if(Tile.clicked) {
					bitmap.add(Tile.tiles[row][col]);
				}
				else {
					bitmap.add(null);
				}
			}
		}
		return bitmap; 
	}
	
	public void gethint () {
		int n;
		for (int row = 0; row < Main.HEIGHT;row++) {
			for (int col = 0; col <Main.WIDTH;col++) {
				if(Tile.clicked && Tile.get_value()==getNeighbors(row,col)) {   
					
				}
			}
		}
	}
	
	private int getNeighbors(int row, int col) {
		ArrayList<Tile> neighbors = new ArrayList<Tile>();
		int n=0; 
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if(!Tile.clicked) {
					n++; 
				}
			}
		
		}
		return n; 
		 
	}
	private ArrayList<Tile> findNeighbors(int row, int col) {
		for (Tile tile : Tile.get_neighbors()) {
			array
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
}
