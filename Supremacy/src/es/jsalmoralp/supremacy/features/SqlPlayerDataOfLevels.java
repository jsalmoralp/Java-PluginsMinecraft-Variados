package es.jsalmoralp.supremacy.features;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SqlPlayerDataOfLevels {

	public static boolean playerExists(Connection connection, UUID uuid) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM PlayerLevel WHERE (UUID=?)");
			statement.setString(1, uuid.toString());
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			
		}
		return false;
	}
	
	public static boolean createPlayer(Connection connection, UUID uuid, String name) {
		try {
			if (!playerExists(connection, uuid)) {
				PreparedStatement statement = connection.prepareStatement("INSERT INTO PlayerLevel VALUE (?,?,?)");
				statement.setString(1, uuid.toString());
				statement.setString(2, name);
				statement.setInt(3, 0);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			
		}
		return false;
	}
	
	public static int getLevel(Connection connection, UUID uuid) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM PlayerLevel WHERE (UUID=?)");
			statement.setString(1, uuid.toString());
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				int level = result.getInt("level");
				return level;
			}
		} catch (SQLException e) {
			
		}
		return 0;
	}
	
	public static void setLevel(Connection connection, UUID uuid, int amount) {
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE PlayerLevel SET level=? WHERE (UUID=?)");
			statement.setInt(1, amount);
			statement.setString(2, uuid.toString());
			statement.executeUpdate();
		} catch (SQLException e) {
			
		}
	}
}
