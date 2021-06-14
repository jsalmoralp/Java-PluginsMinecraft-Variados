package jsalmoralp.allinone.eventos;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import jsalmoralp.allinone.Main;

public class EventoConteoSurvival implements Listener {

	// Importando Variables y Funciones de otras Clases
	private Main plugin;
	private FileConfiguration configGlobal;
	private FileConfiguration configConteoSurvival;

	public EventoConteoSurvival(Main plugin) {
		this.plugin = plugin;
		this.configGlobal = plugin.getConfig();
		this.configConteoSurvival = plugin.getConteoSurvivalConfig();
	}

	@EventHandler
	public void matarZombies(EntityDeathEvent evento) {
		if (configGlobal.getString("events.conteo-survival.general").equals("all")
				|| (!configGlobal.getString("events.conteo-survival.general").equals("false")
						&& configGlobal.getString("events.conteo-survival.kills.general").equals("all"))
				|| (!configGlobal.getString("events.conteo-survival.general").equals("false")
						&& !configGlobal.getString("events.conteo-survival.kills.general").equals("false"))
						&& configGlobal.getString("events.conteo-survival.kills.zombies").equals("true")) {
			Player killer = evento.getEntity().getKiller();
			EntityType entidadMuerta = evento.getEntityType();
			if (killer != null && killer.getType().equals(EntityType.PLAYER)
					&& entidadMuerta.equals(EntityType.ZOMBIE)) {
				configConteoSurvival.set("Players." + killer.getUniqueId() + ".name", killer.getName());
				if (configConteoSurvival.contains("Players." + killer.getUniqueId() + ".kills.zombies")) {
					int cantidadZombies = Integer.valueOf(
							configConteoSurvival.getString("Players." + killer.getUniqueId() + ".kills.zombies"));
					configConteoSurvival.set("Players." + killer.getUniqueId() + ".kills.zombies", cantidadZombies + 1);
					plugin.saveConfig();
					return;
				} else {
					configConteoSurvival.set("Players." + killer.getUniqueId() + ".kills.zombies", 1);
					plugin.saveConfig();
					return;
				}
			}
		}
	}
}
