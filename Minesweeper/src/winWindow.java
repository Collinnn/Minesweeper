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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void display() {
		//Making the window that pops up if you click on all the required tiles
		Stage window = new Stage();
		window.setTitle("You Won");
		//Forces the user to deal with the win screen before continuing
		window.initModality(Modality.APPLICATION_MODAL);
		
		//Setting up the layouts
		HBox scoreAndTime = new HBox();
		HBox score = new HBox();
		HBox difficulty = new HBox();
		HBox highscore = new HBox();
		VBox nameBoxLayout = new VBox();
		VBox winWindowLayout = new VBox();
		
		//Adding spacing and alignment to the layouts and making certain layouts invisible unless it is a highscore
		score.setPadding(new Insets(0,30,0,0));
		scoreAndTime.setPadding(new Insets(20,0,20,0));
		scoreAndTime.setAlignment(Pos.TOP_CENTER);
		difficulty.setPadding(new Insets(20,0,20,0));
		difficulty.setAlignment(Pos.CENTER);
		highscore.setPadding(new Insets(20,0,20,0));
		highscore.setAlignment(Pos.CENTER);
		highscore.setVisible(false);
		nameBoxLayout.setPadding(new Insets(20,0,20,0));
		nameBoxLayout.setAlignment(Pos.CENTER);
		nameBoxLayout.setVisible(false);
		
		//Text boxes which retrieves and shows the score as well as time taken to solve the game
		Text scoreText = new Text("Din score er: " + Score.getScore());
		
		Text timeText = new Text("Din tid: " + Main.time.toString());
		
		//Initialising textbox to show the difficulty
		Text difficultyText = new Text();
		
		//The following text objects and textfield are used when a new highscore is made
		Text highscoreText = new Text("New Highscore!!!");
		
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
		saveHighscore.setVisible(false);
		
		//This code checks which difficulty it is currently on and sets the difficulty text to that difficulty
		//It then checks whether the score achieved was a new highscore and reveals the relevant objects for that
		//The custom difficulty doesn't have a leaderboard
		switch(Board.difficulty) {
		case 0:
			difficultyText.setText("Difficulty: Beginner");
			try {
				if((Highscores.isNewHighscore(Score.getScore(),Highscores.beginnerFile))) {
					highscore.setVisible(true);
					nameBoxLayout.setVisible(true);
					saveHighscore.setVisible(true);
				}else {
					
				}
			}catch(Exception FileNotFoundException) {
				System.out.println("file not found");
			}
			
			break;
		case 1:
			difficultyText.setText("Difficulty: Medium");
			try {
				if((Highscores.isNewHighscore(Score.getScore(),Highscores.mediumFile ))) {
					highscore.setVisible(true);
					nameBoxLayout.setVisible(true);
					saveHighscore.setVisible(true);
				}
			}catch(Exception FileNotFoundException) {
			}
			break;
		case 2:
			difficultyText.setText("Difficulty: Expert");
			try {
				if((Highscores.isNewHighscore(Score.getScore(),Highscores.expertFile ))) {
					highscore.setVisible(true);
					nameBoxLayout.setVisible(true);
					saveHighscore.setVisible(true);
				}
			}catch(Exception FileNotFoundException) {
			}
			break;
		case 3:
			difficultyText.setText("Difficulty: Custom " + String.valueOf(Board.height + "x" + Board.width));
			break;
		}
	
		//This segment writes the highscore and the initials of the player to the relevant text file
		saveHighscore.setOnAction(e ->{
			switch(Board.difficulty) {
			case 0:
				try{
					Highscores.write(nameBox.getText(), Score.getScore(), Highscores.beginnerFile);
					window.close();
				}catch(Exception FileNotFoundException) {
					System.out.println("File not found");
				}
				break;
			case 1:
				try{
					Highscores.write(nameBox.getText(), Score.getScore(), Highscores.mediumFile);
					window.close();
				}catch(Exception FileNotFoundException) {
					
				}
				break;
			case 2:
				try{
					Highscores.write(nameBox.getText(), Score.getScore(), Highscores.expertFile);
					window.close();
				}catch(Exception FileNotFoundException) {
					
				}
				break;
			}
		});
		
		// The objects are added to the relevant layouts and they are then all collected in one layout for the winWindow
		score.getChildren().add(scoreText);
		scoreAndTime.getChildren().addAll(score,timeText);
		difficulty.getChildren().add(difficultyText);
		highscore.getChildren().add(highscoreText);
		nameBoxLayout.getChildren().addAll(nameBoxText,nameBox);
		
		winWindowLayout.getChildren().addAll(scoreAndTime,difficulty,nameBoxLayout,saveHighscore);
		
		winWindowLayout.setPrefSize(300, 300);
		winWindowLayout.setAlignment(Pos.CENTER);
		
		//The layouts are addded to a scene and then shown
		Scene scene = new Scene(winWindowLayout);
		window.setScene(scene);
		window.showAndWait();
		
	}
}
