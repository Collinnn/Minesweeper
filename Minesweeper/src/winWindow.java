import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class winWindow {
	
	public static void display() {
		
		Stage window = new Stage();
		window.setTitle("You Won");
		window.initModality(Modality.APPLICATION_MODAL);
		
		
		
		
		Button submitHighscore = new Button("Submit Highscore");
		
		TextField nameBox = new TextField();
		nameBox.setPromptText("Enter 3 Initials here");
		
		window.showAndWait();
		
	}
}
