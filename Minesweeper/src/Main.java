import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
<<<<<<< HEAD
	public static Timer time = new Timer();
=======
	public static Timer time = new Timer(); 
	
	public static boolean firstclicked = false;
	public static int bombsNotFound;
>>>>>>> parent of 4b3996a... Merge branch 'master' of https://github.com/Collinnn/Minesweeper
	public static Board board = new Board();
	

	public static void main(String[] args) {
<<<<<<< HEAD
=======
		bombsNotFound = Board.NoOfBombs;
>>>>>>> parent of 4b3996a... Merge branch 'master' of https://github.com/Collinnn/Minesweeper
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