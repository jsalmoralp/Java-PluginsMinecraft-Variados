package es.jsalmoralp.bplug.listeners;

import es.jsalmoralp.bplug.utilities.ChatUtil;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerChange implements Listener {

	@EventHandler
	public void onServerChange(ServerConnectEvent e) {
		ProxiedPlayer player = e.getPlayer();
		
		for (ProxiedPlayer everyone : ProxyServer.getInstance().getPlayers()) {
			everyone.sendMessage(ChatUtil.format("&a"+player.getName()+" &aconnected &ato &a"+e.getTarget().getName()+" &afrom &a"+player.getServer().getInfo().getName()+"&a!"));
		}
	}
}
