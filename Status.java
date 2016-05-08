public enum Status
{
	TALKING (0),
	IDLE(1),
	ESSENTIALACTION(2),
	WALKING(3);

	public final int status;

	Status(int val)
	{
		this.status = val;
	}
}