import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Tile extends StackPane {
	//Declare int variabler
	private int x,y,bomb,flag;
	private boolean open;
	
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
		
	}
	
	
	
	
	
	
}
