import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Timer {
	public Timeline timeline;

	private int time;
	private int min; 
	
	//Sets the timer to work, with a duration of 1 second. 
	public Timer() {		
		timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),e->increment()));
		timeline.play();
	}
	private void increment () {
		time++;
		//Counts upto 59 sekunds
		if(time<60) {
			Main.labelTimer.setText(String.valueOf(time));
		}
		//After 59 it counts min and sekunds
		else {
			if(time%60==0)
				min++;
			
			Main.labelTimer.setText(String.valueOf(min + ":" + (time-(60*min))));
		}
		
	}
	
	//Pause and stop with control by the boolean. 
	public void pausecounter(boolean stop) {
		if(stop) {
			timeline.pause();
		}
		else {
			timeline.play();
		}
	}
	//Restarts and stops the counter.
	public void restartcounter() {
		timeline.playFromStart();
		timeline.pause();
		time = 0;
	}
	
	public int getTime() { 
		return time;
	}
}
