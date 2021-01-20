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
	
	//Images
	private static final Image bombImg = new Image("bomb1.png");
	private static final ImagePattern bombPattern = new ImagePattern(bombImg, 0, 0, bombImg.getWidth(), bombImg.getHeight(), false);
	
	//Int & bool variables
	private static final int SIZE = 30;
	public int row, col;
	public boolean flagged = false;
	public boolean clicked = false;
	
	//shape, effects, text and stackpane
	//public Rectangle shape;
	
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
	
	//Tile constructor
	public Tile(int row, int column) {
		
		this.row = row; 
		this.col = column; 
		
		Board.tiles[row][column] = this;
		
		
		// Shape customization with methods from JavaFX Rectangle class
		setWidth(SIZE);
		setHeight(SIZE);
		setFill(Color.LIGHTGRAY);
		setStroke(Color.GRAY);		
		setStyle("-fx-arc-height: 6; -fx-arc-width: 6;");
		
		// Highlight
		innerShadow.setRadius(4);
		innerShadow.setChoke(0.5);
		bloom.setInput(innerShadow);
		highlight = bloom;
		
		// Text
		text = new Text("");
		text.setFont(Font.font(null, FontWeight.BOLD, 18));
		
		stack = new StackPane();
		
		stack.getChildren().addAll(this, text);
		Board.grid.add(stack, column, row);
		
		//Setting up interactions
		setOnMouseClicked(this);
		setOnMouseEntered(this);
		setOnMouseExited(this);
	}
	
	public void reveal_tile() {
		clicked = true;
		set_highlight(null);
		if (Board.bombTiles.contains(this)) {
			for (Tile[] tileRows : Board.tiles) {
				for (Tile tile : tileRows) {
					tile.clicked = true;
				}
			}
			for (Tile tile : Board.bombTiles) {
				if (tile.flagged) {
					
				}
				else {
					tile.setFill(bombPattern);	
				}
			}
		}
		else {
			Board.timesClicked++;
			Board.winCounter --;
			setFill(Color.WHITE);
			int val = get_value();
			if (val == 0) {
				for (Tile neighbor : Board.get_neighbors(this)) {
					if (!neighbor.clicked) {
						if (neighbor.flagged) {
							Board.bombsNotFound++;
							TopBarLayout.labelBombCounter.setText(String.valueOf(Board.bombsNotFound));
						}
						neighbor.reveal_tile();
					}
				}
			}
			else {
				text.setText(Integer.toString(val));
				text.setFill(textFill[val]);
			}
		}
		if(Board.winCounter == 0) {
			
		}
	}
	
	public Integer get_value() {
		int neighborBombs = 0;
		for (Tile neighbor : Board.get_neighbors(this)) {
    		if (Board.bombTiles.contains(neighbor)) {
    			neighborBombs++;
    		}
    	}
		return neighborBombs;
	}
	
	public void set_highlight(Color color) {
		if (color == null) {
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
