package me.noodles.tntblockdamage;

import org.bukkit.plugin.java.*;
import org.bukkit.plugin.*;
import org.bukkit.event.entity.*;
import org.bukkit.block.*;
import org.bukkit.util.Vector;
import org.bukkit.event.*;
import org.bukkit.command.*;
import org.bukkit.*;
import org.bukkit.entity.*;

public class TNTBlockDamage extends JavaPlugin implements Listener
{
	
	private UpdateChecker checker;
	public static TNTBlockDamage plugin;

	
    public void onEnable() {
    	TNTBlockDamage.plugin = this;
    	final PluginDescriptionFile VarUtilType = this.getDescription();
		this.getLogger().info("TNTBlockDamage V" + VarUtilType.getVersion() + " starting...");
		this.saveDefaultConfig();
        this.reloadConfig();
        this.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this);
        this.setEnabled(true);
		this.getLogger().info("TNTBlockDamage V" + VarUtilType.getVersion() + " started!");
		this.getLogger().info("TNTBlockDamage V" + VarUtilType.getVersion() + " checking for updates...");
        this.checker = new UpdateChecker(this);
        if (this.checker.isConnected()) {
            if (this.checker.hasUpdate()) {
            	getServer().getConsoleSender().sendMessage("------------------------");
            	getServer().getConsoleSender().sendMessage("TNTBlockDamage is outdated!");
            	getServer().getConsoleSender().sendMessage("Newest version: " + this.checker.getLatestVersion());
            	getServer().getConsoleSender().sendMessage("Your version: " + TNTBlockDamage.plugin.getDescription().getVersion());
            	getServer().getConsoleSender().sendMessage("Please Update Here: https://www.spigotmc.org/resources/20392");
                getServer().getConsoleSender().sendMessage("------------------------");
            }
            else {
            	getServer().getConsoleSender().sendMessage("------------------------");
            	getServer().getConsoleSender().sendMessage("TNTBlockDamage is up to date!");
            	getServer().getConsoleSender().sendMessage("------------------------");
            }
        }
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes"})
   	public static TNTBlockDamage getPlugin() {
           return (TNTBlockDamage)getPlugin((Class) TNTBlockDamage.class);
       }
       

    @EventHandler
    public void onEntityExplode(final EntityExplodeEvent event) {
        for (final Block b : event.blockList()) {
            if (b.getType() != Material.TNT) {
                final float x = -2.0f + (float)(Math.random() * 5.0);
                final float y = -3.0f + (float)(Math.random() * 7.0);
                final float z = -2.5f + (float)(Math.random() * 6.0);
				final FallingBlock fallingBlock = b.getWorld().spawnFallingBlock(b.getLocation(), b.getType(), b.getData());
                fallingBlock.setDropItem(false);
                fallingBlock.setVelocity(new Vector(x, y, z));
                b.setType(Material.AIR);
            }
        }
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
