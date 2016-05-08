public enum MenuType
{
	NONE(0),
	MAIN(1),
	INVENTORY(2),
	PARTY(3),
	DIALOGUE(4);

	public final int type;

	MenuType(int val)
	{
		this.type = val;
	}
}