package sylcode.levels.rules;

public class CraftRule 
{
	String itemID;
	int numberOfCrafts;
	
	public CraftRule(String itemID,int numberOfCrafts)
	{
		this.itemID = itemID;
		this.numberOfCrafts = numberOfCrafts;
	}
	
	public String getItemID()
	{
		return this.itemID;
	}
	
	
	public int getNumberOfCrafts()
	{
		return this.numberOfCrafts;
	}
	
	public void setNumberOfCrafts(int n)
	{
		this.numberOfCrafts = n;
	}
	
	public void updateCrafts(int numberOfCrafts)
	{
		this.numberOfCrafts += numberOfCrafts;
	}
}
