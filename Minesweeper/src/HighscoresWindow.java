
//might not be needed
import java.io.File;
import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HighscoresWindow {

	//Just for testing purposes
	
	public static void display() throws FileNotFoundException {
			
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Leaderboards");
		
		//This should be moved to model
		File beginnerFile = new File("Highscores.txt");
		File mediumFile = new File("Highscores.txt");
		File expertFile = new File("Highscores.txt");
			
		ListView<String> beginnerListView = new ListView<>();
		listViewAdd(beginnerListView, beginnerFile);
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
			
		Scene scene = new Scene(primaryLayout);
		window.setScene(scene);
		window.showAndWait();	
		
		}
	
		public static void listViewAdd(ListView<String> listview, File f) throws FileNotFoundException {
			Highscores h = new Highscores(f);
			for(int i = 0; i < h.getHighscores().length; i++) {
				listview.getItems().add(h.getHighscores()[i].split(":")[0] + "\t" + 
			h.getHighscores()[i].split(":")[1]);
			}
		}

}
