package es.jsalmoralp.trollolo.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import es.jsalmoralp.trollolo.Main;
import es.jsalmoralp.trollolo.aggregates.InventoryGUI;

public class CommandTroll implements CommandExecutor {
	// Constructor
	private Main main;
	public CommandTroll(Main main) {
		this.main = main;
	}
	
	// Archivos de Configuracion
	FileConfiguration config = main.getCustomConfig();
	
	// Variables para el Chat
	String cnameForChat = ChatColor.AQUA + "[ TROLL ]  ";
	String cForChat = ChatColor.GREEN + "/troll";
	String cdescForChat = ChatColor.WHITE + "Es el Comando del Plugin Trollolo.";
	String separator = ChatColor.RED + "<------------------------------>";
	String noExist = ChatColor.RED + "Este comando no existe!";
	String correct = ChatColor.GREEN + "Correcto!";
	String use = ChatColor.WHITE + "Usa";

	// Variables para "/troll help"
	String cdesConsole_help_ForChat = ChatColor.WHITE + "--> Ver las posibilidades del comando por Consola.";
	String cdesPlayer_help_ForChat = ChatColor.WHITE + "--> Ver las posibilidades del comando para el Player.";

	// Variables para "/troll"
	String cdesc___ForChat = ChatColor.WHITE + "--> Comando que interactua con las opciones del Plugin Supremacy.";

	// Variables para "/troll reload"
	String cdesc_reload_ForChat = ChatColor.WHITE + "--> Recarga la Config de los Spawns del Servidor.";
	String cresStart_reload_ForChat = ChatColor.YELLOW + "Se inicia la recarga de la Config...";
	String cresConfig_reload_ForChat = ChatColor.WHITE + "Recarga de la Config...";
	

	public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
		/////////////////////////////////////////////////////////////////////////////////
		////////////////////// Comandos que ejecutara la Consola ////////////////////////
		///////////////////////////////////////////////////////////////////////////////// 
		if (!(sender instanceof Player)) {
			return false;
		} else {
			/////////////////////////////////////////////////////////////////////////////////
			////////////////////// Comandos que ejecutara el Usuario/Player /////////////////
			///////////////////////////////////////////////////////////////////////////////// 
			Player player = (Player) sender;
			if (player.isOp() || player.hasPermission("trollolo.use")) {
				if (args.length <= 0) {
					// "/troll"
					player.sendMessage(cnameForChat + cdescForChat);
					player.sendMessage(cnameForChat + use + " " + cForChat + " help <page> " + cdesPlayer_help_ForChat);
					return true;
				} else if (args.length > 0) {
					if ((args[0].equalsIgnoreCase("help") && (args.length == 1))
							|| (args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("1") && args.length == 2)) {
						// "/troll help" "/troll help 1"
						player.sendMessage(cnameForChat + ChatColor.WHITE + "pagina 1 de 1");
						player.sendMessage(use + " " + cForChat + cdesc___ForChat);
						return true;
					} else if (args[0].equalsIgnoreCase("help") && (args.length >= 2)) {
						// "/troll help asdasd asd"
						player.sendMessage(cnameForChat + ChatColor.WHITE + "El comando " + cForChat + " help"
								+ ChatColor.WHITE + "solo tiene 1 pagina.");
						player.sendMessage(
								use + " " + cForChat + " help <page> " + ChatColor.WHITE + "--> Page solo puede ser [1].");
						return true;
					} else if (args[0].equalsIgnoreCase("reload") && (args.length == 1)) {
						// "/troll reload"
						player.sendMessage(separator);
						player.sendMessage(cnameForChat + cresStart_reload_ForChat);
						main.reloadCustomConfig();
						player.sendMessage(cnameForChat + ChatColor.DARK_GREEN + "config.yml");
						main.reloadCustomConfigMessages();
						player.sendMessage(cnameForChat + ChatColor.DARK_GREEN + "/messages/"
								+ config.getString("Config.lang") + ".yml");
						player.sendMessage(cnameForChat + cresConfig_reload_ForChat + "   " + correct);
						player.sendMessage(separator);
						return true;
					} else if (args[0].equalsIgnoreCase("troll") && (args.length == 1)) {
						InventoryGUI inv = new InventoryGUI(main);
						inv.createPlayersInventory(player);
						return true;
					} else {
						// "/troll asdasd asd"
						player.sendMessage(cnameForChat + noExist);
						player.sendMessage(cnameForChat + use + cForChat + " help <page> " + cdesPlayer_help_ForChat);
						return true;
					}
				}
			} else {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo tienes Permisos para ejecutar este comando!"));
			}
		}
		return false;
	}
}
