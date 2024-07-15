package ru.nwtls.simplertpplugin;

import org.bukkit.plugin.java.JavaPlugin;
import ru.nwtls.simplertpplugin.commands.RandomTeleportCommand;

public final class SimpleRtpPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("rtp").setExecutor(new RandomTeleportCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
