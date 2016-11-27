package sylcode.levels.rules;

public class TravelRule {
	int Distance;
	String MovementType;
	
	public TravelRule(int Distance, String MovementType)
	{
		this.Distance = Distance;
		this.MovementType = MovementType;
	}
	
	public int getDistance()
	{
		return this.Distance;
	}
	
	public String getMovementType()
	{
		return this.MovementType;
	}
	
	public void setDistance(int n)
	{
		this.Distance = n;
	}
	
	public void updateDistance(int Distance)
	{
		this.Distance += Distance;
	}
}