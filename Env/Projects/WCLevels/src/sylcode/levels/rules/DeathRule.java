package sylcode.levels.rules;

public class DeathRule {
	int deaths;
	
	public DeathRule(int deaths)
	{
		this.deaths = deaths;
	}
	
	public int getNumberOfKills()
	{
		return this.deaths;
	}
	public void setDeaths(int n)
	{
		this.deaths = n;
	}
	
	public void updateDeaths(int deaths)
	{
		this.deaths += deaths;
	}
}