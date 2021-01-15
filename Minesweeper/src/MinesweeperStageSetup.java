import javafx.scene.layout.BorderPane;

public class MinesweeperStageSetup {

	public static BorderPane setStage() {
		
	BorderPane topLevel = new BorderPane();
	BorderPane midLevel = new BorderPane();
	
	midLevel.setTop(TopBarLayout.getTopBar());
	midLevel.setCenter(Board.grid);
	
	topLevel.setTop(MenuLayout.getMenuBar());
	topLevel.setCenter(midLevel);
	
	return topLevel;
	}
	
	
}
