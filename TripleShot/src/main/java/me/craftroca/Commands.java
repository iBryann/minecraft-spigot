package me.craftroca;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;

            if (player.isOp()) {
                if (command.getName().equalsIgnoreCase("arco")) {
                    player.getInventory().addItem(ItemManager.TripleShotCrossbow);
                }
            }
        }

        return false;
    }
}
