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

public class ComandoHub implements CommandExecutor {

	// Importando Variables y Funciones de otras Clases
	private Main plugin;
	private FileConfiguration configGlobal;
	private FileConfiguration configSpawns;

	public ComandoHub(Main plugin) {
		this.plugin = plugin;
		this.configGlobal = plugin.getConfig();
		this.configSpawns = plugin.getSpawnsConfig();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {

		// Si el Modulo est� Activado
		if (configGlobal.getString("modules.general").equals("all")
				|| (!configGlobal.getString("modules.general").equals("false")
						&& configGlobal.getString("modules.hub").equals("true"))) {

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

					// Comando "/hub help"
					if (args[0].equalsIgnoreCase("help") && args.length == 1) {
						jugador.sendMessage(plugin.separador);
						jugador.sendMessage(plugin.nombre + "/hub set");
						jugador.sendMessage(plugin.separador);
						return true;
					}

					// Comando "/hub set"
					else if (args[0].equalsIgnoreCase("set") && args.length == 1) {
						Location loc = jugador.getLocation();
						String world = loc.getWorld().getName();
						double x = loc.getX();
						double y = loc.getY();
						double z = loc.getZ();
						float yaw = loc.getYaw();
						float pitch = loc.getPitch();
						configSpawns.set("server.hub.world", world);
						configSpawns.set("server.hub.x", x);
						configSpawns.set("server.hub.y", y);
						configSpawns.set("server.hub.z", z);
						configSpawns.set("server.hub.yaw", yaw);
						configSpawns.set("server.hub.pitch", pitch);
						plugin.saveSpawnsConfig();
						jugador.sendMessage(plugin.nombre + ChatColor.GREEN
								+ "El Hub del servidor ha sido puesto correctamente !!!");
						return true;
					}

					// Comando "/hub cualquier cosa"
					else {
						jugador.sendMessage(plugin.nombre + ChatColor.RED + "Este comando no existe !!!");
						jugador.sendMessage(plugin.nombre + "Usa " + ChatColor.GREEN + "/hub help" + ChatColor.WHITE
								+ " para ver la lista de comandos.");
						return true;
					}

				}

				// Comando "/hub"
				else {

					// Si el Hub del Servidor ya ha sido creado el jugador ser� teleportado
					if (configSpawns.contains("server.hub.world")) {
						World world = plugin.getServer().getWorld(configSpawns.getString("server.hub.world"));
						double x = Double.valueOf(configSpawns.getString("server.hub.x"));
						double y = Double.valueOf(configSpawns.getString("server.hub.y"));
						double z = Double.valueOf(configSpawns.getString("server.hub.z"));
						float yaw = Float.valueOf(configSpawns.getString("server.hub.yaw"));
						float pitch = Float.valueOf(configSpawns.getString("server.hub.pitch"));
						Location loc = new Location(world, x, y, z, yaw, pitch);
						jugador.teleport(loc);
						return true;
					}

					// Si el Spawn del Mundo aun no ha sido creado
					else {
						jugador.sendMessage(plugin.nombre
								+ "Lamentamos el inconveniente, el Hub de la Network aun no ha sido creado.");
						jugador.sendMessage(plugin.nombre + "Usa " + ChatColor.GREEN + "/hub help" + ChatColor.WHITE
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