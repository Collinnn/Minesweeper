package view;
import javafx.scene.layout.BorderPane;
import model.Board;

public class StageSetup {

	public static BorderPane getStage() {
		
	BorderPane topLevel = new BorderPane();
	BorderPane midLevel = new BorderPane();
	
	midLevel.setTop(TopBarLayout.getTopBar());
	midLevel.setCenter(Board.grid);
	
	topLevel.setTop(MenuLayout.getMenuBar());
	topLevel.setCenter(midLevel);
	
	return topLevel;
	}
}
