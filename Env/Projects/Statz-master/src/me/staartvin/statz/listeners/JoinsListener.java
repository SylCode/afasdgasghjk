package me.staartvin.statz.listeners;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.staartvin.statz.Statz;
import me.staartvin.statz.datamanager.PlayerStat;
import me.staartvin.statz.util.StatzUtil;

public class JoinsListener implements Listener {

	private final Statz plugin;
	public static HashMap<UUID, Integer> updateID = new HashMap<>();

	public JoinsListener(final Statz plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onJoin(final PlayerJoinEvent event) {

		final PlayerStat stat = PlayerStat.JOINS;

		// Get player
		final Player player = event.getPlayer();
		
		// Do general check
				if (!plugin.doGeneralCheck(player)) return;

		// Update name in database.
		plugin.getDataManager().setPlayerInfo(player.getUniqueId(), PlayerStat.PLAYERS,
				StatzUtil.makeQuery("uuid", player.getUniqueId().toString(), "playerName", player.getName()));

		// Get player info.
		//		final PlayerInfo info = plugin.getDataManager().getPlayerInfo(player.getUniqueId(), stat);
		//
		//		// Get current value of stat.
		//		double currentValue = 0;
		//
		//		// Check if it is valid!
		//		if (info.isValid()) {
		//			currentValue = info.getTotalValue();
		//		}

		// Update value to new stat.
		plugin.getDataManager().setPlayerInfo(player.getUniqueId(), stat,
				StatzUtil.makeQuery("uuid", player.getUniqueId().toString(), "value", 1));

		// Check if player already has a checker running.
		if (!updateID.containsKey(player.getUniqueId())) {
			// Player has joined, so create a timer that runs every minute to add time.
			BukkitRunnable run = new BukkitRunnable() {
				public void run() {
					if (!player.isOnline()) {
						updateID.remove(player.getUniqueId());
						this.cancel();
						return;
					}

//					// Get player info.
//					final PlayerInfo info = plugin.getDataManager().getPlayerInfo(player.getUniqueId(),
//							PlayerStat.TIME_PLAYED, StatzUtil.makeQuery("world", player.getWorld().getName()));
//
//					// Get current value of stat.
//					double currentValue = 0;
//
//					// Check if it is valid!
//					if (info.isValid()) {
//						currentValue += info.getTotalValue();
//					}

					// Update value to new stat.
					plugin.getDataManager().setPlayerInfo(player.getUniqueId(), PlayerStat.TIME_PLAYED,
							StatzUtil.makeQuery("uuid", player.getUniqueId().toString(), "value", 1,
									"world", player.getWorld().getName()));

				}
			};

			BukkitTask task = run.runTaskTimerAsynchronously(plugin,
					20 * 60 /*If currentValue is 0, schedule a check immediately, otherwise after a minute*/,
					20 * 60 /*Every minute*/);

			updateID.put(player.getUniqueId(), task.getTaskId());
		}
	}

}
