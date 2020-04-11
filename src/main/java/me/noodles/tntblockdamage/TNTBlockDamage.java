package me.noodles.tntblockdamage;

import me.noodles.tntblockdamage.commands.TntCommand;
import me.noodles.tntblockdamage.listeners.BlockListener;
import me.noodles.tntblockdamage.listeners.UpdateJoinEvent;
import me.noodles.tntblockdamage.utilities.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class TNTBlockDamage extends JavaPlugin {

    public void onEnable() {
        final String version = this.getDescription().getVersion();

        this.getLogger().info(String.format("TNTBlockDamage v%s starting ...", version));

		this.saveDefaultConfig();
        this.reloadConfig();

        this.getLogger().info(String.format("TNTBlockDamage v%s loading commands ...", version));

        this.registerCommand("tnt", new TntCommand());

        this.getLogger().info(String.format("TNTBlockDamage v%s loading events ...", version));

        this.registerEvents(this, new UpdateJoinEvent(this), new BlockListener());

        this.getLogger().info(String.format("TNTBlockDamage v%s started ...", version));

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

}
