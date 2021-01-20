import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SettingsWindow {
	
	private static final int BHEIGHT = 15;
	private static final int MHEIGHT = 15;
	private static final int EHEIGHT = 15;
	private static int CHEIGHT = 15;
	private static int CHMAX = 30;
	
	private static final int BWIDTH = 20;
	private static final int MWIDTH = 20;
	private static final int EWIDTH = 20;
	private static int CWIDTH = 20;
	private static int CWMAX = 62;
	
	private static final int BBOMBS = 20;
	private static final int MBOMBS = 20;
	private static final int EBOMBS = 20;
	private static int CBOMBS = 20;

	public static void display() {	
		Stage window = new Stage();
		
		window.setTitle("Settings");
		
		//Int af settings vindue
		HBox settingsMenu = new HBox();
		VBox radioMenu = new VBox();
		VBox newGameButton = new VBox();
		VBox height = new VBox();
		VBox heightInset = new VBox();
		VBox width = new VBox();
		VBox widthInset = new VBox();
		VBox bombs = new VBox();
		VBox bombsInset = new VBox();
		
		ToggleGroup radioButtons = new ToggleGroup();
		//Radiobutton naming and connecting
		RadioButton beginner = new RadioButton("Beginner");
		RadioButton medium = new RadioButton("Medium");
		RadioButton expert = new RadioButton("Expert");
		RadioButton custom = new RadioButton("Custom");
		
		beginner.setToggleGroup(radioButtons);
		medium.setToggleGroup(radioButtons);
		expert.setToggleGroup(radioButtons);
		custom.setToggleGroup(radioButtons);
		
		
		// Toggles the difficulty change to int between 0-3 to make it read the current difficulty from someplace else
		switch(Board.difficulty) {
		case 0:
			beginner.setSelected(true);
			break;
		case 1:
			medium.setSelected(true);
			break;
		case 2:
			expert.setSelected(true);
			break;
		case 3:
			custom.setSelected(true);
			break;
		}
		
		
		//Naming
		Button newGame = new Button("New Game");
		
		Text heightText = new Text("Height");
		Text widthText = new Text("Width");
		Text bombText = new Text("Bombs");
		
		//Get value and set it to the different tekst parts of the difficulty's
		Text bHeight = new Text(String.valueOf(BHEIGHT));
		Text mHeight = new Text(String.valueOf(MHEIGHT));
		Text eHeight = new Text(String.valueOf(EHEIGHT));
		
		
		Text bWidth = new Text(String.valueOf(BWIDTH));
		Text mWidth = new Text(String.valueOf(MWIDTH));
		Text eWidth = new Text(String.valueOf(EWIDTH));
		
		
		Text bBombs = new Text(String.valueOf(BBOMBS));
		Text mBombs = new Text(String.valueOf(MBOMBS));
		Text eBombs = new Text(String.valueOf(EBOMBS));
		
		
		TextField cHeight = new TextField(String.valueOf(CHEIGHT));
		TextField cWidth = new TextField(String.valueOf(CWIDTH));
		TextField cBombs = new TextField(String.valueOf(CBOMBS));
		if(custom.isSelected()) {
			cHeight.setText(String.valueOf(Board.height));
			cWidth.setText(String.valueOf(Board.width));
			cBombs.setText(String.valueOf(Board.noOfBombs));
		}
		
		cHeight.setPrefWidth(60);
		//Error message if wrong input
		Text cHError = new Text("Max is 30 min is 1");
		cHError.setFill(Color.RED);
		cHError.setVisible(false);
		
		cWidth.setPrefWidth(60);
		Text cWError = new Text("Max is 62 min is 1");
		cWError.setFill(Color.RED);
		cWError.setVisible(false);
		
		cBombs.setPrefWidth(60);
		Text cBError = new Text("Set to Max 2000");
		cBError.setFill(Color.RED);
		cBError.setVisible(false);
		
		
		newGame.setOnAction(e ->{
			if(beginner.isSelected()) {
				Board.height = BHEIGHT;
				Board.width = BWIDTH;
				Board.noOfBombs = BBOMBS;
				Board.difficulty = 0;
				Main.board.reset();
			}else if(medium.isSelected()) {
				Board.height = MHEIGHT;
				Board.width = MWIDTH;
				Board.noOfBombs = MBOMBS;
				Board.difficulty = 1;
				Main.board.reset();
			}else if(expert.isSelected()) {
				Board.height = EHEIGHT;
				Board.width = EWIDTH;
				Board.noOfBombs = EBOMBS;
				Board.difficulty = 2;
				Main.board.reset();
			}else if(custom.isSelected()) {
				try {
					int h = Integer.valueOf(cHeight.getText());
					int w = Integer.valueOf(cWidth.getText());
					int b = Integer.valueOf(cBombs.getText());
					if(h<1 || h>CHMAX) {
						h = CHEIGHT;
						cHError.setVisible(true);
					}else {
						cHError.setVisible(false);
					}
					
					if(w<1 || w > CWMAX) {
						w = CWIDTH;
						cWError.setVisible(true);
					}else {
						cWError.setVisible(false);
					}
					
					if(b<1) {
						b= CBOMBS;
						cBError.setText("Min is 1");
						cBError.setVisible(true);
					}else if(b >= (h*w)-9) {
						b= (h*w)-9;
						cBError.setText("Set to Max " + String.valueOf((h*w)-9));
						cBError.setVisible(true);
					}else {
						cBError.setVisible(false);
					}
					Board.height = h;
					Board.width = w;
					Board.noOfBombs = b;
					Board.difficulty = 3;
					Main.board.reset();
				}catch(Exception IllegalArgumentException){
					Board.height = CHEIGHT;
					Board.width = CWIDTH;
					Board.noOfBombs = CBOMBS;
					Board.difficulty = 3;
					Main.board.reset();
				}
			}
			Main.root.sizeToScene();
			Main.root.centerOnScreen();
		});
		
		
		
		
		cHeight.disableProperty().bind(custom.selectedProperty().not());
		cWidth.disableProperty().bind(custom.selectedProperty().not());
		cBombs.disableProperty().bind(custom.selectedProperty().not());
		
		
		
		
		heightInset.getChildren().addAll(bHeight,mHeight,eHeight);
		heightInset.setPadding(new Insets(0,0,0,8));
		widthInset.getChildren().addAll(bWidth,mWidth,eWidth);
		widthInset.setPadding(new Insets(0,0,0,8));
		bombsInset.getChildren().addAll(bBombs,mBombs,eBombs);
		bombsInset.setPadding(new Insets(0,0,0,8));
		
		height.getChildren().addAll(heightText,heightInset,cHeight,cHError);
		height.setPadding(new Insets(0,10,10,10));
		width.getChildren().addAll(widthText,widthInset,cWidth,cWError);
		width.setPadding(new Insets(0,10,10,10));
		bombs.getChildren().addAll(bombText,bombsInset,cBombs,cBError);
		bombs.setPadding(new Insets(0,10,10,10));
		
		
		
		
		
		newGameButton.getChildren().add(newGame);
		newGameButton.setPadding(new Insets(10,0,0,0));
		
		radioMenu.getChildren().addAll(beginner,medium,expert,custom,newGameButton);
		radioMenu.setPadding(new Insets(20,0,10,20));
		
		settingsMenu.getChildren().addAll(radioMenu,height,width,bombs);
		
		
		
		Scene scene = new Scene(settingsMenu);
		window.setScene(scene);
		window.showAndWait();
		
	}
}
