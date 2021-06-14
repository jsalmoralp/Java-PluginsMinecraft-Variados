package es.jsalmoralp.supremacy.automobsspawning;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.scheduler.BukkitScheduler;

import es.jsalmoralp.supremacy.Main;

public class AutoSpawningRegenerate {
	int taskID;
	long ticks;
	
	private Main main;
	public AutoSpawningRegenerate(Main main, long ticks) {
		this.main = main;
		this.ticks = ticks;
	}
	
	public void executionWithStop() {
		BukkitScheduler sh = Bukkit.getServer().getScheduler();
		taskID = sh.scheduleSyncRepeatingTask(main, new Runnable() {
			public void run() {
				Bukkit.getScheduler().cancelTask(taskID);
				ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
				Bukkit.dispatchCommand(console, "supremacy spawn removeAllMobs");	
				Bukkit.dispatchCommand(console, "supremacy spawn allMobs");
			}
		}, 0L, ticks);
	}
	
	public void execution() {
		BukkitScheduler sh = Bukkit.getServer().getScheduler();
		taskID = sh.scheduleSyncRepeatingTask(main, new Runnable() {
			public void run() {
				ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
				Bukkit.dispatchCommand(console, "supremacy spawn removeAllMobs");	
				Bukkit.dispatchCommand(console, "supremacy spawn allMobs");
			}
		}, 0L, ticks);
	}
}
