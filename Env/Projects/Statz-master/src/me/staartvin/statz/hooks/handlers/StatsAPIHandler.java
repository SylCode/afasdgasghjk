package me.staartvin.statz.hooks.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

import me.staartvin.statz.Statz;
import me.staartvin.statz.hooks.Dependency;
import me.staartvin.statz.hooks.DependencyHandler;
import nl.lolmewn.stats.api.StatsAPI;
import nl.lolmewn.stats.api.stat.Stat;
import nl.lolmewn.stats.api.stat.StatEntry;
import nl.lolmewn.stats.api.user.StatsHolder;
import nl.lolmewn.stats.bukkit.BukkitMain;

/**
 * Handles all connections with Stats
 * <p>
 * Date created: 21:02:34 15 mrt. 2014
 * 
 * @author Staartvin
 * 
 */
public class StatsAPIHandler implements DependencyHandler {

	private StatsAPI api;
	private BukkitMain stats;
	private final Statz plugin;

	public StatsAPIHandler(final Statz instance) {
		plugin = instance;
	}

	/* (non-Javadoc)
	 * @see me.armar.plugins.autorank.hooks.DependencyHandler#get()
	 */
	@Override
	public Plugin get() {
		final Plugin plugin = this.plugin.getServer().getPluginManager()
				.getPlugin(Dependency.STATS.getInternalString());

		try {
			// WorldGuard may not be loaded
			if (plugin == null || !(plugin instanceof BukkitMain)) {
				return null; // Maybe you want throw an exception instead
			}
		} catch (final NoClassDefFoundError exception) {
			this.plugin.debugMessage(ChatColor.RED
					+ "Could not find Stats because it's probably disabled! Does Stats properly connect to your MySQL database?");
			return null;
		}

		return plugin;
	}

	/**
	 * Gets the total blocks of a certain id and damage value placed/broken
	 * 
	 * @param uuid UUID to check for
	 * @param id Item ID to check for
	 * @param damageValue Damage value to check for. (negative number to not
	 *            skip check)
	 * @param worldName World to check in. Null for global.
	 * @param statName Either "Block break" or "Block place"
	 * @return amount player placed/broke of a block
	 */
	@SuppressWarnings("deprecation")
	public int getBlocksStat(final UUID uuid, final int id, final int damageValue, final String worldName,
			final String statName) {
		if (!isAvailable())
			return 0;

		final Collection<StatEntry> stat = getStatType(statName, uuid);
		boolean checkDamageValue = false;

		if (damageValue > 0) {
			checkDamageValue = true;
		}

		int value = 0;

		for (final StatEntry s : stat) {
			final Map<String, Object> metadata = s.getMetadata();

			// Check world
			if (worldName != null && metadata.containsKey("world")) {
				// Not in the world we look for
				if (!metadata.get("world").equals(worldName))
					continue;
			}

			// Check damage value
			if (checkDamageValue) {
				if (metadata.containsKey("data")) {
					if (!metadata.get("data").equals(damageValue))
						continue;
				}
			}

			// Check correct id
			if (metadata.containsKey("name")) {
				final Material material = Material.matchMaterial(metadata.get("name").toString());

				if (material.getId() != id)
					continue;
			}

			value += s.getValue();
		}

		return value;

		/*int value = 0;
		
		
		if (damageValue > 0) {
			checkDamageValue = true;
		}
		
		for (final Object[] vars : blockStat.getAllVariables()) {
		
			if (checkDamageValue) {
				// VAR 0 = blockID INT, VAR 1 = damageValue BYTE, VAR 2 = (true = break, false = place) BOOLEAN
		
				final byte byteValue = (Byte) vars[1];
		
				if ((Integer) vars[0] == id && byteValue == damageValue) {
					value += blockStat.getValue(vars);
				}
			} else {
				if ((Integer) vars[0] == id) {
					value += blockStat.getValue(vars);
				}
			}
		}
		
		return value;*/
	}

	public EntityType getEntityType(final String entityName) {
		try {
			return EntityType.valueOf(entityName.toUpperCase());
		} catch (final Exception e) {
			return null;
		}
	}

	public int getNormalStat(final UUID uuid, final String statName, final String worldName) {
		if (!isAvailable())
			return 0;

		final Collection<StatEntry> stat = getStatType(statName, uuid);

		int value = 0;

		for (final StatEntry s : stat) {
			final Map<String, Object> metadata = s.getMetadata();

			if (worldName != null && metadata.containsKey("world")) {
				// Not in the world we look for
				if (!metadata.get("world").equals(worldName))
					continue;
			}

			value += s.getValue();
		}

		return value;

		/*
		
		for (final Object[] vars : stat.getAllVariables()) {
			value += stat.getValue(vars);
		}
		
		return value;*/
	}

	/**
	 * Get the stats of a player, a new stat will be created if it didn't exist
	 * yet.
	 * 
	 * @param statName Name of the stat to get
	 * @param uuid UUID to get the stats of.
	 * @return Requested stat of the player
	 */
	public Collection<StatEntry> getStatType(final String statName, final UUID uuid) {

		if (uuid == null) {
			return new ArrayList<StatEntry>();
		}

		final StatsHolder holder = stats.getUserManager().getUser(uuid);

		if (holder == null) {
			plugin.debugMessage("UUID '" + uuid.toString() + "' was not found in Stats database!");

			return new ArrayList<StatEntry>();
		}

		final Stat stat = stats.getStatManager().getStat(statName);

		if (stat == null)
			throw new IllegalArgumentException("Unknown stat '" + statName + "'!");

		return holder.getStats(stat);
	}

	public int getTotalBlocksBroken(final UUID uuid, final String worldName) {
		if (!isAvailable())
			return 0;

		return this.getNormalStat(uuid, "Blocks broken", worldName);
	}

	public int getTotalBlocksMoved(final UUID uuid, final int type, final String worldName) {
		if (!isAvailable())
			return 0;

		final String statName = "Move";

		final Collection<StatEntry> stat = getStatType(statName, uuid);

		int value = 0;

		for (final StatEntry s : stat) {

			final Map<String, Object> metadata = s.getMetadata();

			if (worldName != null && metadata.containsKey("world")) {
				// Not in the world we look for
				if (!metadata.get("world").equals(worldName))
					continue;
			}

			if (metadata.containsKey("type") && (Integer) metadata.get("type") != type)
				continue;

			value += s.getValue();

		}

		return value;
	}

	public int getTotalBlocksPlaced(final UUID uuid, final String worldName) {
		if (!isAvailable())
			return 0;

		return this.getNormalStat(uuid, "Blocks placed", worldName);
	}

	public int getTotalMobsKilled(final UUID uuid, final String mobName, final String worldName) {
		if (!isAvailable())
			return 0;

		final String statName = "Kill";

		// Mob type
		String type = null;

		if (mobName != null && !mobName.equals("")) {

			type = EntityType.valueOf(mobName.toUpperCase().replaceAll(" ", "_")).toString();
		}

		final Collection<StatEntry> stat = getStatType(statName, uuid);

		int value = 0;

		for (final StatEntry s : stat) {

			final Map<String, Object> metadata = s.getMetadata();

			if (worldName != null && metadata.containsKey("world")) {
				// Not in the world we look for
				if (!metadata.get("world").equals(worldName))
					continue;
			}

			if (type != null && metadata.containsKey("entityType") && !metadata.get("entityType").equals(type))
				continue;

			// If no type was given (so any mob can be killed, exclude 'player' kills, as most admins don't see players as a real mob).
			if (type == null && metadata.containsKey("entityType") && metadata.get("entityType").equals("PLAYER"))
				continue;

			value += s.getValue();

		}

		return value;
	}

	public int getTotalPlayTime(final UUID uuid, final String worldName) {
		if (!isAvailable())
			return 0;

		return this.getNormalStat(uuid, "Playtime", worldName);
	}

	/**
	 * Get a list of uuids that Stats has logged.
	 * @return a list of uuids that represent players that Stats has logged in its database.
	 */
	public List<UUID> getLoggedPlayers() {
		List<UUID> playerNames = new ArrayList<>();

		for (OfflinePlayer player : plugin.getServer().getOfflinePlayers()) {
			StatsHolder user = this.getStatsHolder(player.getUniqueId());
			
			if (user == null) {
				plugin.getLogger().warning("Could not load data from user " + player.getName() + ". Skipping him/her!");
				continue;
			}
			
			playerNames.add(player.getUniqueId());
		}

		return playerNames;
	}

	public StatsHolder getStatsHolder(UUID uuid) {

		StatsHolder user = stats.getUserManager().getUser(uuid);

		// Users are only loaded when they are offline, so try to force-load them.
		if (user == null) {
			try {
				user = stats.getUserManager().loadUser(uuid, stats.getStatManager());
				return user;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return null;
			}
		}

		return user;
	}

	/* (non-Javadoc)
	 * @see me.armar.plugins.autorank.hooks.DependencyHandler#isAvailable()
	 */
	@Override
	public boolean isAvailable() {
		return api != null;
	}

	/* (non-Javadoc)
	 * @see me.armar.plugins.autorank.hooks.DependencyHandler#isInstalled()
	 */
	@Override
	public boolean isInstalled() {
		final Plugin plugin = get();

		return plugin != null && plugin.isEnabled();
	}

	/* (non-Javadoc)
	 * @see me.armar.plugins.autorank.hooks.DependencyHandler#setup()
	 */
	@Override
	public boolean setup(final boolean verbose) {
		if (!isInstalled()) {
			if (verbose) {
				plugin.debugMessage(ChatColor.RED + Dependency.STATS.getInternalString() + " has not been found!");
			}
			return false;
		} else {

			api = plugin.getServer().getServicesManager().getRegistration(StatsAPI.class).getProvider();

			stats = (BukkitMain) get();

			if (api != null) {
				return true;
			} else {
				if (verbose) {
					plugin.debugMessage(ChatColor.RED + Dependency.STATS.getInternalString()
							+ " has been found but cannot be used!");
				}
				return false;
			}
		}
	}

	public void addStat(final Stat stat) {
		if (!isAvailable())
			return;

		api.getStatManager().addStat(stat);
	}

	public StatsAPI getAPI() {
		return api;
	}
}
