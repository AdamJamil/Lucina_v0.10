import java.util.ArrayList;
import javafx.scene.image.Image;

public class Location implements java.io.Serializable
{
	transient ArrayList<NPC> npcs = new ArrayList<NPC>();
	short[][] imgMap;
	boolean[][] passMap;
	Image input = null;

}