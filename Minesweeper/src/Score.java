import java.util.HashSet;


public class Score {
	
	private static int score = 0;
	
	public static Integer get3BV() {
		//HashSet is used in that every item is unique and the order does not matter.
		HashSet<HashSet<Tile>> groups = new HashSet<HashSet<Tile>>();
		for (int row = 0; row < Board.height; row++) {
			for (int col = 0; col < Board.width; col++) {
				Tile tile = Board.tiles[row][col];
				if (!Board.bombTiles.contains(tile)) {
					HashSet<Tile> tileGroup = Board.getGroup(tile);
					groups.add(tileGroup);
				}
			}
		}
		return groups.size();
	}
	
	public static int getScore(Timer time) {
		
		score =  time.getTime() / (get3BV() / Board.timesClicked);
		
		return score;
	}
	
	public static int resetScore() {
		return score = 0;
	}
}
