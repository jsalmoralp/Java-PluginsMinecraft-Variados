package es.jsalmoralp.trollolo.aggregates;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import es.jsalmoralp.trollolo.Main;
import es.jsalmoralp.trollolo.features.Troll;

public class InventoryGUI implements Listener {
	/*
	 * Constructor
	 */
	
	private Main main;
	public InventoryGUI(Main main) {
		this.main = main;
	}
	
	/*
	 * Clases utilizadas
	 */
	private Troll troll;

	/*
	 * Archivos de configuracion
	 */
	FileConfiguration configMessages = main.getCustomConfigMessages();
	
	/*
	 * Metodos de creacion de Inventarios para la funcionalidad de la GUI del Plugin
	 */
	@SuppressWarnings("deprecation")
	public void createPlayersInventory(Player player) {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', configMessages.getString("ConfigurableMessages.playersInventoryName")));
		int i = 0;
		for (Player playerOnline : Bukkit.getOnlinePlayers()) {
			if (!playerOnline.getName().equals(player.getName())) {
				ItemStack headPlayerOnline = new ItemStack(Material.PLAYER_HEAD,1);
				SkullMeta meta = (SkullMeta) headPlayerOnline.getItemMeta();
				meta.setOwner(playerOnline.getName());
				headPlayerOnline.setItemMeta(meta);
				inv.setItem(i, headPlayerOnline);
				i++;
				if (i == 54) {
					break;
				}
			}
		}
		player.openInventory(inv);
		return;
	}

	public void createTrollsInventory(Player trollPlayer, String trolledPlayer) {
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', configMessages.getString("ConfigurableMessages.trollsInventoryName").replace("%player%", trolledPlayer)));
		ItemStack item = new ItemStack(Material.CREEPER_HEAD,1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', configMessages.getString("ConfigurableMessages.trollsInventory.trollCreeper.itemName")));
		List<String> lore = configMessages.getStringList("ConfigurableMessages.trollsInventory.trollCreeper.itemLore");
		for (int i=0;i<lore.size();i++) {
			lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(0, item);
		
		item = new ItemStack(Material.BEDROCK,1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', configMessages.getString("ConfigurableMessages.trollsInventory.trollVoid.itemName")));
		lore = configMessages.getStringList("ConfigurableMessages.trollsInventory.trollVoid.itemLore");
		for (int i=0;i<lore.size();i++) {
			lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(1, item);
		
		trollPlayer.openInventory(inv);
		
		Troll troll = new Troll(trolledPlayer, trollPlayer.getName());
		if (troll.isPlayerTrolling(trollPlayer.getName())) {
			troll.addTroll(troll);
		}
		return;
	}
	
	/*
	 * Metodos de los eventos para la funcionalidad de la GUI del Plugin
	 */
	@EventHandler
	public void clickingPlayersInventory(InventoryClickEvent event) {
		String titleWithColors = ChatColor.translateAlternateColorCodes('&', configMessages.getString("ConfigurableMessages.playersInventoryName"));
		String title = ChatColor.stripColor(titleWithColors);
		if (!ChatColor.stripColor(event.getInventory().getName()).equals(title)) {
			return;
		} else {
			if (event.getCurrentItem() == null ||
					event.getCurrentItem().getType() == Material.AIR ||
					event.getSlotType() == null) {
				event.setCancelled(true);
				return;
			} else {
				Player player = (Player) event.getWhoClicked();
				event.setCancelled(true);
				if (event.getInventory().equals(player.getOpenInventory().getTopInventory())) {
					SkullMeta meta = (SkullMeta) event.getCurrentItem().getItemMeta();
					@SuppressWarnings("deprecation")
					String nameTrolledPlayer = meta.getOwner();
					createTrollsInventory(player, nameTrolledPlayer);
				}
				return;
			}
		}
	}
	
	@EventHandler
	public void clickingTrollsInventory(InventoryClickEvent event) {
		Player trollPlayer = (Player) event.getWhoClicked();
		if (troll.isPlayerTrolling(trollPlayer.getName())) {
			if (event.getCurrentItem() == null ||
					event.getCurrentItem().getType() == Material.AIR ||
					event.getSlotType() == null) {
				event.setCancelled(true);
				return;
			} else {
				event.setCancelled(true);
				Troll actualTroll= troll.getTroll(trollPlayer.getName());
				Player trolledPlayer = Bukkit.getPlayer(actualTroll.getTrolledPlayer());
				if (event.getSlot() == 0) {
					Location l = trolledPlayer.getLocation();
					double newC = l.getX()+1;
					l.setX(newC);
					trolledPlayer.getWorld().spawnEntity(l, EntityType.CREEPER);
					
					l = trolledPlayer.getLocation();
					newC = l.getX()-1;
					l.setX(newC);
					trolledPlayer.getWorld().spawnEntity(l, EntityType.CREEPER);
					
					l = trolledPlayer.getLocation();
					newC = l.getZ()+1;
					l.setZ(newC);
					trolledPlayer.getWorld().spawnEntity(l, EntityType.CREEPER);
					
					l = trolledPlayer.getLocation();
					newC = l.getZ()-1;
					l.setZ(newC);
					trolledPlayer.getWorld().spawnEntity(l, EntityType.CREEPER);
					
					trollPlayer.closeInventory();
				} else if (event.getSlot() == 1) {
					Location l = trolledPlayer.getLocation().clone();
					l.setY(-1);
					trolledPlayer.teleport(l);
					
					trollPlayer.closeInventory();
				}
			}
		}
	}
	
	@EventHandler
	public void closeInventory(InventoryCloseEvent event) {
		Player trollPlayer = (Player) event.getPlayer();
		if (troll.isPlayerTrolling(trollPlayer.getName())) {
			troll.removeTroll(trollPlayer.getName());
		}
	}
	
	@EventHandler
	public void removeTrollOnTrollerExitToServer(PlayerQuitEvent event) {
		Player trollPlayer = (Player) event.getPlayer();
		if (troll.isPlayerTrolling(trollPlayer.getName())) {
			troll.removeTroll(trollPlayer.getName());
		}
	}
}
