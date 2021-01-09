import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Tile implements EventHandler<MouseEvent> {
	
	//For nu! static int variables
	private static final int SIZE = 40;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	static int xtile = WIDTH/SIZE;
	static int ytile = HEIGHT/SIZE;
	
	//Declare int variables
	private int x,y,bomb;
	private boolean clicked, flagged = false;
	
	//Felter for billeder, firkanter og Text
	private Rectangle shape;
	private Text text;
	private StackPane stack;
	
	//Billeder
	private static final Image bombImg = new Image("bomb1.png");
	private static final ImagePattern bombPattern = new ImagePattern(bombImg, 0, 0, bombImg.getWidth(), bombImg.getHeight(), false);
	
	private static final Image flagImg = new Image("Flag.png");
	private static final ImagePattern flagPattern = new ImagePattern(flagImg, 0, 0, flagImg.getWidth(), flagImg.getHeight(), false);
	
	// Array for all tiles and tiles with bombs
	public static Tile[][] tiles = new Tile[HEIGHT][WIDTH];
	public static ArrayList<Tile> bombTiles = new ArrayList<Tile>();
	
	//Tile konstruktøren
	public Tile(int x, int y) {
		
		this.x = x; 
		this.y = y;
		//this.bomb = bomb;
		//this.flag = flag; 
		
		this.stack = new StackPane();
		
		//Farver for firkant, uden noget kendt
		this.shape = new Rectangle(SIZE-2, SIZE-2);
		this.shape.setFill(Color.LIGHTGRAY);
		this.shape.setStroke(Color.GRAY);		
		
		//Text
		this.text = new Text("");
		this.text.setFont(Font.font(18));
		
		//Image
		//tileimage.setVisible(false);
		
		this.stack.getChildren().addAll(this.shape, this.text);//, tileimage);
		Main.root.add(this.stack, x, y);
		
		this.shape.setOnMouseClicked(this);
		
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
		if (this.flagged) {
			
		}
		else if (bombTiles.contains(this)) {
			for (Tile tile : bombTiles) {
				tile.shape.setFill(bombPattern);	
			}
		}
		else {
			this.shape.setFill(Color.WHITE);
			int val = this.get_value();
			if (val != 0) {
				this.text.setText(Integer.toString(val));
			}
		}
	}
	
	@Override
	public void handle(MouseEvent event) {
		if (!this.clicked) {
			if (event.getButton() == MouseButton.PRIMARY) {
				if (!this.flagged) {
					this.clicked = true;
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
			
			/*
			if(flag==0) {
				flag++;   Virker ikke på denne måde. 
				tileimage.setVisible(true);
			}
			*/
		}
	}	
}
