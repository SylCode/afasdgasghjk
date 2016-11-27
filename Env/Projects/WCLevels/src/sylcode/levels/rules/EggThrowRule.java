package sylcode.levels.rules;

public class EggThrowRule 
{
	int NumberOfEggs;
	
	public EggThrowRule(int NumberOfEggs)
	{
		this.NumberOfEggs = NumberOfEggs;
	}
	
	public int getNumberOfEggs()
	{
		return this.NumberOfEggs;
	}
	
	public void setNumberOfEggs(int n)
	{
		this.NumberOfEggs = n;
	}
	
	public void updateNumberOfEggs(int NumberOfEggs)
	{
		this.NumberOfEggs += NumberOfEggs;
	}
}
