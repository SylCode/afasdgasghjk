package tasks;

import org.bukkit.scheduler.BukkitRunnable;

import sylcode.levels.PlayerList;

public class InformPlayersTask extends BukkitRunnable
{
	private PlayerList list;

    public InformPlayersTask(PlayerList list) 
    {
        this.list = list;
    }

    @Override
    public void run() 
    {
    	list.informPlayers();
    }
}
