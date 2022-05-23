package me.revisao.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands implements CommandExecutor {
    public Commands(JavaPlugin plugin) {
        plugin.getCommand("GM").setExecutor(this);
        plugin.getCommand("DIA").setExecutor(this);
        plugin.getCommand("KIT").setExecutor(this);
        plugin.getCommand("CHUVA").setExecutor(this);
        plugin.getCommand("CURAR").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            String cmd = command.getName();
            Player p = (Player) sender;
            World activeWorld = Bukkit.getWorlds().get(0);

            if (cmd.equalsIgnoreCase("kit")) {
                ItemStack item1 = new ItemStack(Material.STONE_SWORD, 3);
                p.getInventory().addItem(item1);
                p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
            }
            else if (cmd.equalsIgnoreCase("dia")) {
                activeWorld.setTime(0);
                activeWorld.setThundering(false);
                activeWorld.setStorm(false);
                Bukkit.broadcastMessage(ChatColor.GREEN + "AMANHECEU!");
            }
            else if (cmd.equalsIgnoreCase("chuva")) {
                if (activeWorld.hasStorm()) {
                    activeWorld.setStorm(false);
                    activeWorld.setThundering(false);
                    Bukkit.broadcastMessage(ChatColor.YELLOW + "O céu esta limpo!");
                } else {
                    activeWorld.setStorm(true);
                    activeWorld.setThundering(true);
                    Bukkit.broadcastMessage(ChatColor.BLUE + "Pegue seu guarda-chuvas!");
                }
            }
            else if (cmd.equalsIgnoreCase("curar")) {
                if (p.getHealth() < 20) {
                    p.setHealth(20);
                } else {
                    p.sendMessage(ChatColor.GREEN + "Sua vida já está cheia!");
                }
            }
            else if (cmd.equalsIgnoreCase("gm")) {
                if (p.getGameMode().ordinal() == 0) {
                    p.setGameMode(p.getGameMode().SURVIVAL);
                } else {
                    p.setGameMode(p.getGameMode().CREATIVE);
                }

                p.sendMessage(ChatColor.GREEN + "Modo de jogo alterado para " + p.getGameMode().name() + ".");
            }
            else {
                p.sendMessage(ChatColor.RED + "O commando \"" + cmd + "\" não existe.");
            }

        }

        return false;
    }
}