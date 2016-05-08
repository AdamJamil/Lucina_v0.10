import java.util.HashMap;
import java.util.ArrayList;

public class NPCData implements java.io.Serializable
{
	HashMap<Class<? extends NPC>, NPC> npc = new HashMap<Class<? extends NPC>, NPC>();
	public void configure(NPC lol)
	{
		if (npc.containsKey(lol.getClass()))
		{
			lol = npc.get(lol);
		}
	}

	public void update(ArrayList<NPC> npcs)
	{
		for(int i = 0; i < npcs.size(); i++)
		{
			npc.putIfAbsent(npcs.get(i).getClass(), npcs.get(i));
		}
	}
}