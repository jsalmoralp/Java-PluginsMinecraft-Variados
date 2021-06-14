package es.jsalmoralp.bplug;

import es.jsalmoralp.bplug.commands.MessageCommand;
import es.jsalmoralp.bplug.listeners.ServerChange;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {
	
	public void onEnable() {
		getProxy().getPluginManager().registerCommand(this, new MessageCommand());
		getProxy().getPluginManager().registerListener(this, new ServerChange());
	}
	
	public void onDisable() {
		
	}
}
