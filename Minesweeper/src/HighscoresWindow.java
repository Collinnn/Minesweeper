
import java.io.File;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
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
	
	public static void display() throws FileNotFoundException {
			
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Leaderboards");
		window.setResizable(false);
			
		ListView<String> listView = new ListView<>();
		
		VBox headerLayout = new VBox();
		Label difficultyLabel = new Label();
		Label playerLabel = new Label("Player\tHighscore");
		
		HBox buttonLayout = new HBox();
		Button beginnerButton = new Button("Beginner");
		Button mediumButton = new Button("Medium");
		Button expertButton = new Button("Expert");
		buttonLayout.getChildren().addAll(beginnerButton,mediumButton,expertButton);
		buttonLayout.setAlignment(Pos.CENTER);
		
		VBox primaryLayout = new VBox();
		primaryLayout.setPrefHeight(291);
		primaryLayout.getChildren().addAll(headerLayout,listView,buttonLayout);
		
		//Fills listView with highscores from initial difficulty
		listViewFill(listView,Highscores.beginnerFile);
		//Text to show which difficulty is initially selected
		difficultyLabel.setText("Beginner");
		playerLabel.setPadding(new Insets(0,0,0,8));
		difficultyLabel.setPadding(new Insets(0,0,0,8));
		
		headerLayout.getChildren().addAll(difficultyLabel,playerLabel);
		
		//When button is clicked, the listView should be cleared and items should be added
		//according to selected difficulty
		beginnerButton.setOnAction(e -> {
			difficultyLabel.setText("Beginner");
			listView.getItems().clear();
			try {
				listViewFill(listView,Highscores.beginnerFile);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		
		//When button is clicked, the listView should be cleared and items should be added
		//according to selected difficulty
		mediumButton.setOnAction(e -> {
			difficultyLabel.setText("Medium");
			listView.getItems().clear();
			try {
				listViewFill(listView,Highscores.mediumFile);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		
		//When button is clicked, the listView should be cleared and items should be added
		//according to selected difficulty
		expertButton.setOnAction(e -> {
			difficultyLabel.setText("Expert");
			listView.getItems().clear();
			try {
				listViewFill(listView,Highscores.expertFile);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		
		Scene scene = new Scene(primaryLayout);
		window.setScene(scene);
		window.showAndWait();
		}
		
		//This method fills listView highscores according to selected difficulty
		public static void listViewFill(ListView<String> listview, File f) throws FileNotFoundException {
			Highscores.read(f);
			//-1:*** is just an initial score and should not be shown as
			//a highscore in listView. Instead it should show -. 
			for(int i = 0; i < Highscores.getHighscores().length; i++) {
				if(Highscores.getHighscores()[i].equals("-1:***")) {
					listview.getItems().add("-\t\t-");
				} else {
					listview.getItems().add(Highscores.getHighscores()[i].split(":")[1] + "\t\t" + 
				Highscores.getHighscores()[i].split(":")[0]);
				}
			}
		}

}
