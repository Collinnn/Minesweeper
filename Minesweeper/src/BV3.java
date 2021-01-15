import java.util.HashSet;


public class BV3 {
	
	public static Integer get_3BV() {
		HashSet<HashSet<Tile>> groups = new HashSet<HashSet<Tile>>();
		for (int row = 0; row < Board.HEIGHT; row++) {
			for (int col = 0; col < Board.WIDTH; col++) {
				Tile tile = Board.tiles[row][col];
				if (!Board.bombTiles.contains(tile)) {
					HashSet<Tile> tileGroup = Board.get_group(tile);
					groups.add(tileGroup);
				}
			}
		}
		return groups.size();
	}
}
