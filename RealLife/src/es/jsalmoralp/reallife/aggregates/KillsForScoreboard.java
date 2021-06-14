package es.jsalmoralp.reallife.aggregates;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import es.jsalmoralp.reallife.Main;

public class KillsForScoreboard implements Listener {
	// Constructor
	private Main main;
	public KillsForScoreboard(Main main) {
		this.main = main;
	}
	
	// Evento que hara un Conteo de los Mobs Asesinados
	@EventHandler
	public void PlayerKillEnderman(EntityDeathEvent event) {
		FileConfiguration configRealLifePlayersKills = main.getCustomConfigRealLifePlayersKills();
		Player killer = event.getEntity().getKiller();
		EntityType entity = event.getEntityType();
		if ((killer != null) && killer.getType().equals(EntityType.PLAYER) && entity.equals(EntityType.ENDERMAN)) {
			configRealLifePlayersKills.set("Players."+killer.getUniqueId()+".name", killer.getName());
			if (configRealLifePlayersKills.contains("Players."+killer.getUniqueId()+".kills.endermanKills")) {
				int amountKilled = Integer.valueOf(configRealLifePlayersKills.getString("Players."+killer.getUniqueId()+".kills.endermanKills"));
				configRealLifePlayersKills.set("Players."+killer.getUniqueId()+".kills.endermanKills", amountKilled+1);
				main.saveCustomConfigRealLifePlayersKills();
				return;
			} else {
				configRealLifePlayersKills.set("Players."+killer.getUniqueId()+".kills.endermanKills", 1);
				main.saveCustomConfigRealLifePlayersKills();
				return;
			}
			
		}
	}
	
	// Evento 
	
}