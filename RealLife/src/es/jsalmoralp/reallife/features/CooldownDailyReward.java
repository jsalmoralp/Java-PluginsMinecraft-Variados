package es.jsalmoralp.reallife.features;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import es.jsalmoralp.reallife.Main;

public class CooldownDailyReward {
	private Main main;
	public CooldownDailyReward(Main main) {
		this.main = main;
	}
	
	public String getCooldown(Player player) {
		FileConfiguration configRealLifePlayersRewards = main.getCustomConfigRealLifePlayersRewards();
		String pathtime = "Players."+player.getUniqueId()+".cooldown-dailyReward"; 
		if(configRealLifePlayersRewards.contains(pathtime)){
		    String timecooldownString = configRealLifePlayersRewards.getString(pathtime);
		    long timecooldown = Long.valueOf(timecooldownString);
		    long millis = System.currentTimeMillis();
	        long cooldown = 86400; //En Segundos
	        long cooldownmil = cooldown*1000;
	       
	        long espera = millis - timecooldown;
	        long esperaDiv = espera/1000;
	        long esperatotalseg = cooldown - esperaDiv;
	        long esperatotalmin = esperatotalseg/60;
	        long esperatotalhour = esperatotalmin/60;
	        if(((timecooldown + cooldownmil) > millis) && (timecooldown != 0)){                
	           if(esperatotalseg > 59){
	               esperatotalseg = esperatotalseg - 60*esperatotalmin;
	           }
	           String time = "";
	           if(esperatotalseg != 0){
	              time = esperatotalseg+"s";
	           }
	                           
	           if(esperatotalmin > 59){
	               esperatotalmin = esperatotalmin - 60*esperatotalhour;
	           }   
	           if(esperatotalmin > 0){
	               time = esperatotalmin+"min"+" "+time;
	           }
	           
	           if(esperatotalhour > 0){
	               time = esperatotalhour+ "h"+" " + time;
	           }
	           //Aun no se termina el cooldown
	           return time;
	        }else{
	            //Ya se termino el cooldown
	        	return "-1";
	        }
		}else{
		    //Usa el comando por primera vez, ya que no existe el path en la configRealLifeConfig
		   return "-1";
		}
	}
	
} 
