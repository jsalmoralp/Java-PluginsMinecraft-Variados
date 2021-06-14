package jsalmoralp.allinone.comandos;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jsalmoralp.allinone.Main;

public class ComandoReport implements CommandExecutor {
	
	// Importando Variables y Funciones de otras Clases
	private Main plugin;
	private FileConfiguration configGlobal;
	private FileConfiguration configReports;
	public ComandoReport(Main plugin) {
		this.plugin = plugin;
		this.configGlobal = plugin.getConfig();
		this.configReports = plugin.getReportsConfig();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
		
		// Si el Modulo está Activado
		if (configGlobal.getString("modules.general").equals("all")
				|| (!configGlobal.getString("modules.general").equals("false") && configGlobal.getString("modules.reports").equals("true"))) {
		
			// Si el Comando es ejecutado por la Consola
			if (!(sender instanceof Player)) {
				Bukkit.getConsoleSender().sendMessage(plugin.nombre+"No puedes ejecutar este comando desde la consola !!!");
				return false;
			} // FIN: Si el Comando es ejecutado por la Consola
			
			// Si el Comando es ejecutado por un Jugador
			else if (sender instanceof Player) {
				Player jugador = (Player) sender;
				
				if (args.length > 0) {
					
					//Comando "/report help"
					if (args[0].equalsIgnoreCase("help") && args.length == 1) {
						jugador.sendMessage(plugin.separador);
						jugador.sendMessage(plugin.nombre+"/report <player>");
						jugador.sendMessage(plugin.separador);
						return true;
					}
					
					//Comando "/report <player>"
					String primerArg = args[0];
					if (Bukkit.getPlayer(primerArg) != null) {
						List<String> reportados = configReports.getStringList("reported-players");
						String uuidReportada = String.valueOf(Bukkit.getPlayer(primerArg).getUniqueId());
						if (!reportados.contains(uuidReportada)) {
							reportados.add(uuidReportada);
							configReports.set("reported-players", reportados);
							plugin.saveReportsConfig();
							jugador.sendMessage(plugin.nombre+"El Usuario "+ChatColor.DARK_RED+primerArg+ChatColor.RED+" ha sido reportado !!");
						}
						else {
							jugador.sendMessage(plugin.nombre+"El Usuario ya ha sido reportado !!");
						}
						return true;
					} 
					
					// Comando "/report noPlayerOnline cualquier cosa"
					else {
						jugador.sendMessage(plugin.nombre+ChatColor.RED+"El jugador que intentas reportar no existe o no está conectado !!!");
						jugador.sendMessage(plugin.nombre+"Usa "+ChatColor.GREEN+"/report help"+ChatColor.WHITE+" para ver la lista de comandos.");
						return true;
					}

				}
				
				// Comando "/report"
				else {
					jugador.sendMessage(plugin.nombre+"Usa "+ChatColor.GREEN+"/report help"+ChatColor.WHITE+" para ver la lista de comandos.");
					return true;
				}
				
			} // FIN: Si el Comando es ejecutado por un Jugador
			
			// Si el Comando no es ejecutado por la Consola ni un Jugador
			
		}
		
		return true;
		
	}
	
}