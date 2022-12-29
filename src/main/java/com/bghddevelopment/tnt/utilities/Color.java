package com.bghddevelopment.tnt.utilities;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

/**
 * Author:  Kim (Thinkverse) Hallberg <work@hallberg.kim>
 * Created: 2020-04-11 19:12
 */
public class Color {

    public static void tell(final CommandSender sender, final String... messages) {
        Arrays.stream(messages).map(Color::translate).forEach(sender::sendMessage);
    }
    public static String translate(final String value) {
        return ChatColor.translateAlternateColorCodes('&', value);
    }

}
