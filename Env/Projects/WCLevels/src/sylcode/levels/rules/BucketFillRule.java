package sylcode.levels.rules;

public class BucketFillRule {
	int numberOfFill;
	
	public BucketFillRule(int numberOfFill)
	{
		this.numberOfFill = numberOfFill;
	}
	
	
	public int getNumberOfFill()
	{
		return this.numberOfFill;
	}
	
	public void setNumberOfFill(int n)
	{
		this.numberOfFill = n;
	}
	
	public void updateNumberOfFill(int numberOfFill)
	{
		this.numberOfFill += numberOfFill;
	}
}