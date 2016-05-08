import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public final class Derst extends Location
{
	public boolean addNPC(int size, int min, int hour)
	{
		double dpdt = Math.pow(1 * (1 - (size / 30)) * (Math.sin((hour * 60 + min) * 3.1415 / (24 * 60))), 3);
		dpdt *= 100;
		if ((int)(Math.random() * 100) <= (int)dpdt)
			return true;
		return false;
	}
}