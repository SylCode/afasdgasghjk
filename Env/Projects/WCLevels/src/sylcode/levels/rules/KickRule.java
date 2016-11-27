package sylcode.levels.rules;

public class KickRule {
	int NumberKicked;
	
	public KickRule(int NumberKicked)
	{
		this.NumberKicked = NumberKicked;
	}
	
	public int getNumberJoined()
	{
		return this.NumberKicked;
	}
	
	public void setNumberJoined(int n)
	{
		this.NumberKicked = n;
	}
	
	public void updateNumberJoined(int NumberKicked)
	{
		this.NumberKicked += NumberKicked;
	}
}