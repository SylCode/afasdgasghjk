package sylcode.levels.rules;

public class VillagerTradeRule 
{
	String itemID;
	int Number;
	
	public VillagerTradeRule(String itemID, int Number)
	{
		this.itemID = itemID;
		this.Number = Number;
	}
	
	public String getItemID()
	{
		return this.itemID;
	}
	
	public int getNumberOfSells()
	{
		return this.Number;
	}
	
	public void setNumberOfSells(int n)
	{
		this.Number = n;
	}
	
	public void updateSells(int Number)
	{
		this.Number += Number;
	}
}
