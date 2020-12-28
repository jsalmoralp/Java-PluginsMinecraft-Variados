package jsalmoralp.allinone.librerias;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import jsalmoralp.allinone.Main;

public class BaseArchivosDeConfiguracion {
	
	// Importando Variables y Funciones de otras Clases
	private Main plugin;
	public BaseArchivosDeConfiguracion(Main plugin) {
		this.plugin = plugin;
	}
	
	/*
	 * Bases para el funcionamiento de los Archivos de Configuración
	 */
	
	// Función GET
	public FileConfiguration getOtherConfig(FileConfiguration variableFileConfiguration, File variableFile, String nombreArchivoConfiguracion){
		if(variableFileConfiguration == null){
			reloadOtherConfig(variableFileConfiguration, variableFile, nombreArchivoConfiguracion);
		}
		return variableFileConfiguration;
	}
	
	// Función RELOAD
	public void reloadOtherConfig(FileConfiguration variableFileConfiguration, File variableFile, String nombreArchivoConfiguracion){
		if(variableFileConfiguration == null){
			variableFile = new File(plugin.getDataFolder(), nombreArchivoConfiguracion);
		}
		variableFileConfiguration = YamlConfiguration.loadConfiguration(variableFile);
		Reader defConfigStream;
		try{
			defConfigStream = new InputStreamReader(plugin.getResource(nombreArchivoConfiguracion),"UTF8");
			if(defConfigStream != null){
				YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
				variableFileConfiguration.setDefaults(defConfig);
			}			
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}
	
	// Función SAVE
	public void saveOtherConfig(FileConfiguration variableFileConfiguration, File variableFile){
		try{
			variableFileConfiguration.save(variableFile);			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	// Función REGISTER
	public void registerOtherConfig(FileConfiguration variableFileConfiguration, File variableFile, String nombreArchivoConfiguracion){
		variableFile = new File(plugin.getDataFolder(),"messages.yml");
		if(!variableFile.exists()){
			this.getOtherConfig(variableFileConfiguration, variableFile, nombreArchivoConfiguracion).options().copyDefaults(true);
			saveOtherConfig(variableFileConfiguration, variableFile);
		}
	}
	
	/*::FIN::
	 * Bases para el funcionamiento de los Archivos de Configuración
	 */

}
