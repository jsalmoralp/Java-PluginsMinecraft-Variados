package es.jsalmoralp.bplug.utilities;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatUtil {
	
	public static TextComponent format(String input) {
		return new TextComponent(ChatColor.translateAlternateColorCodes('&', input));
	}
	
}
