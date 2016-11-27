package sylcode.levels.rules;

public class BucketEmptyRule {
	int numberOfEmpty;
	
	public BucketEmptyRule(int numberOfEmpty)
	{
		this.numberOfEmpty = numberOfEmpty;
	}
	
	
	public int getNumberOfEmpty()
	{
		return this.numberOfEmpty;
	}
	
	public void setNumberOfEmpty(int n)
	{
		this.numberOfEmpty = n;
	}
	
	public void updateNumberOfEmpty(int numberOfEmpty)
	{
		this.numberOfEmpty += numberOfEmpty;
	}
}