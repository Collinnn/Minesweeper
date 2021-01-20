import java.util.function.UnaryOperator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class winWindow {
	
	private static int LEN = 3;
	
	public static void display() {
		
		Stage window = new Stage();
		window.setTitle("You Won");
		window.initModality(Modality.APPLICATION_MODAL);
		
		HBox scoreAndTime = new HBox();
		HBox score = new HBox();
		HBox difficulty = new HBox();
		VBox nameBoxLayout = new VBox();
		VBox winWindowLayout = new VBox();
		
		score.setPadding(new Insets(0,30,0,0));
		scoreAndTime.setPadding(new Insets(20,0,20,0));
		scoreAndTime.setAlignment(Pos.TOP_CENTER);
		difficulty.setPadding(new Insets(20,0,20,0));
		difficulty.setAlignment(Pos.CENTER);
		nameBoxLayout.setPadding(new Insets(20,0,20,0));
		nameBoxLayout.setAlignment(Pos.CENTER);
		
		
		Text scoreText = new Text("Din score er: " + String.valueOf(Board.timesClicked));
		
		Text timeText = new Text("Din tid: " + Main.time.toString());
		
		Text difficultyText = new Text();
		
		switch(Board.difficulty) {
		case 0:
			difficultyText.setText("Difficulty: Beginner");
			break;
		case 1:
			difficultyText.setText("Difficulty: Medium");
			break;
		case 2:
			difficultyText.setText("Difficulty: Expert");
			break;
		case 3:
			difficultyText.setText("Difficulty: Custom " + String.valueOf(Board.height + "x" + Board.width));
			break;
		}
		
		Text nameBoxText = new Text("Write Initials");
		TextField nameBox = new TextField("XXX");
		nameBox.setMaxSize(40,24);
		
		// This short code segment is borrowed from kleopatra's comment on this Stackoverflow question 
		// as we couldn't find a way to implement it by ourselves. It sets a max length of the textfield and any new
		// text entered replaces the earlier characters in the textfield
		// https://stackoverflow.com/questions/15159988/javafx-2-2-textfield-maxlength
		UnaryOperator<Change> modifyChange = c -> {
			if(c.isContentChange()) {
				int newLength = c.getControlNewText().length();
				if(newLength > LEN) {
					String tail = c.getControlNewText().substring(newLength-LEN,newLength);
					c.setText(tail);
					
					int oldLength = c.getControlText().length();
					c.setRange(0,oldLength);
				}
			}
			return c;
		};
		
		nameBox.setTextFormatter(new TextFormatter(modifyChange));
		// The code segment ends here
		
		
		Button saveHighscore = new Button("Save Highscore");
		
		score.getChildren().add(scoreText);
		scoreAndTime.getChildren().addAll(score,timeText);
		difficulty.getChildren().add(difficultyText);
		nameBoxLayout.getChildren().addAll(nameBoxText,nameBox);
		
		winWindowLayout.getChildren().addAll(scoreAndTime,difficulty,nameBoxLayout,saveHighscore);
		
		winWindowLayout.setPrefSize(300, 300);
		winWindowLayout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(winWindowLayout);
		window.setScene(scene);
		window.showAndWait();
		winWindowLayout.requestFocus();
		
	}
}
