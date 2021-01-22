package view;


import java.io.File;
import java.io.FileNotFoundException;

import controller.HighscoreController;
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
import model.Highscores;

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
			HighscoreController.Controller(difficultyLabel,listView,Highscores.beginnerFile,"Beginner");
		});
		
		//When button is clicked, the listView should be cleared and items should be added
		//according to selected difficulty
		mediumButton.setOnAction(e -> {
			HighscoreController.Controller(difficultyLabel,listView,Highscores.mediumFile,"Medium");
		});
		
		//When button is clicked, the listView should be cleared and items should be added
		//according to selected difficulty
		expertButton.setOnAction(e -> {
			HighscoreController.Controller(difficultyLabel,listView,Highscores.expertFile,"Expert");
		});
		
		Scene scene = new Scene(primaryLayout);
		window.setScene(scene);
		window.showAndWait();
		}
		
		//Fills listView with highscores from chosen difficulty
		public static void listViewFill(ListView<String> listview, File f) throws FileNotFoundException {
			Highscores.read(f);
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
