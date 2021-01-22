package controller;

import main.Main;
import model.Board;
import view.SettingsWindow;

//Main responsibility Christopher Zwinge
public class SettingsController extends SettingsWindow {
	
	//Handles changing the difficulty of the game
	public static void handle() {
			if(beginner.isSelected()) {
				Board.height = BHEIGHT;
				Board.width = BWIDTH;
				Board.noOfBombs = BBOMBS;
				Board.difficulty = 0;
				Main.board.reset();
			}else if(medium.isSelected()) {
				Board.height = MHEIGHT;
				Board.width = MWIDTH;
				Board.noOfBombs = MBOMBS;
				Board.difficulty = 1;
				Main.board.reset();
			}else if(expert.isSelected()) {
				Board.height = EHEIGHT;
				Board.width = EWIDTH;
				Board.noOfBombs = EBOMBS;
				Board.difficulty = 2;
				Main.board.reset();
			} 
			//Tries to take the values from the textfields and checks whether they are within the bounds we have setup
			//If the values pasted are not numbers the default is chosen and an error is displayed,
			//if they are numbers they are changed to the chosen values unless the values are 
			//smaller than the minimum size or bigger than the maximum size.
			//If this occurs the errortext is shown.
			else if(custom.isSelected()) {
				try {
					int h = Integer.valueOf(cHeight.getText());
					int w = Integer.valueOf(cWidth.getText());
					int b = Integer.valueOf(cBombs.getText());
					if(h<4 || h>CHMAX) {
						h = CHEIGHT;
						cHError.setText("Max is 30 min is 4");
						cHError.setVisible(true);
					}else {
						cHError.setVisible(false);
					}
					
					if(w<4 || w > CWMAX) {
						w = CWIDTH;
						cWError.setVisible(true);
					}else {
						cWError.setVisible(false);
					}
					
					if(b<1) {
						b= 1;
						cBError.setText("Min is 1");
						cBError.setVisible(true);
					}else if(b >= (h*w)-9) {
						b= (h*w)-9;
						cBError.setText("Set to Max " + String.valueOf((h*w)-9));
						cBError.setVisible(true);
					}else {
						cBError.setVisible(false);
					}
					Board.height = h;
					Board.width = w;
					Board.noOfBombs = b;
					Board.difficulty = 3;
					Main.board.reset();
				}catch(Exception IllegalArgumentException){
					cHError.setText("Enter only numbers");
					cHError.setVisible(true);
					Board.height = CHEIGHT;
					Board.width = CWIDTH;
					Board.noOfBombs = CBOMBS;
					Board.difficulty = 3;
					Main.board.reset();
				}
			}
			//Commands to make sure the game window is resized when the content is and to center it.
			Main.root.sizeToScene();
			Main.root.centerOnScreen();
	}
}
