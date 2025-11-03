package com.plugin_salud;

import org.bukkit.plugin.java.JavaPlugin;

import com.plugin_salud.ServicioSalud.ServicioSalud;
import com.plugin_salud.ServicioSalud.ServicioSaludImpl;
import com.plugin_salud.commands.ComandoSalud;

public class App extends JavaPlugin{
    // Generar comando de salud
    private ServicioSalud servicioSalud;
    private ComandoSalud comandoSalud;

    // Cuando inicia el servidor
    @Override // Para indicar que se están sobreescribiendo métodos de JavaPlugin
    public void onEnable() {
        configurarServicios();
        registrarComandos();
        getLogger().info("Plugin iniciado");
    }

    // El servidor finaliza
    @Override
    public void onDisable() {
        getLogger().info("Plugin cerrado");
    }

    // Otros
    private void configurarServicios() {
        servicioSalud = new ServicioSaludImpl();
    }

    private void registrarComandos() {
        comandoSalud = new ComandoSalud(servicioSalud);
        getCommand("salud").setExecutor(comandoSalud);
    }
}