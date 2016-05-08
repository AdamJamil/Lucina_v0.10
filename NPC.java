import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class NPC extends Drawable implements java.io.Serializable
{
	Status s = Status.IDLE;
	int x, y;

	public String toString()
	{
		return "lol";
	}
}