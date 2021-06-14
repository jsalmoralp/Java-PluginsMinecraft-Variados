package es.jsalmoralp.supremacy.aggregates;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

//import es.jsalmoralp.supremacy.Main;

public class FarmEvents implements Listener {
	/*
	 * Constructor
	 */
/*	private Main main;
	public FarmEvents(Main main) {
		this.main = main;
	} */
	
	/*
	 * Eventos de la Mision "El buen Recolector de Cultivos"
	 */
	@EventHandler
	public void plantCrops(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		ItemStack itemCarrot = new ItemStack(Material.CARROT);
		if (event.getItemInHand() == itemCarrot) {
			player.sendMessage("Has plantado una zanahoria!");
		}
		
		
	}
	
	@EventHandler
	public void growCrops(BlockGrowEvent event) {
		
	}
	
	@EventHandler
	public void recolectCrops(EntityPickupItemEvent event) {
		
	}
}
