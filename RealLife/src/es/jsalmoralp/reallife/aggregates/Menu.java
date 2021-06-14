package es.jsalmoralp.reallife.aggregates;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu implements Listener {
	
	// Metodo que creara el Inventario Principal para el Jugador
	public void principalMenu(Player player) {
		Inventory inv = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&', "&2Menu"));
		
		ItemStack item = new ItemStack(Material.SHIELD, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lArmeria"));
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7Te teletransporta a la Armeria de la Fortaleza"));
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(20,item);
		
		item = new ItemStack(Material.WRITABLE_BOOK, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lRegistrate en las Olimpiadas"));
		lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7Compite para ver quien tiene mejor ratio de Kills/Deaths"));
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(22,item);
		
		item = new ItemStack(Material.ENDER_CHEST, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lElije tu Profesion"));
		lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7Elije tu Profesion para obtener las ventajas de ella"));
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(24,item);
		
		ItemStack decoration = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE, 1);
		for(int s=0;s<=8;s++) {
			inv.setItem(s, decoration);
		}
		for(int i=45;i<=53;i++) {
			inv.setItem(i, decoration);
		}
		inv.setItem(9,decoration);
		inv.setItem(18,decoration);
		inv.setItem(27,decoration);
		inv.setItem(36,decoration);
		inv.setItem(17,decoration);
		inv.setItem(26,decoration);
		inv.setItem(35,decoration);
		inv.setItem(44,decoration);
		
		player.openInventory(inv);
		return;
	}
	
	// Metodo que creara un Inventario de las Profesiones para el Jugador
	public void profesionsMenu(Player player) {
		/*
		 * Jinete 		--> Caballero 	--> Husar 			(Caballo)
		 * Miliciano 	--> Lancero 	--> Lansquenet		(Lanza)
		 * Escudero 	--> Espadero 	--> Huscarle		(Espada)
		 * Hostigador 	--> Arquero 	--> Arbalestero		(Arco)
		 * Salteador 	--> Vanguardia 	--> Berserker		(Hacha)
		*/
		Inventory inv = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&', "&2Menu de Profesiones"));
		ItemStack item = new ItemStack(Material.SHIELD, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4&lArmeria"));
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7Te teletransporta a la Armeria de la Fortaleza"));
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(20,item);
		
		item = new ItemStack(Material.WRITABLE_BOOK, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lRegistrate en las Olimpiadas"));
		lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7Compite para ver quien tiene mejor ratio de Kills/Deaths"));
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(22,item);
		
		item = new ItemStack(Material.ENDER_CHEST, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lElije tu Profesion"));
		lore = new ArrayList<String>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7Elije tu Profesion para obtener las ventajas de ella"));
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(24,item);
		
		ItemStack decoration = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE, 1);
		for(int s=0;s<=8;s++) {
			inv.setItem(s, decoration);
		}
		for(int i=45;i<=51;i++) {
			inv.setItem(i, decoration);
		}
		inv.setItem(9,decoration);
		inv.setItem(18,decoration);
		inv.setItem(27,decoration);
		inv.setItem(36,decoration);
		inv.setItem(17,decoration);
		inv.setItem(26,decoration);
		inv.setItem(35,decoration);
		inv.setItem(44,decoration);
		
		player.openInventory(inv);
		return;
	}

	// Evento de cuando el Jugador Cliquea en el Inventario
	@EventHandler
	public void clickingInventory(InventoryClickEvent event) {
		String realInvName = ChatColor.translateAlternateColorCodes('&', "&2Menu");
		String invName = ChatColor.stripColor(realInvName);
		if (ChatColor.stripColor(event.getInventory().getName()).equals(invName)) {
			if (event.getCurrentItem() == null || event.getSlotType() == null || event.getCurrentItem().getType() == Material.AIR) {
				event.setCancelled(true);
				return;	
			} else {
				if (event.getCurrentItem().hasItemMeta()) {
					Player player = (Player) event.getWhoClicked();
					event.setCancelled(true);
					if (event.getSlot() == 20) {
						//accion
						return;
					} else if (event.getSlot() == 22) {
						//accion
						return;
					} else if (event.getSlot() == 24) {
						profesionsMenu(player);
						return;
					} else {
						return;
					}
				} else {
					event.setCancelled(true);
					return;
				}
			}
		}
	}
}
