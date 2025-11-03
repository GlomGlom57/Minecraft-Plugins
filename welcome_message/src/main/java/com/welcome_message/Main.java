package com.welcome_message;

import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;

import org.bukkit.ChatColor;

public class Main extends JavaPlugin implements Listener{
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info(ChatColor.DARK_PURPLE + "Welcome_Message iniciado!");
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.DARK_PURPLE + "Welcome_Message cerrado.");
    }

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Bienvenido, " + player.getName() + ".");

        event.setJoinMessage(null);

        final String connMesg = ChatColor.GREEN + player.getName() + " se ha conectado al servidor.";
        for (Player p : player.getServer().getOnlinePlayers()) {
            if (!p.equals(player))
                p.sendMessage(connMesg);
        }
    }
}