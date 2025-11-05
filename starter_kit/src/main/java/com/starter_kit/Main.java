package com.starter_kit;

import org.bukkit.plugin.java.JavaPlugin;
import com.starter_kit.KitService.KitService;
import com.starter_kit.KitService.KitServiceImpl;
import com.starter_kit.commands.KitCommand;

public class Main extends JavaPlugin {
    private KitService kitService;
    private KitCommand kitCommand;

    @Override
    public void onEnable() {
        configureService();
        configureCommands();
        getLogger().info("Starter Kit iniciado.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Starter kit desactivado.");
    }

    private void configureService() {
        kitService = new KitServiceImpl();
    }

    private void configureCommands() {
        kitCommand = new KitCommand(kitService);
        getCommand("starterkit").setExecutor(kitCommand);
    }
}