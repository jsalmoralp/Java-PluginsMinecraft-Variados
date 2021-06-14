package jsalmoralp.allinone.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jsalmoralp.allinone.Main;

public class ComandoSpawn implements CommandExecutor {

	// Importando Variables y Funciones de otras Clases
	private Main plugin;
	private FileConfiguration configGlobal;
	private FileConfiguration configSpawns;

	public ComandoSpawn(Main plugin) {
		this.plugin = plugin;
		this.configGlobal = plugin.getConfig();
		this.configSpawns = plugin.getSpawnsConfig();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {

		// Si el Modulo está Activado
		if (configGlobal.getString("modules.general").equals("all")
				|| (!configGlobal.getString("modules.general").equals("false")
						&& configGlobal.getString("modules.spawn").equals("true"))) {

			// Si el Comando es ejecutado por la Consola
			if (!(sender instanceof Player)) {
				Bukkit.getConsoleSender()
						.sendMessage(plugin.nombre + "No puedes ejecutar este comando desde la consola !!!");
				return false;
			} // FIN: Si el Comando es ejecutado por la Consola

			// Si el Comando es ejecutado por un Jugador
			else if (sender instanceof Player) {
				Player jugador = (Player) sender;
				if (args.length > 0) {

					// Comando "/spawn help"
					if (args[0].equalsIgnoreCase("help")) {
						jugador.sendMessage(plugin.separador);
						jugador.sendMessage(plugin.nombre + "/spawn set <onJoin/world>");
						jugador.sendMessage(plugin.nombre + "/spawn tp <?player>");
						jugador.sendMessage(plugin.separador);
						return true;
					}

					// Comando "/spawn set <onJoin/world>"
					else if (args[0].equalsIgnoreCase("set") && args.length == 2) {
						if (args[1].equalsIgnoreCase("onJoin")) {
							Location loc = jugador.getLocation();
							String world = loc.getWorld().getName();
							double x = loc.getX();
							double y = loc.getY();
							double z = loc.getZ();
							float yaw = loc.getYaw();
							float pitch = loc.getPitch();
							configSpawns.set("server.playerJoin.world", world);
							configSpawns.set("server.playerJoin.x", x);
							configSpawns.set("server.playerJoin.y", y);
							configSpawns.set("server.playerJoin.z", z);
							configSpawns.set("server.playerJoin.yaw", yaw);
							configSpawns.set("server.playerJoin.pitch", pitch);
							plugin.saveConfig();
							jugador.sendMessage(plugin.nombre + ChatColor.GREEN
									+ "El spawn de entrada al servidor ha sido puesto correctamente !!!");

							return true;
						} else if (args[1].equalsIgnoreCase("world")) {
							Location loc = jugador.getLocation();
							String world = loc.getWorld().getName();
							double x = loc.getX();
							double y = loc.getY();
							double z = loc.getZ();
							float yaw = loc.getYaw();
							float pitch = loc.getPitch();
							configSpawns.set("server.perWorlds." + world, world);
							configSpawns.set("server.perWorlds." + world + ".x", x);
							configSpawns.set("server.perWorlds." + world + ".y", y);
							configSpawns.set("server.perWorlds." + world + ".z", z);
							configSpawns.set("server.perWorlds." + world + ".yaw", yaw);
							configSpawns.set("server.perWorlds." + world + ".pitch", pitch);
							plugin.saveConfig();
							jugador.sendMessage(plugin.nombre + ChatColor.GREEN + "El spawn del Mundo " + world
									+ " ha sido puesto correctamente !!!");

							return true;
						}
					}

					// Comando "/spawn tp <world> <?player>"
					else if (args[0].equalsIgnoreCase("tp") && args.length == 2) {
						// TODO: Terminar comando "/spawn tp <world> <?player>"
						return true;
					}

					// Comando "/spawn cualquier cosa"
					else {
						jugador.sendMessage(plugin.nombre + ChatColor.RED + "Este comando no existe !!!");
						jugador.sendMessage(plugin.nombre + "Usa " + ChatColor.GREEN + "/spawn help" + ChatColor.WHITE
								+ " para ver la lista de comandos.");
						return true;
					}

				}

				// Comando "/spawn"
				else {
					String mundoActual = jugador.getWorld().getName();

					// Si el Spawn del Mundo ya ha sido creado el jugador será teleportado
					if (configSpawns.contains("server.perWorlds." + mundoActual + ".x")) {
						World world = plugin.getServer().getWorld(mundoActual);
						double x = Double.valueOf(configSpawns.getString("server.perWorlds." + mundoActual + ".x"));
						double y = Double.valueOf(configSpawns.getString("server.perWorlds." + mundoActual + ".y"));
						double z = Double.valueOf(configSpawns.getString("server.perWorlds." + mundoActual + ".z"));
						float yaw = Float.valueOf(configSpawns.getString("server.perWorlds." + mundoActual + ".yaw"));
						float pitch = Float
								.valueOf(configSpawns.getString("server.perWorlds." + mundoActual + ".pitch"));
						Location loc = new Location(world, x, y, z, yaw, pitch);
						jugador.teleport(loc);
						return true;
					}

					// Si el Spawn del Mundo aun no ha sido creado
					else {
						jugador.sendMessage(plugin.nombre
								+ "Lamentamos el inconveniente, el Spawn de este Mundo aun no ha sido creado.");
						jugador.sendMessage(plugin.nombre + "Usa " + ChatColor.GREEN + "/spawn help" + ChatColor.WHITE
								+ " para ver la lista de comandos.");
						return true;
					}

				}

			} // FIN: Si el Comando es ejecutado por un Jugador

			// Si el Comando no es ejecutado por la Consola ni un Jugador

		}

		return true;

	}

}
