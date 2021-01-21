import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Highscores {
	
	public static File beginnerFile = new File("beginnerHighscores.txt");
	public static File mediumFile = new File("mediumHighscores.txt");
	public static File expertFile = new File("expertHighscores.txt");
	
	private static final int size = 10;
	private static String[] highscores = new String[size];
	private static Scanner input;
	private static PrintStream output;
	
	public static void createFile() throws IOException {
		if(!beginnerFile.exists()) {
			beginnerFile.createNewFile();
			reset(beginnerFile);
		} 
		if(!mediumFile.exists()) {
			mediumFile.createNewFile();
			reset(mediumFile);
		}
		if(!expertFile.exists()) {
			expertFile.createNewFile();
			reset(expertFile);
		}
	}
	
	//reads from file and loads highscores into array
	public static void read(File f) throws FileNotFoundException {
		input = new Scanner(f);
		for(int i = 0; i < highscores.length; i++) {
			highscores[i] = input.next();
		}
	}
	
	public static boolean isNewHighscore(int score, File f) throws FileNotFoundException {
		read(f);
		boolean newHighscore = false;
		for(int i = highscores.length - 1; i >= 0; i--) {
			if(Integer.parseInt(highscores[i].split(":")[0]) == -1) {
				newHighscore = true;
			}
		}
		for(int i = highscores.length - 1; i >= 0; i--) {
			if(score < Integer.parseInt(highscores[i].split(":")[0])) {
				newHighscore = true;
			}
		}
		return newHighscore;
	}
	
	//loads new highscore into array and updates data-file containing highscores
	public static void write(String name, int score, File f) throws FileNotFoundException {
		read(f);
		boolean firstLoopEntered = false;
		output = new PrintStream(f);
		for(int i = highscores.length - 1; i >= 0; i--) {
			if(Integer.parseInt(highscores[i].split(":")[0]) == -1) {
				firstLoopEntered = true;
				highscores[i] = score + ":" + name;
				break;
			}
		}
		if(!firstLoopEntered) {
			for(int i = highscores.length - 1; i >= 0; i--) {
				if(score < Integer.parseInt(highscores[i].split(":")[0])) {
					highscores[i] = score + ":" + name;
					break;
				}
			}
		}
		sort(highscores.length -1);
		for(int i = 0; i < highscores.length; i++) {
			output.println(highscores[i]);
		}
	}
	
	
	private static void sort(int i) {
		boolean orderIsChanged = false;
		if(i == 0) {
			orderIsChanged = true; 
		}
		while(!orderIsChanged) {
			String tempString1 = highscores[i];
			
			if(Integer.parseInt(highscores[i-1].split(":")[0]) == -1){
				highscores[i] = "-1:***";
				highscores[i-1] = tempString1;
				sort(i-1);
			}
			if(Integer.parseInt(highscores[i].split(":")[0]) != -1) {
				if(Integer.parseInt(highscores[i-1].split(":")[0])> Integer.parseInt(highscores[i].split(":")[0]) ) {
					String tempString2 = highscores[i-1];
					highscores[i-1] = tempString1;
					highscores[i] = tempString2;
					sort(i-1);
					orderIsChanged = true;
				}
				else {
					i--;
				}
			}else {
				orderIsChanged = true;
			}
			
			if(i == 0) {
				orderIsChanged = true;
			}
		}
	}
	
	//resets data-file to default state
	public static void reset(File f) throws FileNotFoundException {
		output = new PrintStream(f);
		for(int i = 0; i < highscores.length; i++) {
			output.println("-1:***");
		}
	}
	
	public static String[] getHighscores() {
		String[] copy = highscores.clone();
		return copy;
	}
}
