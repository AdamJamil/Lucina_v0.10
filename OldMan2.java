import javafx.scene.image.Image;

public class OldMan2 extends TalkingNPC
{
	public OldMan2()
	{
		x = 21 * 32;
		y = 21 * 32;
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
		topic = new Dialogue[3];
		topic[0] = new Dialogue(
			       new String[]{
					   "meester old men : hello",
					   "meester old men : goodbye",
					   "meester old men : im calling the police",
					   "player : wtf"
				   });
		topic[1] = new Dialogue(
			       new String[]{
					   "meester old men : this is ur last warning",
					   "meester old men : jk ily",
					   "meester old men : jk i kill u",
					   "player : rofl"
				   });
		topic[2] = new Dialogue(
			       new String[]{
					   "meester old men : rip player",
					   "player : aight w/e lol pce"
				   });
	}
}