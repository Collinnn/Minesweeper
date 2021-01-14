
import java.io.File;
import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HighscoresWindow {

	//Just for testing purposes
	
	public static void display() throws FileNotFoundException {
			
		Stage window = new Stage();
		window.setTitle("Leaderboards");
			
		ListView<String> beginnerListView = new ListView<>();
		listViewAdd(beginnerListView);
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
		window.showAndWait();	
		
		}
	
		public static void listViewAdd(ListView<String> listview) throws FileNotFoundException {
			//temporary for testing
			File f = new File("Highscores.txt");
			Highscores beginnerHighscore = new Highscores(f);
			for(int i = 0; i < beginnerHighscore.getHighscores().length; i++) {
				listview.getItems().add(beginnerHighscore.getHighscores()[i]);
			}
		}

}
