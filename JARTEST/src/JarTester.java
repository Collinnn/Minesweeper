

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JarTester extends Application{
	
	private static Scene scene;

	public static void main(String[] args) {
		launch();
	}


	@Override
	public void start(Stage topLevelStage) throws Exception {
		
		topLevelStage.setWidth(400);
		topLevelStage.setHeight(400);
		topLevelStage.show();
		
		// Create a Stack Pane as the root of the scene-tree
		Group group = new Group();
		
		// build the scene
		scene = new Scene(group);
		scene.setFill(Color.WHITE);

		
		//Create a button
	      Button button = new Button("Toggle"); 
	     
	      
	      //Creating a Grid Pane 
	      GridPane gridPane = new GridPane();    
	      
	      //Setting size for the pane 
	      gridPane.setMinSize(100, 100); 
	      gridPane.add(button,0,0);
	     
	      
	      group.getChildren().add(gridPane);
		
		  // reacting to buttons
	      button.setOnAction(this::handleClickToggle);
	    
		
		
		topLevelStage.setScene(scene);
		
	}
	
	private void handleClickToggle(ActionEvent event) {
		if(scene.getFill()==Color.WHITE) {
			scene.setFill(Color.BLACK);
		} else {
			scene.setFill(Color.WHITE);
		}
		
	}


}
