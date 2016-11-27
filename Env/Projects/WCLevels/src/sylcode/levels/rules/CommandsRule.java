package sylcode.levels.rules;

public class CommandsRule {
	String command;
	String argument;
	int numberOfCommands;
	
	public CommandsRule(String command, String argument, int numberOfCommands)
	{
		this.command = command;
		this.argument = argument;
		this.numberOfCommands = numberOfCommands;
	}
	
	public String getCommand()
	{
		return this.command;
	}
	
	public String getArgument()
	{
		return this.argument;
	}
	
	public int getNumberOfCommands()
	{
		return this.numberOfCommands;
	}
	
	public void setNumberOfCommands(int n)
	{
		this.numberOfCommands = n;
	}
	
	public void updateNumberOfCommands(int numberOfCommands)
	{
		this.numberOfCommands += numberOfCommands;
	}
}