package me.revisao.events;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Events implements Listener {

    @EventHandler
    public void SuperSalto(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Block bloco = p.getLocation().getBlock();

        if (p.getGameMode() != GameMode.CREATIVE && bloco.getRelative(BlockFace.DOWN).getType() != Material.AIR) {
            p.setAllowFlight(true);
            p.setFlying(false);
        }
    }


    @EventHandler
    public void SuperSaltoAtiva(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        Block bloco = p.getLocation().getBlock();

        if (p.getGameMode() != GameMode.CREATIVE) {
            p.setVelocity(p.getLocation().getDirection().multiply(3).setY(1));

            p.setAllowFlight(false);

            p.sendMessage("Pulou");
        }
    }

    @EventHandler
    public void AtirarFlexa(EntityDamageByEntityEvent e) {
        Entity flexa = e.getDamager();
        Entity vitima = e.getEntity();


        Bukkit.broadcastMessage("Vitima: " + vitima.getName());
        Bukkit.broadcastMessage("Dano: " + e.getFinalDamage());
        Bukkit.broadcastMessage("Causa: " + e.getCause());
    }

    @EventHandler
    public void Teste(ProjectileLaunchEvent e) {
        Projectile flexa = e.getEntity();
        Entity a = (Entity) flexa.getShooter();

        if (flexa.getType() == EntityType.ARROW && a.getType() == EntityType.PLAYER){
            Player p = (Player) flexa.getShooter();

            flexa.setVelocity(flexa.getVelocity().multiply(3));

            //p.sendMessage(ChatColor.LIGHT_PURPLE + "Você lançou uma flexa!");
        }

    }

    @EventHandler
    public void Pesca(PlayerFishEvent e) {
        Player p = e.getPlayer();

        ItemStack hand = p.getInventory().getItemInMainHand();

        if (hand.getType() == Material.FISHING_ROD) {

            ItemMeta im = hand.getItemMeta();
            im.addEnchant(Enchantment.WATER_WORKER, 1, true);
            hand.setItemMeta(im);


            p.sendMessage(ChatColor.BLUE + p.getName() + " está percando!");

        }

    }

    @EventHandler
    public void BichoNasce(CreatureSpawnEvent e) {
        LivingEntity p = e.getEntity();

        if (p.getType() == EntityType.SKELETON) {
            p.setCustomName("Zé do meu ovo");
        }
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        p.sendMessage("Bem-vindo, " + p.getName() + "!");


        //NAÇÃO DO AR
//        p.removePotionEffect(PotionEffectType.JUMP);
//        p.removePotionEffect(PotionEffectType.SLOW_FALLING);
//        p.removePotionEffect(PotionEffectType.SPEED);
//
//        p.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);
//        p.removePotionEffect(PotionEffectType.WATER_BREATHING);
//        p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
//
//        p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 20000000, 0, false, false, false));
//        p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 20000000, 0, false, false, false));



        //NAÇÃO DO AR
	    p.removePotionEffect(PotionEffectType.JUMP);
	    p.removePotionEffect(PotionEffectType.SLOW_FALLING);
	    p.removePotionEffect(PotionEffectType.SPEED);

	    p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20000000, 3, false, false, false));
	    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20000000, 0, false, false, false));
	    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20000000, 2, false, false, false));
    }


    @EventHandler
    public void TridenteSagrado(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        if (player.getInventory().getItemInMainHand().getType() == Material.TRIDENT &&
                (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)) {
            //Location blocoClicado = event.getClickedBlock().getLocation();
            Location blocoClicado = player.getTargetBlock(null, 100).getLocation();

            player.getWorld().strikeLightning(blocoClicado);
            player.getWorld().strikeLightning(blocoClicado);
            player.getWorld().strikeLightning(blocoClicado);
            player.getWorld().strikeLightning(blocoClicado);

            player.getWorld().createExplosion(blocoClicado, 10, false);
        }
    }

    @EventHandler
    public void FlexaExplosiva(ProjectileHitEvent e) {
        Projectile entity = e.getEntity();
        Player p = (Player) entity.getShooter();

        if (e.getEntityType() == EntityType.ARROW) {
	    	p.sendMessage("X: " + (p.getLocation().getX() - entity.getLocation().getBlockX()));
	    	p.sendMessage("Y: " + (p.getLocation().getY() - entity.getLocation().getBlockY()));
	    	p.sendMessage("Z: " + (p.getLocation().getZ() - entity.getLocation().getBlockZ()));

            entity.getWorld().strikeLightning(entity.getLocation());
            entity.getWorld().createExplosion(entity.getLocation(), 3, false);
        }
    }

}
