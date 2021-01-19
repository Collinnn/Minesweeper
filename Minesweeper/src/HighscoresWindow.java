
import java.io.File;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HighscoresWindow {
	
	public static void display() throws FileNotFoundException {
			
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Leaderboards");
		window.setResizable(false);
			
		ListView<String> listView = new ListView<>();
		listViewFill(listView,Highscores.beginnerFile);
			
		VBox headerLayout = new VBox();
		Label difficultyLabel = new Label();
		difficultyLabel.setText("Beginner");
		difficultyLabel.setPadding(new Insets(0,0,0,8));
		Label playerLabel = new Label("Player\tHighscore");
		playerLabel.setPadding(new Insets(0,0,0,8));
		headerLayout.getChildren().addAll(difficultyLabel,playerLabel);
		
		HBox buttonLayout = new HBox();
		Button beginnerButton = new Button("Beginner");
		beginnerButton.setOnAction(e -> {
			difficultyLabel.setText("Beginner");
			listView.getItems().clear();
			try {
				listViewFill(listView,Highscores.beginnerFile);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		
		Button mediumButton = new Button("Medium");
		mediumButton.setOnAction(e -> {
			difficultyLabel.setText("Medium");
			listView.getItems().clear();
			try {
				listViewFill(listView,Highscores.mediumFile);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		Button expertButton = new Button("Expert");
		expertButton.setOnAction(e -> {
			difficultyLabel.setText("Expert");
			listView.getItems().clear();
			try {
				listViewFill(listView,Highscores.expertFile);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		
		buttonLayout.getChildren().addAll(beginnerButton,mediumButton,expertButton);
			
		VBox primaryLayout = new VBox();
		primaryLayout.getChildren().addAll(headerLayout,listView,buttonLayout);
		
		Scene scene = new Scene(primaryLayout,173,291);
		window.setScene(scene);
		window.showAndWait();
		}
		
		public static void listViewFill(ListView<String> listview, File f) throws FileNotFoundException {
			Highscores h = new Highscores(f);
			for(int i = 0; i < h.getHighscores().length; i++) {
				if(h.getHighscores()[i].equals("***:-1")) {
					listview.getItems().add("-\t\t-");
				} else {
					listview.getItems().add(h.getHighscores()[i].split(":")[0] + "\t\t" + 
				h.getHighscores()[i].split(":")[1]);
				}
			}
		}

}
