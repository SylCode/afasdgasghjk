package sylcode.levels.rules;

public class PlayTimeRule {
	int Time;
	
	public PlayTimeRule(int Time)
	{
		this.Time = Time;
	}
	
	public int getTime()
	{
		return this.Time;
	}
	
	public void setTime(int n)
	{
		this.Time = n;
	}
	
	public void updateTime(int Time)
	{
		this.Time += Time;
	}
}