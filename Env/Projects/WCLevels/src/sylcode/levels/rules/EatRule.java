package sylcode.levels.rules;

public class EatRule 
{
	String Food;
	int NumberOfEat;
	
	public EatRule(String Food, int NumberOfEat)
	{
		this.Food = Food;
		this.NumberOfEat = NumberOfEat;
	}
	
	public String getFood()
	{
		return this.Food;
	}
	
	public int getNumberOfEat()
	{
		return this.NumberOfEat;
	}
	
	public void setNumberOfEat(int n)
	{
		this.NumberOfEat = n;
	}
	
	public void updateNumberOfEat(int NumberOfEat)
	{
		this.NumberOfEat += NumberOfEat;
	}
}
