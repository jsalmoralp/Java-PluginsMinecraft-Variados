package es.jsalmoralp.supremacy.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import es.jsalmoralp.supremacy.Main;
import es.jsalmoralp.supremacy.aggregates.Menu;
import es.jsalmoralp.supremacy.automobsspawning.AutoSpawningBasicMobs;
import es.jsalmoralp.supremacy.automobsspawning.AutoSpawningBossMobs;
import es.jsalmoralp.supremacy.automobsspawning.AutoSpawningMajorMobs;
import es.jsalmoralp.supremacy.automobsspawning.AutoSpawningMediumMobs;
import es.jsalmoralp.supremacy.features.CountdownMenu;

public class CommandOPSupremacy implements CommandExecutor {
	// Constructor
	private Main main;
	public CommandOPSupremacy(Main main) {
		this.main = main;
	}
	
	// Archivos de Configuracion
	FileConfiguration config = main.getCustomConfig();
	FileConfiguration configMobsSpawns = main.getCustomConfigMobsSpawns();
	
	// Variables para el Chat
	String cnameForChat = ChatColor.AQUA + "[ SUPREMACY ]  ";
	String cForChat = ChatColor.GREEN + "/opsupremacy";
	String cdescForChat = ChatColor.WHITE + "Es el Comando del Plugin Supremacy.";
	String separator = ChatColor.RED + "<------------------------------>";
	String noExist = ChatColor.RED + "Este comando no existe!";
	String correct = ChatColor.GREEN + "Correcto!";
	String use = ChatColor.WHITE + "Usa";

	// Variables para "/opsupremacy help"
	String cdesConsole_help_ForChat = ChatColor.WHITE + "--> Ver las posibilidades del comando por Consola.";
	String cdesPlayer_help_ForChat = ChatColor.WHITE + "--> Ver las posibilidades del comando para el Player.";

	// Variables para "/opsupremacy"
	String cdesc___ForChat = ChatColor.WHITE + "--> Comando que interactua con las opciones del Plugin Supremacy.";

	// Variables para "/opsupremacy reload"
	String cdesc_reload_ForChat = ChatColor.WHITE + "--> Recarga la Config de los Spawns del Servidor.";
	String cresStart_reload_ForChat = ChatColor.YELLOW + "Se inicia la recarga de la Config...";
	String cresConfig_reload_ForChat = ChatColor.WHITE + "Recarga de la Config...";

	// Variables para "/opsupremacy removeAllMobs"
	String cdesc_removeAllmobs_ForChat = ChatColor.WHITE + "--> Comando General para Spawnear los diferentes tipos de Mobs.";
	
	// Variables para "/opsupremacy spawn"
	String cdesc_spawn__ForChat = ChatColor.WHITE + "--> Comando General para Spawnear los diferentes tipos de Mobs.";

	// Variables para "/opsupremacy spawn help"
	String cdesc_spawn_help_ForChat = ChatColor.WHITE + "--> Usalo para ver las opciones para Spawnear los diferentes tipos de Mobs.";
	
	// Variables para "/opsupremacy spawn allMobs"
	String cdesc_spawn_allMobs_ForChat = ChatColor.WHITE + "--> Comando que Spawnea Todos los tipos de Mobs.";

	// Variables para "/opsupremacy spawn basicMobs"
	String cdesc_spawn_basicMobs_ForChat = ChatColor.WHITE + "--> Comando que Spawnea los Mobs de tipo Basico.";

	// Variables para "/opsupremacy spawn mediumMobs"
	String cdesc_spawn_mediumMobs_ForChat = ChatColor.WHITE + "--> Comando que Spawnea los Mobs de tipo Medio.";

	// Variables para "/opsupremacy spawn majorMobs"
	String cdesc_spawn_majorMobs_ForChat = ChatColor.WHITE + "--> Comando que Spawnea los Mobs de tipo Mayor.";

	// Variables para "/opsupremacy spawn bossMobs"
	String cdesc_spawn_bossMobs_ForChat = ChatColor.WHITE + "--> Comando que Spawnea los Mobs de tipo Boss.";
	
	// Variables para "/opsupremacy spawn"
	String cdesc_setSpawn__ForChat = ChatColor.WHITE + "--> Comando General para Spawnear los diferentes tipos de Mobs.";

	// Variables para "/opsupremacy spawn help"
	String cdesc_setSpawn_help_ForChat = ChatColor.WHITE + "--> Usalo para ver las opciones para Spawnear los diferentes tipos de Mobs.";
	
	// Variables para "/opsupremacy setSpawn basicMobs"
	String cdesc_setSpawn_basicMobs_ForChat = ChatColor.WHITE + "--> Comando que coloca un punto de Spawn para los Mobs de tipo Basico.";

	// Variables para "/opsupremacy setSpawn mediumMobs"
	String cdesc_setSpawn_mediumMobs_ForChat = ChatColor.WHITE + "--> Comando que coloca un punto de Spawn para los Mobs de tipo Medio.";

	// Variables para "/opsupremacy setSpawn majorMobs"
	String cdesc_setSpawn_majorMobs_ForChat = ChatColor.WHITE + "--> Comando que coloca un punto de Spawn para los Mobs de tipo Mayor.";

	// Variables para "/opsupremacy setSpawn bossMobs"
	String cdesc_setSpawn_bossMobs_ForChat = ChatColor.WHITE + "--> Comando que coloca un punto de Spawn para los Mobs de tipo Boss.";
	

	public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
		/////////////////////////////////////////////////////////////////////////////////
		////////////////////// Comandos que ejecutara la Consola ////////////////////////
		///////////////////////////////////////////////////////////////////////////////// 
		if (!(sender instanceof Player)) {
			if (args.length <= 0) {
				// "/opsupremacy"
				Bukkit.getConsoleSender().sendMessage(cnameForChat + cdescForChat);
				Bukkit.getConsoleSender()
						.sendMessage(cnameForChat + use + " " + cForChat + " help <page> " + cdesConsole_help_ForChat);
				return false;
			} else if (args.length > 0) {
				if ((args[0].equalsIgnoreCase("help") && (args.length == 1))
						|| (args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("1") && (args.length == 2))) {
					// "/opsupremacy help" "/opsupremacy help 1"
					Bukkit.getConsoleSender().sendMessage(cnameForChat + ChatColor.WHITE + "pagina 1 de 1");
					Bukkit.getConsoleSender().sendMessage(use + " " + cForChat + " reload " + cdesc_reload_ForChat);
					Bukkit.getConsoleSender().sendMessage(cForChat + " spawn " + cdesc_spawn__ForChat);
					Bukkit.getConsoleSender()
							.sendMessage(use + " " + cForChat + " spawn help " + cdesc_spawn_help_ForChat);
					return false;
				} else if (args[0].equalsIgnoreCase("spawm") && args[1].equalsIgnoreCase("help")
						&& (args.length == 2)) {
					// "/opsupremacy help" "/opsupremacy spawn help"
					Bukkit.getConsoleSender()
							.sendMessage(use + " " + cForChat + " spawn all " + cdesc_spawn_allMobs_ForChat);
					Bukkit.getConsoleSender()
							.sendMessage(use + " " + cForChat + " spawn basic " + cdesc_spawn_basicMobs_ForChat);
					Bukkit.getConsoleSender()
							.sendMessage(use + " " + cForChat + " spawn medium " + cdesc_spawn_mediumMobs_ForChat);
					Bukkit.getConsoleSender()
							.sendMessage(use + " " + cForChat + " spawn major " + cdesc_spawn_majorMobs_ForChat);
					Bukkit.getConsoleSender()
							.sendMessage(use + " " + cForChat + " spawn boss " + cdesc_spawn_bossMobs_ForChat);
					return false;
				} else if (args[0].equalsIgnoreCase("help") && (args.length >= 2)) {
					// "/opsupremacy help asdasd asd"
					Bukkit.getConsoleSender().sendMessage(cnameForChat + ChatColor.WHITE + "El comando " + cForChat
							+ " help" + ChatColor.WHITE + "solo tiene 1 pagina.");
					Bukkit.getConsoleSender().sendMessage(
							use + " " + cForChat + " help <page> " + ChatColor.WHITE + "--> Page solo puede ser [1].");
					return false;
				} else if (args[0].equalsIgnoreCase("reload") && (args.length == 1)) {
					// "/opsupremacy reload"
					Bukkit.getConsoleSender().sendMessage(separator);
					Bukkit.getConsoleSender().sendMessage(cnameForChat + cresStart_reload_ForChat);
					main.reloadCustomConfig();
					Bukkit.getConsoleSender().sendMessage(cnameForChat + ChatColor.DARK_GREEN + "config.yml");
					main.reloadCustomConfigMessages();
					Bukkit.getConsoleSender().sendMessage(cnameForChat + ChatColor.DARK_GREEN + "/messages/"
							+ config.getString("Config.lang") + ".yml");
					Bukkit.getConsoleSender()
							.sendMessage(cnameForChat + ChatColor.DARK_GREEN + "/opsupremacy/players/kills.yml");
					Bukkit.getConsoleSender().sendMessage(cnameForChat + cresConfig_reload_ForChat + "   " + correct);
					Bukkit.getConsoleSender().sendMessage(separator);
					return false;
				} else if (args[0].equalsIgnoreCase("spawn")) {
					if (args.length == 1) {
						Bukkit.getConsoleSender().sendMessage(use + " " + cForChat + " spawn help " + cdesc_spawn_help_ForChat);
						return false;
					} else if (args[1].equalsIgnoreCase("all") && (args.length == 2)) {
						spawnBasicMobs();
						spawnMediumMobs();
						spawnMajorMobs();
						spawnBossMobs();
						return false;
					} else if (args[1].equalsIgnoreCase("basic") && (args.length == 2)) {
						spawnBasicMobs();
						return false;
					} else if (args[1].equalsIgnoreCase("medium") && (args.length == 2)) {
						spawnMediumMobs();
						return false;
					} else if (args[1].equalsIgnoreCase("major") && (args.length == 2)) {
						spawnMajorMobs();
						return false;
					} else if (args[1].equalsIgnoreCase("boss") && (args.length == 2)) {
						spawnMajorMobs();
						return false;
					} else if (args[1].equalsIgnoreCase("remove-all") && (args.length == 2)) {
						String world = config.getString("Config.configured-world");
						removeAllEntities(world);
						return false;
					} else {
						// "/opsupremacy spawn asdasd asd"
						Bukkit.getConsoleSender().sendMessage(cnameForChat + noExist);
						Bukkit.getConsoleSender()
								.sendMessage(use + " " + cForChat + " spawn help " + cdesc_spawn_help_ForChat);
						return false;
					}
				} else {
					// "/opsupremacy asdasd asd"
					Bukkit.getConsoleSender().sendMessage(cnameForChat + noExist);
					Bukkit.getConsoleSender()
							.sendMessage(cnameForChat + use + cForChat + " help <page> " + cdesConsole_help_ForChat);
					return false;
				}
			}
		} else {
			/////////////////////////////////////////////////////////////////////////////////
			////////////////////// Comandos que ejecutara el Usuario/Player /////////////////
			///////////////////////////////////////////////////////////////////////////////// 
			Player player = (Player) sender;
			if (args.length <= 0) {
				// "/opsupremacy"
				player.sendMessage(cnameForChat + cdescForChat);
				player.sendMessage(cnameForChat + use + " " + cForChat + " help <page> " + cdesPlayer_help_ForChat);
				return true;
			} else if (args.length > 0) {
				if ((args[0].equalsIgnoreCase("help") && (args.length == 1))
						|| (args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("1") && args.length == 2)) {
					// "/opsupremacy help" "/opsupremacy help 1"
					player.sendMessage(cnameForChat + ChatColor.WHITE + "pagina 1 de 1");
					player.sendMessage(use + " " + cForChat + cdesc___ForChat);
					return true;
				} else if (args[0].equalsIgnoreCase("spawm") && args[1].equalsIgnoreCase("help")
						&& (args.length == 2)) {
					// "/opsupremacy help" "/opsupremacy spawn help"
					player.sendMessage(use + " " + cForChat + " spawn allMobs " + cdesc_spawn_allMobs_ForChat);
					player.sendMessage(use + " " + cForChat + " spawn basicMobs " + cdesc_spawn_basicMobs_ForChat);
					player.sendMessage(use + " " + cForChat + " spawn mediumMobs " + cdesc_spawn_mediumMobs_ForChat);
					player.sendMessage(use + " " + cForChat + " spawn majorMobs " + cdesc_spawn_majorMobs_ForChat);
					player.sendMessage(use + " " + cForChat + " spawn bossMobs " + cdesc_spawn_bossMobs_ForChat);
					return false;
				} else if (args[0].equalsIgnoreCase("spawm") && args[1].equalsIgnoreCase("help")
						&& (args.length == 2)) {
					// "/opsupremacy help" "/opsupremacy setSpawn help"
					player.sendMessage(use + " " + cForChat + " setSpawn basicMobs " + cdesc_setSpawn_basicMobs_ForChat);
					player.sendMessage(use + " " + cForChat + " setSpawn mediumMobs " + cdesc_setSpawn_mediumMobs_ForChat);
					player.sendMessage(use + " " + cForChat + " setSpawn majorMobs " + cdesc_setSpawn_majorMobs_ForChat);
					player.sendMessage(use + " " + cForChat + " setSpawn bossMobs " + cdesc_setSpawn_bossMobs_ForChat);
					return false;
				} else if (args[0].equalsIgnoreCase("help") && (args.length >= 2)) {
					// "/opsupremacy help asdasd asd"
					player.sendMessage(cnameForChat + ChatColor.WHITE + "El comando " + cForChat + " help"
							+ ChatColor.WHITE + "solo tiene 1 pagina.");
					player.sendMessage(
							use + " " + cForChat + " help <page> " + ChatColor.WHITE + "--> Page solo puede ser [1].");
					return true;
				} else if (args[0].equalsIgnoreCase("reload") && (args.length == 1)) {
					// "/opsupremacy reload"
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
				} else if (args[0].equalsIgnoreCase("menu") && (args.length == 1)) {
					// "/opsupremacy menu"
					if (config.getString("Modules.countdown.OpenMenu").equals("true")) {
						if (!main.isPlayerInCountdownOpenMenu(player)) {
							main.addPlayerInCountdownOpenMenu(player);
							CountdownMenu cm = new CountdownMenu(main, 3, player);
							cm.execution();
						}
						return true;
					} else {
						Menu inv = new Menu();
						inv.principalMenu(player);
						return true;
					}
				} else if (args[0].equals("spawn")) {
					if (args.length == 1) {
						player.sendMessage(use + " " + cForChat + " spawn help " + cdesc_spawn_help_ForChat);
						return true;
					} else if (args[1].equalsIgnoreCase("allMobs") && (args.length == 2)) {
						spawnBasicMobs();
						spawnMediumMobs();
						spawnMajorMobs();
						spawnBossMobs();
						return true;
					} else if (args[1].equalsIgnoreCase("basicMobs") && (args.length == 2)) {
						spawnBasicMobs();
						return true;
					} else if (args[1].equalsIgnoreCase("mediumMobs") && (args.length == 2)) {
						spawnMediumMobs();
						return true;
					} else if (args[1].equalsIgnoreCase("majorMobs") && (args.length == 2)) {
						spawnMajorMobs();
						return true;
					} else if (args[1].equalsIgnoreCase("bossmobs") && (args.length == 2)) {
						spawnMajorMobs();
						return true;
					} else if (args[1].equalsIgnoreCase("remove-allMobs") && (args.length == 2)) {
						String world = player.getWorld().getName();
						removeAllEntities(world);
						return true;
					} else {
						// "/opsupremacy spawn asdasd asd"
						player.sendMessage(cnameForChat + noExist);
						player.sendMessage(use + " " + cForChat + " spawn help " + cdesc_spawn_help_ForChat);
						return true;
					}
				} else if (args[0].equals("setSpawn")) {
					if (args[1].equalsIgnoreCase("basicMobs") && (args.length == 2)) {
						for (int i=1;i<=1;i++) {
							if (!configMobsSpawns.contains("Spawns.BasicMobs.location-"+i)) {
								Location loc = player.getLocation();
								String world = loc.getWorld().getName();
								double x = loc.getX();
								double y = loc.getY();
								double z = loc.getZ();
								float yaw = loc.getYaw();
								float pitch = loc.getPitch();
								configMobsSpawns.set("Spawns.BasicMobs.location-"+i+".world", world);
								configMobsSpawns.set("Spawns.BasicMobs.location-"+i+".x", x);
								configMobsSpawns.set("Spawns.BasicMobs.location-"+i+".y", y);
								configMobsSpawns.set("Spawns.BasicMobs.location-"+i+".z", z);
								configMobsSpawns.set("Spawns.BasicMobs.location-"+i+".yaw", yaw);
								configMobsSpawns.set("Spawns.BasicMobs.location-"+i+".pitch", pitch);
								main.saveCustomConfigMobsSpawns();
								return true;
							}
						}
						return true;
					} else if (args[1].equalsIgnoreCase("mediumMobs") && (args.length == 2)) {
						for (int i=1;i<=1;i++) {
							if (!configMobsSpawns.contains("Spawns.MediumMobs.location-"+i)) {
								Location loc = player.getLocation();
								String world = loc.getWorld().getName();
								double x = loc.getX();
								double y = loc.getY();
								double z = loc.getZ();
								float yaw = loc.getYaw();
								float pitch = loc.getPitch();
								configMobsSpawns.set("Spawns.MediumMobs.location-"+i+".world", world);
								configMobsSpawns.set("Spawns.MediumMobs.location-"+i+".x", x);
								configMobsSpawns.set("Spawns.MediumMobs.location-"+i+".y", y);
								configMobsSpawns.set("Spawns.MediumMobs.location-"+i+".z", z);
								configMobsSpawns.set("Spawns.MediumMobs.location-"+i+".yaw", yaw);
								configMobsSpawns.set("Spawns.MediumMobs.location-"+i+".pitch", pitch);
								main.saveCustomConfigMobsSpawns();
								return true;
							}
						}
						return true;
					} else if (args[1].equalsIgnoreCase("majorMobs") && (args.length == 2)) {
						for (int i=1;i<=1;i++) {
							if (!configMobsSpawns.contains("Spawns.MajorMobs.location-"+i)) {
								Location loc = player.getLocation();
								String world = loc.getWorld().getName();
								double x = loc.getX();
								double y = loc.getY();
								double z = loc.getZ();
								float yaw = loc.getYaw();
								float pitch = loc.getPitch();
								configMobsSpawns.set("Spawns.MajorMobs.location-"+i+".world", world);
								configMobsSpawns.set("Spawns.MajorMobs.location-"+i+".x", x);
								configMobsSpawns.set("Spawns.MajorMobs.location-"+i+".y", y);
								configMobsSpawns.set("Spawns.MajorMobs.location-"+i+".z", z);
								configMobsSpawns.set("Spawns.MajorMobs.location-"+i+".yaw", yaw);
								configMobsSpawns.set("Spawns.MajorMobs.location-"+i+".pitch", pitch);
								main.saveCustomConfigMobsSpawns();
								return true;
							}
						}
						return true;
					} else if (args[1].equalsIgnoreCase("bossMobs") && (args.length == 2)) {
						for (int i=1;i<=1;i++) {
							if (!configMobsSpawns.contains("Spawns.BossMobs.location-"+i)) {
								Location loc = player.getLocation();
								String world = loc.getWorld().getName();
								double x = loc.getX();
								double y = loc.getY();
								double z = loc.getZ();
								float yaw = loc.getYaw();
								float pitch = loc.getPitch();
								configMobsSpawns.set("Spawns.BossMobs.location-"+i+".world", world);
								configMobsSpawns.set("Spawns.BossMobs.location-"+i+".x", x);
								configMobsSpawns.set("Spawns.BossMobs.location-"+i+".y", y);
								configMobsSpawns.set("Spawns.BossMobs.location-"+i+".z", z);
								configMobsSpawns.set("Spawns.BossMobs.location-"+i+".yaw", yaw);
								configMobsSpawns.set("Spawns.BossMobs.location-"+i+".pitch", pitch);
								main.saveCustomConfigMobsSpawns();
								return true;
							}
						}
						return true;
					} else {
						// "/opsupremacy setSpawn asdasd asd"
						player.sendMessage(cnameForChat + noExist);
						player.sendMessage(use + " " + cForChat + " setSpawn help " + cdesc_setSpawn_help_ForChat);
						return true;
					}
					
				} else {
					// "/opsupremacy asdasd asd"
					player.sendMessage(cnameForChat + noExist);
					player.sendMessage(cnameForChat + use + cForChat + " help <page> " + cdesPlayer_help_ForChat);
					return true;
				}
			}
		}
		return false;
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	///////////////// Metodos que Spawnearan los distintos tipos de Mobs //////////////
	///////////////////////////////////////////////////////////////////////////////////
	public void spawnBasicMobs() {
		if (config.getString("Modules.features.AutoSpawning.types.BasicMobs").equals("true")) {
			for (int i = 1; i >= 1; i++) {
				if (configMobsSpawns.contains("Spawns.BasicMobs.location-" + i)) {
					World world = Bukkit.getWorld(
							configMobsSpawns.getString("Spawns.BasicMobs.location-" + i + ".world"));
					double x = Double.valueOf(
							configMobsSpawns.getString("Spawns.BasicMobs.location-" + i + ".x"));
					double y = Double.valueOf(
							configMobsSpawns.getString("Spawns.BasicMobs.location-" + i + ".y"));
					double z = Double.valueOf(
							configMobsSpawns.getString("Spawns.BasicMobs.location-" + i + ".z"));
					float yaw = Float.valueOf(
							configMobsSpawns.getString("Spawns.BasicMobs.location-" + i + ".yaw"));
					float pitch = Float.valueOf(
							configMobsSpawns.getString("Spawns.BasicMobs.location-" + i + ".pitch"));
					Location loc = new Location(world, x, y, z, yaw, pitch);
					AutoSpawningBasicMobs autoSpawningBasicMobs = new AutoSpawningBasicMobs(main, 1200, loc);
					autoSpawningBasicMobs.executionWithStop();
				} else {
					break;
				}
			}
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
					cnameForChat + "&aSe han generado los mobs de tipo Basico."));
		}
	}
	
	public void spawnMediumMobs() {
		if (config.getString("Modules.features.AutoSpawning.types.MediumMobs").equals("true")) {
			for (int i = 1; i >= 1; i++) {
				if (configMobsSpawns.contains("Spawns.MediumMobs.location-" + i)) {
					World world = Bukkit.getWorld(
							configMobsSpawns.getString("Spawns.MediumMobs.location-" + i + ".world"));
					double x = Double.valueOf(
							configMobsSpawns.getString("Spawns.MediumMobs.location-" + i + ".x"));
					double y = Double.valueOf(
							configMobsSpawns.getString("Spawns.MediumMobs.location-" + i + ".y"));
					double z = Double.valueOf(
							configMobsSpawns.getString("Spawns.MediumMobs.location-" + i + ".z"));
					float yaw = Float.valueOf(
							configMobsSpawns.getString("Spawns.MediumMobs.location-" + i + ".yaw"));
					float pitch = Float.valueOf(
							configMobsSpawns.getString("Spawns.MediumMobs.location-" + i + ".pitch"));
					Location loc = new Location(world, x, y, z, yaw, pitch);
					AutoSpawningMediumMobs autoSpawningMediumMobs = new AutoSpawningMediumMobs(main, 1200, loc);
					autoSpawningMediumMobs.executionWithStop();
				} else {
					break;
				}
			}
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
					cnameForChat + "&aSe han generado los mobs de tipo Medio."));
		}
	}
	
	public void spawnMajorMobs() {
		if (config.getString("Modules.features.AutoSpawning.types.MajorMobs").equals("true")) {
			for (int i = 1; i >= 1; i++) {
				if (configMobsSpawns.contains("Spawns.MajorMobs.location-" + i)) {
					World world = Bukkit.getWorld(
							configMobsSpawns.getString("Spawns.MajorMobs.location-" + i + ".world"));
					double x = Double.valueOf(
							configMobsSpawns.getString("Spawns.MajorMobs.location-" + i + ".x"));
					double y = Double.valueOf(
							configMobsSpawns.getString("Spawns.MajorMobs.location-" + i + ".y"));
					double z = Double.valueOf(
							configMobsSpawns.getString("Spawns.MajorMobs.location-" + i + ".z"));
					float yaw = Float.valueOf(
							configMobsSpawns.getString("Spawns.MajorMobs.location-" + i + ".yaw"));
					float pitch = Float.valueOf(
							configMobsSpawns.getString("Spawns.MajorMobs.location-" + i + ".pitch"));
					Location loc = new Location(world, x, y, z, yaw, pitch);
					AutoSpawningMajorMobs autoSpawningMajorMobs = new AutoSpawningMajorMobs(main, 1200, loc);
					autoSpawningMajorMobs.executionWithStop();
				} else {
					break;
				}
			}
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
					cnameForChat + "&aSe han generado los mobs de tipo Mayor."));
		}
	}
	
	public void spawnBossMobs() {
		if (config.getString("Modules.features.AutoSpawning.types.BossMobs").equals("true")) {
			for (int i = 1; i >= 1; i++) {
				if (configMobsSpawns.contains("Spawns.BossMobs.location-" + i)) {
					World world = Bukkit.getWorld(
							configMobsSpawns.getString("Spawns.BossMobs.location-" + i + ".world"));
					double x = Double.valueOf(
							configMobsSpawns.getString("Spawns.BossMobs.location-" + i + ".x"));
					double y = Double.valueOf(
							configMobsSpawns.getString("Spawns.BossMobs.location-" + i + ".y"));
					double z = Double.valueOf(
							configMobsSpawns.getString("Spawns.BossMobs.location-" + i + ".z"));
					float yaw = Float.valueOf(
							configMobsSpawns.getString("Spawns.BossMobs.location-" + i + ".yaw"));
					float pitch = Float.valueOf(
							configMobsSpawns.getString("Spawns.BossMobs.location-" + i + ".pitch"));
					Location loc = new Location(world, x, y, z, yaw, pitch);
					AutoSpawningBossMobs autoSpawningBossMobs = new AutoSpawningBossMobs(main, 1200, loc);
					autoSpawningBossMobs.executionWithStop();
				} else {
					break;
				}
			}
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
					cnameForChat + "&aSe han generado los mobs de tipo Boss."));
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	
	// Metodo que borrara todas las entidades del mapa
	public void removeAllEntities(String world) {
		Bukkit.getServer().getWorld(world).getLivingEntities().clear();
	}
}
