package es.jsalmoralp.bplug.commands;

import es.jsalmoralp.bplug.utilities.ChatUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MessageCommand extends Command {

	public MessageCommand() {
		super("message", "bungeecord.command.list", "msg", "m");
	}
	
	@Override
	public void execute(CommandSender commandSender, String[] args) {
		if (commandSender instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) commandSender;
			
			if (args.length==0) {
				player.sendMessage(ChatUtil.format("&cInvalid arguments. /msg <player> <msg>"));
				return;
			}
			
			String targetStr = args[0];
			
			if (ProxyServer.getInstance().getPlayer(targetStr)==null) {
				player.sendMessage(ChatUtil.format("&cPlayer " + targetStr + " is offline. /msg <player> <msg>"));
				return;
			}
			
			if (isNullArgument(args, 1)) {
				player.sendMessage(ChatUtil.format("&cInvalid arguments. /msg <player> <msg>"));
				return;
			}
			
			ProxiedPlayer target = ProxyServer.getInstance().getPlayer(targetStr);
			
			if (isNullArgument(args, 1)) {
				target.sendMessage(ChatUtil.format("&cInvalid arguments. /msg <player> <msg>"));
				return;
			}
			
			String message = "";
			for (int x=1;x<args.length;x++) {
				if (message.equals("")) {
					message = args[x];
				} else {
					message = message + " " + args[x];
				}
			}
			
			target.sendMessage(ChatUtil.format("&aMessage from " + player.getName() + " reads: &f" + message));
			player.sendMessage(ChatUtil.format("&aMessage to " + target.getName() + " reads: &f" + message));
			
			/*
			 * 
			 * SocialSpy xyz
			 * 
			 */
		}
	}
	
	private boolean isNullArgument(String[] args, int index) {
		try {
			@SuppressWarnings("unused")
			String temp = args[index];
			return false;
		} catch (IndexOutOfBoundsException ex) {
			return true;
		}
	}
}
