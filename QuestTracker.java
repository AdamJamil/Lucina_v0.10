public class QuestTracker
{
	CurrentVariables var;
	NPCData data;
	Player player;
	boolean flag[];

	public QuestTracker(CurrentVariables cv, NPCData nd, Player pl)
	{
		player = pl;
		var = cv;
		data = nd;
		flag = new boolean[1000];
	}

	public void check()
	{
		if (((TalkingNPC)data.npc.get(OldMan2.class)).topicCount == 2 || flag[0])
		{
			flag[0] = true;
			System.out.println("u deed it");
		}
	}
}