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
	
	// Declaration of constants for the different difficulties
	private static final int BHEIGHT = 9;
	private static final int MHEIGHT = 16;
	private static final int EHEIGHT = 30;
	private static int CHEIGHT = 20;
	private static int CHMAX = 30;
	
	private static final int BWIDTH = 9;
	private static final int MWIDTH = 30;
	private static final int EWIDTH = 30;
	private static int CWIDTH = 40;
	private static int CWMAX = 62;
	
	private static final int BBOMBS = 10;
	private static final int MBOMBS = 99;
	private static final int EBOMBS = 160;
	private static int CBOMBS = 300;

	
	// Display method to show the settingsMenu as well determine it's behaviors
	public static void display() {	
		
		Stage window = new Stage();
		
		window.setTitle("Settings");
		
		// Initialisation of the different layout used in the settingsMenu
		HBox settingsMenu = new HBox();
		VBox radioMenu = new VBox();
		VBox newGameButton = new VBox();
		VBox height = new VBox();
		VBox heightInset = new VBox();
		VBox width = new VBox();
		VBox widthInset = new VBox();
		VBox bombs = new VBox();
		VBox bombsInset = new VBox();
		
		//Initialisation of buttons to select difficulty as well as togglegroup to ensure only one can be selected
		ToggleGroup radioButtons = new ToggleGroup();
		RadioButton beginner = new RadioButton("Beginner");
		RadioButton medium = new RadioButton("Medium");
		RadioButton expert = new RadioButton("Expert");
		RadioButton custom = new RadioButton("Custom");
		
		beginner.setToggleGroup(radioButtons);
		medium.setToggleGroup(radioButtons);
		expert.setToggleGroup(radioButtons);
		custom.setToggleGroup(radioButtons);
		
		
		//Selects the current difficulty and activates the relevant button
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
		
		
		//Button to confirm the changes and start a new game
		Button newGame = new Button("New Game");
		
		//topText for the different columns
		Text heightText = new Text("Height");
		Text widthText = new Text("Width");
		Text bombText = new Text("Bombs");
		
		//Text to show the sizes of and the amount of bombs for each difficulty
		Text bHeight = new Text(String.valueOf(BHEIGHT));
		Text mHeight = new Text(String.valueOf(MHEIGHT));
		Text eHeight = new Text(String.valueOf(EHEIGHT));
		
		
		Text bWidth = new Text(String.valueOf(BWIDTH));
		Text mWidth = new Text(String.valueOf(MWIDTH));
		Text eWidth = new Text(String.valueOf(EWIDTH));
		
		
		Text bBombs = new Text(String.valueOf(BBOMBS));
		Text mBombs = new Text(String.valueOf(MBOMBS));
		Text eBombs = new Text(String.valueOf(EBOMBS));
		
		//Makes editable textfields which shows the default values of the custom difficulty
		//If custom was already selected the current values are shown instead
		TextField cHeight = new TextField(String.valueOf(CHEIGHT));
		TextField cWidth = new TextField(String.valueOf(CWIDTH));
		TextField cBombs = new TextField(String.valueOf(CBOMBS));
		if(custom.isSelected()) {
			cHeight.setText(String.valueOf(Board.height));
			cWidth.setText(String.valueOf(Board.width));
			cBombs.setText(String.valueOf(Board.noOfBombs));
		}
		
		//Sets the default width's of the textfields as well as preparing error text to be shown
		//if the value is outside the max or minimum for the field.
		cHeight.setPrefWidth(60);
		Text cHError = new Text("Max is 30 min is 4");
		cHError.setFill(Color.RED);
		cHError.setVisible(false);
		
		cWidth.setPrefWidth(60);
		Text cWError = new Text("Max is 62 min is 4");
		cWError.setFill(Color.RED);
		cWError.setVisible(false);
		
		cBombs.setPrefWidth(60);
		Text cBError = new Text("Set to Max 2000");
		cBError.setFill(Color.RED);
		cBError.setVisible(false);
		
		//Makes the button retrieve the relevant information from the constants to change the difficulty and reset the game
		//when pressed.
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
			} 
			//Tries to take the values from the textfields and checks whether they are within the bounds we have setup
			//If the values pasted are not numbers the default is chosen and an error is displayed,
			//if they are numbers they are changed to the chosen values unless the values are 
			//smaller than the minimum size or bigger than the maximum size.
			//If this occurs the errortext is shown.
			else if(custom.isSelected()) {
				try {
					int h = Integer.valueOf(cHeight.getText());
					int w = Integer.valueOf(cWidth.getText());
					int b = Integer.valueOf(cBombs.getText());
					if(h<4 || h>CHMAX) {
						h = CHEIGHT;
						cHError.setText("Max is 30 min is 4");
						cHError.setVisible(true);
					}else {
						cHError.setVisible(false);
					}
					
					if(w<4 || w > CWMAX) {
						w = CWIDTH;
						cWError.setVisible(true);
					}else {
						cWError.setVisible(false);
					}
					
					if(b<1) {
						b= 1;
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
					cHError.setText("Enter only numbers");
					cHError.setVisible(true);
					Board.height = CHEIGHT;
					Board.width = CWIDTH;
					Board.noOfBombs = CBOMBS;
					Board.difficulty = 3;
					Main.board.reset();
				}
			}
			//Commands to make sure the game window is resized when the content is and to center it.
			Main.root.sizeToScene();
			Main.root.centerOnScreen();
		});
		
		
		
		//Makes sure the textfields for the custom difficulty are only selectable if the difficulty is chosen
		cHeight.disableProperty().bind(custom.selectedProperty().not());
		cWidth.disableProperty().bind(custom.selectedProperty().not());
		cBombs.disableProperty().bind(custom.selectedProperty().not());
		
		
		
		//Adding the Text and textfields to the layout and adding Padding to make it align with each other as well as
		//to add spacing between elements 
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
		
		
		
		
		//Adding the rest of the elements into their respective layouts as well as adding Padding
		newGameButton.getChildren().add(newGame);
		newGameButton.setPadding(new Insets(10,0,0,0));
		
		radioMenu.getChildren().addAll(beginner,medium,expert,custom,newGameButton);
		radioMenu.setPadding(new Insets(20,0,10,20));
		
		//Adding all of the layouts to a single final layout
		settingsMenu.getChildren().addAll(radioMenu,height,width,bombs);
		
		
		//Adding the final layout to the scene and showing the window
		Scene scene = new Scene(settingsMenu);
		window.setScene(scene);
		window.show();
		
	}
}
