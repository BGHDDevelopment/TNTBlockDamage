package me.noodles.tntblockdamage.utilities;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

/**
 * Author:  Kim (Thinkverse) Hallberg <work@hallberg.kim>
 * Created: 2020-04-11 19:12
 */
public class Common {

    public static void tell(final CommandSender sender, final String... messages) {
        Arrays.stream(messages).map(Common::translate).forEach(sender::sendMessage);
    }

    private static String translate(final String value) {
        return ChatColor.translateAlternateColorCodes('&', value);
    }

}
