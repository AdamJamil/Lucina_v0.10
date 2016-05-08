public class Dialogue
{
	String[] line;
	boolean[] choice;

	public Dialogue(String[] input)
	{
		line = new String[input.length];
		choice = new boolean[input.length];
		for(int i = 0; i < input.length; i++)
		{
			line[i] = input[i];
		}
	}
}