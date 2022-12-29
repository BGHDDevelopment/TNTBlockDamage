package com.bghddevelopment.tnt.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.bghddevelopment.tnt.TNTBlockDamage;
import com.bghddevelopment.tnt.utilities.Color;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;

@CommandAlias("tnt")
@Description("TNT Fire.")
@CommandPermission("tntblockdamage.use")
@Conditions("noconsole")
public class NewTNTCommand extends BaseCommand {

    @Dependency
    private TNTBlockDamage plugin;

    @Default
    public void onDefault(CommandSender sender) {
        Player player = (Player) sender;
        Color.tell(player, "&c&lALERT! &f&lFIRING TNT");
        TNTPrimed tnt = player.getWorld().spawn(player.getEyeLocation(), TNTPrimed.class);
        tnt.setVelocity(player.getLocation().getDirection().multiply(2));
    }
}
