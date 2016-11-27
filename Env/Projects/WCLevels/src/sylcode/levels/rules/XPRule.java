package sylcode.levels.rules;

public class XPRule {
	int numberOfXP;
	
	public XPRule(int numberOfXP)
	{
		this.numberOfXP = numberOfXP;
	}
	
	public int getXP()
	{
		return this.numberOfXP;
	}
	
	public void setXP(int n)
	{
		this.numberOfXP = n;
	}
	
	public void updateXP(int numberOfXP)
	{
		this.numberOfXP += numberOfXP;
	}
}