package jsalmoralp.allinone.eventos;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import jsalmoralp.allinone.Main;

public class EventoPlayerJoin implements Listener {

	// Importando Variables y Funciones de otras Clases
	private Main plugin;
	private FileConfiguration configGlobal;
	private FileConfiguration configSpawns;
	private FileConfiguration configMessages;

	public EventoPlayerJoin(Main plugin) {
		this.plugin = plugin;
		this.configGlobal = plugin.getConfig();
		this.configSpawns = plugin.getConfig();
		this.configMessages = plugin.getConfig();
	}

	@EventHandler
	public void teleportAlEntrar(PlayerJoinEvent evento) {
		if (configGlobal.getString("events.playerJoin.general").equals("all")
				|| !configGlobal.getString("events.playerJoin.general").equals("false")
						&& configGlobal.getString("events.playerJoin.teleport").equals("true")) {
			Player jugador = evento.getPlayer();
			if (configSpawns.contains("server.playerJoin.world")) {
				World world = plugin.getServer().getWorld(configSpawns.getString("server.playerJoin.world"));
				double x = Double.valueOf(configSpawns.getString("server.playerJoin.x"));
				double y = Double.valueOf(configSpawns.getString("server.playerJoin.y"));
				double z = Double.valueOf(configSpawns.getString("server.playerJoin.z"));
				float yaw = Float.valueOf(configSpawns.getString("server.playerJoin.yaw"));
				float pitch = Float.valueOf(configSpawns.getString("server.playerJoin.pitch"));
				Location loc = new Location(world, x, y, z, yaw, pitch);
				jugador.teleport(loc);
			}
		}
		return;
	}

	@EventHandler
	public void mensajeBienvenidaAlEntrar(PlayerJoinEvent evento) {
		if (configGlobal.getString("events.playerJoin.general").equals("all")
				|| !configGlobal.getString("events.playerJoin.general").equals("false")
						&& configGlobal.getString("events.playerJoin.message").equals("true")) {
			Player jugador = evento.getPlayer();
			String messagePath = "events.playerJoin.message";
			if (configGlobal.getString(messagePath).equals("true")) {
				List<String> mensajes = configMessages.getStringList(messagePath);
				for (int i = 0; i < mensajes.size(); i++) {
					String texto = mensajes.get(i);
					jugador.sendMessage(ChatColor.translateAlternateColorCodes('&',
							texto.replaceAll("%player%", jugador.getName())));
				}
			}
		}

		return;
	}
}
