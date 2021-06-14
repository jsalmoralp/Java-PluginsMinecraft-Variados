package es.jsalmoralp.supremacy.features;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import es.jsalmoralp.supremacy.Main;
import es.jsalmoralp.supremacy.aggregates.Menu;

public class CountdownMenu {
	int taskID;
	int time;
	private Player player;
	
	private Main main;
	public CountdownMenu(Main main, int time, Player player) {
		this.main = main;
		this.time = time;
		this.player = player;
	}
	
	public void execution() {
		BukkitScheduler sh = Bukkit.getServer().getScheduler();
		taskID = sh.scheduleSyncRepeatingTask(main, new Runnable() {
			public void run() {
				if (time == 0) {
					Bukkit.getScheduler().cancelTask(taskID);;
					Menu inv = new Menu();
					inv.principalMenu(player);
					main.removePlayerInCountdownOpenMenu(player);
					return;
				} else {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "Tiempo para reabrir el Inventario: "+time));
					time--;
				}
			}
		}, 0L, 20);
	}
	
}
