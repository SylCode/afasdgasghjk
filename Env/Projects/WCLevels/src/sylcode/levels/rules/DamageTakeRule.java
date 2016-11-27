package sylcode.levels.rules;

public class DamageTakeRule 
{
	String DamageType;
	int amount;
	
	public DamageTakeRule(String DamageType, int amount)
	{
		this.DamageType = DamageType;
		this.amount = amount;
	}
	
	public String getDamageType()
	{
		return this.DamageType;
	}
	
	public int getAmount()
	{
		return this.amount;
	}
	
	public void setAmount(int n)
	{
		this.amount = n;
	}
	
	public void updateDamage(int amount)
	{
		this.amount += amount;
	}
}
