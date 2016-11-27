package sylcode.levels.rules;

public class KillRule {
	String entityID;
	int numberOfKills;
	
	public KillRule(String entityID,int numberOfKills)
	{
		this.entityID = entityID;
		this.numberOfKills = numberOfKills;
	}
	
	public String getEntityID()
	{
		return this.entityID;
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