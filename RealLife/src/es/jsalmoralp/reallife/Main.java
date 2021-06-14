package es.jsalmoralp.reallife;

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

import es.jsalmoralp.reallife.aggregates.KillsForScoreboard;
import es.jsalmoralp.reallife.aggregates.KillsWithReward;
import es.jsalmoralp.reallife.commands.CommandRealLife;

public class Main extends JavaPlugin {
	// Le damos acceso al archivo "plugin.yml"
	PluginDescriptionFile pdffile = getDescription();
	// Obtenemos algunas variables del archivo "plugin.yml"
	public String version = pdffile.getVersion();
	public String name = pdffile.getName();

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

	// Variable para las Configs del Plugin
	FileConfiguration config = this.getCustomConfig();

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

	// Metodo que utilizaremos para registrar la Config de esta Clase, que
	// activaremos atraves del metodo "onEnable" de esta Clase
	public void registerConfigs() {
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
		Bukkit.getConsoleSender().sendMessage(nameForChat + "Configs:");
		registerCustomConfig();
		registerCustomConfigMessages();
		registerCustomConfigRealLifeConfig();
		registerCustomConfigRealLifePlayersKills();
		registerCustomConfigRealLifePlayersRewards();
		Bukkit.getConsoleSender().sendMessage(spacingNewComponent + readAllConfigs + done);
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
	}

	// Metodo que utilizaremos para registrar los Comandos que queramos activar
	// atraves del metodo "onEnable"
	public void registerCommands() {
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
		Bukkit.getConsoleSender().sendMessage(nameForChat + "Comandos:");
		if (config.getString("Modules.commands.realLife").equals("true")) {
			this.getCommand("reallife").setExecutor(new CommandRealLife(this));
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ /realLife ]" + done);
		} else if (config.getString("Modules.commands.realLife").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ /realLife ]" + disabled);
		} else if (!config.getString("Modules.commands.realLife").equals("true") &&
				!config.getString("Modules.commands.realLife").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ /realLife ]" + error);
		}
		Bukkit.getConsoleSender().sendMessage(spacingNewComponent + readAllCommands + done);
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
	}

	// Metodo que utilizaremos para registrar los Eventos que queramos activar
	// atraves del metodo "onEnable"
	public void registerEvents() {
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
		Bukkit.getConsoleSender().sendMessage(nameForChat + "Eventos:");
		if (config.getString("Modules.Survival.events").equals("true")) {
			pm.registerEvents(new KillsForScoreboard(this), this);
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ Survival ]" + done);
		} else if (config.getString("Modules.Survival.events").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ Survival ]" + disabled);
		} else if (!config.getString("Modules.Survival.events").equals("true") &&
				!config.getString("Modules.Survival.events").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ Survival ]" + error);
		}
		if (config.getString("Modules.Survival.events").equals("true")) {
			pm.registerEvents(new KillsWithReward(this), this);
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ Survival ]" + done);
		} else if (config.getString("Modules.Survival.events").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ Survival ]" + disabled);
		} else if (!config.getString("Modules.Survival.events").equals("true") &&
				!config.getString("Modules.Survival.events").equals("false")) {
			Bukkit.getConsoleSender().sendMessage(doubleSpacingNewComponent + ChatColor.AQUA + "[ Survival ]" + error);
		}
		Bukkit.getConsoleSender().sendMessage(spacingNewComponent + readAllEvents + done);
		Bukkit.getConsoleSender().sendMessage(nameForChat + lowSpacer);
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
    
    
	///////////////////////////////////
	//// Archivo de Configuracion  ---> realLife/config.yml
	///////////////////////////////////
    private FileConfiguration customConfigRealLifeConfig = null;
	private File customConfigRealLifeConfigFile = null;

	public FileConfiguration getCustomConfigRealLifeConfig() {
        if (customConfigRealLifeConfig == null) {
            reloadCustomConfigRealLifeConfig();
        }
        return customConfigRealLifeConfig;
    }
	
	public void reloadCustomConfigRealLifeConfig() {
        if (customConfigRealLifeConfigFile == null) {
        customConfigRealLifeConfigFile = new File(this.getDataFolder()+"/realLife/", "config.yml");
        }
        customConfigRealLifeConfig = YamlConfiguration.loadConfiguration(customConfigRealLifeConfigFile);
        
        // Look for defaults in the jar
        Reader defConfigRealLifeConfigStream;
        try {
            defConfigRealLifeConfigStream = new InputStreamReader(this.getResource("realLife/config.yml"), "UTF8");
            if (defConfigRealLifeConfigStream != null) {
                YamlConfiguration defConfigRealLifeConfig = YamlConfiguration.loadConfiguration(defConfigRealLifeConfigStream);
                customConfigRealLifeConfig.setDefaults(defConfigRealLifeConfig);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    public void saveCustomConfigRealLifeConfig() {
        try {
            customConfigRealLifeConfig.save(customConfigRealLifeConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void registerCustomConfigRealLifeConfig() {
        customConfigRealLifeConfigFile = new File(this.getDataFolder()+"/realLife/", "config.yml");
        if (!customConfigRealLifeConfigFile.exists()) {
        	this.getCustomConfigRealLifeConfig().options().copyDefaults(true);
        	saveCustomConfigRealLifeConfig();
        }
    }
    
    
	///////////////////////////////////
	//// Archivo de Configuracion  ---> realLife/players/kills.yml
	///////////////////////////////////
    private FileConfiguration customConfigRealLifePlayersKills = null;
	private File customConfigRealLifePlayersKillsFile = null;

	public FileConfiguration getCustomConfigRealLifePlayersKills() {
        if (customConfigRealLifePlayersKills == null) {
            reloadCustomConfigRealLifePlayersKills();
        }
        return customConfigRealLifePlayersKills;
    }
	
	public void reloadCustomConfigRealLifePlayersKills() {
        if (customConfigRealLifePlayersKillsFile == null) {
        customConfigRealLifePlayersKillsFile = new File(this.getDataFolder()+"/realLife/players/", "kills.yml");
        }
        customConfigRealLifePlayersKills = YamlConfiguration.loadConfiguration(customConfigRealLifePlayersKillsFile);
        
        // Look for defaults in the jar
        Reader defConfigRealLifePlayersKillsStream;
        try {
            defConfigRealLifePlayersKillsStream = new InputStreamReader(this.getResource("realLife/players/kills.yml"), "UTF8");
            if (defConfigRealLifePlayersKillsStream != null) {
                YamlConfiguration defConfigRealLifePlayersKills = YamlConfiguration.loadConfiguration(defConfigRealLifePlayersKillsStream);
                customConfigRealLifePlayersKills.setDefaults(defConfigRealLifePlayersKills);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    public void saveCustomConfigRealLifePlayersKills() {
        try {
            customConfigRealLifePlayersKills.save(customConfigRealLifePlayersKillsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void registerCustomConfigRealLifePlayersKills() {
        customConfigRealLifePlayersKillsFile = new File(this.getDataFolder()+"/realLife/players/", "kills.yml");
        if (!customConfigRealLifePlayersKillsFile.exists()) {
        	this.getCustomConfigRealLifePlayersKills().options().copyDefaults(true);
        	saveCustomConfigRealLifePlayersKills();
        }
    }
    
    
	///////////////////////////////////
	//// Archivo de Configuracion  ---> realLife/players/rewards.yml
	///////////////////////////////////
    private FileConfiguration customConfigRealLifePlayersRewards = null;
	private File customConfigRealLifePlayersRewardsFile = null;

	public FileConfiguration getCustomConfigRealLifePlayersRewards() {
        if (customConfigRealLifePlayersRewards == null) {
            reloadCustomConfigRealLifePlayersRewards();
        }
        return customConfigRealLifePlayersRewards;
    }
	
	public void reloadCustomConfigRealLifePlayersRewards() {
        if (customConfigRealLifePlayersRewardsFile == null) {
        customConfigRealLifePlayersRewardsFile = new File(this.getDataFolder()+"/realLife/players/", "rewards.yml");
        }
        customConfigRealLifePlayersRewards = YamlConfiguration.loadConfiguration(customConfigRealLifePlayersRewardsFile);
        
        // Look for defaults in the jar
        Reader defConfigRealLifePlayersRewardsStream;
        try {
            defConfigRealLifePlayersRewardsStream = new InputStreamReader(this.getResource("realLife/players/rewards.yml"), "UTF8");
            if (defConfigRealLifePlayersRewardsStream != null) {
                YamlConfiguration defConfigRealLifePlayersRewards = YamlConfiguration.loadConfiguration(defConfigRealLifePlayersRewardsStream);
                customConfigRealLifePlayersRewards.setDefaults(defConfigRealLifePlayersRewards);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    
    public void saveCustomConfigRealLifePlayersRewards() {
        try {
            customConfigRealLifePlayersRewards.save(customConfigRealLifePlayersRewardsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void registerCustomConfigRealLifePlayersRewards() {
        customConfigRealLifePlayersRewardsFile = new File(this.getDataFolder()+"/realLife/players/", "rewards.yml");
        if (!customConfigRealLifePlayersRewardsFile.exists()) {
        	this.getCustomConfigRealLifePlayersRewards().options().copyDefaults(true);
        	saveCustomConfigRealLifePlayersRewards();
        }
    }
    
	
}
