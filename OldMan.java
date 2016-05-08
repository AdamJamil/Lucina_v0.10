import java.util.ArrayList;
import javafx.scene.image.Image;

public class OldMan extends WalkingNPC
{
	public OldMan(boolean[][] passMap, ArrayList<NPC> npcs)
	{
		super(passMap, npcs);
		x = ((int)(Math.random() * 49)) * 32;
		y = ((int)(Math.random() * 49)) * 32;
		tarX = ((int)(Math.random() * 49)) * 32;
		tarY = ((int)(Math.random() * 49)) * 32;
		spriteCollection.put(WalkingStatus.FORWARDIDLE, new Image("OldMan\\F.png"));
		spriteCollection.put(WalkingStatus.FORWARD1, new Image("OldMan\\F1.png"));
		spriteCollection.put(WalkingStatus.FORWARDHALF, new Image("OldMan\\F.png"));
		spriteCollection.put(WalkingStatus.FORWARD2, new Image("OldMan\\F2.png"));
		spriteCollection.put(WalkingStatus.BACKWARDIDLE, new Image("OldMan\\B.png"));
		spriteCollection.put(WalkingStatus.BACKWARD1, new Image("OldMan\\B1.png"));
		spriteCollection.put(WalkingStatus.BACKWARDHALF, new Image("OldMan\\B.png"));
		spriteCollection.put(WalkingStatus.BACKWARD2, new Image("OldMan\\B2.png"));
		spriteCollection.put(WalkingStatus.RIGHTIDLE, new Image("OldMan\\R.png"));
		spriteCollection.put(WalkingStatus.RIGHT1, new Image("OldMan\\R1.png"));
		spriteCollection.put(WalkingStatus.RIGHTHALF, new Image("OldMan\\R.png"));
		spriteCollection.put(WalkingStatus.RIGHT2, new Image("OldMan\\R2.png"));
		spriteCollection.put(WalkingStatus.LEFTIDLE, new Image("OldMan\\L.png"));
		spriteCollection.put(WalkingStatus.LEFT1, new Image("OldMan\\L1.png"));
		spriteCollection.put(WalkingStatus.LEFTHALF, new Image("OldMan\\L.png"));
		spriteCollection.put(WalkingStatus.LEFT2, new Image("OldMan\\L2.png"));
	}
}