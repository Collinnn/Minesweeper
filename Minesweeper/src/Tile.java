import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.InnerShadow;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Tile implements EventHandler<MouseEvent> {
	// Array for all tiles and tiles with bombs
	public static Tile[][] tiles = new Tile[Main.HEIGHT][Main.WIDTH];
	public static ArrayList<Tile> bombTiles = new ArrayList<Tile>();
	
	//Images
	private static final Image bombImg = new Image("bomb1.png");
	private static final ImagePattern bombPattern = new ImagePattern(bombImg, 0, 0, bombImg.getWidth(), bombImg.getHeight(), false);
	
	private static final Image flagImg = new Image("Flag.png");
	private static final ImagePattern flagPattern = new ImagePattern(flagImg, 0, 0, flagImg.getWidth(), flagImg.getHeight(), false);
	
	//Int & bool variables
	public static final int SIZE = 30;
	private static int row;
	private static int col;
	private boolean flagged = false;
	public static boolean clicked = false;
	
	//shape, text and stackpane
	private Rectangle shape;
	private static final Bloom bloomEffect = new Bloom();
	private static final InnerShadow innerShadowEffect = new InnerShadow(3, Color.BLUE);
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
		
		tiles[row][column] = this;
		
		stack = new StackPane();
		
		//Farver for firkant og dimensioner, ikke klikket
		shape = new Rectangle(SIZE, SIZE);
		shape.setFill(Color.LIGHTGRAY);
		shape.setStroke(Color.GRAY);		
		shape.setStyle("-fx-arc-height: 6; -fx-arc-width: 6;");
		
		//Highlights
		innerShadowEffect.setRadius(4);
		innerShadowEffect.setColor(Color.DARKBLUE);
		innerShadowEffect.setChoke(0.5);
		bloomEffect.setInput(innerShadowEffect);
		
		//Text
		text = new Text("");
		text.setFont(Font.font(null, FontWeight.BOLD, 18));
		
		stack.getChildren().addAll(shape, text);//, tileimage);
		Main.root.add(stack, column, row);
		
		//Setting up interactions
		shape.setOnMouseClicked(this);
		shape.setOnMouseEntered(this);
		shape.setOnMouseExited(this);
	}
	
	
	public static ArrayList<Tile> get_neighbors() {
		ArrayList<Tile> neighbors = new ArrayList<Tile>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (!(i == 0 && j == 0)) {
					try {
						neighbors.add(tiles[row+i][col+j]);
					}
					catch(Exception e) {
					}
				}
			}
		}
		return neighbors;
	}
	
	public static Integer get_value() {
		int neighborBombs = 0;
		for (Tile neighbor : get_neighbors()) {
    		if (Tile.bombTiles.contains(neighbor)) {
    			neighborBombs++;
    		}
    	}
		return neighborBombs;
	}
	
	public void reveal_tile() {
		clicked = true;
		shape.setEffect(null);
		if (bombTiles.contains(this)) {
			for (Tile[] tileRows : tiles) {
				for (Tile tile : tileRows) {
					tile.clicked = true;
				}
				
			}
			for (Tile tile : bombTiles) {
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
				for (Tile tile : get_neighbors()) {
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
	
	public static void reset() {
		for(int i =0; i< Main.HEIGHT; i++) {
			for(int j = 0; j<Main.WIDTH; j++) {
				tiles[i][j].shape.setFill(Color.LIGHTGRAY);
			}
		}
	}
	
	
	@Override
	public void handle(MouseEvent event) {
		if (!clicked) {
			if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
				shape.setEffect(bloomEffect);
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
						Main.label1.setText(String.valueOf(Main.bombsNotFound));
				}
				else {
					shape.setFill(flagPattern);
						Main.bombsNotFound --;
						Main.label1.setText(String.valueOf(Main.bombsNotFound));
				}
				flagged = !flagged;
			}
		}
	}	
}
