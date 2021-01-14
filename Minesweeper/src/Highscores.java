import java.io.*;
import java.util.*;

public class Highscores {
	
	private static final int size = 10;
	private int[] highscores = new int[size];
	//use on start up
	public Highscores(File f) throws FileNotFoundException {
		this.read(f);
	}
	
	public void read(File f) throws FileNotFoundException {
		Scanner input = new Scanner(f);
		for (int i = 0; i < size; i++) {
			highscores[i] = input.nextInt();
		}
	}
	
	public void write(int score, File f) throws FileNotFoundException {//use upon winning game
		PrintStream output = new PrintStream(f);
		for(int i = 0; i < size; i++) {
			//checks if highscore beaten
			if (score > highscores[i]) {
				highscores[i] = score;
				break;
			}
		} 
		//saves changes
		for (int i = 0; i < size; i++) {
			output.print(highscores[i] + " ");
		} 
	}
	//for debugging purposes
	public void reset(File f) throws FileNotFoundException {
		PrintStream output = new PrintStream(f);
		for (int i = 0; i < size; i++) {
			highscores[i] = 0;
		}
		for (int i = 0; i < size; i++) {
			output.print(highscores[i] + " ");
		}
	}
	//placeholder
	public int[] getHighscores() {
		int[] copy = highscores.clone();
		return copy;
	}
	
}
