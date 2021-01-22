package controller;
import java.io.File;
import java.io.FileNotFoundException;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import view.HighscoresWindow;

//Main responsibility Simon Peter Sundt Poulsen
public class HighscoreController {
	
	//Method changes difficulty label, clears and updates listView according to chosen difficulty
	//Works with HighscoresWindow's buttons
	public static  void Controller(Label label, ListView<String> listView,File file,String input) {
		label.setText(input);
		listView.getItems().clear();
		try {
			HighscoresWindow.listViewFill(listView,file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}
	
