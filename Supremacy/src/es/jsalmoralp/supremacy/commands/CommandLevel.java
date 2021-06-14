package es.jsalmoralp.supremacy.commands;

import java.sql.Connection;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import es.jsalmoralp.supremacy.Main;
import es.jsalmoralp.supremacy.features.SqlPlayerDataOfLevels;
import es.jsalmoralp.supremacy.utils.MySqlConnection;
import net.md_5.bungee.api.ChatColor;

public class CommandLevel implements CommandExecutor {
	// Constructor
	private Main main;
	public CommandLevel(Main main) {
		this.main = main;
	}
	
	public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
		FileConfiguration config = main.getCustomConfig();
		Connection connection;
		if (config.getString("Config.database-connection").equalsIgnoreCase("mysql")) {
			connection = MySqlConnection.getConnection();
		} else {
			connection = null;
		}
		
		if (!(sender instanceof Player)) {
			if (args.length == 3) {
				if (args[0].equalsIgnoreCase("give")) {
					Player player = Bukkit.getPlayerExact(args[1]);
					if (player == null) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aEl jugador debe estar conectado para darle niveles."));
					} else {
						try {
							int amount = Integer.valueOf(args[2]);
							if (amount > 0) {
								int actualLevel = SqlPlayerDataOfLevels.getLevel(connection, player.getUniqueId());
								SqlPlayerDataOfLevels.setLevel(connection, player.getUniqueId(), actualLevel+amount);
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aLe acabas de dar &7"+amount+" niveles $aa "+player.getName()));
							} else {
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aDebes ingresar un numero valido."));
							}
						} catch (NumberFormatException e) {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aDebes ingresar un numero valido."));
						}
					}
				} else if (args[0].equalsIgnoreCase("give")) {
					Player player = Bukkit.getPlayerExact(args[1]);
					if (player == null) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aEl jugador debe estar conectado para darle niveles."));
					} else {
						try {
							int amount = Integer.valueOf(args[2]);
							if (amount > 0) {
								int actualLevel = SqlPlayerDataOfLevels.getLevel(connection, player.getUniqueId());
								if (amount >= actualLevel) {
									SqlPlayerDataOfLevels.setLevel(connection, player.getUniqueId(), 0);
								} else {
									SqlPlayerDataOfLevels.setLevel(connection, player.getUniqueId(), actualLevel-amount);
								}
								
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aLe acabas de quitar &7"+amount+" niveles $aa "+player.getName()));
							} else {
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aDebes ingresar un numero valido."));
							}
						} catch (NumberFormatException e) {
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aDebes ingresar un numero valido."));
						}
					}
				} else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aEjecuta /level give <jugador> <cantidad> para darle nivel al jugador."));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aEjecuta /level take <jugador> <cantidad> para quitarle nivel al jugador."));
				}
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aEjecuta /level give <jugador> <cantidad> para darle nivel al jugador."));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aEjecuta /level take <jugador> <cantidad> para quitarle nivel al jugador."));
			}
			return true;
		} else {
			Player player = (Player) sender;
			if (args.length <= 0) {
				// "/level"
				int level = SqlPlayerDataOfLevels.getLevel(connection, player.getUniqueId());
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aActualmente tienes: &7"+level+" niveles."));
			} else {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aEjecuta /level para ver tu nivel actual."));
			}
			return true;
		}
	}
}
