package me.staartvin.statz.hooks.handlers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.royaldev.royalcommands.RoyalCommands;

import me.staartvin.statz.Statz;
import me.staartvin.statz.hooks.Dependency;
import me.staartvin.statz.hooks.DependencyHandler;

/**
 * Handles all connections with RoyalCommands
 * <p>
 * Date created: 21:02:05 15 mrt. 2014
 * 
 * @author Staartvin
 * 
 */
public class RoyalCommandsHandler implements DependencyHandler {

	private RoyalCommands api;
	private final Statz plugin;

	public RoyalCommandsHandler(final Statz instance) {
		plugin = instance;
	}

	/* (non-Javadoc)
	 * @see me.armar.plugins.autorank.hooks.DependencyHandler#get()
	 */
	@Override
	public Plugin get() {
		final Plugin plugin = this.plugin.getServer().getPluginManager()
				.getPlugin(Dependency.ROYAL_COMMANDS.getInternalString());

		// WorldGuard may not be loaded
		if (plugin == null || !(plugin instanceof RoyalCommands)) {
			return null; // Maybe you want throw an exception instead
		}

		return plugin;
	}

	public boolean isAFK(final Player player) {
		if (!isAvailable())
			return false;

		return api.getAPI().getPlayerAPI().isAfk(player);
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
		final RoyalCommands plugin = (RoyalCommands) get();

		return plugin != null && plugin.isEnabled();
	}

	/* (non-Javadoc)
	 * @see me.armar.plugins.autorank.hooks.DependencyHandler#setup()
	 */
	@Override
	public boolean setup(final boolean verbose) {
		if (!isInstalled()) {
			if (verbose) {
				plugin.debugMessage(
						ChatColor.RED + Dependency.ROYAL_COMMANDS.getInternalString() + " has not been found!");
			}
			return false;
		} else {
			api = (RoyalCommands) get();

			if (api != null) {
				return true;
			} else {
				if (verbose) {
					plugin.debugMessage(ChatColor.RED + Dependency.ROYAL_COMMANDS.getInternalString()
							+ " has been found but cannot be used!");
				}
				return false;
			}
		}
	}
}
