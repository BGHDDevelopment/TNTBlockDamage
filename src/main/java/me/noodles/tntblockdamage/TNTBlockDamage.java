package me.noodles.tntblockdamage;

import me.noodles.tntblockdamage.listeners.BlockListener;
import me.noodles.tntblockdamage.listeners.UpdateJoinEvent;
import me.noodles.tntblockdamage.utilities.UpdateChecker;
import org.bukkit.plugin.java.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;
import org.bukkit.command.*;
import org.bukkit.*;
import org.bukkit.entity.*;

public class TNTBlockDamage extends JavaPlugin {
	
	private UpdateChecker checker;
	public static TNTBlockDamage plugin;

	
    public void onEnable() {
    	TNTBlockDamage.plugin = this;
    	final PluginDescriptionFile VarUtilType = this.getDescription();
		this.getLogger().info("TNTBlockDamage V" + VarUtilType.getVersion() + " starting...");
		this.saveDefaultConfig();
        this.reloadConfig();
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

    private void registerEvents(final JavaPlugin plugin, final Listener... listeners) {
        for (final Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes"})
   	public static TNTBlockDamage getPlugin() {
           return (TNTBlockDamage)getPlugin((Class) TNTBlockDamage.class);
       }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String CommandLabel, final String[] args) {
    	if (!(sender instanceof Player)){
            Bukkit.getServer().getLogger().info("Only players can do this!");
            return true;
    		}
    	
    	final Player p = (Player)sender;
        CommandLabel.equalsIgnoreCase("tnt");
        if (!sender.isOp()) {
            sender.hasPermission("tntblockdamage.firetnt");
        }
        p.sendMessage(String.valueOf(ChatColor.RED.toString()) + ChatColor.BOLD + "ALERT:" + ChatColor.WHITE.toString() + ChatColor.BOLD + " FIRING TNT");
        @SuppressWarnings({ "rawtypes", "unchecked" })
		final TNTPrimed tnt = (TNTPrimed)p.getWorld().spawn(p.getEyeLocation(), (Class)TNTPrimed.class);
        tnt.setVelocity(p.getLocation().getDirection().multiply(2));
        return true;
    }
}
