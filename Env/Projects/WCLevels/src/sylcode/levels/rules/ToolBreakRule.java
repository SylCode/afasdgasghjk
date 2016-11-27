package sylcode.levels.rules;

public class ToolBreakRule 
{
	String itemID;
	int NumberOfBreaks;
	
	public ToolBreakRule(String itemID, int numberOfBreaks)
	{
		this.itemID = itemID;
		this.NumberOfBreaks = numberOfBreaks;
	}
	
	public String getItemID()
	{
		return this.itemID;
	}
	
	public int getNumberOfBreaks()
	{
		return this.NumberOfBreaks;
	}
	
	public void setNumberOfBreaks(int n)
	{
		this.NumberOfBreaks = n;
	}
	
	public void updateNumberOfBreaks(int numberOfBreaks)
	{
		this.NumberOfBreaks += numberOfBreaks;
	}
}
