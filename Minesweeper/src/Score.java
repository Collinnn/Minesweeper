import java.util.HashSet;


public class Score {
	
	private static int score = 0;
	
	public static Integer get3BV() {
		/*
		 * Method returns minimum number of
		 * clicks necessary to win (same as number of groups).
		 */
		int groups = 0;
		HashSet<Tile> checkedTiles = new HashSet<Tile>();
		
		// Increments groups counter if a new group of tiles (unchecked tiles) is found
		for (Tile[] tileRow : Board.tiles) {
			for (Tile tile : tileRow) {
				// Checks if current tile has been checked or contains a bomb
				if (!checkedTiles.contains(tile) && !Board.bombTiles.contains(tile)) {
					checkedTiles.addAll(Board.getGroup(tile)); 
					groups++;
				}
			}
		}
		return groups;
	}
	
	public static int getScore() {
		int divisor = (get3BV()/ Board.timesClicked) == 0?1:get3BV()/ Board.timesClicked;
		score =  Main.time.getTime() / divisor;
		if(score == 0) score++;
		return score;
	}
	
}
