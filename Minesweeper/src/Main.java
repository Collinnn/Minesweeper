import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Timer time = new Timer();
	public static Board board = new Board();
	public static Stage root = new Stage();
	
	public static void main(String[] args) {
		time.timeline.pause();
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		root = stage;
		root.setTitle("Minesweeper");
		root.getIcons().add(new Image("bomb1.png"));
		root.setScene(new Scene(MinesweeperStageSetup.setStage()));
		root.setResizable(false);
		root.show();
	}
}