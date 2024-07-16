package ru.nwtls.simplertpplugin;

import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.paper.PaperCommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
//import org.incendo.cloud.execution.ExecutionCoordinator;
//import org.incendo.cloud.paper.LegacyPaperCommandManager;
//import org.incendo.cloud.paper.PaperCommandManager;
import ru.nwtls.simplertpplugin.commands.RandomTeleportCommand;

import java.util.function.Function;

public final class SimpleRtpPlugin extends JavaPlugin {
    PaperCommandManager<CommandSender> commandManager;
    @Override
    public void onEnable() {
        // Plugin startup logic

//        PaperCommandManager<CommandSourceStack> commandManager = PaperCommandManager.builder()
//                .executionCoordinator(ExecutionCoordinator.simpleCoordinator())
//                .buildOnEnable(this);
        try {
            commandManager = new PaperCommandManager<>(
                    /* Owning plugin */ this,
                    /* (1) */ CommandExecutionCoordinator.simpleCoordinator(),
                    /* (2) */ Function.identity(),
                    Function.identity()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        new RandomTeleportCommand(commandManager);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

//    public static SimpleRtpPlugin getInstance() {
//        return SimpleRtpPlugin.getPlugin(SimpleRtpPlugin.class);
//    }
}
