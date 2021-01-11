import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class timer {
	public Timeline timeline;

	private int time;
	public timer() {		
		timeline = new Timeline();
		timeline.setCycleCount(999);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),e->increment()));
		timeline.play();
	}
	private void increment () {
		System.out.println("timer"+ time);
		time++; 
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
	}
	public int getTime() { 
		return time;
	}
}
