import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Arrays;

public class Highscores {
	
	public static File beginnerFile = new File("Highscores.txt");
	public static File mediumFile = new File("Highscores.txt");
	public static File expertFile = new File("Highscores.txt");
	
	private static final int size = 10;
	private static String[] highscores = new String[size];
	private static Scanner input;
	private static PrintStream output;
	
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
		for(int i = highscores.length - 1; i >= 0; i++) {
			if(score < Integer.parseInt(highscores[i].split(":")[0])) {
				newHighscore = true;
			}
		}
		return newHighscore;
	}
	
	//loads new highscore into array and updates data-file containing highscores
	public static void write(String name, int score, File f) throws FileNotFoundException {
		read(f);
		output = new PrintStream(f);
		for(int i = highscores.length - 1; i >= 0; i++) {
			if(score < Integer.parseInt(highscores[i].split(":")[0])) {
				highscores[i] = score + ":" + name;
				Arrays.sort(highscores);
				break;
			}
		}
		for(int i = 0; i < highscores.length; i++) {
			output.println(highscores[i]);
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
