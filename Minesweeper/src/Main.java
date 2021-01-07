import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Random;
import java.util.ArrayList;


public class Main extends Application implements EventHandler<ActionEvent> {
	Button button;
	static int HEIGHT = 10;
	static int WIDTH = 10;
	static int NoOfBombs = 20;
	Button[][] tiles = new Button[WIDTH][WIDTH];
	ArrayList<Integer> bombTiles = new ArrayList<Integer>();
	
	Image img = new Image("bombe.png");
	
	static GridPane grid = new GridPane();
	
	public static void main(String[] args) {
		launch(args);
		
	}
	

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Minesweeper");
		stage.setScene(new Scene(grid));
		
	    for (int row = 0; row < 10; row++) {
	        for (int col = 0; col < 10; col++) {
	            Button button = new Button();
	            button.setPrefSize(40, 40);
	            grid.add(button, col, row);
            	tiles[row][col] = button;
	        }
	    }
	    
	    Random rand = new Random();
	    int bombs = 0;
	    while (bombs < NoOfBombs) {
	    	int randRow = rand.nextInt(HEIGHT);
	    	int randCol = rand.nextInt(WIDTH);
	    	int tile = WIDTH * randRow + randCol;
	    	if (!bombTiles.contains(tile)) {
	    		bombTiles.add(tile);
	    		ImageView view = new ImageView(img);
	            view.setFitWidth(20);
	        	view.setPreserveRatio(true);
	        		
	            tiles[randRow][randCol].setOnAction(e -> tiles[randRow][randCol].setGraphic(view));
	            System.out.println(tile);
	    		bombs++;
	    	}
	    }
	    
	    stage.show();
	}
	
	public static ArrayList<Integer> get_neighbors(int tile) {
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		
		
		return neighbors;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
