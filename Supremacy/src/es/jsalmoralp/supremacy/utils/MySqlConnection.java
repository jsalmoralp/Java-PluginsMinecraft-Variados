package es.jsalmoralp.supremacy.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import es.jsalmoralp.supremacy.Main;

public class MySqlConnection {
	
	private static Connection connection;
	
	private String host;
	private int port;
	private String database;
	private String user;
	private String password;
	
	private static Main main;
	static FileConfiguration config = main.getCustomConfig();
	
	public MySqlConnection(String host, int port, String database, String user, String password) {
		this.host = host;
		this.port = port;
		this.database = database;
		this.user = user;
		this.password = password;
		
		try {
			synchronized(this) {
				if (connection != null && !connection.isClosed()) {
					Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[&cSupremacy&4] &c Error al conectar a MySQL!"));
					return;
				}
				Class.forName("com.mysql.jdbc.Driver");
				MySqlConnection.connection = DriverManager.getConnection("jdbc:mysql://"+this.host+":"+this.port+"/"+this.database, this.user, this.password);
				Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&4[&cSupremacy&4] &c Plugin conectado a MySQL!"));
			}
			
		} catch(SQLException e) {
			
		} catch(ClassNotFoundException e) {
			
		}
	}

	public static MySqlConnection makeDataBaseConnection() {
		String host = config.getString("Config.database-connection.host");
		int port = config.getInt("Config.database-connection.port");
		String database = config.getString("Config.database-connection.database");
		String user = config.getString("Config.database-connection.user");
		String password = config.getString("Config.database-connection.password");
		MySqlConnection mysqlConnection = new MySqlConnection(host, port, database, user, password);
		
		return mysqlConnection;
	}
	
	public Connection getMySQLConnection() {
		return getConnection();
	}
	
	
	public static Connection getConnection() {
		return connection;
	}
	public static void setConnection(Connection connection) {
		MySqlConnection.connection = connection;
	}
}
