public class TalkingNPC extends NPC
{
	transient Dialogue[] topic;
	int topicCount = -1;

	public Dialogue getTopic()
	{
		if (topicCount != topic.length)
			topicCount++;
		return topic[topicCount];
	}
}