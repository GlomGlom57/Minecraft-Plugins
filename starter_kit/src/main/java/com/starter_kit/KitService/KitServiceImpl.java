package com.starter_kit.KitService;

import org.bukkit.Material;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.sql.*;
import java.util.UUID;

public class KitServiceImpl implements KitService {
	private void giveStarterKit(Player player) {
		final ItemStack swordW = new ItemStack(Material.WOODEN_SWORD, 1); // Wooden Sword
		final ItemStack pickaxeW = new ItemStack(Material.WOODEN_PICKAXE, 1); // Wooden pickaxe
		final ItemStack breads = new ItemStack(Material.BREAD, 5); // 5 breads
		Inventory pInventory = player.getInventory();

		pInventory.addItem(swordW);
		pInventory.addItem(pickaxeW);
		pInventory.addItem(breads);

		player.sendMessage(ChatColor.GREEN + "Starter kit given.");
	}

	private void calcWait(Player player, long diffTime, long waitMilis) {
		final long milisLeft = waitMilis - diffTime;
		final long secsLeft = milisLeft / 1000;
		final long minutesLeft = secsLeft / 60;
		final long secsF = secsLeft % 60;

		player.sendMessage(ChatColor.DARK_RED + "You must wait " + minutesLeft + " minutes and " + secsF + " seconds.");
	}

	@Override
	public void setKit(Player player) {
		final String dbURL = "jdbc:sqlite:dataKit.db";
		final String createTable = "CREATE TABLE IF NOT EXISTS lastKit(" +
		"id TEXT PRIMARY KEY, " + 
		"time TIMESTAMP NOT NULL)";
		final String insertSql = "INSERT INTO lastkit(id, time) VALUES(?, ?)";
		final String selectSql = "SELECT time from lastKit WHERE id = ?";
		final String updateSql = "UPDATE lastkit SET time = ? WHERE id = ?";
		final long waitMilis = 3600_000L;

		final UUID uuid = player.getUniqueId(); // Player UUID
		final String idString = uuid.toString(); // Convert to string
		final Timestamp currTime = new Timestamp(System.currentTimeMillis()); // Current time
	
		try (Connection dbConn = DriverManager.getConnection(dbURL)) { // Connect to database
			// Create table if does not exists
			try (Statement stmt = dbConn.createStatement()) { 
				stmt.execute(createTable);
			}

			// Select UUID
			try (PreparedStatement psSelect = dbConn.prepareStatement(selectSql)) {
				psSelect.setString(1, idString);

				try (ResultSet result = psSelect.executeQuery()) {
					if (result.next()) { // If player register exists
						final Timestamp lastTime = result.getTimestamp("time");
						final long difference = currTime.getTime() - lastTime.getTime();

						if (difference >= waitMilis) { // If difference exceeds 1 hour
							try (PreparedStatement psUpdate = dbConn.prepareStatement(updateSql)) {
								psUpdate.setTimestamp(1, currTime);
								psUpdate.setString(2, idString);
								psUpdate.executeUpdate();

								giveStarterKit(player);
							}
						} else {
							calcWait(player, difference, waitMilis);
						}
					} else { // If not, create
						try (PreparedStatement psInsert = dbConn.prepareStatement(insertSql)) {
							psInsert.setString(1, idString);
							psInsert.setTimestamp(2, currTime);
							psInsert.executeUpdate();
						
							giveStarterKit(player);
						}
					}
				}
			}
		}

		catch (SQLException e) {
			player.sendMessage(ChatColor.RED + "Error connecting to DB: " + e.toString());
		}
	}
}