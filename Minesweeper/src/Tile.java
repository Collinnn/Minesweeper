import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
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


public class Tile implements EventHandler<MouseEvent> {
	
	//Images
	private static final Image bombImg = new Image("bomb1.png");
	private static final ImagePattern bombPattern = new ImagePattern(bombImg, 0, 0, bombImg.getWidth(), bombImg.getHeight(), false);
	
	private static final Image flagImg = new Image("Flag.png");
	private static final ImagePattern flagPattern = new ImagePattern(flagImg, 0, 0, flagImg.getWidth(), flagImg.getHeight(), false);
	
	//Int & bool variables
	private static final int SIZE = 30;
	public int row, col;
	public boolean flagged = false;
	public boolean clicked = false;
	
	//shape, effects, text and stackpane
	public Rectangle shape;
	private static final Bloom bloomEffect = new Bloom();
	private static final InnerShadow innerShadowEffect = new InnerShadow(3, Color.BLUE);
	private static Effect highlight;
	
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
		
		stack = new StackPane();
		
		//Farver for firkant, uden noget kendt
		shape = new Rectangle(SIZE, SIZE);
		shape.setFill(Color.LIGHTGRAY);
		shape.setStroke(Color.GRAY);		
		shape.setStyle("-fx-arc-height: 6; -fx-arc-width: 6;");
		
		//Highlights
		innerShadowEffect.setRadius(4);
		innerShadowEffect.setColor(Color.DARKBLUE);
		innerShadowEffect.setChoke(0.5);
		bloomEffect.setInput(innerShadowEffect);
		highlight = bloomEffect;
		
		//Text
		text = new Text("");
		text.setFont(Font.font(null, FontWeight.BOLD, 18));
		
		stack.getChildren().addAll(shape, text);//, tileimage);
		Board.grid.add(stack, column, row);
		
		//Setting up interactions
		shape.setOnMouseClicked(this);
		shape.setOnMouseEntered(this);
		shape.setOnMouseExited(this);

	}
	
	private void reveal_tile() {
		clicked = true;
		shape.setEffect(null);
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
					tile.shape.setFill(bombPattern);	
				}
			}
		}
		else {
			shape.setFill(Color.WHITE);
			int val = get_value();
			if (val == 0) {
				for (Tile tile : Board.get_neighbors(this)) {
					if (!tile.clicked) {
						tile.reveal_tile();
					}
				}
			}
			else {
				text.setText(Integer.toString(val));
				text.setFill(textFill[val]);
			}
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
	
	@Override
	public void handle(MouseEvent event) {
		if (!clicked) {
			if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
				shape.setEffect(highlight);
			}
			else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
				shape.setEffect(null);
			}
			
			if (event.getButton() == MouseButton.PRIMARY) {
				if (!flagged) {
					reveal_tile();
					if(!Main.firstclicked) {
						Main.time.timeline.play();
						Main.firstclicked = true;
					}
				}
			}
			else if (event.getButton() == MouseButton.SECONDARY) {
				if (flagged) {
					shape.setFill(Color.LIGHTGRAY);
						Main.bombsNotFound ++;
						Main.labelBombCounter.setText(String.valueOf(Main.bombsNotFound));
				}
				else {
					shape.setFill(flagPattern);
						Main.bombsNotFound --;
						Main.labelBombCounter.setText(String.valueOf(Main.bombsNotFound));
				}
				flagged = !flagged;
			}
		}
	}	
}
