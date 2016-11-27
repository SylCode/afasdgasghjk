package sylcode.levels.rules;

public class JoinRule {
	int NumberJoined;
	
	public JoinRule(int NumberJoined)
	{
		this.NumberJoined = NumberJoined;
	}
	
	public int getNumberJoined()
	{
		return this.NumberJoined;
	}
	
	public void setNumberJoined(int n)
	{
		this.NumberJoined = n;
	}
	
	public void updateNumberJoined(int NumberJoined)
	{
		this.NumberJoined += NumberJoined;
	}
}