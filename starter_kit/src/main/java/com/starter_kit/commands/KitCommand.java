package com.starter_kit.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.starter_kit.KitService.KitService;

public class KitCommand implements CommandExecutor {
	private final KitService kitService;

	public KitCommand(KitService kitService) {
		this.kitService = kitService;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("El comando debe ser enviado por un jugador.");
			return false;
		}

		// Cast sender to player
		Player player = (Player) sender;
		
		if (command.getName().equalsIgnoreCase("starterkit")) {
			kitService.setKit(player);
		}
		return true;
	}
}