package tasks;

import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CheckPlayerInventoryTask extends BukkitRunnable
{
	private Player player;
	private List<String> commands;
	
	public CheckPlayerInventoryTask(String playerName, List<String> commands) 
	{
		this.player = Bukkit.getPlayer(playerName);
		this.commands = commands;
	}
	@Override
	public void run() 
	{
		for (Iterator<String> iterator = commands.iterator(); iterator.hasNext();) 
		{
			if(player.getInventory().firstEmpty()!=-1)
			{
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), iterator.next());
				iterator.remove();
			}
			else
			{
				player.sendMessage(ChatColor.DARK_RED+"Ваш инвентарь полон. Чтобы получить награду освободите " + commands.size() + "слотов");
				break;
			}
		}
		if(commands.size()==0)
			this.cancel();
	}

}
