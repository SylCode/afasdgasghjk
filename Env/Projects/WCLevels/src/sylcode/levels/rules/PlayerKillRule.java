package sylcode.levels.rules;

public class PlayerKillRule {
	String name;
	int numberOfKills;
	
	public PlayerKillRule(String name,int numberOfKills)
	{
		this.name = name;
		this.numberOfKills = numberOfKills;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getNumberOfKills()
	{
		return this.numberOfKills;
	}
	public void setNumberOfKills(int n)
	{
		this.numberOfKills = n;
	}
	
	public void updateKills(int numberOfKills)
	{
		this.numberOfKills += numberOfKills;
	}
}