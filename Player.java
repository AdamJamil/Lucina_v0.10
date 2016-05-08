import java.util.HashMap;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Player extends Drawable implements java.io.Serializable
{
	int x = 20 * 32, y = 20 * 32;
	int internalTick = 0;
	Status s = Status.IDLE;
	CurrentVariables var;
	boolean godmode = true;
	String name = "PLAYER";

	public Player(CurrentVariables cVar)
	{
		var = cVar;
		loadImages();
		loadFrame();
	}

	public void loadImages()
	{
		spriteCollection = new HashMap<WalkingStatus, Image>();
		spriteCollection.put(WalkingStatus.FORWARDIDLE, new Image("Player\\F.png"));
		spriteCollection.put(WalkingStatus.FORWARD1, new Image("Player\\F1.png"));
		spriteCollection.put(WalkingStatus.FORWARDHALF, new Image("Player\\F.png"));
		spriteCollection.put(WalkingStatus.FORWARD2, new Image("Player\\F2.png"));
		spriteCollection.put(WalkingStatus.BACKWARDIDLE, new Image("Player\\B.png"));
		spriteCollection.put(WalkingStatus.BACKWARD1, new Image("Player\\B1.png"));
		spriteCollection.put(WalkingStatus.BACKWARDHALF, new Image("Player\\B.png"));
		spriteCollection.put(WalkingStatus.BACKWARD2, new Image("Player\\B2.png"));
		spriteCollection.put(WalkingStatus.RIGHTIDLE, new Image("Player\\R.png"));
		spriteCollection.put(WalkingStatus.RIGHT1, new Image("Player\\R1.png"));
		spriteCollection.put(WalkingStatus.RIGHTHALF, new Image("Player\\R.png"));
		spriteCollection.put(WalkingStatus.RIGHT2, new Image("Player\\R2.png"));
		spriteCollection.put(WalkingStatus.LEFTIDLE, new Image("Player\\L.png"));
		spriteCollection.put(WalkingStatus.LEFT1, new Image("Player\\L1.png"));
		spriteCollection.put(WalkingStatus.LEFTHALF, new Image("Player\\L.png"));
		spriteCollection.put(WalkingStatus.LEFT2, new Image("Player\\L2.png"));
	}

	public void loadFrame()
	{
		KeyFrame frame = new KeyFrame(Duration.millis(4f),
		                              new EventHandler<ActionEvent>()
		                              {
										  @Override
										  public void handle(ActionEvent event)
										  {
											  if (s == Status.WALKING)
											  {
												  if ((ws == WalkingStatus.BACKWARDIDLE) || (ws == WalkingStatus.BACKWARD1) || (ws == WalkingStatus.BACKWARDHALF) || (ws == WalkingStatus.BACKWARD2))
												  {
													  if (godmode)
													  {
														  y-=2;
														  internalTick++;
														  if (internalTick == 4)
															   ws = WalkingStatus.BACKWARD1;
														  if (internalTick == 8)
															   ws = WalkingStatus.BACKWARDHALF;
														  if (internalTick == 12)
															   ws = WalkingStatus.BACKWARD2;
														  if (internalTick == 16)
														  {
															   s = Status.IDLE;
															   ws = WalkingStatus.BACKWARDIDLE;
															   internalTick = 0;
															   var.passMap[(int)(x / 32)][(int)(y / 32) + 1] = true;
														  }
													  }
													  else
													  {
														  y--;
														  internalTick++;
														  if (internalTick == 8)
															   ws = WalkingStatus.BACKWARD1;
														  if (internalTick == 16)
															   ws = WalkingStatus.BACKWARDHALF;
														  if (internalTick == 24)
															   ws = WalkingStatus.BACKWARD2;
														  if (internalTick == 32)
														  {
															   s = Status.IDLE;
															   ws = WalkingStatus.BACKWARDIDLE;
															   internalTick = 0;
															   var.passMap[(int)(x / 32)][(int)(y / 32) + 1] = true;
														  }

													  }
												  }
												  if ((ws == WalkingStatus.FORWARDIDLE) || (ws == WalkingStatus.FORWARD1) || (ws == WalkingStatus.FORWARDHALF) || (ws == WalkingStatus.FORWARD2))
												  {
													  if (godmode)
													  {
														  y+=2;
														  internalTick++;
														  if (internalTick == 4)
															   ws = WalkingStatus.FORWARD1;
														  if (internalTick == 8)
															   ws = WalkingStatus.FORWARDHALF;
														  if (internalTick == 12)
															   ws = WalkingStatus.FORWARD2;
														  if (internalTick == 16)
														  {
															   s = Status.IDLE;
															   ws = WalkingStatus.FORWARDIDLE;
															   internalTick = 0;
															   var.passMap[(int)(x / 32)][(int)(y / 32) - 1] = true;
														  }
													  }
													  else
													  {
														  y++;
														  internalTick++;
														  if (internalTick == 8)
															   ws = WalkingStatus.FORWARD1;
														  if (internalTick == 16)
															   ws = WalkingStatus.FORWARDHALF;
														  if (internalTick == 24)
															   ws = WalkingStatus.FORWARD2;
														  if (internalTick == 32)
														  {
															   s = Status.IDLE;
															   ws = WalkingStatus.FORWARDIDLE;
															   internalTick = 0;
															   var.passMap[(int)(x / 32)][(int)(y / 32) - 1] = true;
														  }
													  }
												  }
												  if ((ws == WalkingStatus.LEFTIDLE) || (ws == WalkingStatus.LEFT1) || (ws == WalkingStatus.LEFTHALF) || (ws == WalkingStatus.LEFT2))
												  {
													  if (godmode)
													  {
														  x-=2;
														  internalTick++;
														  if (internalTick == 4)
															   ws = WalkingStatus.LEFT1;
														  if (internalTick == 8)
															   ws = WalkingStatus.LEFTHALF;
														  if (internalTick == 12)
															   ws = WalkingStatus.LEFT2;
														  if (internalTick == 16)
														  {
															   s = Status.IDLE;
															   ws = WalkingStatus.LEFTIDLE;
															   internalTick = 0;
															   var.passMap[(int)(x / 32) + 1][(int)(y / 32)] = true;
														  }
													  }
													  else
													  {
														  x--;
														  internalTick++;
														  if (internalTick == 8)
															   ws = WalkingStatus.LEFT1;
														  if (internalTick == 16)
															   ws = WalkingStatus.LEFTHALF;
														  if (internalTick == 24)
															   ws = WalkingStatus.LEFT2;
														  if (internalTick == 32)
														  {
															   s = Status.IDLE;
															   ws = WalkingStatus.LEFTIDLE;
															   internalTick = 0;
															   var.passMap[(int)(x / 32) + 1][(int)(y / 32)] = true;
														  }
													  }
												  }
												  if ((ws == WalkingStatus.RIGHTIDLE) || (ws == WalkingStatus.RIGHT1) || (ws == WalkingStatus.RIGHTHALF) || (ws == WalkingStatus.RIGHT2))
												  {
													  if (godmode)
													  {
														  x+=2;
														  internalTick++;
														  if (internalTick == 4)
															   ws = WalkingStatus.RIGHT1;
														  if (internalTick == 8)
															   ws = WalkingStatus.RIGHTHALF;
														  if (internalTick == 12)
															   ws = WalkingStatus.RIGHT2;
														  if (internalTick == 16)
														  {
															   s = Status.IDLE;
															   ws = WalkingStatus.RIGHTIDLE;
															   internalTick = 0;
															   var.passMap[(int)(x / 32) - 1][(int)(y / 32)] = true;
														  }
													  }
													  else
													  {
														  x++;
														  internalTick++;
														  if (internalTick == 8)
															   ws = WalkingStatus.RIGHT1;
														  if (internalTick == 16)
															   ws = WalkingStatus.RIGHTHALF;
														  if (internalTick == 24)
															   ws = WalkingStatus.RIGHT2;
														  if (internalTick == 32)
														  {
															   s = Status.IDLE;
															   ws = WalkingStatus.RIGHTIDLE;
															   internalTick = 0;
															   var.passMap[(int)(x / 32) - 1][(int)(y / 32)] = true;
														  }
													  }
												  }
											  }
										  }
									  }
		);
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.getKeyFrames().add(frame);
		timeline.play();
	}

	public Image getSprite()
	{
		return spriteCollection.get(ws);
	}

	public void setWalkingStatus(WalkingStatus newWS)
	{
		ws = newWS;
	}

	public void up()
	{
		if (s == Status.IDLE)
		{
			if (var.passMap[(int)(x / 32)][(int)(y / 32) - 1])
				s = Status.WALKING;
			var.passMap[(int)(x / 32)][(int)(y / 32) - 1] = false;
			ws = WalkingStatus.BACKWARDIDLE;

		}
	}

	public void down()
	{
		if (s == Status.IDLE)
		{
			if (var.passMap[(int)(x / 32)][(int)(y / 32) + 1])
				s = Status.WALKING;
			var.passMap[(int)(x / 32)][(int)(y / 32) + 1] = false;
			ws = WalkingStatus.FORWARDIDLE;
		}
	}

	public void left()
	{
		if (s == Status.IDLE)
		{
			if (var.passMap[(int)(x / 32) - 1][(int)(y / 32)])
				s = Status.WALKING;
			var.passMap[(int)(x / 32) - 1][(int)(y / 32)] = false;
			ws = WalkingStatus.LEFTIDLE;
		}
	}

	public void right()
	{
		if (s == Status.IDLE)
		{
			if (var.passMap[(int)(x / 32) + 1][(int)(y / 32)])
				s = Status.WALKING;
			var.passMap[(int)(x / 32) + 1][(int)(y / 32)] = false;
			ws = WalkingStatus.RIGHTIDLE;
		}
	}
}