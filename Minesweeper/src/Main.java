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
<<<<<<< Updated upstream
		Board board = new Board();

		
		BorderPane topItems = new BorderPane();
		
		HBox middleBox = new HBox();
		middleBox.setAlignment(Pos.CENTER);
		
		StackPane leftPane = new StackPane();
		StackPane rightPane = new StackPane();
		Rectangle left = new Rectangle(90,40);
		Rectangle right = new Rectangle(90,40);
		left.setFill(Color.BLACK);
		right.setFill(Color.BLACK);
		
		
		labelBombCounter = new Label(String.valueOf(bombsNotFound));
		labelBombCounter.setTextFill(Color.WHITE);
		
		Button reset = new Button("reset");
		reset.setOnAction(e -> {
			board.reset();
			
		});
		
		labelTimer.setText("0");
		labelTimer.setTextFill(Color.WHITE);
		
		
		leftPane.getChildren().addAll(left, labelBombCounter);
		rightPane.getChildren().addAll(right, labelTimer);
		
		middleBox.getChildren().add(reset);
		
		leftPane.setPadding(new Insets(10,0,10,20));
		rightPane.setPadding(new Insets(10,20,10,0));

		
		topItems.setLeft(leftPane);
		topItems.setRight(rightPane);
		topItems.setCenter(middleBox);
		
		
		
		Menu fileMenu = new Menu("_File");
		
		MenuItem settings = new MenuItem("Settings");
		settings.setOnAction(e -> SettingsWindow.display());
		MenuItem leaderboards = new MenuItem("Leaderboards");
		leaderboards.setOnAction(e -> {
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
		
		
		BorderPane topLevel = new BorderPane();
		BorderPane midLevel = new BorderPane();
		
		midLevel.setTop(topItems);
		midLevel.setCenter(Board.grid);
		
		topLevel.setTop(menuBar);
		topLevel.setCenter(midLevel);
		
		stage.setScene(new Scene(topLevel));
		
=======
		stage.setScene(new Scene(MinesweeperStageSetup.setStage()));
>>>>>>> Stashed changes
		stage.show();
	}
}