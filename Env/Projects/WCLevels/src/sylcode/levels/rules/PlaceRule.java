package sylcode.levels.rules;

public class PlaceRule {
	int blockID;
	int metadata;
	int numberOfPlace;
	
	public PlaceRule(int blockID, int metadata, int numberOfPlace)
	{
		this.blockID = blockID;
		this.numberOfPlace = numberOfPlace;
		this.metadata = metadata;
	}
	
	public int getBlockID()
	{
		return this.blockID;
	}
	
	public int getMetaData()
	{
		return this.metadata;
	}
	
	public int getNumberOfPlace()
	{
		return this.numberOfPlace;
	}
	
	public void setNumberOfPlace(int n)
	{
		this.numberOfPlace = n;
	}
	
	public void updatePlace(int numberOfPlaces)
	{
		this.numberOfPlace += numberOfPlaces;
	}
}