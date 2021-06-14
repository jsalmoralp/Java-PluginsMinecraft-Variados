package jsalmoralp.allinone;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import jsalmoralp.allinone.comandos.ComandoConteoSurvival;
import jsalmoralp.allinone.comandos.ComandoHub;
import jsalmoralp.allinone.comandos.ComandoPrincipal;
import jsalmoralp.allinone.comandos.ComandoSpawn;
import jsalmoralp.allinone.comandos.ComandoStreams;
import jsalmoralp.allinone.eventos.EventoPlayerJoin;
import jsalmoralp.allinone.librerias.BaseArchivosDeConfiguracion;

public class Main extends JavaPlugin {

	// Acceso y manejo del archivo "plugin.yml"
	PluginDescriptionFile pdffile = getDescription();
	public String version = ChatColor.RED + "Version: " + pdffile.getVersion() + " ";
	public String nombre = ChatColor.GREEN + "[ " + pdffile.getName() + " ] " + ChatColor.WHITE;

	// Variables para los Archivos de Configuración
	FileConfiguration configMessages = null;
	File configMessagesFile = null;
	String configMessagesString = "messages.yml";
	FileConfiguration configSpawns = null;
	File configSpawnsFile = null;
	String configSpawnsString = "spawns.yml";
	FileConfiguration configReports = null;
	File configReportsFile = null;
	String configReportsString = "reports.yml";
	FileConfiguration configConteoSurvival = null;
	File configConteoSurvivalFile = null;
	String configConteoSurvivalString = "conteoSurvival.yml";

	// Otras variables
	public String rutaConfigGlobal;
	public String separador = ChatColor.YELLOW + "<------------------------->";

	// Importando Variables y Funciones de otras Clases
	private BaseArchivosDeConfiguracion baseConfigs;

	public Main(BaseArchivosDeConfiguracion baseConfigs) {
		this.baseConfigs = baseConfigs;
	}

	// Activación del Plugin
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(separador);
		Bukkit.getConsoleSender().sendMessage(nombre + "Inicializando ...");
		Bukkit.getConsoleSender().sendMessage(nombre + version);

		// Activación de registros
		registroDeConfiguraciones();
		registroDeComandos();
		registroDeEventos();

		Bukkit.getConsoleSender().sendMessage(
				nombre + "El plugin ha sido " + ChatColor.GREEN + "ACTIVADO" + ChatColor.WHITE + " correctamente !!!");
		Bukkit.getConsoleSender().sendMessage(separador);
	} // FIN: Activación del Plugin

	// Desactivación del Plugin
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(separador);
		Bukkit.getConsoleSender().sendMessage(nombre + "Desactivando ...");
		Bukkit.getConsoleSender().sendMessage(nombre + version);
		Bukkit.getConsoleSender().sendMessage(nombre + "El plugin ha sido " + ChatColor.GREEN + "DESACTIVADO"
				+ ChatColor.WHITE + " correctamente !!!");
		Bukkit.getConsoleSender().sendMessage(separador);
	} // FIN: Desactivación del Plugin

	// Registro de Configuraciones
	public void registroDeConfiguraciones() {
		// Configuraciones Globales
		File configGlobal = new File(this.getDataFolder(), "config.yml");
		rutaConfigGlobal = configGlobal.getPath();
		if (!configGlobal.exists()) {
			this.getConfig().options().copyDefaults(true);
			saveConfig();
		}

	} // FIN: Registro de Configuraciones

	// Registro de Comandos
	public void registroDeComandos() {
		// Comandos Globales
		this.getCommand("aio").setExecutor(new ComandoPrincipal(this));

		// Comandos de Configuración
		this.getCommand("hub").setExecutor(new ComandoHub(this));
		this.getCommand("spawn").setExecutor(new ComandoSpawn(this));

		// Comandos de Chat
		this.getCommand("streams").setExecutor(new ComandoStreams(this));

		// Conteos
		this.getCommand("cs").setExecutor(new ComandoConteoSurvival(this));

	} // FIN: Registro de Comandos

	// Registro de Eventos
	public void registroDeEventos() {
		PluginManager pm = getServer().getPluginManager();

		// Eventos de Entrada
		pm.registerEvents(new EventoPlayerJoin(this), this);

	} // FIN: Registro de Eventos

	/*
	 * Funciones para el funcionamiento de los Archivos de Configuración
	 */

	// Archivo "messages.yml"
	public FileConfiguration getMessagesConfig() {
		return baseConfigs.getOtherConfig(configMessages, configMessagesFile, configMessagesString);
	}

	public void reloadMessagesConfig() {
		baseConfigs.reloadOtherConfig(configMessages, configMessagesFile, configMessagesString);
	}

	public void saveMessagesConfig() {
		baseConfigs.saveOtherConfig(configMessages, configMessagesFile);
	}

	public void registerMessagesConfig() {
		baseConfigs.registerOtherConfig(configMessages, configMessagesFile, configMessagesString);
	}

	// Archivo "spawns.yml"
	public FileConfiguration getSpawnsConfig() {
		return baseConfigs.getOtherConfig(configSpawns, configSpawnsFile, configSpawnsString);
	}

	public void reloadSpawnsConfig() {
		baseConfigs.reloadOtherConfig(configSpawns, configSpawnsFile, configSpawnsString);
	}

	public void saveSpawnsConfig() {
		baseConfigs.saveOtherConfig(configSpawns, configSpawnsFile);
	}

	public void registerSpawnsConfig() {
		baseConfigs.registerOtherConfig(configSpawns, configSpawnsFile, configSpawnsString);
	}

	// Archivo "reports.yml"
	public FileConfiguration getReportsConfig() {
		return baseConfigs.getOtherConfig(configReports, configReportsFile, configReportsString);
	}

	public void reloadReportsConfig() {
		baseConfigs.reloadOtherConfig(configReports, configReportsFile, configReportsString);
	}

	public void saveReportsConfig() {
		baseConfigs.saveOtherConfig(configReports, configReportsFile);
	}

	public void registerReportsConfig() {
		baseConfigs.registerOtherConfig(configReports, configReportsFile, configReportsString);
	}

	// Archivo "conteoSurvival.yml"
	public FileConfiguration getConteoSurvivalConfig() {
		return baseConfigs.getOtherConfig(configConteoSurvival, configConteoSurvivalFile, configConteoSurvivalString);
	}

	public void reloadConteoSurvivalConfig() {
		baseConfigs.reloadOtherConfig(configConteoSurvival, configConteoSurvivalFile, configConteoSurvivalString);
	}

	public void saveConteoSurvivalConfig() {
		baseConfigs.saveOtherConfig(configConteoSurvival, configConteoSurvivalFile);
	}

	public void registerConteoSurvivalConfig() {
		baseConfigs.registerOtherConfig(configConteoSurvival, configConteoSurvivalFile, configConteoSurvivalString);
	}

	/*
	 * ::FIN:: Bases para el funcionamiento de los Archivos de Configuración
	 */

}
