public enum WalkingStatus
{
	FORWARDIDLE(0x0),
	FORWARD1(0x1),
	FORWARDHALF(0x2),
	FORWARD2(0x3),
	BACKWARDIDLE(0x4),
	BACKWARD1(0x5),
	BACKWARDHALF(0x6),
	BACKWARD2(0x7),
	LEFTIDLE(0x8),
	LEFT1(0x9),
	LEFTHALF(0xA),
	LEFT2(0xB),
	RIGHTIDLE(0xC),
	RIGHT1(0xD),
	RIGHTHALF(0xE),
	RIGHT2(0xF);

	public final byte status;

	WalkingStatus(int val)
	{
		this.status = (byte)val;
	}
}