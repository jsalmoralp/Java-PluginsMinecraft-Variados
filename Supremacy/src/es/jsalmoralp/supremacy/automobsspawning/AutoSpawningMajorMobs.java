package es.jsalmoralp.supremacy.automobsspawning;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import es.jsalmoralp.supremacy.Main;

public class AutoSpawningMajorMobs {
	int taskID;
	long ticks;
	Location loc;
	
	private Main main;
	public AutoSpawningMajorMobs(Main main, long ticks, Location loc) {
		this.main = main;
		this.ticks = ticks;
		this.loc = loc;
	}
	BukkitScheduler sh = Bukkit.getServer().getScheduler();
	public void executionWithStop() {
		taskID = sh.scheduleSyncRepeatingTask(main, new Runnable() {
			public void run() {
				Bukkit.getScheduler().cancelTask(taskID);
				majorMobs();
			}
		}, 100L, ticks);
	}
	
	public void majorMobs() {
			LivingEntity entidadZombie = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			Zombie zombie = (Zombie) entidadZombie;
			zombie.setCustomName(ChatColor.translateAlternateColorCodes('&', "&cSoldado"));
			zombie.setCustomNameVisible(true);
			zombie.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET, 1));
			zombie.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
			zombie.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS, 1));
			zombie.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS, 1));
			zombie.getEquipment().setItemInMainHand(new ItemStack(Material.WOODEN_SWORD, 1));
			
			LivingEntity entidadSkeleton = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.SKELETON);
			Skeleton skeleton = (Skeleton) entidadSkeleton;
			skeleton.setCustomName(ChatColor.translateAlternateColorCodes('&', "&cSoldado"));
			skeleton.setCustomNameVisible(true);
			skeleton.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET, 1));
			skeleton.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
			skeleton.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS, 1));
			skeleton.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS, 1));
			skeleton.getEquipment().setItemInMainHand(new ItemStack(Material.WOODEN_SWORD, 1));
	}
}
