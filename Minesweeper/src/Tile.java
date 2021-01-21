import javafx.scene.image.Image;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Tile extends Rectangle implements EventHandler<MouseEvent> {
	/*
	 * The Tile class uses JavaFX's Rectangle object to create the tiles on the board.
	 * The class uses a constructor to create individual Tile objects.
	 */
	
	private static final Image bombImg = new Image("bomb1.png");
	private static final ImagePattern bombPattern = new ImagePattern(bombImg, 0, 0, bombImg.getWidth(), bombImg.getHeight(), false);
	
	private static final int SIZE = 30; // Tile width and height
	public int row, col;
	public boolean flagged = false;
	public boolean clicked = false;
	public boolean tempUsed = false; // Used for temporarily marking a tile
	
	// Highlight effect for tiles
	private Bloom bloom = new Bloom();
	private final InnerShadow innerShadow = new InnerShadow(3, Color.DARKBLUE);
	private Effect highlight;
	
	private Text text;
	private StackPane stack;
	
	private Color[] textFill = new Color[] {
			Color.BLUE, 
			Color.GREEN, 
			Color.RED, 
			Color.DARKBLUE, 
			Color.DARKGREEN, 
			Color.DARKRED,
			Color.CRIMSON,
			Color.BLACK
			};
	
	public Tile(int row, int column) {
		
		this.row = row; 
		this.col = column; 
		
		Board.tiles[row][column] = this; // The tile will be accessed through this array/matrix
		
		
		// Sets the appearance of the tiles
		setWidth(SIZE);
		setHeight(SIZE);
		setFill(Color.LIGHTGRAY);
		setStroke(Color.GRAY);		
		setStyle("-fx-arc-height: 6; -fx-arc-width: 6;"); // Rounded corners
		
		// Sets the highlight appearance
		innerShadow.setRadius(4);
		innerShadow.setChoke(0.5);
		bloom.setInput(innerShadow);
		highlight = bloom;
		
		
		text = new Text("");
		text.setFont(Font.font(null, FontWeight.BOLD, 18));
		text.setVisible(false);
		
		stack = new StackPane();
		stack.getChildren().addAll(this, text); // Stacks text on top of tile
		Board.grid.add(stack, column, row);
		
		// Set mouse events for the tile
		setOnMouseClicked(this);
		setOnMouseEntered(this);
		setOnMouseExited(this);
	}
	
	public void revealTile() {
		/*
		 * Reveals tile when clicked.
		 * Revealing a bomb reveals all bombs.
		 * Revealing a blank tile reveals the entire group of tiles.
		 */
		clicked = true;
		setHighlight(null);
		// Reveals all bombs and disables all tiles when a bomb is clicked
		if (Board.bombTiles.contains(this)) {
			Main.time.pausecounter(true);
			Board.disableAll();
			for (Tile tile : Board.bombTiles) {
				if (tile.flagged) {
					
				}
				else {
					tile.setFill(bombPattern);	
				}
			}
		}	
		else {
			Board.winCounter --;
			setFill(Color.WHITE);
			int val = getValue();
			// When clicking on a blank tile: Reveal all tiles belonging to that tiles group
			if (val == 0) {
				for (Tile neighbor : Board.getNeighbors(this)) {
					if (!neighbor.clicked) {
						// Increment bomb counter if a tile in the group is flagged
						if (neighbor.flagged) {
							Board.bombsNotFound++;
							TopBarLayout.labelBombCounter.setText(String.valueOf(Board.bombsNotFound));
						}
						neighbor.revealTile();
					}
				}
			}
			// Show the tile's number
			else {
				text.setText(Integer.toString(val));
				text.setFill(textFill[val]);
				text.setVisible(true);
			}
		}
	}
	
	public Integer getValue() {
		/*
		 * Returns the Tile's value/number
		 */
		int neighborBombs = 0;
		// Counts number of neighbors containing bombs
		for (Tile neighbor : Board.getNeighbors(this)) {
    		if (Board.bombTiles.contains(neighbor)) {
    			neighborBombs++;
    		}
    	}
		return neighborBombs;
	}
	
	public void setHighlight(Color color) {
		/*
		 * Sets the highlight color for the Tile
		 */
		if (color == null) { // null = no highlight effect
			setEffect(null);
		}
		else {
			innerShadow.setColor(color);
			setEffect(highlight);
		}
	}
	
	@Override
	public void handle(MouseEvent event) {
		TileController.onClick(this, event);
	}
}
