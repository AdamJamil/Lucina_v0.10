import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class Lucina extends Application
{
	long time = 0;
	HashMap<Short, Image> textures = new HashMap<Short, Image>();
	CurrentVariables var = null;
	NPCData data = null;
	Player player = null;
	QuestTracker qt = null;
	InputStream is = null;
	Sequencer sequencer = null;
	Font font = null, smallFont = null;
	boolean w = false, a = false, s = false, d = false, main = true;
	boolean load = false, nue/*new*/ = false, selectedSlot = false;
	String[] saveNames = new String[4];
	int min = 0, hour = 0, day = 0, sec = 0;
	int lx = (int)(Math.random() * 18) + 2, ly = (int)(Math.random() * 18) + 2;
	int ux = (int)(Math.random() * 18) + 2, uy = (int)(Math.random() * 18) + 2;
	int cx = (int)(Math.random() * 18) + 2, cy = (int)(Math.random() * 18) + 2;
	int ix = (int)(Math.random() * 18) + 2, iy = (int)(Math.random() * 18) + 2;
	int nx = (int)(Math.random() * 18) + 2, ny = (int)(Math.random() * 18) + 2;
	int ax = (int)(Math.random() * 18) + 2, ay = (int)(Math.random() * 18) + 2;
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {

        primaryStage.setTitle("Lucina");
		Canvas canvas = new Canvas(736, 416);
        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 736, 416);//23 tiles x 13 tiles
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);
        primaryStage.setScene(scene);
		primaryStage.show();

		int loadCount = 0;
		try
		{
			for(int i = 0; i >= 0; i++)
			{
				textures.put(new Short((short)i), new Image("Textures//" + Integer.toString(i) + ".png"));
				System.out.println(textures.get((short)i).getHeight());
				loadCount = i;
			}
		} catch(IllegalArgumentException e) {System.out.println("Successfully loaded " + (loadCount + 1) + " image(s)!");}
		try
		{
			font = Font.loadFont(new FileInputStream(new File("p.ttf")), 20);
			smallFont = Font.loadFont(new FileInputStream(new File("p.ttf")), 12);
		} catch (FileNotFoundException e) {e.printStackTrace();}

		GraphicsContext g = canvas.getGraphicsContext2D();
		//mainMenu(g);
		min = 0;
		sec = 0;

		loadSaveData();
		data = new NPCData();
		var = new CurrentVariables(Derst.class, data);
		player = new Player(var);
		qt = new QuestTracker(var, data, player);
		KeyFrame frame = new KeyFrame(Duration.millis(4f),
		                              new EventHandler<ActionEvent>()
		                              {
										  @Override
										  public void handle(ActionEvent event)
										  {
											  canvas.getGraphicsContext2D().clearRect(0, 0, 736, 416);
											  if (main)
											  {
												  mainMenu(canvas.getGraphicsContext2D());
												  sec++;
												  return;
											  }
											  var.addNPC(min, hour);
											  loadViewPort(canvas.getGraphicsContext2D());
											  data.update(var.npcs);
											  qt.check();
											  sec++;
											  if (sec > 4)
											  {
												  min++;
												  sec = 0;
												  if (min > 59)
												  {
													  hour++;
													  min = 0;
													  if (hour > 23)
													  {
														  day++;
														  hour = 0;
													  }
												  }
											  }
											  if (w)
												 player.up();
											  if (s)
												 player.down();
											  if (a)
												 player.left();
											  if (d)
												 player.right();
										  }
									  }
		);
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.getKeyFrames().add(frame);
		timeline.play();
    }

    public void mainMenu(GraphicsContext g)
    {
		if (min == 0)
		{
			if (sec == 0)
			{
				try
				{
					sequencer = MidiSystem.getSequencer();
					sequencer.open();
					is = new BufferedInputStream(new FileInputStream(new File("Title.mid")));
					sequencer.setSequence(is);
				} catch (InvalidMidiDataException|IOException|MidiUnavailableException event) {event.printStackTrace();}
				sequencer.start();
			}
			if (sec % 12 < 6)
			{
				g.setFill(Color.BLACK);
				g.fillRect(0, 0, 716, 436);
			}
			else if (sec % 12 == 6)
			{
				g.setFill(Color.WHITE);
				g.fillRect(0, 0, 716, 436);
				if (sec == 42)
				{
					sec = 0;
					min++;
				}
			}
			else;
		}
		else if (min == 1)
		{
			Color c = Color.BLACK;
			System.out.println(sec);
			c = new Color(c.getRed(), c.getGreen(), c.getBlue(), (double)sec / 700);
			g.setFill(c);
			g.fillRect(0, 0, 736, 416);
			if (sec == 500)
			{
				sec = 0;
				min++;
			}
		}
		else if (min == 2)
		{
			Color c = Color.BLACK;
			c = new Color(c.getRed(), c.getGreen(), c.getBlue(), (((double)(500 + sec) / 700) > 1) ? 1 : ((double)(500 + sec)) / 700);
			g.setFill(c);
			g.fillRect(0, 0, 736, 416);
			g.setFont(font);
			g.setFill(Color.WHITE);
			g.fillText("L", 50 + lx * 500 / (double) (sec + 30),
			                50 + ly * 500 / (double) (sec + 100));
			g.fillText("U", 90 + ux * 500 / (double) (sec + 30),
			                50 + uy * 500 / (double) (sec + 100));
			g.fillText("C", 130 + cx * 500 / (double) (sec + 30),
			                50 + cy * 500 / (double) (sec + 100));
			g.fillText("I", 170 + ix * 500 / (double) (sec + 30),
			                50 + iy * 500 / (double) (sec + 100));
			g.fillText("N", 200 + nx * 500 / (double) (sec + 30),
			                50 + ny * 500 / (double) (sec + 100));
			g.fillText("A", 240 + ax * 500 / (double) (sec + 30),
			                50 + ay * 500 / (double) (sec + 100));
			if (sec > 700)
			{
				c = Color.WHITE;
				c = new Color(c.getRed(), c.getGreen(), c.getBlue(), (((double)(-700 + sec) / 700) > 1) ? 1 : ((double)(-700 + sec)) / 700);
				g.setFill(c);
				g.fillText("NEW", 100, 150);
				g.fillText("CONTINUE", 100, 210);
				g.fillText("LOAD", 100, 270);
				g.fillText("QUIT", 100, 330);
				if (!nue && !load)
					g.fillText(">", 70, 150 + var.cursor * 60);
				else if (nue)
				{
					g.fillText(">", 250, 150 + var.cursor * 60);
					c = Color.WHITE;
					c = new Color(c.getRed(), c.getGreen(), c.getBlue(), (((double)(-700 + sec) / 700) > 1) ? 1 : ((double)(-700 + sec)) / 700);
					g.setFill(c);
					g.fillRoundRect(271, 125, 180, 38, 20, 20);
					g.fillRoundRect(271, 185, 180, 38, 20, 20);
					g.fillRoundRect(271, 245, 180, 38, 20, 20);
					g.fillRoundRect(271, 305, 180, 38, 20, 20);
					g.setFill(Color.BLACK);
					g.fillRoundRect(273, 127, 176, 34, 20, 20);
					g.fillRoundRect(273, 187, 176, 34, 20, 20);
					g.fillRoundRect(273, 247, 176, 34, 20, 20);
					g.fillRoundRect(273, 307, 176, 34, 20, 20);
					g.setFont(smallFont);
					c = Color.WHITE;
					c = new Color(c.getRed(), c.getGreen(), c.getBlue(), (((double)(-700 + sec) / 700) > 1) ? 1 : ((double)(-700 + sec)) / 700);
					g.setFill(c);
					for(int i = 1; i < 5; i++)
					{
						if (saveNames[i - 1] instanceof String)
							g.fillText(saveNames[i - 1], 283, 148 + (i - 1) * 60);
						else
							g.fillText("Empty Slot", 283, 148 + (i - 1) * 60);
					}
					if (selectedSlot)
						g.fillText("Would you like to overwrite this file?", 460, 147 + var.cursor * 60);
				}
				else if (load)
				{
					g.fillText(">", 250, 150 + var.cursor * 60);
					c = Color.WHITE;
					c = new Color(c.getRed(), c.getGreen(), c.getBlue(), (((double)(-700 + sec) / 700) > 1) ? 1 : ((double)(-700 + sec)) / 700);
					g.setFill(c);
					g.fillRoundRect(271, 125, 180, 38, 20, 20);
					g.fillRoundRect(271, 185, 180, 38, 20, 20);
					g.fillRoundRect(271, 245, 180, 38, 20, 20);
					g.fillRoundRect(271, 305, 180, 38, 20, 20);
					g.setFill(Color.BLACK);
					g.fillRoundRect(273, 127, 176, 34, 20, 20);
					g.fillRoundRect(273, 187, 176, 34, 20, 20);
					g.fillRoundRect(273, 247, 176, 34, 20, 20);
					g.fillRoundRect(273, 307, 176, 34, 20, 20);
					g.setFont(smallFont);
					c = Color.WHITE;
					c = new Color(c.getRed(), c.getGreen(), c.getBlue(), (((double)(-700 + sec) / 700) > 1) ? 1 : ((double)(-700 + sec)) / 700);
					g.setFill(c);
					for(int i = 1; i < 5; i++)
					{
						if (saveNames[i - 1] instanceof String)
							g.fillText(saveNames[i - 1], 283, 148 + (i - 1) * 60);
						else
							g.fillText("Empty Slot", 283, 148 + (i - 1) * 60);
					}
					if (selectedSlot)
						g.fillText("Would you like to load this file?", 460, 147 + var.cursor * 60);
				}

			}
		}
	}

    public void loadViewPort(GraphicsContext gc)
    {
		//calculate bounds of camera
		int startX = 0, startY = 0;
		int playerX = (int)(player.x), playerY = (int)(player.y);

		if (player.x <= 11 * 32)
		{
			startX = 0;
			playerX = player.x;
		}
		else if ((int)(player.x / 32) >= var.imgMap.length - 12)
		{
			startX = 32 * (var.imgMap.length - 23);
			playerX = (23 * 32) - (var.imgMap.length * 32 - player.x);
		}
		else
		{
			startX = (int)(player.x) - (11 * 32);
			playerX = 11 * 32;
		}

		if (player.y <= 6 * 32)
		{
			startY = 0;
			playerY = player.y;
		}
		else if (player.y >= 32 * (var.imgMap[0].length - 7))
		{
			startY = 32 * (var.imgMap[0].length - 13);
			playerY = (32 * 13) - (var.imgMap[0].length * 32 - player.y);
		}
		else
		{
			startY = (int)(player.y) - (6 * 32);
			playerY = 6 * 32;
		}
		//draws shit
		for(int i = -1; i < 24; i++)
			for(int j = -1; j < 14; j++)
				if ((i >= 0) && (i + (int)(startX / 32) < var.imgMap.length) && (j >= 0) && (j + (int)(startY / 32) < var.imgMap[0].length))
					gc.drawImage(textures.get(var.imgMap[i + (int)(startX / 32)][j + (int)(startY / 32)]), i * 32 - startX % 32, j * 32 - startY % 32);
		for(int i = 0; i < var.npcs.size(); i++)
		{
			NPC placeHolder = var.npcs.get(i);
			if (placeHolder.x >= startX - 32 && placeHolder.y >= startY - 32 && placeHolder.x <= startX + 736 && placeHolder.y <= startY + 416)
			{
				gc.drawImage(placeHolder.getSprite(), placeHolder.x - startX, placeHolder.y - startY);
			}
		}
		for(int i = 0; i < var.walkingnpcs.size(); i++)
		{
			NPC placeHolder = var.walkingnpcs.get(i);
			if (placeHolder.x >= startX - 32 && placeHolder.y >= startY - 32 && placeHolder.x <= startX + 736 && placeHolder.y <= startY + 416)
			{
				gc.drawImage(placeHolder.getSprite(), placeHolder.x - startX, placeHolder.y - startY);
			}
		}
		gc.drawImage(player.getSprite(), playerX + (16 - player.getSprite().getWidth() / 2), playerY);
		Color nightFilter = Color.NAVY;
		nightFilter = new Color(nightFilter.getRed(), nightFilter.getGreen(), nightFilter.getBlue(), 0.45 - 0.45 * (Math.sin(Math.PI * (hour * 60 + min) / (24 * 60))));

		gc.setFill(nightFilter);
		gc.fillRect(0, 0, 736, 416);
		if (var.mType == MenuType.MAIN)
		{
			gc.setFont(font);
			Color c = Color.ROYALBLUE;
			gc.setFill(Color.BLACK);
			gc.fillRoundRect(548, 6, 182, 404, 20, 20);
			gc.setFill(Color.WHITE);
			gc.fillRoundRect(549, 7, 180, 402, 20, 20);
			gc.setFill(Color.BLACK);
			gc.fillRoundRect(551, 9, 176, 398, 20, 20);
			c = new Color(c.getRed() / 1.4, c.getGreen() / 1.4, c.getBlue() / 1.4, 1.0);
			gc.setFill(c);
			gc.fillRoundRect(552, 10, 174, 396, 20, 20);
			gc.setFill(Color.DARKRED);
			//gc.strokeText("Save", 580, 40);
			//gc.strokeText("Load", 580, 80);
			//gc.strokeText("Party", 580, 120);
			//gc.strokeText("Inventory", 580, 160);
			gc.fillText("Save", 580, 40);
			gc.fillText("Load", 580, 80);
			gc.fillText("Party", 580, 120);
			gc.fillText("Inventory", 580, 160);
			gc.setFill(Color.BLACK);
			gc.fillText(">", 560, 37 + var.cursor * 40);
			gc.strokeText(">", 560, 37 + var.cursor * 40);
		}
		else if (var.mType == MenuType.INVENTORY)
		{
			gc.setFont(font);
			Color c = Color.ROYALBLUE;
			gc.setFill(Color.BLACK);
			gc.fillRoundRect(4, 4, 728, 408, 20, 20);
			gc.setFill(Color.WHITE);
			gc.fillRoundRect(5, 5, 726, 406, 20, 20);
			gc.setFill(Color.BLACK);
			gc.fillRoundRect(7, 7, 722, 402, 20, 20);
			c = new Color(c.getRed() / 1.4, c.getGreen() / 1.4, c.getBlue() / 1.4, 1.0);
			gc.setFill(c);
			gc.fillRoundRect(8, 8, 720, 400, 20, 20);
		}
		else if (var.mType == MenuType.DIALOGUE)
		{
			gc.setFont(font);
			Color c = Color.ROYALBLUE;
			gc.setFill(Color.BLACK);
			gc.fillRoundRect(4, 304, 728, 108, 20, 20);
			gc.setFill(Color.WHITE);
			gc.fillRoundRect(5, 305, 726, 106, 20, 20);
			gc.setFill(Color.BLACK);
			gc.fillRoundRect(7, 307, 722, 102, 20, 20);
			c = new Color(c.getRed() / 1.4, c.getGreen() / 1.4, c.getBlue() / 1.4, 1.0);
			gc.setFill(c);
			gc.fillRoundRect(8, 308, 720, 100, 20, 20);
			gc.setFill(Color.WHITE);
			gc.fillText(var.cDialogue.line[var.dCursor].substring(0, var.dCursor2), 50, 348);
			if (var.dCursor2 < var.cDialogue.line[var.dCursor].length())
				var.dCursor2++;
		}
	}

	public void onKeyPressed(KeyEvent e)
	{
		if (e.getCode() == KeyCode.W)
			w();
		if (e.getCode() == KeyCode.S)
			s();
		if (e.getCode() == KeyCode.A)
			a();
		if (e.getCode() == KeyCode.D)
			d();
		if (e.getCode() == KeyCode.E)
			select();
		if (e.getCode() == KeyCode.Q)
			cancel();
		if (e.getCode() == KeyCode.SPACE)
			interact();
	}

	public void onKeyReleased(KeyEvent e)
	{
		if (e.getCode() == KeyCode.W)
			w = false;
		if (e.getCode() == KeyCode.S)
			s = false;
		if (e.getCode() == KeyCode.A)
			a = false;
		if (e.getCode() == KeyCode.D)
			d = false;
	}

	public void w()
	{
		if (main)
		{
			var.cursor--;
			var.cursor += 4;
			var.cursor %= 4;
			selectedSlot = false;
		}
		else if (var.mType == MenuType.NONE)
			w = true;
		else if (var.mType == MenuType.MAIN)
		{
			var.cursor--;
			var.cursor += 4;
			var.cursor %= 4;
		}
	}

	public void s()
	{
		if (main)
		{
			var.cursor++;
			var.cursor += 4;
			var.cursor %= 4;
			selectedSlot = false;
		}
		else if (var.mType == MenuType.NONE)
			s = true;
		else if (var.mType == MenuType.MAIN)
		{
			var.cursor++;
			var.cursor += 4;
			var.cursor %= 4;
		}
	}

	public void a()
	{
		if (var.mType == MenuType.NONE)
			a = true;
	}

	public void d()
	{
		if (var.mType == MenuType.NONE)
			d = true;
	}

	public void select()
	{
		if (main)
		{
			if (nue)
				if (!selectedSlot)
					selectedSlot = true;
				else
				{
					main = false;
					var.saveFile = var.cursor + 1;
				}
			else if (load)
				if (!selectedSlot)
					selectedSlot = true;
				else
				{
					main = false;
					var.saveFile = var.cursor + 1;
					load(Integer.toString(var.saveFile));
				}
			else if (var.cursor == 0)
				nue = true;
			else if (var.cursor == 1)
				load("1");
			else if (var.cursor == 2)
				load = true;
			else if (var.cursor == 3)
				System.exit(0);
		}
		else if (var.mType == MenuType.NONE)
			var.mType = MenuType.MAIN;
		else if (var.mType == MenuType.MAIN)
		{
			if (var.cursor == 3)
				var.mType = MenuType.INVENTORY;
			else if (var.cursor == 0)
				save();
			else if (var.cursor == 1)
				load("1");
		}
	}

	public void cancel()
	{
		if (main)
		{
			if (!selectedSlot)
			{
				nue = false;
				load = false;
			}
			selectedSlot = false;
		}
		else if (var.mType == MenuType.MAIN)
			var.mType = MenuType.NONE;
		else if (var.mType == MenuType.INVENTORY)
			var.mType = MenuType.NONE;
		else if (var.mType == MenuType.DIALOGUE)
			var.mType = MenuType.NONE;
	}

	public void interact()
	{
		if (var.mType == MenuType.NONE && player.s == Status.IDLE)
		{
			for(int i = 0; i < var.npcs.size(); i++)
			{
				if (var.npcs.get(i).x == player.x + ((player.ws == WalkingStatus.LEFTIDLE) ? -32 : (player.ws == WalkingStatus.RIGHTIDLE) ? 32 : 0)
				 && var.npcs.get(i).y == player.y - ((player.ws == WalkingStatus.FORWARDIDLE) ? -32 : (player.ws == WalkingStatus.BACKWARDIDLE) ? 32 : 0))
				{
					if (var.npcs.get(i) instanceof TalkingNPC)
					{
						TalkingNPC holder = (TalkingNPC)var.npcs.get(i);
						var.cDialogue = holder.getTopic();
						var.mType = MenuType.DIALOGUE;
					}
				}
			}
		}
		else if (var.mType == MenuType.DIALOGUE)
		{
			if (var.dCursor != var.cDialogue.line.length - 1)
			{
				if (var.dCursor2 == var.cDialogue.line[var.dCursor].length())
				{
					var.dCursor2 = 0;
					var.dCursor++;
				}
				else
					var.dCursor2 = var.cDialogue.line[var.dCursor].length();
			}
			else
			{
				var.cDialogue = null;
				var.mType = MenuType.NONE;
				var.dCursor = 0;
				var.dCursor2 = 0;
			}
		}
	}

	public void save()
	{
		String loadName = Integer.toString(var.saveFile);
		try
		{
			FileOutputStream fileOut = new FileOutputStream("Saves\\" + loadName + "\\var.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(var);
			fileOut = new FileOutputStream("Saves\\" + loadName + "\\data.ser");
			out = new ObjectOutputStream(fileOut);
			out.writeObject(data);
			fileOut = new FileOutputStream("Saves\\" + loadName + "\\player.ser");
			out = new ObjectOutputStream(fileOut);
			out.writeObject(player);
			out.close();
			fileOut.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Saved!");
		}
	}

	public void load(String loadName)
	{
		try
		{
			FileInputStream fileIn = new FileInputStream("Saves\\" + loadName + "\\data.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			data = (NPCData) in.readObject();
			System.out.println("data loaded");
			fileIn = new FileInputStream("Saves\\" + loadName + "\\var.ser");
			in = new ObjectInputStream(fileIn);
			var = (CurrentVariables) in.readObject();
			var.load(var.locName);
			var.mType = MenuType.MAIN;
			System.out.println("var loaded");
			fileIn = new FileInputStream("Saves\\" + loadName + "\\player.ser");
			in = new ObjectInputStream(fileIn);
			player = new Player(var);
			player = (Player) in.readObject();
			player.loadImages();
			player.loadFrame();
			player.var = var;
			System.out.println("player loaded");
			qt = new QuestTracker(var, data, player);
			System.out.println("quest tracker initialized");
			in.close();
			fileIn.close();
		}
		catch(IOException|ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}

	public void add(Dialogue d)
	{
		var.mType = MenuType.DIALOGUE;
		var.cDialogue = d;
	}

	public void loadSaveData()
	{
		Player player = null;
		for(int i = 1; i < 5; i++)
		{
			try
			{
				FileInputStream fileIn = new FileInputStream("Saves\\" + Integer.toString(i) + "player.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				player = (Player) in.readObject();
				saveNames[i - 1] = player.name;
			}
			catch(IOException|ClassNotFoundException e)
			{
				continue;
			}
		}
	}
}