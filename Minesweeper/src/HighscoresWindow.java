
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HighscoresWindow {

	public static void display() {
			
		Stage window = new Stage();
		window.setTitle("Leaderboards");
			
		ListView<String> beginnerListView = new ListView<>();
		ListView<String> mediumListView = new ListView<>();
		ListView<String> expertListView; new ListView<>();
			
		HBox buttonLayout = new HBox();
		Button beginnerButton = new Button("Beginner");
		Button mediumButton = new Button("Medium");
		Button expertButton = new Button("Expert");
		buttonLayout.setAlignment(Pos.CENTER);
		buttonLayout.getChildren().addAll(beginnerButton,mediumButton,expertButton);
			
		HBox headerLayout = new HBox();
		Label playerLabel = new Label("Player");
		Label highscoreLabel = new Label("Highscore");
		headerLayout.getChildren().addAll(playerLabel,highscoreLabel);
			
		VBox primaryLayout = new VBox();
		primaryLayout.getChildren().addAll(headerLayout,beginnerListView,buttonLayout);
			
		Scene scene = new Scene(primaryLayout, 400,300);
		window.setScene(scene);
		window.show();
		window.showAndWait();	
		
		}

}
