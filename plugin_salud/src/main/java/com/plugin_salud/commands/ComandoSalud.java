package com.plugin_salud.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.plugin_salud.ServicioSalud.ServicioSalud;

public class ComandoSalud implements CommandExecutor {
	// Generar servicio de salud
	private final ServicioSalud servicioSalud;

	public ComandoSalud(ServicioSalud servicioSalud) {
		this.servicioSalud = servicioSalud;
	}

	// Recibir comandos
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// Verificar si el comando fue enviado por un jugador
		if (!(sender instanceof Player)) {
			sender.sendMessage("El comando debe ser enviado por un jugador.");
			return true;
		}
		
		// Castear jugador
		Player jugador = (Player) sender;

		// Verificar comando ignorando el case
		if (command.getName().equalsIgnoreCase("salud")) {
			if (args.length == 0)
				servicioSalud.regenerarSalud(jugador, 20);

			else if (args.length > 1) {
				Mensajes.enviarMensaje(sender, "&4Exceso de parámetros.\n/salud <cantidad>");
			}
			else {
				try {
					int cantidad = Integer.parseInt(args[0]);
					servicioSalud.regenerarSalud(jugador, cantidad);
				}
				catch (Exception e) {
					Mensajes.enviarMensaje(sender, "&4Debe ingresar caracteres numéricos.");
				}
			}
		}

		return false;
	}
}
