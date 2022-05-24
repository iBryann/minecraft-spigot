package me.region;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.BoundingBox;

public class Events implements Listener {
    private BoundingBox region;

    public Events() {
        this.region = new BoundingBox(-174, 111, 190, -152, 121, 172);
    }

    @EventHandler
    public void onPlaceLiquid(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlockClicked().getRelative(event.getBlockFace());
        Location loc = block.getLocation();
        Boolean intoRegion = region.contains(loc.getX(), loc.getY(), loc.getZ());

        if (intoRegion) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.YELLOW + "PlayerBucketEmptyEvent");
        }
    }

    @EventHandler
    public void onSpreadLiquids(BlockFromToEvent event) {
        Location loc = event.getBlock().getLocation();
        Boolean intoRegion = region.contains(loc.getX(), loc.getY(), loc.getZ());

        if (intoRegion && event.getBlock().isLiquid()) {
            event.setCancelled(true);
            Bukkit.broadcastMessage(ChatColor.YELLOW + "BlockFromTo");
        }
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location loc = event.getBlock().getLocation();
        Boolean intoRegion = region.contains(loc.getX(), loc.getY(), loc.getZ());

        if (intoRegion) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.YELLOW + "BlockBreak");
        }
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Location loc = event.getBlock().getLocation();
        Boolean intoRegion = region.contains(loc.getX(), loc.getY(), loc.getZ());

        if (intoRegion) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.YELLOW + "BlockPlace");
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location loc = player.getLocation();
        Boolean intoRegion = region.contains(loc.getX(), loc.getY(), loc.getZ());

        if (intoRegion) {
            player.teleport(
                player.getLocation().add(
                    event.getFrom().toVector().subtract(
                        event.getTo().toVector()
                    ).normalize().multiply(2)
                )
            );
        }
    }
}
