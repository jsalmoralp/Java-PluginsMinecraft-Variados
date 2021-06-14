package jsalmoralp.allinone.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import jsalmoralp.allinone.Main;

public class ComandoStreams implements CommandExecutor{
	
	// Importando Variables y Funciones de otras Clases
	private Main plugin;
	private FileConfiguration configGlobal;
	public ComandoStreams(Main plugin) {
		this.plugin = plugin;
		this.configGlobal = plugin.getConfig();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
		
		// Si el Modulo está Activado
		if (configGlobal.getString("modules.general").equals("all")
				|| (!configGlobal.getString("modules.general").equals("false") && configGlobal.getString("modules.spawn").equals("true"))) {
			
			// Si el Comando es ejecutado por la Consola
			if (!(sender instanceof Player) && configGlobal.getString("modules.streams").equals("true")) {
				Bukkit.getConsoleSender().sendMessage(plugin.nombre+"No puedes ejecutar este comando desde la consola !!!");
				return false;
			} // FIN: Si el Comando es ejecutado por la Consola
			
			// Si el Comando es ejecutado por un Jugador
			else if ((sender instanceof Player) && configGlobal.getString("modules.streams").equals("true")) {
				Player jugador = (Player) sender;
				jugador.sendMessage(plugin.separador);
				jugador.sendMessage(plugin.nombre+"Visita los streams de :");
				jugador.sendMessage(plugin.nombre+"    "+ChatColor.GREEN+"pablom10 => "+ChatColor.YELLOW+" ");
				jugador.sendMessage(plugin.separador);
				return true;
			} // FIN: Si el Comando es ejecutado por un Jugador
			
			// Si el Comando no es ejecutado por la Consola ni un Jugador
			
		}
		
		return true;
		
	}

}
