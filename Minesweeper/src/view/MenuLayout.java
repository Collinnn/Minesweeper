package view;
import java.io.FileNotFoundException;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuLayout {

	public static MenuBar getMenuBar() {
		//Making a menu at the top of the layout to show settings and highscores
		Menu fileMenu = new Menu("File");
		
		//Adding the items in the menu and making them open their relevant windows.
		MenuItem settings = new MenuItem("Settings...");
		settings.setOnAction(e -> SettingsWindow.display());
		MenuItem highscores = new MenuItem("Leaderboards...");
		highscores.setOnAction(e -> {
			try {
				HighscoresWindow.display();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		fileMenu.getItems().addAll(settings, highscores);
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu);
		
		return menuBar;
	}
}
