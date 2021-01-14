import java.io.FileNotFoundException;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuLayout {

	public static MenuBar getMenuBar() {
		Menu fileMenu = new Menu("_File");
		
		MenuItem settings = new MenuItem("Settings");
		settings.setOnAction(e -> SettingsWindow.display());
		MenuItem leaderboards = new MenuItem("Leaderboards");
		leaderboards.setOnAction(e -> {
			//autogenereret
			try {
				HighscoresWindow.display();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		MenuItem exit = new MenuItem("Exit");
		fileMenu.getItems().addAll(settings, leaderboards, exit);
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu);
		
		return menuBar;
	}
}
