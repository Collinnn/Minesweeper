import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Timer time = new Timer();
	public static Board board = new Board();
	
	public static void main(String[] args) {
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