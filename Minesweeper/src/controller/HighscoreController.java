package controller;
import java.io.File;
import java.io.FileNotFoundException;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Highscores;
public class HighscoreController {
	
	
	public static  void Controller(Label label, ListView<String> listView,File file,String input) {
		label.setText(input);
		listView.getItems().clear();
		try {
			Highscores.listViewFill(listView,file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}
	
