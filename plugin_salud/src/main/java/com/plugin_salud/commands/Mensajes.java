package com.plugin_prueba.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Mensajes {
	public static void enviarMensaje(CommandSender sender, String message) {
		if (sender instanceof Player) {
			Player jugador = (Player) sender;
			jugador.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
		}

		else {
			sender.sendMessage(message);
		}
	}
}
