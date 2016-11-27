package me.staartvin.statz.hooks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.ChatColor;

import me.staartvin.statz.Statz;
import me.staartvin.statz.hooks.handlers.AFKTerminatorHandler;
import me.staartvin.statz.hooks.handlers.ASkyBlockHandler;
import me.staartvin.statz.hooks.handlers.AcidIslandHandler;
import me.staartvin.statz.hooks.handlers.EssentialsHandler;
import me.staartvin.statz.hooks.handlers.FactionsHandler;
import me.staartvin.statz.hooks.handlers.GriefPreventionHandler;
import me.staartvin.statz.hooks.handlers.JobsHandler;
import me.staartvin.statz.hooks.handlers.McMMOHandler;
import me.staartvin.statz.hooks.handlers.NuVotifierHandler;
import me.staartvin.statz.hooks.handlers.OnTimeHandler;
import me.staartvin.statz.hooks.handlers.RPGmeHandler;
import me.staartvin.statz.hooks.handlers.RoyalCommandsHandler;
import me.staartvin.statz.hooks.handlers.StatisticsAPIHandler;
import me.staartvin.statz.hooks.handlers.StatsAPIHandler;
import me.staartvin.statz.hooks.handlers.UltimateCoreHandler;
import me.staartvin.statz.hooks.handlers.VaultHandler;
import me.staartvin.statz.hooks.handlers.VotifierHandler;
import me.staartvin.statz.hooks.handlers.WorldGuardHandler;

/**
 * This class is used for loading all the dependencies Statz has. <br>
 * Not all dependencies are required, some are optional.
 * <p>
 * Date created: 18:18:43 2 mrt. 2014
 * 
 * @author Staartvin
 * 
 */
public class DependencyManager {

	private final HashMap<Dependency, DependencyHandler> handlers = new HashMap<Dependency, DependencyHandler>();

	private final Statz plugin;

	public DependencyManager(final Statz instance) {
		plugin = instance;

		// Register handlers
		try {
			handlers.put(Dependency.VOTIFIER, new VotifierHandler(instance));
		} catch (NoClassDefFoundError e) {
			plugin.debugMessage("Could not load Votifier!");
		}

		try {
			handlers.put(Dependency.JOBS, new JobsHandler(instance));
		} catch (NoClassDefFoundError e) {
			plugin.debugMessage("Could not load Jobs!");
		}

		try {
			handlers.put(Dependency.MCMMO, new McMMOHandler(instance));
		} catch (NoClassDefFoundError e) {
			plugin.debugMessage("Could not load mcMMO!");
		}

		try {
			handlers.put(Dependency.ASKYBLOCK, new ASkyBlockHandler(instance));
		} catch (NoClassDefFoundError e) {
			plugin.debugMessage("Could not load ASkyBlock!");
		}

		try {
			handlers.put(Dependency.ACIDISLAND, new AcidIslandHandler(instance));
		} catch (NoClassDefFoundError e) {
			plugin.debugMessage("Could not load AcidIsland!");
		}

		try {
			handlers.put(Dependency.WORLDGUARD, new WorldGuardHandler(instance));
		} catch (NoClassDefFoundError e) {
			plugin.debugMessage("Could not load WorldGuard!");
		}

		try {
			handlers.put(Dependency.ROYAL_COMMANDS, new RoyalCommandsHandler(instance));
		} catch (NoClassDefFoundError e) {
			plugin.debugMessage("Could not load RoyalCommands!");
		}

		try {
			handlers.put(Dependency.ON_TIME, new OnTimeHandler(instance));
		} catch (NoClassDefFoundError e) {
			plugin.debugMessage("Could not load OnTime!");
		}

		try {
			handlers.put(Dependency.AFKTERMINATOR, new AFKTerminatorHandler(instance));
		} catch (NoClassDefFoundError e) {
			plugin.debugMessage("Could not load afkTerminator!");
		}
		try {
			handlers.put(Dependency.ESSENTIALS, new EssentialsHandler(instance));
		} catch (NoClassDefFoundError e) {
			plugin.debugMessage("Could not load Essentials!");
		}
		try {
			handlers.put(Dependency.FACTIONS, new FactionsHandler(instance));
		} catch (NoClassDefFoundError e) {
			plugin.debugMessage("Could not load Factions!");
		}

		try {
			handlers.put(Dependency.STATISTICS, new StatisticsAPIHandler(instance));
		} catch (NoClassDefFoundError e) {
			plugin.debugMessage("Could not load Statistics!");
		}

		try {
			handlers.put(Dependency.STATS, new StatsAPIHandler(instance));
		} catch (NoClassDefFoundError e) {
			plugin.debugMessage("Could not load Stats!");
		}

		try {
			handlers.put(Dependency.ULTIMATE_CORE, new UltimateCoreHandler(instance));
		} catch (NoClassDefFoundError e) {
			plugin.debugMessage("Could not load UltimateCore!");
		}

		try {
			handlers.put(Dependency.VAULT, new VaultHandler(instance));
		} catch (NoClassDefFoundError e) {
			plugin.debugMessage("Could not load Vault!");
		}

		try {
			handlers.put(Dependency.GRIEF_PREVENTION, new GriefPreventionHandler(instance));
		} catch (NoClassDefFoundError e) {
			plugin.debugMessage("Could not load GriefPrevention!");
		}

		try {
			handlers.put(Dependency.RPGME, new RPGmeHandler(instance));
		} catch (NoClassDefFoundError e) {
			plugin.debugMessage("Could not load RPGMe!");
		}

		try {
			handlers.put(Dependency.NUVOTIFIER, new NuVotifierHandler(instance));
		} catch (NoClassDefFoundError e) {
			plugin.debugMessage("Could not load NuVotifier!");
		}
	}

	/**
	 * Gets a specific dependency.
	 * 
	 * @param dep Dependency to get.
	 * @return the {@linkplain DependencyHandler} that is associated with the
	 *         given {@linkplain Dependency}, can be null.
	 */
	public DependencyHandler getDependency(final Dependency dep) {

		if (!handlers.containsKey(dep)) {
			throw new IllegalArgumentException("Unknown dependency '" + dep.toString() + "'");
		} else {
			return handlers.get(dep);
		}
	}

	/*public StatsPlugin getStatsPlugin() {
		return statsPluginManager.getStatsPlugin();
	}*/

	/**
	 * Gets whether the given player is AFK.
	 * <br>
	 * Obeys the AFK setting in the Settings.yml.
	 * 
	 * @param player Player to check.
	 * @return true if the player is supspected of being AFK, false otherwise.
	 */
	/*public boolean isAFK(final Player player) {
		if (!plugin.getConfigHandler().useAFKIntegration()) {
			return false;
		}
	
		if (handlers.get(dependency.ESSENTIALS).isAvailable()) {
			plugin.debugMessage("Using Essentials for AFK");
			return ((EssentialsHandler) handlers.get(dependency.ESSENTIALS)).isAFK(player);
		} else if (handlers.get(dependency.ROYALCOMMANDS).isAvailable()) {
			plugin.debugMessage("Using RoyalCommands for AFK");
			return ((RoyalCommandsHandler) handlers.get(dependency.ROYALCOMMANDS)).isAFK(player);
		} else if (handlers.get(dependency.ULTIMATECORE).isAvailable()) {
			plugin.debugMessage("Using UltimateCore for AFK");
			return ((UltimateCoreHandler) handlers.get(dependency.ULTIMATECORE)).isAFK(player);
		} else if (handlers.get(dependency.AFKTERMINATOR).isAvailable()) {
			plugin.debugMessage("Using AFKTerminator for AFK");
			return ((AFKTerminatorHandler) handlers.get(dependency.AFKTERMINATOR)).isAFK(player);
		}
		// No suitable plugin found
		return false;
	}*/

	/**
	 * Loads all dependencies used for Statz. <br>
	 * Statz will check for dependencies and shows the output on the console.
	 * 
	 * 
	 */
	public void loadDependencies() {

		// Make seperate loading bar

		plugin.debugMessage(ChatColor.YELLOW + "---------------[Statz Dependencies]---------------");
		plugin.debugMessage(ChatColor.GREEN + "Searching dependencies...");

		// Load all dependencies
		for (final DependencyHandler depHandler : handlers.values()) {
			// Make sure to respect settings
			boolean succeeded = depHandler.setup(true);

			if (succeeded) {
				Dependency dependency = this.getDependencyByHandler(depHandler);

				if (dependency == null)
					continue;

				// NuVotifier has the same internal name, and hence cannot be distinguished from Votifier. 
				// That's why we provide a special case.
				if (dependency == Dependency.NUVOTIFIER) {
					plugin.debugMessage(ChatColor.GREEN + "NuVotifier was found and Statz now tracks its data!");
				} else {
					plugin.debugMessage(ChatColor.GREEN + dependency.getInternalString()
							+ " was found and Statz now tracks its data!");
				}

			}
		}

		// Make seperate stop loading bar
		plugin.debugMessage(ChatColor.YELLOW + "---------------[Statz Dependencies]---------------");

		plugin.debugMessage("Loaded libraries and dependencies");
	}

	/**
	 * Get the Dependency by the Dependency Handler
	 * @param depHandler The dependency handler to get the dependency from.
	 * @return the dependency that is associated with this dependency handler or null if no association was found.
	 */
	public Dependency getDependencyByHandler(DependencyHandler depHandler) {
		for (Entry<Dependency, DependencyHandler> entry : handlers.entrySet()) {
			if (entry.getValue().equals(depHandler)) {
				return entry.getKey();
			}
		}
		return null;
	}

	public boolean isAvailable(Dependency dep) {
		DependencyHandler handler = handlers.get(dep);

		if (handler == null)
			return false;

		return handler.isAvailable();
	}

	public List<Dependency> getAvailableDependencies() {
		List<Dependency> dependencies = new ArrayList<>();

		for (Dependency d : Dependency.values()) {
			if (this.isAvailable(d)) {
				dependencies.add(d);
			}
		}

		return dependencies;
	}

}
