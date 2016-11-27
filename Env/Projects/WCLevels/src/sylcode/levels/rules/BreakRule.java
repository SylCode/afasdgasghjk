package sylcode.levels.rules;

public class BreakRule {
	int blockID;
	int metadata;
	int numberOfBreaks;
	
	public BreakRule(int blockID, int metadata, int numberOfBreaks)
	{
		this.blockID = blockID;
		this.metadata = metadata;
		this.numberOfBreaks = numberOfBreaks;
	}
	
	public int getBlockID()
	{
		return this.blockID;
	}

	public int getMetaData()
	{
		return this.metadata;
	}
	
	public int getNumberOfBreaks()
	{
		return this.numberOfBreaks;
	}
	
	public void setNumberOfBreaks(int n)
	{
		this.numberOfBreaks = n;
	}
	
	public void updateBreaks(int numberOfBreaks)
	{
		this.numberOfBreaks += numberOfBreaks;
	}
}