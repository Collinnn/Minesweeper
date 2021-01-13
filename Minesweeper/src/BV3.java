import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class BV3 {
	
	public static HashSet<Tile> generateGroup(Tile tile) {
		HashSet<Tile> group = new HashSet<Tile>();
		group.add(tile);
		if (tile.get_value() == 0 && !Tile.bombTiles.contains(tile)) {
			tile.clicked = true;
			for (Tile neighbor : tile.get_neighbors()) {
				if (!group.contains(neighbor) && !neighbor.clicked) {
					neighbor.clicked = true;
					group.addAll(generateGroup(neighbor));
				}
			}
		}
		else {
			for (Tile neighbor : tile.get_neighbors()) {
				if (!tile.clicked && neighbor.get_value() == 0 && !Tile.bombTiles.contains(neighbor)) {
					group.addAll(generateGroup(neighbor));
				}
			}
		}
		tile.clicked = false;
		
		return group;
	}
	
	
	
	public static Integer get_3BV() {
		HashSet<HashSet<Tile>> groups = new HashSet<HashSet<Tile>>();
		for (int row = 0; row < Main.HEIGHT; row++) {
			for (int col = 0; col < Main.WIDTH; col++) {
				Tile tile = Tile.tiles[row][col];
				if (!Tile.bombTiles.contains(tile)) {
					HashSet<Tile> tileGroup = generateGroup(tile);
					groups.add(tileGroup);
				}
			}
		}
		return groups.size();
	}
}
