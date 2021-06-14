package es.jsalmoralp.supremacy.features;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import es.jsalmoralp.supremacy.Main;

public class CountdownTeleport {
	int taskID;
	int time;
	private Player player;
	private Location loc;
	
	private Main main;
	public CountdownTeleport(Main main, int time, Player player, Location loc) {
		this.main = main;
		this.time = time;
		this.player = player;
		this.loc = loc;
	}
	
	public void execution() {
		BukkitScheduler sh = Bukkit.getServer().getScheduler();
		taskID = sh.scheduleSyncRepeatingTask(main, new Runnable() {
			public void run() {
				if (time == 0) {
					Bukkit.getScheduler().cancelTask(taskID);
					player.teleport(loc);
					return;
				} else {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "Teletransportado en:"+time));
				}
			}
		}, 0L, 20);
	}
	
}
