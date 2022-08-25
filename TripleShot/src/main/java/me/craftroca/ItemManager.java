package me.craftroca;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack TripleShotCrossbow;

    public static void init() {
        createTripleShotCrossbow();
    }

    private static void createTripleShotCrossbow() {
        ItemStack item = new ItemStack(Material.CROSSBOW, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.BLUE + "Item Ability: Three Shots");
        lore.add("Shoots treee arrow instead of 1");
        lore.add("Very damage!");

        meta.setLore(lore);
        meta.setDisplayName(ChatColor.GREEN + "Triple Show CROSSBOW");
        item.setItemMeta(meta);

        TripleShotCrossbow = item;
    }
}
