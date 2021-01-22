package model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import javafx.scene.control.ListView;

public class Highscores {
	
	public static File beginnerFile = new File("beginnerHighscores.txt");
	public static File mediumFile = new File("mediumHighscores.txt");
	public static File expertFile = new File("expertHighscores.txt");
	
	private static final int size = 10;
	private static String[] highscores = new String[size];
	private static Scanner input;
	private static PrintStream output;
	
	//Creates the leaderboard files in the same directory as the project
	//or jar file, if it isn't already there.
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
	
	//Reads from a leaderboard file and stores the data in a string array
	public static void read(File f) throws FileNotFoundException {
		input = new Scanner(f);
		for(int i = 0; i < highscores.length; i++) {
			highscores[i] = input.next();
		}
	}
	
	//Checks if a new highscore has been achieved. The lower the score the better.
	//-1 is a default highscore and should always be replaced.
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
	
	//Loads leaderboard files with new data, if a new high score has been achieved.
	//-1 is a default highscore and should always be replaced.
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
		//If -1 has been replaced, no more replacement should be done.
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
	
	//Sorts every element of the highscores array such that lower scores
	//have lower indices and higher scores have higher indices with the
	//exception of -1, which should be placed after any other score.
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
	
	//resets leaderboard file to default state 
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
	public static void listViewFill(ListView<String> listview, File f) throws FileNotFoundException {
		Highscores.read(f);
		for(int i = 0; i < Highscores.getHighscores().length; i++) {
			if(Highscores.getHighscores()[i].equals("-1:***")) {
				listview.getItems().add("-\t\t-");
			} else {
				listview.getItems().add(Highscores.getHighscores()[i].split(":")[1] + "\t\t" + 
			Highscores.getHighscores()[i].split(":")[0]);
			}
		}
	}
}
