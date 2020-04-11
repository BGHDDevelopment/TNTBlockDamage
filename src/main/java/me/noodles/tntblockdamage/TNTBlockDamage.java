package me.noodles.tntblockdamage;

import me.noodles.tntblockdamage.commands.TntCommand;
import me.noodles.tntblockdamage.listeners.BlockListener;
import me.noodles.tntblockdamage.listeners.UpdateJoinEvent;
import me.noodles.tntblockdamage.utilities.UpdateChecker;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;
import org.bukkit.*;

public class TNTBlockDamage extends JavaPlugin {
	
	private UpdateChecker checker;
	public static TNTBlockDamage plugin;

	
    public void onEnable() {
    	TNTBlockDamage.plugin = this;
    	final PluginDescriptionFile VarUtilType = this.getDescription();
		this.getLogger().info("TNTBlockDamage V" + VarUtilType.getVersion() + " starting...");
		this.saveDefaultConfig();
        this.reloadConfig();
        this.registerCommand("tnt", new TntCommand());
        this.registerEvents(this, new UpdateJoinEvent(this), new BlockListener());
        this.setEnabled(true);
		this.getLogger().info("TNTBlockDamage V" + VarUtilType.getVersion() + " started!");

        if (getConfig().getBoolean("CheckForUpdates.Enabled", true)) {
            new UpdateChecker(this, 20392).getLatestVersion(remoteVersion -> {
                getLogger().info("Checking for Updates ...");

                if (getDescription().getVersion().equalsIgnoreCase(remoteVersion)) {
                    getLogger().info("No new version available");
                } else {
                    getLogger().warning(String.format("Newest version: %s is out! You are running version: %s", remoteVersion, getDescription().getVersion()));
                    getLogger().warning("Please Update Here: http://www.spigotmc.org/resources/20392");
                }
            });
        }
    }

    private void registerCommand(final String command, final CommandExecutor executor) {
        this.getCommand(command).setExecutor(executor);
    }

    private void registerEvents(final JavaPlugin plugin, final Listener... listeners) {
        for (final Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes"})
   	public static TNTBlockDamage getPlugin() {
           return (TNTBlockDamage)getPlugin((Class) TNTBlockDamage.class);
       }

}
