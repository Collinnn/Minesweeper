import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Timer time = new Timer(); 
	
	public static Label labelBombCounter = new Label();
	public static Label labelTimer = new Label();
	public static boolean firstclicked = false;
	public static int bombsNotFound;
	public static Board board = new Board();
	

	public static void main(String[] args) {
		bombsNotFound = Board.NoOfBombs;
		time.timeline.pause();
		launch(args);
	}
	
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Minesweeper");
		stage.setScene(new Scene(MinesweeperStageSetup.setStage()));
		stage.show();
	}
}