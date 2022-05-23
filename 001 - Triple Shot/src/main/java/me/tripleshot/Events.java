package me.tripleshot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;

public class Events implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        LivingEntity target = (LivingEntity) event.getEntity();

        if (damager instanceof Arrow) {
            Arrow arrow = (Arrow) damager;
            LivingEntity shooter = (LivingEntity) arrow.getShooter();

            if (shooter instanceof Pillager && target instanceof Pillager) {
                event.setCancelled(true);
                Bukkit.broadcastMessage(
                    ChatColor.RED +
                    shooter.getName() + " —> " + target.getName() +
                    " - DANO CANCELADO" +
                    "\n  Vida: " + shooter.getHealth() + "/" + shooter.getMaxHealth()
                );
            }
            else {
                Bukkit.broadcastMessage(
                    ChatColor.GREEN +
                    shooter.getName() + " —> " + target.getName() +
                    "\n  Vida: " + target.getHealth() + "/" + target.getMaxHealth() +
                    "\n  Dano: " + event.getDamage() +
                    "\n  Dano final: " + event.getFinalDamage() +
                    "\n  Causa: " + event.getCause()
                );
            }
        }
    }

    @EventHandler
    public void onShoot(EntityShootBowEvent event) {
        if (
            event.getBow().getType() == Material.CROSSBOW &&
            event.getProjectile() instanceof Arrow &&
            event.getEntity() instanceof Pillager
        ) {
            Pillager player = (Pillager)event.getEntity();
            Arrow arrow = (Arrow)event.getProjectile();
            int seconds = 10;
            int ticks = seconds * 20;

            arrow.setFireTicks(ticks);
            arrow.setVelocity(arrow.getVelocity().multiply(2));

            Arrow arrow1 = player.getWorld().spawn(player.getLocation(), Arrow.class);
            arrow1.setDamage(arrow.getDamage() / 2);
            arrow1.setKnockbackStrength(2);
            arrow1.setShooter(player);
            arrow1.setFireTicks(ticks);
            arrow1.setVelocity(arrow.getVelocity().rotateAroundY(Math.toRadians(10)));

            Arrow arrow2 = player.getWorld().spawn(player.getLocation(), Arrow.class);
            arrow2.setDamage(arrow.getDamage() / 2);
            arrow2.setKnockbackStrength(2);
            arrow2.setShooter(player);
            arrow2.setFireTicks(ticks);
            arrow2.setVelocity(arrow.getVelocity().rotateAroundY(Math.toRadians(-10)));
        }
    }

}
