package controller;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import main.Main;
import model.Board;
import view.Tile;
import view.TopBarLayout;
import view.WinWindow;


//Main responsibility Jacob Martens
public class TileController {
	
	// Flag image
	static final Image flagImg = new Image("Flag.png");
	static final ImagePattern flagPattern = new ImagePattern(flagImg, 0, 0, flagImg.getWidth(), flagImg.getHeight(), false);

	public static void onClick(Tile tile, MouseEvent event) {
		/*
		 * Method handles mouse events on the board.
		 * 
		 */
		if (!tile.clicked) {
			// Highlight tile when mouse enters
			if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
				tile.setHighlight(Color.DARKBLUE);
			}
			// Remove highlight from tile when mouse exits
			else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
				tile.setHighlight(null);
			}
			
			// Handles left clicks
			if (event.getButton() == MouseButton.PRIMARY) {
				if (!tile.flagged) {
					Board.timesClicked++;
					
					// Call firstClick-method on first click. Otherwise, reveal clicked tile
					if (!Board.firstclicked) {
						Board.firstClick(tile);
					}
					else {
						tile.revealTile();
					}
					
					// Displays winning screen when game is won
					if (Board.winCounter == 0) {
						Board.disableAll();
						Main.time.pausecounter(true);
						WinWindow.display();
					}
				}
			}
			
			// Handles right clicks
			else if (event.getButton() == MouseButton.SECONDARY) {
				// Unflag tiles containing a flag and increment bomb counter
				if (tile.flagged) {
					tile.setFill(Color.LIGHTGRAY);
						Board.bombsNotFound++;
						TopBarLayout.labelBombCounter.setText(String.valueOf(Board.bombsNotFound));
				}
				
				// Place a flag and decrement bomb counter
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
