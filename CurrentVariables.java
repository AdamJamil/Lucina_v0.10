import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class CurrentVariables implements java.io.Serializable
{
	short[][] imgMap;
	boolean[][] passMap;
	MenuType mType = MenuType.NONE;
	int cursor = 0, cursor2 = 0;
	int dCursor = 0, dCursor2 = 0;
	NPCData data;
	String locName;
	transient ArrayList<NPC> npcs = new ArrayList<NPC>();
	transient ArrayList<NPC> walkingnpcs = new ArrayList<NPC>();
	Location current;
	Class<? extends Location> locClass;
	transient Dialogue cDialogue;
	int saveFile;

	public CurrentVariables(Class<? extends Location> area, NPCData inD)
	{
		data = inD;
		locClass = area;
		if (area == Derst.class)
		{
			current = new Derst();
			load("Derst");
		}
	}

	public void load(String path)
	{
		npcs = new ArrayList<NPC>();
		walkingnpcs = new ArrayList<NPC>();
		locName = path;
		BufferedImage img = null, img2 = null;
		try
		{
			img = ImageIO.read(new File(path + "\\" + path + "Map.png"));
			img2 = ImageIO.read(new File(path + "\\" + path + "PassMap.png"));
		}
		catch (IOException e) {e.printStackTrace();}
		imgMap = new short[img.getWidth()][img.getHeight()];
		passMap = new boolean[img.getWidth()][img.getHeight()];
		for(int i = 0; i < img.getWidth(); i++)
		{
			for(int j = 0; j < img.getHeight(); j++)
			{
				Color c = new Color(img.getRGB(i, j));
				imgMap[i][j] = (short)c.getRed();
				imgMap[i][j] += (short)(256 * c.getBlue());
				c = new Color(img2.getRGB(i, j));
				passMap[i][j] = ((c.getRed() == 255) && (c.getBlue() == 255) && (c.getGreen() == 255));
			}
		}

		if (path.equals("Derst"))
		{
			OldMan2 npc2 = new OldMan2();
			data.configure(npc2);
			npcs.add(npc2);
			passMap[21][21] = false;
		}
	}

	public void addNPC(int min, int hour)
	{
		if (locName.equals("Derst"))
		{
			if (((Derst)current).addNPC(walkingnpcs.size(), min, hour))
			{
				addWalkingNPC();
			}
		}
	}

	public void addWalkingNPC()
	{
		walkingnpcs.add(new OldMan(passMap, walkingnpcs));
	}
}