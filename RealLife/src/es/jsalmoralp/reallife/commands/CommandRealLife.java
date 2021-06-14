package es.jsalmoralp.reallife.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import es.jsalmoralp.reallife.Main;
import es.jsalmoralp.reallife.features.CooldownDailyReward;
import es.jsalmoralp.reallife.aggregates.Menu;

public class CommandRealLife implements CommandExecutor {
	// Constructor
	private Main main;
	public CommandRealLife(Main main) {
		this.main = main;
	}
	
	// Archivos de Configuracion
	FileConfiguration configRealLifePlayersKills = main.getCustomConfigRealLifePlayersKills();
	FileConfiguration configRealLifeConfig = main.getCustomConfigRealLifeConfig();
	
	// Variables para el Chat
	String cnameForChat = ChatColor.AQUA+"[ SURVIVAL ]  ";
	String cForChat = ChatColor.GREEN + "/realLife";
	String cdescForChat = ChatColor.WHITE + "Es el Comando del Modulo RealLife.";
	String separator = ChatColor.RED + "<------------------------------>";
	String noExist = ChatColor.RED + "Este comando no existe!";
	String correct = ChatColor.GREEN + "Correcto!";
	String use = ChatColor.WHITE + "Usa";
	
	// Variables para "/realLife help"
	String cdesConsole_help_ForChat = ChatColor.WHITE + "--> Ver las posibilidades del comando por Consola.";
	String cdesPlayer_help_ForChat = ChatColor.WHITE + "--> Ver las posibilidades del comando para el Player.";
	
	// Variables para "/realLife"
	String cdesc___ForChat = ChatColor.WHITE + "--> Comando que interactua con las opciones del Modulo RealLife.";
	
	// Variables para "/realLife reload"
	String cdesc_reload_ForChat = ChatColor.WHITE + "--> Recarga la Config de los Spawns del Servidor.";
	String cresStart_reload_ForChat = ChatColor.YELLOW + "Se inicia la recarga de la Config...";
	String cresConfig_reload_ForChat = ChatColor.WHITE + "Recarga de la Config...";
	
	// Variables para "/realLife showkills" "/realLife showkills <player>"
	String cdesc_showkills_ForChat = ChatColor.WHITE + "--> Muestra las kills del Jugador.";
	
	
	public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
		/////////////////////////////////////////////////////////////////////////////////
		////////////////////// Comandos que ejecutara la Consola ////////////////////////
		/////////////////////////////////////////////////////////////////////////////////
		if (!(sender instanceof Player)) {
			String namePlayer = "";
			Player player = null;
			if (args.length <= 0) {
				// "/realLife"
				Bukkit.getConsoleSender().sendMessage(cnameForChat + cdescForChat);
				Bukkit.getConsoleSender().sendMessage(cnameForChat + use + " " + cForChat + " help <page> " +
						cdesConsole_help_ForChat);
				return false;
			} else if (args.length > 0) {
				if ((args[0].equalsIgnoreCase("help") && (args.length == 1)) || 
						(args[0].equalsIgnoreCase("help") && 
								args[1].equalsIgnoreCase("1") &&
								(args.length == 2))) {
					// "/realLife help" "/realLife help 1"
					Bukkit.getConsoleSender().sendMessage(cnameForChat + ChatColor.WHITE + "pagina 1 de 1");
					Bukkit.getConsoleSender().sendMessage(use + " " + cForChat + " reload " + cdesc_reload_ForChat);
					Bukkit.getConsoleSender().sendMessage(use + " " + cForChat + " showkills <player> " + cdesc_showkills_ForChat);
					return false;
				} else if (args[0].equalsIgnoreCase("help") && (args.length >= 2)) {
					// "/realLife help asdasd asd"
					Bukkit.getConsoleSender().sendMessage(cnameForChat + ChatColor.WHITE + "El comando " + 
							cForChat + " help" + ChatColor.WHITE + "solo tiene 1 pagina.");
					Bukkit.getConsoleSender().sendMessage(use + " " + cForChat + " help <page> " + ChatColor.WHITE +
						"--> Page solo puede ser [1].");
					return false;
				} else if (args[0].equalsIgnoreCase("reload") && (args.length == 1)) {
					// "/realLife reload"
					Bukkit.getConsoleSender().sendMessage(separator);
					Bukkit.getConsoleSender().sendMessage(cnameForChat + cresStart_reload_ForChat);
					main.reloadCustomConfigRealLifePlayersKills();
					Bukkit.getConsoleSender().sendMessage(cnameForChat + ChatColor.DARK_GREEN + "/realLife/players/kills.yml");
					Bukkit.getConsoleSender().sendMessage(cnameForChat + cresConfig_reload_ForChat+"   " + correct);
					Bukkit.getConsoleSender().sendMessage(separator);
					return false;
				} else if (args[0].equalsIgnoreCase("showkills") && (args.length == 2)) {
					// "/realLife showkills"
					namePlayer = args[1];
					player = Bukkit.getPlayer(namePlayer);
					if (configRealLifePlayersKills.contains("Players."+player.getUniqueId()+".kills")) {
						Bukkit.getConsoleSender().sendMessage(separator);
						Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA+"Has matado como Jugador:");
						Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE+"Endermans: " + ChatColor.YELLOW + configRealLifePlayersKills.getString("Players."+player.getUniqueId()+".kills.endermanKills"));
						Bukkit.getConsoleSender().sendMessage(separator);
						return true;
					} else {
						player.sendMessage(separator);
						player.sendMessage(ChatColor.AQUA+"No has matado nada como Jugador!!");
						player.sendMessage(separator);
						return true;
					}
				} else {
					// "/realLife asdasd asd"
					Bukkit.getConsoleSender().sendMessage(cnameForChat + noExist);
					Bukkit.getConsoleSender().sendMessage(cnameForChat + use + cForChat +" help <page> " + cdesConsole_help_ForChat);
					return false;
				}
			}
		} else {
			/////////////////////////////////////////////////////////////////////////////////
			////////////////////// Comandos que ejecutara el Usuario/Player /////////////////
			/////////////////////////////////////////////////////////////////////////////////  
			Player player = (Player) sender;
			if (args.length <= 0) {
				// "/realLife"
				player.sendMessage(cnameForChat + cdescForChat);
				player.sendMessage(cnameForChat + use + " " + cForChat + " help <page> " +
						cdesPlayer_help_ForChat);
				return true;
			} else if (args.length > 0) {
				if ((args[0].equalsIgnoreCase("help") && (args.length == 1)) || 
						(args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("1") && args.length == 2)) {
					// "/realLife help" "/realLife help 1"
					player.sendMessage(cnameForChat + ChatColor.WHITE + "pagina 1 de 1");
					player.sendMessage(use + " " + cForChat + cdesc___ForChat);
					player.sendMessage(use + " " + cForChat + " showkills " + cdesc_showkills_ForChat);
					return true;
				} else if (args[0].equalsIgnoreCase("help") && (args.length >= 2)) {
					// "/realLife help asdasd asd"
					player.sendMessage(cnameForChat + ChatColor.WHITE + "El comando " + 
							cForChat + " help" + ChatColor.WHITE + "solo tiene 1 pagina.");
					player.sendMessage(use + " " + cForChat + " help <page> " + ChatColor.WHITE +
						"--> Page solo puede ser [1].");
					return true;
				} else if (args[0].equalsIgnoreCase("reload") && (args.length == 1)) {
					// "/realLife reload"
					player.sendMessage(separator);
					player.sendMessage(cnameForChat + cresStart_reload_ForChat);
					main.reloadCustomConfigRealLifePlayersKills();
					player.sendMessage(cnameForChat + ChatColor.DARK_GREEN + "/realLife/players/kills.yml");
					player.sendMessage(cnameForChat + cresConfig_reload_ForChat+"   " + correct);
					player.sendMessage(separator);
					return true;
				} else if (args[0].equalsIgnoreCase("showkills") && (args.length == 1)) {
					// "/realLife showkills"
					if (configRealLifePlayersKills.contains("Players."+player.getUniqueId()+".kills")) {
						player.sendMessage(separator);
						player.sendMessage(ChatColor.AQUA+"Has matado como Jugador:");
						player.sendMessage(ChatColor.WHITE+"Endermans: " + ChatColor.YELLOW + configRealLifePlayersKills.getString("Players."+player.getUniqueId()+".kills.endermanKills"));
						player.sendMessage(separator);
						return true;
					} else {
						player.sendMessage(separator);
						player.sendMessage(ChatColor.AQUA+"No has matado nada como Jugador!!");
						player.sendMessage(separator);
						return true;
					}
				} else if (args[0].equals("reward") && (args.length == 1)) {
					CooldownDailyReward c = new CooldownDailyReward(main);
					String pathTime_dailyReward = "Players."+player.getUniqueId()+".cooldown-dailyReward";
					String cooldown_dailyReward = c.getCooldown(player);
					if (cooldown_dailyReward.equals("-1")) {
						long millis = System.currentTimeMillis();
						configRealLifeConfig.set(pathTime_dailyReward, millis);
						main.saveCustomConfigRealLifeConfig();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lAcabas de recibir tu recompensa diaria!"));
						player.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
					} else {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDebes esperar &7"+cooldown_dailyReward+" &cpara obtener una nueva recompensa!"));
					}
					return true;
				} else if (args[0].equals("menu") && (args.length == 1)) {
					Menu inv = new Menu();
					inv.principalMenu(player);
					return true;
				} else {
					// "/realLife asdasd asd"
					player.sendMessage(cnameForChat + noExist);
					player.sendMessage(cnameForChat + use + cForChat +" help <page> " + cdesPlayer_help_ForChat);
					return true;
				}
			}
		}
		return false;
	}
}
