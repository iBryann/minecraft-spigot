package me.region;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


public final class Region extends JavaPlugin {
    @Override
    public void onEnable() {
        new Commands().Register(this);
        getServer().getPluginManager().registerEvents(new Events(), this);

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Regiões carregado!");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Regiões descarregado.");
    }
}
