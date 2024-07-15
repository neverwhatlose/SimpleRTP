package ru.nwtls.simplertpplugin.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class RandomTeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute this command.");
            return true;
        }
        Player player = (Player) sender;
        World world = player.getWorld();

        // Идею с таймером придется отложить...

//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            int i = 3;
//            @Override
//            public void run() {
//                Component msg = Component.text()
//                        .append(Component.text("Teleporting in " + i + " seconds"))
//                        .color(NamedTextColor.GRAY)
//                        .build();
//                player.sendMessage(msg);
//                i--;
//                if (i == 0) {
//                    this.cancel();
//                }
//            }
//        }, 0, 1000);

        Random rand = new Random();
        int x = rand.nextInt(5000);
        int z = rand.nextInt(5000);
        int y = world.getHighestBlockYAt(x, z);
        Location loc = new Location(world, x, y, z);
        player.teleport(loc);
        return true;
    }
}
