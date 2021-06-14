package es.jsalmoralp.supremacy;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import es.jsalmoralp.supremacy.aggregates.FarmEvents;
import es.jsalmoralp.supremacy.aggregates.KillsForScoreboard;
import es.jsalmoralp.supremacy.aggregates.Menu;
import es.jsalmoralp.supremacy.automobsspawning.AutoSpawningRegenerate;
import es.jsalmoralp.supremacy.commands.CommandOPSupremacy;
import es.jsalmoralp.supremacy.features.PlayersListener;
import es.jsalmoralp.supremacy.utils.MySqlConnection;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	// Le damos acceso al archivo "plugin.yml"
	PluginDescriptionFile pdffile = getDescription();
	// Obtenemos algunas variables del archivo "plugin.yml"
	public String version = pdffile.getVersion();
	public String name = pdffile.getName();
	
	//Implementamos Plugins externos
	private static Economy econ = null;
	/*
	 * private static WorldGuard wGuard = null;
	 * private static WorldEdit wEdit = null;
	 */

	// Variable base que utilizaremos para realizar algunnas acciones con as
	// distintas clases que forman nuestro plugin
	public PluginManager pm = getServer().getPluginManager();

	// Creacion de variables para el Chat
	public String nameForChat = ChatColor.BLUE + "[ " + ChatColor.YELLOW + name + ChatColor.BLUE + " ]  ";
	public String spacingNewComponent = nameForChat + ChatColor.RED + "--->   ";
	public String doubleSpacingNewComponent = nameForChat + ChatColor.RED + "--- --->   ";
	public String done = "   " + ChatColor.GREEN + "   Hecho!";
	public String disabled = "   " + ChatColor.GRAY + "   Desactivado!";
	public String error = "   " + ChatColor.RED + "   Error!";
	public String spacer = ChatColor.RED + "<------------------------------>";
	public String lowSpacer = ChatColor.RED + "<--------------->";
	public String safeStop = ChatColor.GREEN + "Parada SEGURA Completa!";
	public String readAllConfigs = ChatColor.WHITE + "Carga de las Configuraciones disponibles:";
	public String readAllEvents = ChatColor.WHITE + "Carga de los Eventos disponibles:";
	public String readAllCommands = ChatColor.WHITE + "Carga de los Comandos disponibles:";

	// Variables para las Configs del Plugin
	FileConfiguration config = this.getCustomConfig();
	
	////////////////////////////////////////////////////////////////
	//////////// Variables y metodos para los Countdowns////////////
	////////////////////////////////////////////////////////////////
	
	// Countdown de abrir el Inventario Principal
	private ArrayList<Player> playersInCountdownOpenMenu;
	public void addPlayerInCountdownOpenMenu(Player player) {
		playersInCountdownOpenMenu.add(player);
	}
	public void removePlayerInCountdownOpenMenu(Player player) {
		playersInCountdownOpenMenu.remove(player);
	}
	public boolean isPlayerInCountdownOpenMenu(Player player) {
		if (playersInCountdownOpenMenu.contains(player)) {
			return true;
		} else {
			return false;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////// Metodos de inicio y parada del Plugin ///////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Metodo que ejecutara su contenido cuando el plugin se active/inicie
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(spacer);
		Bukkit.getConsoleSender().sendMessage(nameForChat + ChatColor.WHITE + "Se esta iniciando...");
		Bukkit.getConsoleSender().sendMessage(nameForChat + ChatColor.WHITE + "Version: " + ChatColor.GREEN
				+ version);

		// Activacion de los Registros
		Bukkit.getConsoleSender().sendMessage(nameForChat + ChatColor.WHITE + "Activacion de los Funcionalidades de este Plugin");
		showDependenciesIsActive();
		registerConfigs();
		registerCommands();
		registerEvents();
		database_connection();
		
		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage(nameForChat + ChatColor.RED + "   ----> " + ChatColor.GREEN
				+ "Carga Completa!" + ChatColor.RED + " <----");
		Bukkit.getConsoleSender().sendMessage(spacer);
	}

	// Metodo que ejecutara su contenido cuando el plugin se desactive/detenga
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(spacer);
		Bukkit.getConsoleSender().sendMessage(nameForChat + ChatColor.WHITE + "Se esta deteniendo...");
		Bukkit.getConsoleSender().sendMessage(nameForChat + ChatColor.WHITE + "Version: " + ChatColor.GREEN + version);
		
		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage(nameForChat + ChatColor.RED + "   ----> " + safeStop + ChatColor.RED + " <----");
		Bukkit.getConsoleSender().sendMessage(spacer);
	}

	
	////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////// Metodos de Registro ///////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Metodo que utilizaremos para ver las Dependencias de este Plugin, que
	// activaremos atraves del metodo "onEnable" de esta Clase
	public void showDependenciesIsActive() {
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
		Bukkit.getConsoleSender().sendMessage(nameForChat + "Dependencies:");
		if (setupEconomy()) {
			Bukkit.getConsoleSender().sendMessage(spacingNewComponent + "Plugin Vault" + done);
		} else {
			Bukkit.getConsoleSender().sendMessage(spacingNewComponent + "Plugin Vault" + disabled);
		}
	/*	if (setupWorldGuard()) {
			Bukkit.getConsoleSender().sendMessage(spacingNewComponent + "Plugin WorldGuard" + done);
		} else {
			Bukkit.getConsoleSender().sendMessage(spacingNewComponent + "Plugin WorldGuard" + disabled);
		} */
	/*	if (setupWorldEdit()) {
			Bukkit.getConsoleSender().sendMessage(spacingNewComponent + "Plugin WorldEdit" + done);
		} else {
			Bukkit.getConsoleSender().sendMessage(spacingNewComponent + "Plugin WorldEdit" + disabled);
		} */
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
	}
	
	// Metodo que utilizaremos para registrar la Config de este Plugin, que
	// activaremos atraves del metodo "onEnable" de esta Clase
	public void registerConfigs() {
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
		Bukkit.getConsoleSender().sendMessage(nameForChat + "Configs:");
		registerCustomConfig();
		registerCustomConfigMessages();
		registerCustomConfigMobsSpawns();
		Bukkit.getConsoleSender().sendMessage(spacingNewComponent + readAllConfigs + done);
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
	}

	// Metodo que utilizaremos para registrar los Comandos que queramos activar
	// atraves del metodo "onEnable"
	public void registerCommands() {
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
		Bukkit.getConsoleSender().sendMessage(nameForChat + "Comandos:");
		if (config.getString("Modules.commands.opsupremacy").equals("true")) {
			this.getCommand("opopsupremacy").setExecutor(new CommandOPSupremacy(this));
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ /opsupremacy ]" + done);
		} else if (config.getString("Modules.commands.opsupremacy").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ /opsupremacy ]" + disabled);
		} else if (!config.getString("Modules.commands.opsupremacy").equals("true") &&
				!config.getString("Modules.commands.opsupremacy").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ /opsupremacy ]" + error);
		}
		Bukkit.getConsoleSender().sendMessage(spacingNewComponent + readAllCommands + done);
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
	}

	// Metodo que utilizaremos para registrar los Eventos que queramos activar
	// atraves del metodo "onEnable"
	public void registerEvents() {
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
		Bukkit.getConsoleSender().sendMessage(nameForChat + "Eventos:");
		if (config.getString("Modules.aggregates.KillsForScoreboard").equals("true")) {
			pm.registerEvents(new KillsForScoreboard(this), this);
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ Scoreboard ]" + done);
		} else if (config.getString("Modules.aggregates.KillsForScoreboard").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ Scoreboard ]" + disabled);
		} else if (!config.getString("Modules.aggregates.KillsForScoreboard").equals("true") &&
				!config.getString("Modules.aggregates.KillsForScoreboard").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ Scoreboard ]" + error);
		}
		if (config.getString("Modules.aggregates.Menu").equals("true")) {
			pm.registerEvents(new Menu(), this);
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ Menu ]" + done);
		} else if (config.getString("Modules.aggregates.Menu").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ Menu ]" + disabled);
		} else if (!config.getString("Modules.aggregates.Menu").equals("true") &&
				!config.getString("Modules.aggregates.Menu").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ Menu ]" + error);
		}
		if (config.getString("Modules.aggregates.FarmEvents").equals("true")) {
			pm.registerEvents(new FarmEvents(), this);
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ FarmEvents ]" + done);
		} else if (config.getString("Modules.aggregates.FarmEvents").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ FarmEvents ]" + disabled);
		} else if (!config.getString("Modules.aggregates.FarmEvents").equals("true") &&
				!config.getString("Modules.aggregates.FarmEvents").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ FarmEvents ]" + error);
		}
		if (config.getString("Modules.features.PlayersListener").equals("true")) {
			pm.registerEvents(new PlayersListener(this), this);
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ PlayersListener ]" + done);
		} else if (config.getString("Modules.features.PlayersListener").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ PlayersListener ]" + disabled);
		} else if (!config.getString("Modules.features.PlayersListener").equals("true") &&
				!config.getString("Modules.features.PlayersListener").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ PlayersListener ]" + error);
		}
		Bukkit.getConsoleSender().sendMessage(spacingNewComponent + readAllEvents + done);
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
	}
	
	// Metodo que utilizaremos para registrar las Tareas Repetitivas que queramos activar
	// atraves del metodo "onEnable"
	public void registerRepTasks() {
		if (config.getString("Modules.features.AutoSpawning.general").equals("true")) {
			AutoSpawningRegenerate autoSpawningRegenerate = new AutoSpawningRegenerate(this, 72000);
			autoSpawningRegenerate.execution();		
		}
	}
	
	/*
	 * Metodos relacionados con la conexion a la Base de Datos
	 */
	public void database_connection() {
		if (config.getString("Config.database-connection").equalsIgnoreCase("mysql")) {
			MySqlConnection.makeDataBaseConnection();
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////// Metodos adicionales ///////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}
	
	public Economy getEconomy() {
		return Main.econ;
	}
	
/*	private boolean setupWorldGuard() {
		if (getServer().getPluginManager().getPlugin("WorldGuard") == null) {
			return false;
		}
		RegisteredServiceProvider<WorldGuard> rsp = getServer().getServicesManager().getRegistration(WorldGuard.class);
		if (rsp == null) {
			return false;
		}
		wGuard = rsp.getProvider();
		return wGuard != null;
	}
	
	public WorldGuardPlugin getWorldGuard() {
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}
		return (WorldGuardPlugin) plugin;
	} */
	
/*	private boolean setupWorldEdit() {
		if (getServer().getPluginManager().getPlugin("WorldEdit") == null) {
			return false;
		}
		RegisteredServiceProvider<WorldEdit> rsp = getServer().getServicesManager().getRegistration(WorldEdit.class);
		if (rsp == null) {
			return false;
		}
		wEdit = rsp.getProvider();
		return wEdit != null;
	}
	
	public WorldEditPlugin getWorldEdit() {
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
		if (plugin == null || !(plugin instanceof WorldEditPlugin)) {
			return null;
		}
		return (WorldEditPlugin) plugin;
	} */
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////// Archivos de Configuracion /////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////////
	//// Archivo de Configuracion  ---> config.yml
	///////////////////////////////////
	private FileConfiguration customConfig = null;
	private File customConfigFile = null;

	public FileConfiguration getCustomConfig() {
        if (customConfig == null) {
            reloadCustomConfig();
        }
        return customConfig;
    }
	
	public void reloadCustomConfig() {
        if (customConfigFile == null) {
        customConfigFile = new File(this.getDataFolder(), "config.yml");
        }
        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
        
        // Look for defaults in the jar
        Reader defConfigStream;
        try {
            defConfigStream = new InputStreamReader(this.getResource("config.yml"), "UTF8");
            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                customConfig.setDefaults(defConfig);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    public void saveCustomConfig() {
        try {
            customConfig.save(customConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void registerCustomConfig() {
        customConfigFile = new File(this.getDataFolder(), "config.yml");
        if (!customConfigFile.exists()) {
        	this.getCustomConfig().options().copyDefaults(true);
        	saveCustomConfig();
        }
    }
    
    
	///////////////////////////////////
	//// Archivo de Configuracion  ---> messages/es.yml
	///////////////////////////////////
    private FileConfiguration customConfigMessages = null;
	private File customConfigMessagesFile = null;
	private String optionMessagesYML = getCustomConfig().getString("Config.lang");

	public FileConfiguration getCustomConfigMessages() {
        if (customConfigMessages == null) {
            reloadCustomConfigMessages();
        }
        return customConfigMessages;
    }
	
	public void reloadCustomConfigMessages() {
        if (customConfigMessagesFile == null) {
        customConfigMessagesFile = new File(this.getDataFolder()+"/messages/", optionMessagesYML+".yml");
        }
        customConfigMessages = YamlConfiguration.loadConfiguration(customConfigMessagesFile);
        
        // Look for defaults in the jar
        Reader defConfigMessagesStream;
        try {
            defConfigMessagesStream = new InputStreamReader(this.getResource("messages/"+optionMessagesYML+".yml"), "UTF8");
            if (defConfigMessagesStream != null) {
                YamlConfiguration defConfigMessages = YamlConfiguration.loadConfiguration(defConfigMessagesStream);
                customConfigMessages.setDefaults(defConfigMessages);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    public void saveCustomConfigMessages() {
        try {
            customConfigMessages.save(customConfigMessagesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void registerCustomConfigMessages() {
        customConfigMessagesFile = new File(this.getDataFolder()+"/messages/", optionMessagesYML+".yml");
        if (!customConfigMessagesFile.exists()) {
        	this.getCustomConfigMessages().options().copyDefaults(true);
        	saveCustomConfigMessages();
        }
    }
    
    
	///////////////////////////////////
	//// Archivo de Configuracion  ---> supremacy/mobs/spawns.yml
	///////////////////////////////////
    private FileConfiguration customConfigMobsSpawns = null;
	private File customConfigMobsSpawnsFile = null;

	public FileConfiguration getCustomConfigMobsSpawns() {
        if (customConfigMobsSpawns == null) {
            reloadCustomConfigMobsSpawns();
        }
        return customConfigMobsSpawns;
    }
	
	public void reloadCustomConfigMobsSpawns() {
        if (customConfigMobsSpawnsFile == null) {
        customConfigMobsSpawnsFile = new File(this.getDataFolder()+"/supremacy/mobs/", "spawns.yml");
        }
        customConfigMobsSpawns = YamlConfiguration.loadConfiguration(customConfigMobsSpawnsFile);
        
        // Look for defaults in the jar
        Reader defConfigMobsSpawnsStream;
        try {
            defConfigMobsSpawnsStream = new InputStreamReader(this.getResource("supremacy/mobs/spawns.yml"), "UTF8");
            if (defConfigMobsSpawnsStream != null) {
                YamlConfiguration defConfigMobsSpawns = YamlConfiguration.loadConfiguration(defConfigMobsSpawnsStream);
                customConfigMobsSpawns.setDefaults(defConfigMobsSpawns);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    public void saveCustomConfigMobsSpawns() {
        try {
            customConfigMobsSpawns.save(customConfigMobsSpawnsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void registerCustomConfigMobsSpawns() {
        customConfigMobsSpawnsFile = new File(this.getDataFolder()+"/supremacy/mobs/spawns.yml");
        if (!customConfigMobsSpawnsFile.exists()) {
        	this.getCustomConfigMobsSpawns().options().copyDefaults(true);
        	saveCustomConfigMobsSpawns();
        }
    }
    
    
}
