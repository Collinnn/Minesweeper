import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class timer {
	public Timeline timeline;
	
	public timer() {
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1)));	
	}
	
	
	public void stopcounter(boolean stop) {
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
		return timeline.getCycleCount();
	}
	
	
}
