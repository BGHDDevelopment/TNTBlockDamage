package me.noodles.tntblockdamage.listeners;

import me.noodles.tntblockdamage.TNTBlockDamage;
import me.noodles.tntblockdamage.utilities.UpdateChecker;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateJoinEvent implements Listener {
    private final TNTBlockDamage plugin;

    public UpdateJoinEvent(TNTBlockDamage plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (getPlugin().getConfig().getBoolean("Update.Enabled", true)) {
            if (player.hasPermission("tntblockdamage.update")) {
                if (getPlugin().getConfig().getBoolean("CheckForUpdates.Enabled", true)) {
                    new UpdateChecker(getPlugin(), 20392).getLatestVersion(version -> {
                        if (!getPlugin().getDescription().getVersion().equalsIgnoreCase(version)) {
                            player.sendMessage(ChatColor.GRAY + "=========================");
                            player.sendMessage(ChatColor.RED + "TNTBlockDamage is outdated!");
                            player.sendMessage(ChatColor.GREEN + "Newest version: " + version);
                            player.sendMessage(ChatColor.RED + "Your version: " + getPlugin().getDescription().getVersion());
                            player.sendMessage(ChatColor.GRAY + "=========================");
                        }
                    });
                }
            }
        }
    }

    private TNTBlockDamage getPlugin() {
        return plugin;
    }
}
    