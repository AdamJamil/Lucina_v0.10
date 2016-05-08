import java.util.HashMap;
import javafx.scene.image.Image;

public class Drawable implements java.io.Serializable
{
	WalkingStatus ws = WalkingStatus.FORWARDIDLE;
	transient HashMap<WalkingStatus, Image> spriteCollection = new HashMap<WalkingStatus, Image>();
	public Image getSprite()
	{
		return spriteCollection.get(this.ws);
	}
}