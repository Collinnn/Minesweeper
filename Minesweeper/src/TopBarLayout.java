import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TopBarLayout {
	
	//Initialisation of Label to be updated outside this class
	public static Label labelBombCounter = new Label();
	public static Label labelTimer = new Label();
	
	//Method to make and return the layout of the top of the game
	public static BorderPane getTopBar() {
		
		//Initialising layouts and alignment
		BorderPane topItems = new BorderPane();
		
		HBox middleBox = new HBox();
		middleBox.setAlignment(Pos.CENTER);
		Region buffer = new Region();
		buffer.setPadding(new Insets(0,30,0,30));
		
		//Making the background of the bombcounter and the timer as well as making the rectangles act as borders for these
		StackPane leftPane = new StackPane();
		StackPane rightPane = new StackPane();
		Rectangle left = new Rectangle(90,40);
		Rectangle right = new Rectangle(90,40);
		left.setFill(Color.LIGHTGRAY);
		right.setFill(Color.LIGHTGRAY);
		left.setStyle("-fx-arc-height: 10; -fx-arc-width: 10;");
		right.setStyle("-fx-arc-height: 10; -fx-arc-width: 10;");
		
		//Setting the start value of the bombcounter
		labelBombCounter.setText(String.valueOf(Board.bombsNotFound));
		labelBombCounter.setTextFill(Color.BLACK);
		
		//Adding hint and reset buttons as well as calling their respective methods
		Button hint = new Button("Hints");
		hint.setOnAction(e-> Hints.getHint());
		
		Button reset = new Button("Reset");
		reset.setOnAction(e -> Main.board.reset());
		
		//Setting the start value of the timer
		labelTimer.setText("0");
		labelTimer.setTextFill(Color.BLACK);
		
		//Adding the bombcounter and timer as well as their respective backgrounds to their layouts
		leftPane.getChildren().addAll(left, labelBombCounter);
		rightPane.getChildren().addAll(right, labelTimer);
		
		//Adding the buttons to a layout as well as a region to make sure there is space between them
		middleBox.getChildren().addAll(hint,buffer,reset);
		
		middleBox.setPadding(new Insets(0,15,0,15));
		leftPane.setPadding(new Insets(10,0,10,20));
		rightPane.setPadding(new Insets(10,20,10,0));

		//Adding the layouts to the BorderPane which is then returned
		topItems.setLeft(leftPane);
		topItems.setRight(rightPane);
		topItems.setCenter(middleBox);
		
		return topItems;
	}
}

