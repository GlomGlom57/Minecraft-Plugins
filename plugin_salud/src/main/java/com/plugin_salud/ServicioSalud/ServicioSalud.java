package com.plugin_salud.ServicioSalud;

import org.bukkit.entity.Player;

public interface ServicioSalud {
	void regenerarSalud(Player jugador, int cantidad);
}