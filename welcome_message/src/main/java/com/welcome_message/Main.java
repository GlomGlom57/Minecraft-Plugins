package com.welcome_message;

import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.ChatColor;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Welcome_Message iniciado!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Welcome_Message cerrado.");
    }

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Welcome, " + player.getName() + "!");
        event.setJoinMessage(ChatColor.GREEN + player.getName() + " se ha conectado al servidor.");
    }
}