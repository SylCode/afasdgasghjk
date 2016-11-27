package sylcode.levels.rules;

public class ArrowRule {
	int Shots;
	double Power;
	
	public ArrowRule(int numberOfShots, double Power)
	{
		this.Shots = numberOfShots;
		this.Power = Power;
	}
	
	public double getPower()
	{
		return this.Power;
	}
	
	public void setPower(int n)
	{
		this.Power = n;
	}
	
	public void updatePowers(int Power)
	{
		this.Power += Power;
	}
	
	public int getNumberOfShots()
	{
		return this.Shots;
	}
	
	public void setNumberOfShots(int n)
	{
		this.Shots = n;
	}
	
	public void updateShots(int numberOfShots)
	{
		this.Shots += numberOfShots;
	}
}