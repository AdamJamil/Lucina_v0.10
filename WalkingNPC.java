import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class WalkingNPC extends NPC
{
	int internalTick;
	int tarX = 0, tarY = 0;
	boolean[][] map;
	boolean right = false, down = false, directed = false;
	ArrayList<NPC> npcs;
	public WalkingNPC(boolean[][] passMap, ArrayList<NPC> npc)
	{
		npcs = npc;
		map = passMap;
		KeyFrame frame = new KeyFrame(Duration.millis(5f),
		                              new EventHandler<ActionEvent>()
		                              {
										  @Override
										  public void handle(ActionEvent event)
										  {
											  if (s == Status.WALKING)
											  {
												  if ((ws == WalkingStatus.BACKWARDIDLE) || (ws == WalkingStatus.BACKWARD1) || (ws == WalkingStatus.BACKWARDHALF) || (ws == WalkingStatus.BACKWARD2))
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
														   map[(int)(x / 32)][(int)(y / 32) + 1] = true;
													  }
												  }
												  if ((ws == WalkingStatus.FORWARDIDLE) || (ws == WalkingStatus.FORWARD1) || (ws == WalkingStatus.FORWARDHALF) || (ws == WalkingStatus.FORWARD2))
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
														   map[(int)(x / 32)][(int)(y / 32) - 1] = true;
													  }
												  }
												  if ((ws == WalkingStatus.LEFTIDLE) || (ws == WalkingStatus.LEFT1) || (ws == WalkingStatus.LEFTHALF) || (ws == WalkingStatus.LEFT2))
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
														   map[(int)(x / 32) + 1][(int)(y / 32)] = true;
													  }
												  }
												  if ((ws == WalkingStatus.RIGHTIDLE) || (ws == WalkingStatus.RIGHT1) || (ws == WalkingStatus.RIGHTHALF) || (ws == WalkingStatus.RIGHT2))
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
														   map[(int)(x / 32) - 1][(int)(y / 32)] = true;
													  }
												  }
											  }
											  else
											  {
												  if (x != tarX || y != tarY)
												  {
													  directed = false;
													  if (tarX > x)
														  right = true;
													  else
														  right = false;
													  if (tarY > y)
														  down = true;
													  else
														  down = false;
													  if (Math.abs(tarX - x) >= Math.abs(tarY - y))
													  {
														  if (right)
														      if (!right())
														          if (down)
														          {
														             if (!down())
														                 if (!up())
														                     left();
															      }
															      else
															      {
														             if (!up())
														                 if (!down())
														                     left();
																  }
														  if (!directed && !right)
														      if (!left())
														          if (down)
														          {
														             if (!down())
														                 if (!up())
														                     right();
															      }
															      else
															      {
														             if (!up())
														                 if (!down())
														                     right();
																  }
													  }
													  else
													  {
														  if (!down)
														      if (!up())
														          if (right)
														          {
														             if (!right())
														                 if (!left())
														                     down();
															      }
															      else
															      {
														             if (!left())
														                 if (!right())
														                     down();
																  }
														  if (!directed && down)
														  {
														      if (!down())
														          if (right)
														          {
														             if (!right())
														                 if (!left())
														                     up();
															      }
															      else
															      {
														             if (!left())
														                 if (!right())
														                     up();
																  }
															  }

													  }
												  }
												  else //remove
												  {
													  for(int i = 0; i < npc.size(); i++)
													  {
														  if (npcs.get(i).x == x && npcs.get(i).y == y)
														  {
														   	  map[(int)(x / 32)][(int)(y / 32)] = true;
															  npcs.remove(i);
															  break;
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

	public boolean right()
	{
		if (map[(int)(x / 32) + 1][(int)(y / 32)])
		{
			s = Status.WALKING;
			ws = WalkingStatus.RIGHTIDLE;
			map[(int)(x / 32) + 1][(int)(y / 32)] = false;
			directed = true;
			return true;
		}
		return false;
	}

	public boolean left()
	{
		if (map[(int)(x / 32) - 1][(int)(y / 32)])
		{
			s = Status.WALKING;
			ws = WalkingStatus.LEFTIDLE;
			map[(int)(x / 32) - 1][(int)(y / 32)] = false;
			directed = true;
			return true;
		}
		return false;
	}

	public boolean up()
	{
		if (map[(int)(x / 32)][(int)(y / 32) - 1])
		{
			s = Status.WALKING;
			ws = WalkingStatus.BACKWARDIDLE;
			map[(int)(x / 32)][(int)(y / 32) - 1] = false;
			directed = true;
			return true;
		}
		return false;
	}

	public boolean down()
	{
		if (map[(int)(x / 32)][(int)(y / 32) + 1])
		{
			s = Status.WALKING;
			ws = WalkingStatus.FORWARDIDLE;
			map[(int)(x / 32)][(int)(y / 32) + 1] = false;
			directed = true;
			return true;
		}
		return false;
	}
}