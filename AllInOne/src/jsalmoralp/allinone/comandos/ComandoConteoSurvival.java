package jsalmoralp.allinone.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jsalmoralp.allinone.Main;

public class ComandoConteoSurvival implements CommandExecutor {

	// Importando Variables y Funciones de otras Clases
	private Main plugin;
	private FileConfiguration configGlobal;
	private FileConfiguration configConteoSurvival;

	public ComandoConteoSurvival(Main plugin) {
		this.plugin = plugin;
		this.configGlobal = plugin.getConfig();
		this.configConteoSurvival = plugin.getConteoSurvivalConfig();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {

		// Si el Modulo está Activado
		if (configGlobal.getString("modules.general").equals("all")
				|| (!configGlobal.getString("modules.general").equals("false")
						&& configGlobal.getString("modules.conteo-survival").equals("true"))) {

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

					// Comando "/cs help"
					if (args[0].equalsIgnoreCase("help") && args.length == 1) {
						jugador.sendMessage(plugin.separador);
						jugador.sendMessage(plugin.nombre + "/cs kills");
						jugador.sendMessage(plugin.separador);
						return true;
					}

					// Comando "/cs kills"
					else if (args[0].equalsIgnoreCase("kills") && args.length == 1) {
						if (configConteoSurvival.contains("Players." + jugador.getUniqueId())) {
							jugador.sendMessage(plugin.separador);
							if (configConteoSurvival.contains("Players." + jugador.getUniqueId() + ".kills")) {
								jugador.sendMessage("Has matado ha:");
								if (configConteoSurvival
										.contains("Players." + jugador.getUniqueId() + ".kills.zombies")) {
									jugador.sendMessage("Zombies: " + configConteoSurvival
											.getString("Players." + jugador.getUniqueId() + ".kills.zombies"));
								} else {
									jugador.sendMessage("Aún no has matado a nadie.");
								}
							} else {
								jugador.sendMessage(plugin.nombre + "Pero bueno aún no has matado a nada !!");
							}
							jugador.sendMessage(plugin.separador);
						} else {
							jugador.sendMessage(plugin.nombre + "Muevete y corre ha cazar algo !!");
						}
						return true;
					}

					// Comando "/cs cualquier cosa"
					else {
						jugador.sendMessage(plugin.nombre + ChatColor.RED + "Este comando no existe !!!");
						jugador.sendMessage(plugin.nombre + "Usa " + ChatColor.GREEN + "/cs help" + ChatColor.WHITE
								+ " para ver la lista de comandos.");
						return true;
					}

				}

				// Comando "/cs"
				else {
					jugador.sendMessage(plugin.nombre + "Usa " + ChatColor.GREEN + "/cs help" + ChatColor.WHITE
							+ " para ver la lista de comandos.");
					return true;
				}

			} // FIN: Si el Comando es ejecutado por un Jugador

			// Si el Comando no es ejecutado por la Consola ni un Jugador

		}

		return true;

	}

}