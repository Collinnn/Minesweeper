import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SettingsWindow {
	
	private static final int BHEIGHT = 15;
	private static final int MHEIGHT = 15;
	private static final int EHEIGHT = 15;
	private static int CHEIGHT = 15;
	
	private static final int BWIDTH = 20;
	private static final int MWIDTH = 20;
	private static final int EWIDTH = 20;
	private static int CWIDTH = 20;
	
	private static final int BBOMBS = 20;
	private static final int MBOMBS = 20;
	private static final int EBOMBS = 20;
	private static int CBOMBS = 20;

	public static void display() {	
		Stage window = new Stage();
		
		window.setTitle("Settings");
		
		HBox settingsMenu = new HBox();
		
		VBox radioMenu = new VBox();
		VBox submitButton = new VBox();
		VBox height = new VBox();
		VBox width = new VBox();
		VBox bombs = new VBox();
		
		ToggleGroup radioButtons = new ToggleGroup();
		
		RadioButton beginner = new RadioButton("Beginner");
		RadioButton medium = new RadioButton("Medium");
		RadioButton expert = new RadioButton("Expert");
		RadioButton custom = new RadioButton("Custom");
		
		beginner.setToggleGroup(radioButtons);
		medium.setToggleGroup(radioButtons);
		expert.setToggleGroup(radioButtons);
		custom.setToggleGroup(radioButtons);
		
		beginner.setSelected(true);
		Button submit = new Button("Submit");
		
		Text heightText = new Text("Height");
		Text widthText = new Text("Width");
		Text bombText = new Text("Bombs");
		
		Text bHeight = new Text(String.valueOf(BHEIGHT));
		Text mHeight = new Text(String.valueOf(MHEIGHT));
		Text eHeight = new Text(String.valueOf(EHEIGHT));
		
		Text bWidth = new Text(String.valueOf(BWIDTH));
		Text mWidth = new Text(String.valueOf(MWIDTH));
		Text eWidth = new Text(String.valueOf(EWIDTH));
		
		Text bBombs = new Text(String.valueOf(BBOMBS));
		Text mBombs = new Text(String.valueOf(MBOMBS));
		Text eBombs = new Text(String.valueOf(EBOMBS));
		
		height.getChildren().addAll(heightText,bHeight,mHeight,eHeight);
		height.setPadding(new Insets(0,10,10,10));
		width.getChildren().addAll(widthText,bWidth,mWidth,eWidth);
		width.setPadding(new Insets(0,10,10,10));
		bombs.getChildren().addAll(bombText,bBombs,mBombs,eBombs);
		bombs.setPadding(new Insets(0,10,10,10));
		
		
		submitButton.getChildren().add(submit);
		submitButton.setPadding(new Insets(10,0,0,20));
		
		radioMenu.getChildren().addAll(beginner,medium,expert,custom,submitButton);
		radioMenu.setPadding(new Insets(10,0,10,20));
		
		settingsMenu.getChildren().addAll(radioMenu,height,width,bombs);
		
		Scene scene = new Scene(settingsMenu);
		window.setScene(scene);
		window.showAndWait();
		
	}
}
