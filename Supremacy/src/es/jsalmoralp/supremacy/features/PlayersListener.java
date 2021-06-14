package es.jsalmoralp.supremacy.features;

import java.sql.Connection;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import es.jsalmoralp.supremacy.Main;
import es.jsalmoralp.supremacy.utils.MySqlConnection;

public class PlayersListener implements Listener {
	// Constructor
	private Main main;
	public PlayersListener(Main main) {
		this.main = main;
	}
	
    FileConfiguration config = main.getCustomConfig();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Connection connection;
		if (config.getString("Config.database-connection").equalsIgnoreCase("mysql")) {
			connection = MySqlConnection.getConnection();
		} else {
			connection = null;
		}
		if (SqlPlayerDataOfLevels.playerExists(connection, player.getUniqueId())) {
			SqlPlayerDataOfLevels.createPlayer(connection, player.getUniqueId(), player.getName());
		}
	}
}
