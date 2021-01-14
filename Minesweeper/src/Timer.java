import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Timer {
	public Timeline timeline;

	private int time;
	private int min; 
	
	public Timer() {		
		timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),e->increment()));
		timeline.play();
	}
	private void increment () {
		time++;
		if(time<60) {
			Main.labelTimer.setText(String.valueOf(time));
			
		}
		else {
			if(time%60==0) {
				min++;
			}
			Main.labelTimer.setText(String.valueOf(min + ":" + (time-(60*min))));
			
		}
		
	}
	
	public void pausecounter(boolean stop) {

		if(stop) {
			timeline.pause();
		}
		else {
			timeline.play();
		}
	}
	public void restartcounter() {
		timeline.playFromStart();
		timeline.pause();
		time = 0;
	}
	public int getTime() { 
		return time;
	}
}
