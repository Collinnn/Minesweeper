import java.io.*;
import java.util.*;

public class Highscores {
	
	private static final int size = 10;
	private int[] highscores = new int[size];

	public Highscores(File f) throws FileNotFoundException {//use on start up
		this.read(f);
	}
	
	public void read(File f) throws FileNotFoundException {
		Scanner input = new Scanner(f);
		int count = 0;
		for (int i = 0; i < size; i++) {
			highscores[i] = input.nextInt();
		}
	}//reads file
	
	public void write(int score, File f) throws FileNotFoundException {//use upon winning game
		PrintStream output = new PrintStream(f);
		for(int i = 0; i < size; i++) {
			if (score > highscores[i]) {
				highscores[i] = score;
				break;
			}
		} //checks if highscore beaten
		for (int i = 0; i < size; i++) {
			output.print(highscores[i] + " ");
		} //saves changes
	}
	
	public void reset(File f) throws FileNotFoundException {//for debugging purposes
		PrintStream output = new PrintStream(f);
		for (int i = 0; i < size; i++) {
			highscores[i] = 0;
		}
		for (int i = 0; i < size; i++) {
			output.print(highscores[i] + " ");
		}
	}
	
	public int[] getHighscores() {
		int[] copy = highscores.clone();
		return copy;
	}//placeholder
	
}
