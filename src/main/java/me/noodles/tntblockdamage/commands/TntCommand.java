package me.noodles.tntblockdamage.commands;

import me.noodles.tntblockdamage.utilities.Common;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;

import java.util.Collections;
import java.util.List;

public final class TntCommand implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;

            if (player.hasPermission("tntblockdamage.use")) {
                Common.tell(player, "&c&lALERT! &f&lFIRING TNT");

                final TNTPrimed tnt = player.getWorld().spawn(player.getEyeLocation(), TNTPrimed.class);

                tnt.setVelocity(player.getLocation().getDirection().multiply(2));
            } else {
                Common.tell(player, "&cYou don't have permission to use this command!");
            }

            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }

}
