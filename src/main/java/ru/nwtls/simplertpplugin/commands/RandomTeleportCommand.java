package ru.nwtls.simplertpplugin.commands;

import cloud.commandframework.arguments.standard.IntegerArgument;
import cloud.commandframework.arguments.standard.StringArgument;
import cloud.commandframework.paper.PaperCommandManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
//import org.incendo.cloud.paper.PaperCommandManager;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class RandomTeleportCommand {

    public RandomTeleportCommand(@NotNull PaperCommandManager<CommandSender> manager) {
        manager.command(manager
                .commandBuilder("rtp", "randomteleport", "randtp")
                .argument(IntegerArgument.<CommandSender>builder("bound").asOptional())
                .argument(StringArgument.<CommandSender>builder("type").asOptional())
                .handler(ctx -> {
                    if (!(ctx.getSender() instanceof Player)) {
                        ctx.getSender().sendMessage("You must be a player to use this command.");
                        return;
                    }
                    Integer bound = ctx.getOrDefault("bound", 5000);
                    String type = ctx.getOrDefault("type", "player");
                    if (!(type.equals("player") || type.equals("zero"))) {
                        ctx.getSender().sendMessage("Type must be either player or zero");
                        return;
                    }
                    this.handle((Player) ctx.getSender(), bound, type);
                }
        ));
    }

    public void handle(@NotNull Player player, Integer bound, @NotNull String type) {
        World world = player.getWorld();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int i = 3;
            @Override
            public void run() {
                Component msg = Component.text()
                        .append(Component.text("Teleporting in " + i + " seconds"))
                        .color(NamedTextColor.GRAY)
                        .build();
                player.sendMessage(msg);
                i--;
                if (i == 0) {
                    Random rand = new Random();

                    int curX = player.getLocation().getBlockX();
                    int curZ = player.getLocation().getBlockZ();
                    int x;
                    int z;
                    String message;

                    if (type.equals("player")) {
                        x = curX + 2 * rand.nextInt(bound) - bound;
                        z = curZ + 2 * rand.nextInt(bound) - bound;
                        message = "Changed x: " + (x - curX)  + ", z: " + (z - curX) + ", type: " + type;
                    } else {
                        x = 2 * rand.nextInt(bound) - bound;
                        z = 2 * rand.nextInt(bound) - bound;
                        message = "Changed x: " + (x - curX)  + ", z: " + (z - curX) + ", type: " + type;
                    }

                    int y = world.getHighestBlockYAt(x, z);
                    Location loc = new Location(world, x, y, z);
                    player.teleportAsync(loc);

                    player.sendMessage(message);
                    this.cancel();
                }
            }
        }, 0, 1000);
    }
}

