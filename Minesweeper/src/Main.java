import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	
	//The following objects are initialised at the top as public and static so they can be used in other classes
	public static Timer time = new Timer();
	public static Board board = new Board();
	public static Stage root = new Stage();
	
	public static void main(String[] args) throws IOException {
		time.timeline.pause();
		Highscores.createFile();
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		root = stage;
		root.setTitle("Minesweeper");
		root.getIcons().add(new Image("bomb1.png"));
		root.setScene(new Scene(StageSetup.getStage()));
		root.setResizable(false);
		root.show();
	}
}