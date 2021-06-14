package es.jsalmoralp.trollolo;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import es.jsalmoralp.trollolo.aggregates.InventoryGUI;
import es.jsalmoralp.trollolo.commands.CommandTroll;

public class Main extends JavaPlugin {
	// Le damos acceso al archivo "plugin.yml"
	PluginDescriptionFile pdffile = getDescription();
	// Obtenemos algunas variables del archivo "plugin.yml"
	public String version = pdffile.getVersion();
	public String name = pdffile.getName();

	// Variable base que utilizaremos para realizar algunnas acciones con as
	// distintas clases que forman nuestro plugin
	public PluginManager pm = getServer().getPluginManager();

	// Variable para las Configs del Plugin
	FileConfiguration config = this.getCustomConfig();
	
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
		registerConfigs();
		registerCommands();
		registerEvents();
		
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
	
	// Metodo que utilizaremos para registrar la Config de este Plugin, que
	// activaremos atraves del metodo "onEnable" de esta Clase
	public void registerConfigs() {
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
		Bukkit.getConsoleSender().sendMessage(nameForChat + "Configs:");
		registerCustomConfig();
		registerCustomConfigMessages();
		Bukkit.getConsoleSender().sendMessage(spacingNewComponent + readAllConfigs + done);
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
	}

	// Metodo que utilizaremos para registrar los Comandos que queramos activar
	// atraves del metodo "onEnable"
	public void registerCommands() {
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
		Bukkit.getConsoleSender().sendMessage(nameForChat + "Comandos:");
		if (config.getString("Modules.commands.troll").equals("true")) {
			this.getCommand("troll").setExecutor(new CommandTroll(this));
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ /troll ]" + done);
		} else if (config.getString("Modules.commands.supremacy").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ /troll ]" + disabled);
		} else if (!config.getString("Modules.commands.supremacy").equals("true") &&
				!config.getString("Modules.commands.supremacy").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ /troll ]" + error);
		}
		Bukkit.getConsoleSender().sendMessage(spacingNewComponent + readAllCommands + done);
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
	}

	// Metodo que utilizaremos para registrar los Eventos que queramos activar
	// atraves del metodo "onEnable"
	public void registerEvents() {
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
		Bukkit.getConsoleSender().sendMessage(nameForChat + "Eventos:");
		if (config.getString("Modules.features.IventoryGUI").equals("true")) {
			pm.registerEvents(new InventoryGUI(this), this);
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ InventoruGUI ]" + done);
		} else if (config.getString("Modules.features.InventoruGUI").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ InventoruGUI ]" + disabled);
		} else if (!config.getString("Modules.features.InventoruGUI").equals("true") &&
				!config.getString("Modules.features.InventoruGUI").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ InventoruGUI ]" + error);
		}
		Bukkit.getConsoleSender().sendMessage(spacingNewComponent + readAllEvents + done);
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
	}
	
	// Metodo que utilizaremos para registrar las Tareas Repetitivas que queramos activar
	// atraves del metodo "onEnable"
	public void registerRepTasks() {
	/*	if (config.getString("Modules.features.AutoSpawning.general").equals("true")) {
			AutoSpawningRegenerate autoSpawningRegenerate = new AutoSpawningRegenerate(this, 72000);
			autoSpawningRegenerate.execution();		
		}*/
	}
	
	
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
}
