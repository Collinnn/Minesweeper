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
<<<<<<< Updated upstream
	private static final int SIZE = 40;
	private int x,y,bomb;
	private boolean clicked, flagged = false;
=======
	public static final int SIZE = 30;
	private int row, col;
	private boolean flagged = false;
	public boolean clicked = false;
>>>>>>> Stashed changes
	
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
	public Tile(int x, int y) {
		
		this.x = x; 
		this.y = y;
		//this.bomb = bomb;
		//this.flag = flag; 
		
		tiles[y][x] = this;
		
		this.stack = new StackPane();
		
		//Farver for firkant, uden noget kendt
		this.shape = new Rectangle(SIZE, SIZE);
		this.shape.setFill(Color.LIGHTGRAY);
		this.shape.setStroke(Color.GRAY);		
		this.shape.setStyle("-fx-arc-height: 6; -fx-arc-width: 6;");
		
		innerShadowEffect.setRadius(4);
		innerShadowEffect.setColor(Color.DARKBLUE);
		innerShadowEffect.setChoke(0.5);
		bloomEffect.setInput(innerShadowEffect);
		
		//Text
		this.text = new Text("");
		this.text.setFont(Font.font(null, FontWeight.BOLD, 18));
		
		
		this.stack.getChildren().addAll(this.shape, this.text);//, tileimage);
		Main.root.add(this.stack, x, y);
		
<<<<<<< Updated upstream
		this.shape.setOnMouseClicked(this);
		
=======
		shape.setOnMouseClicked(this);
		shape.setOnMouseEntered(this);
		shape.setOnMouseExited(this);

>>>>>>> Stashed changes
	}
	
	public ArrayList<Tile> get_neighbors() {
		ArrayList<Tile> neighbors = new ArrayList<Tile>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (!(i == 0 && j == 0)) {
					try {
						neighbors.add(tiles[this.x+i][this.y+j]);
					}
					catch(Exception e) {
					}
				}
			}
		}
		return neighbors;
	}
	
	public Integer get_value() {
		int neighborBombs = 0;
		for (Tile neighbor : this.get_neighbors()) {
    		if (Tile.bombTiles.contains(neighbor)) {
    			neighborBombs++;
    		}
    	}
		return neighborBombs;
	}
	
	public void reveal_tile() {
<<<<<<< Updated upstream
		this.clicked = true;
		
=======
		clicked = true;
		shape.setEffect(null);
>>>>>>> Stashed changes
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
			this.shape.setFill(Color.WHITE);
			int val = this.get_value();
			if (val == 0) {
				for (Tile tile : this.get_neighbors()) {
					if (!tile.clicked) {
						tile.reveal_tile();
					}
				}
			}
			else {
				this.text.setText(Integer.toString(val));
				this.text.setFill(textFill[val]);
			}
		}
	}
	
	@Override
	public void handle(MouseEvent event) {
<<<<<<< Updated upstream
		if (!this.clicked) {
=======
		if (!clicked) {
			if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
				shape.setEffect(bloomEffect);
			}
			else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
				shape.setEffect(null);
			}
			
>>>>>>> Stashed changes
			if (event.getButton() == MouseButton.PRIMARY) {
				if (!this.flagged) {
					this.reveal_tile();
				}
			}
			else if (event.getButton() == MouseButton.SECONDARY) {
				if (this.flagged) {
					this.shape.setFill(Color.LIGHTGRAY);
				}
				else {
					this.shape.setFill(flagPattern);
				}
				this.flagged = !this.flagged;
			}
		}
	}	
}
