package controller;

import model.Board;
import model.Highscores;
import model.Score;
import view.WinWindow;

public class WinWindowController extends WinWindow {
	
	//Handles submitting highscores in WinWindow
	public static void handle() {
			switch(Board.difficulty) {
			case 0:
				try{
					Highscores.write(nameBox.getText(), Score.getScore(), Highscores.beginnerFile);
					window.close();
				}catch(Exception FileNotFoundException) {
					
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
	}
}
