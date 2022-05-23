package teste.teste;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import teste.teste.commands.Commands;
import teste.teste.events.Events;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Events(), this);
        new Commands(this);

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "PLUGIN CARREGADO!");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "PLUGIN DESCARREGADO!");
        HandlerList.unregisterAll();
    }
}