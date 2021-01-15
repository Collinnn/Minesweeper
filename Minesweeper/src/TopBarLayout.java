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

	public static Label labelBombCounter = new Label();
	public static Label labelTimer = new Label();
	
	
	public static BorderPane getTopBar() {
		
		BorderPane topItems = new BorderPane();
		
		HBox middleBox = new HBox();
		middleBox.setAlignment(Pos.CENTER);
		Region buffer = new Region();
		buffer.setPadding(new Insets(0,60,0,60));
		
		
		StackPane leftPane = new StackPane();
		StackPane rightPane = new StackPane();
		Rectangle left = new Rectangle(90,40);
		Rectangle right = new Rectangle(90,40);
		left.setFill(Color.BLACK);
		right.setFill(Color.BLACK);
		
		
		labelBombCounter = new Label(String.valueOf(Main.bombsNotFound));
		labelBombCounter.setTextFill(Color.WHITE);
		
		Button hint = new Button("Hints");
		hint.setOnAction(e-> Hints.getHint());
		
		Button reset = new Button("Reset");
		reset.setOnAction(e -> Main.board.reset());
		
		labelTimer.setText("0");
		labelTimer.setTextFill(Color.WHITE);
		
		
		leftPane.getChildren().addAll(left, labelBombCounter);
		rightPane.getChildren().addAll(right, labelTimer);
		
		middleBox.getChildren().addAll(hint,buffer,reset);
		
		leftPane.setPadding(new Insets(10,0,10,20));
		rightPane.setPadding(new Insets(10,20,10,0));

		
		topItems.setLeft(leftPane);
		topItems.setRight(rightPane);
		topItems.setCenter(middleBox);
		
		return topItems;
	}
}
