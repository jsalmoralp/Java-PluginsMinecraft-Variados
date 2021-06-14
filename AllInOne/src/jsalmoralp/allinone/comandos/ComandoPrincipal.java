package jsalmoralp.allinone.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import jsalmoralp.allinone.Main;

public class ComandoPrincipal implements CommandExecutor {

	// Importando Variables y Funciones de otras Clases
	private Main plugin;

	public ComandoPrincipal(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
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

				// Comando "/aio help"
				if (args[0].equalsIgnoreCase("help") && args.length == 1) {
					jugador.sendMessage(plugin.separador);
					jugador.sendMessage(plugin.nombre + "Comandos que ofrece el Plugin:");
					jugador.sendMessage(plugin.nombre + "  /hub");
					jugador.sendMessage(plugin.nombre + "  /spawn");
					jugador.sendMessage(plugin.nombre + "  /streams");
					jugador.sendMessage(plugin.nombre + "  /cs");
					jugador.sendMessage(plugin.separador + ChatColor.GREEN + " Página 1/2");
					return true;
				}
				// Comando "/aio help <pagina>"
				else if (args[0].equalsIgnoreCase("help") && args.length == 2) {
					// Comando "/aio help 2"
					if (args[1].equalsIgnoreCase("2")) {
						jugador.sendMessage(plugin.separador);
						jugador.sendMessage(plugin.nombre + "Comandos que ofrece el Plugin:");
						jugador.sendMessage(plugin.nombre + "  /aio reload");
						jugador.sendMessage(plugin.nombre + "  /aio version");
						jugador.sendMessage(plugin.nombre + "  /aio help");
						jugador.sendMessage(plugin.separador + ChatColor.GREEN + " Página 2/2");
						return true;
					}
				}
				// Comando "/allinone reload"
				else if (args[0].equalsIgnoreCase("reload") && args.length == 1) {
					plugin.reloadConfig();
					plugin.reloadMessagesConfig();
					plugin.reloadSpawnsConfig();
					plugin.reloadReportsConfig();
					plugin.reloadConteoSurvivalConfig();
					jugador.sendMessage(plugin.nombre + "El plugin ha sido recargado correctamente !!!");
					return true;
				}

				// Comando "/allinone version"
				else if (args[0].equalsIgnoreCase("version") && args.length == 1) {
					jugador.sendMessage(plugin.nombre + plugin.version);
					return true;
				}

				// Comando "/allinone cualquier cosa"
				else {
					jugador.sendMessage(plugin.nombre + ChatColor.RED + "Este comando no existe !!!");
					jugador.sendMessage(plugin.nombre + "Usa " + ChatColor.GREEN + "/aio help" + ChatColor.WHITE
							+ " para ver la lista de comandos.");
					return true;
				}

			}

			// Comando "/allinone"
			else {
				jugador.sendMessage(plugin.nombre + "Usa " + ChatColor.GREEN + "/aio help" + ChatColor.WHITE
						+ " para ver la lista de comandos.");
				return true;
			}

		} // FIN: Si el Comando es ejecutado por un Jugador

		// Si el Comando no es ejecutado por la Consola ni un Jugador
		return true;

	}

}
