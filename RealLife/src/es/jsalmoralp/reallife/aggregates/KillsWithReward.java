package es.jsalmoralp.reallife.aggregates;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import es.jsalmoralp.reallife.Main;

public class KillsWithReward implements Listener {
	// Constructor
	@SuppressWarnings("unused")
	private Main main;
	public KillsWithReward(Main main) {
		this.main = main;
	}
	
	// Evento que dara Gemas (que seran tradeables) por matar a otros Jugadores
	@EventHandler
	public void PlayerGetGemsForKillOtherPlayer(EntityDeathEvent event) {
		Player killer = event.getEntity().getKiller();
		EntityType entity = event.getEntityType();
		if ((killer != null) && killer.getType().equals(EntityType.PLAYER) && entity.equals(EntityType.PLAYER)) {
			ItemStack item = new ItemStack(Material.EMERALD, 1);
			ItemMeta meta = item.getItemMeta();
			List<String> lore = new ArrayList<String>();
			lore.add(ChatColor.translateAlternateColorCodes('&', "&4&m                                                    "));
			lore.add(ChatColor.translateAlternateColorCodes('&', ""));
			lore.add(ChatColor.translateAlternateColorCodes('&', "&7Se te ha otorgado esta gema por tu gran fuerza."));
			lore.add(ChatColor.translateAlternateColorCodes('&', "&7Puedes utilizarla para comprar items."));
			lore.add(ChatColor.translateAlternateColorCodes('&', "&c&lPrecio: &2$5000"));
			lore.add(ChatColor.translateAlternateColorCodes('&', ""));
			lore.add(ChatColor.translateAlternateColorCodes('&', "&4&m                                                    "));
			meta.setLore(lore);
			meta.addEnchant(Enchantment.LOYALTY, 10, true);
			item.setItemMeta(meta);
			if (killer.getInventory().firstEmpty() == -1) {
				killer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cTu inventario esta lleno asi que no has podido recibir la gema por matar a un jugador"));	
			} else {
				killer.getInventory().addItem(item);
			}
			
			return;
		}
	}
	
}