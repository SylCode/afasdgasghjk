package listeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import sylcode.levels.WCLevels;

public class CmdListener implements CommandExecutor 
{
	WCLevels plg;
	
	public CmdListener(WCLevels plg)
	{
		this.plg = plg;
	}

	@Override
	public boolean onCommand(CommandSender p, Command cmd, String cmdLabel, String[] args) 
	{
		if (!(p instanceof Player))
			return false;
		if (cmdLabel.equalsIgnoreCase("wcl")) 
		{
	       if ((args.length > 0))// && (p.hasPermission("wclevels.wcl"))) 
	       {
	         if (args.length == 1) return ExecuteCmd(p, args[0]);
	         if (args.length == 2) return ExecuteCmd(p, args[0], args[1]);
	         if (args.length == 3) return ExecuteCmd(p, args[0], args[1], args[2]);
	         }
	       //else 
	       //{ 
	    	//   Player player = (Player) p;
	    	//   p.sendMessage(ChatColor.RED+"You have no permission for this command");
	       //}
	       return true;
	     }
		if (cmdLabel.equalsIgnoreCase("completelvl"))
		{
			if ((args.length > 0))// && (p.hasPermission("wclevels.admin"))) 
		       {
		         if (args.length == 1) return ExecuteCmd(p, args[0]);
		         if (args.length == 2) return ExecuteCmd(p, args[0], args[1]);
		         if (args.length == 3) return ExecuteCmd(p, args[0], args[1], args[2]);
		       }
		       return true;
		}
	       
		return false;
	}
	
	private boolean ExecuteCmd(CommandSender p, String cmd)
   {
		if (cmd.equalsIgnoreCase("completelvl"))
		{
			//if(p.hasPermission("wclevels.admin"))
			//{
				Player player = (Player)p;
				this.plg.players.getPlayer(player).completeLevel();
				return true;
			//}
			//else
			//{
		    //	   Player player = (Player) p;
		    //	   p.sendMessage(ChatColor.RED+"You have no permission for this command");
			//}
		}
		if (cmd.equalsIgnoreCase("info"))
		{
			Player player = (Player)p;
			this.plg.players.getPlayer(player).messageStatus(this.plg.getStatzAPI());
			return true;
		}
		
		if (cmd.equalsIgnoreCase("special_info"))// && p.hasPermission("wclevels.admin"))
		{
			Player player = (Player)p;
			this.plg.players.getPlayer(player).messageSpecialStatus(this.plg.getStatzAPI());
		}
		return false;
   }
	
	private boolean ExecuteCmd(CommandSender p, String cmd, String cmd1)
	{
		return false;
   }
	
	private boolean ExecuteCmd(CommandSender p, String cmd, String cmd1, String cmd2)
   {
		return false;
   }
	
}
