import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Tile extends GridPane {
	//Declare int variabler
	private int x,y,bomb,flag;
	private boolean clicked;
	
	//For nu! static int variabler
	static int size = 40;
	static int width = 800;
	static int height = 600;
	static int xtile = width/size;
	static int ytile = height/size;
	
	//Felter for billeder, firkanter og Text
	private final Rectangle felt = new Rectangle(xtile-2,ytile);
	private final ImageView tileimage = new ImageView();
	private final Text text = new Text();
	
	//Billeder
	Image img = new Image("bombe.png");
	
	//Tile konstruktøren
	public Tile(int x, int y, int bomb, int flag) {
		
		this.x = x; 
		this.y = y;
		this.bomb = bomb;
		this.flag = flag; 
		
			//Farver for firkant, uden noget kendt
		felt.setFill(Color.GRAY);
		felt.setFill(Color.BLACK);
		
		//If bomb 
		if (bomb>0) {
			tileimage.setImage(img);
		}
		
		if (flag>0) {
			//tileimage.setImage(flag); MANGLER BILLEDE
			//Something something disable left click
		}
		
		//Text
		text.setFont(Font.font(18));
		text.setVisible(false);
		
		//Image
		tileimage.setVisible(false);
		
		getChildren().addAll(felt,text,tileimage);
		
		setOnMouseClicked(e -> {
			if(e.getButton() == MouseButton.PRIMARY) {
				if(flag==0) {
					
				}
				
			}
			else if (e.getButton() == MouseButton.SECONDARY) {
				if(flag==0) {
					// flag++;   Virker ikke på denne måde. 
					tileimage.setVisible(true);
				}
			}
			
		});
	}
	
	//Handles Leftclick only
	public void onclick() {
		if(clicked) {
			return; 
		}
		
		if (bomb>0) {
			tileimage.setVisible(true);
			return; 
		}
		clicked = true;
		felt.setFill(Color.LIGHTGRAY);
		
	}
	
	
	
	
	
	
}
