import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Highscores {
	
	private static final int size = 10;
	private String[] highscores = new String[size];
	private Scanner input;
	private PrintStream output;
	
	
	public Highscores(File f) throws FileNotFoundException {
		this.read(f);
	}
	
	//reads from data file. Use on start-up.
	public void read(File f) throws FileNotFoundException {
		input = new Scanner(f);
		for(int i = 0; i < highscores.length; i++) {
			highscores[i] = input.next();
		}
	}
	
	//writes to data file. Use upon losing or winning. 
	public void write(String name, int score, File f) throws FileNotFoundException {
		output = new PrintStream(f);
		for(int i = 0; i < highscores.length; i++) {
			if(score > Integer.parseInt(highscores[i].split(":")[1])) {
				highscores[i] = name + ":" + score;
				break;
			}
		}
		for(int i = 0; i < highscores.length; i++) {
			output.println(highscores[i]);
		}
	}
	
	//resets data file to basic format.
	public void reset(File f) throws FileNotFoundException {
		output = new PrintStream(f);
		for(int i = 0; i < highscores.length; i++) {
			output.println("name:-1");
		}
	}
	
	public String[] getHighscores() {
		String[] copy = highscores.clone();
		return copy;
	}
}
