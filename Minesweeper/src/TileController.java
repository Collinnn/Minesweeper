import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;



public class TileController {

	static final Image flagImg = new Image("Flag.png");
	static final ImagePattern flagPattern = new ImagePattern(flagImg, 0, 0, flagImg.getWidth(), flagImg.getHeight(), false);

	public static void onClick(Tile tile, MouseEvent event) {
		if (!tile.clicked) {
			if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
				tile.setHighlight(Color.DARKBLUE);
			}
			else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
				tile.setHighlight(null);
			}
			
			if (event.getButton() == MouseButton.PRIMARY) {
				if (!tile.flagged) {
					if(!Board.firstclicked) {
						Board.firstClick(tile);
					}
					else {
						tile.reveal_tile();
					}
				}
			}
			else if (event.getButton() == MouseButton.SECONDARY) {
				if (tile.flagged) {
					tile.setFill(Color.LIGHTGRAY);
						Board.bombsNotFound++;
						TopBarLayout.labelBombCounter.setText(String.valueOf(Board.bombsNotFound));
				}
				
				else {
					tile.setFill(flagPattern);
						Board.bombsNotFound--;
						TopBarLayout.labelBombCounter.setText(String.valueOf(Board.bombsNotFound));
				}
				
				tile.flagged = !tile.flagged;
			}
		}
	}	
}
