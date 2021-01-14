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

public class SimpleMinesweeper extends Application implements EventHandler<ActionEvent> {
	Button button;
	static int size = 40;
	static int width = 800;
	static int height = 600;
	static int xtile = width/size;
	static int ytile = height/size;
	static int NoOfBombs = 20;
	static Button[][] tiles = new Button[xtile][ytile];
	static ArrayList<Button> bombTiles = new ArrayList<Button>();
	
	Image img = new Image("bombe.png");
	
	static GridPane grid = new GridPane();
	
	public static void main(String[] args) {
		launch(args);
	}
	

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Minesweeper");
		stage.setScene(new Scene(grid));
		
	    for (int row = 0; row < xtile; row++) {
	        for (int col = 0; col < ytile; col++) {
	            Button button = new Button();
	            button.setPrefSize(40, 40);
	            grid.add(button, col, row);
            	tiles[row][col] = button;
	        }
	    }
	    
	    Random rand = new Random();
	    int bombs = 0;
	    while (bombs < NoOfBombs) {
	    	int randRow = rand.nextInt(xtile);
	    	int randCol = rand.nextInt(ytile);
	    	Button tile = tiles[randRow][randCol];
	    	if (!bombTiles.contains(tile)) {
	    		bombTiles.add(tile);
	    		
	    		ImageView view = new ImageView(img);
	            view.setFitWidth(20);
	        	view.setPreserveRatio(true);
	            tiles[randRow][randCol].setOnAction(e ->tiles[randRow][randCol].setGraphic(view));
	           
	    		bombs++;
	    	}
	    }
	    	    
	    for (int row = 0; row < xtile; row++) {
	    	for (int col = 0; col < ytile; col++) {
	    		int neighborBombs = 0;
	    		for (Button tile : get_neighbors(row, col)) {
		    		if (bombTiles.contains(tile)) {
		    			neighborBombs++;
		    		}
		    	}
	    		if (!bombTiles.contains(tiles[row][col])) {
	    			if (neighborBombs != 0) {
	    				int finalRow = row;
		    			int finalCol = col;
		    			int finalNeighborBombs = neighborBombs;
		    			tiles[row][col].setOnAction(e -> tiles[finalRow][finalCol].setText(Integer.toString(finalNeighborBombs)));
	    			}
	    		}
	    	}
	    }
	    stage.show();
	}
	
	
	
	public static ArrayList<Button> get_neighbors(int row, int col) {
		ArrayList<Button> neighbors = new ArrayList<Button>();
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
	
	
		
		
	
	
	@Override
	public void handle(ActionEvent tiles) {
		

	
}
}