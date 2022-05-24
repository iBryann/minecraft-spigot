package me.region;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Commands implements CommandExecutor {

    public void Register(JavaPlugin plugin) {
        plugin.getCommand("a").setExecutor(new Commands());
        plugin.getCommand("b").setExecutor(new Commands());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player && sender.isOp()) {
            Player player = (Player) sender;
            Location loc = player.getLocation();

            if (label.equalsIgnoreCase("a")) {
                player.sendMessage(
                    ChatColor.GREEN +
                    "Vetor início: " +
                    "\n X: " + loc.getX() +
                    "\n Y: " + loc.getY() +
                    "\n Z: " + loc.getZ()
                );

                Vector v1 = new Vector(loc.getX(), loc.getY(), loc.getZ());

            }
            else if (label.equalsIgnoreCase("b")) {
                player.sendMessage(
                    ChatColor.BLUE +
                    "Vetor fim: " +
                    "\n X: " + loc.getX() +
                    "\n Y: " + loc.getY() +
                    "\n Z: " + loc.getZ()
                );

                Vector v2 = new Vector(loc.getX(), loc.getY(), loc.getZ());
            }

        }

        return false;
    }
}
