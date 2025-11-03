package com.plugin_salud.ServicioSalud;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class ServicioSaludImpl implements ServicioSalud {
	@Override
	public void regenerarSalud(Player jugador, int cantidad) {
		// final: No va a cambiar (constante)
		final double maxHealth = jugador.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue();
		double actual = jugador.getHealth();

		if (actual + cantidad >= maxHealth) jugador.setHealth(maxHealth);
		else jugador.setHealth(actual + cantidad);
		
		final int nueva = (int) jugador.getHealth() / 2;

		jugador.sendMessage("Tu vida se regeneró y ahora tienes " + nueva + " corazones.");
	}
}
