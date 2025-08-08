package com.vypnito.vilPickupV;

import com.vypnito.vilPickupV.config.ConfigManager;
import com.vypnito.vilPickupV.util.Utils;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class InteractListener implements Listener {
    
    private final PickupManager pickupManager;
    private final ConfigManager configManager;

    public InteractListener(JavaPlugin plugin, PickupManager pickupManager, ConfigManager configManager) {
        this.pickupManager = pickupManager;
        this.configManager = configManager;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(ignoreCancelled = true)
    private void onVillagerInteract(PlayerInteractEntityEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) return;
        Entity entity = event.getRightClicked();
        
        String entityName = entity.getType().name();
        if (!configManager.getAllowedEntities().contains(entityName)) return;
        
        LivingEntity villager = (LivingEntity) entity;
        Player player = event.getPlayer();
        
        if (player.getGameMode() == GameMode.SPECTATOR) return;
        
        boolean requireShift = configManager.requireShift();
        boolean requireSneak = configManager.requireSneak();
        
        if (requireShift && !player.isSneaking()) return;
        if (requireSneak && !player.isSneaking()) return;
        
        if (configManager.requirePermission() && !player.hasPermission(configManager.getPermissionNode())) {
            player.sendMessage(configManager.getMessage("pickup.no-permission"));
            return;
        }
        
        event.setCancelled(true);
        handlePickup(player, villager);
    }

    private void handlePickup(Player player, LivingEntity villager) {
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(configManager.getMessage("pickup.inventory-full"));
            return;
        }
        
        if (villager instanceof Villager v && isVillagerUpgrading(v)) {
            player.sendMessage(configManager.getMessage("pickup.villager-upgrading"));
            return;
        }
        
        ItemStack item;
        try {
            item = pickupManager.toItemStack(villager);
        } catch (IllegalArgumentException exception) {
            player.sendMessage(configManager.getMessage("errors.save-failed"));
            return;
        }
        
        Utils.setHandOrGive(player, item);
        
        String villagerName = villager.getCustomName() != null ? villager.getCustomName() : "Villager";
        String message = configManager.getMessageNoPrefix("pickup.success").replace("{name}", villagerName);
        player.sendMessage(configManager.getMessage("pickup.success").replace("{name}", villagerName));
        pickupManager.sendPickupEffect(villager);
        
        if (configManager.logPickups()) {
            System.out.println("[VilPickup] " + player.getName() + " picked up villager at " + villager.getLocation());
        }
    }
    
    private boolean isVillagerUpgrading(Villager villager) {
        return villager.isTrading() || (villager.getProfession() == Villager.Profession.NONE && 
               villager.getVillagerExperience() > 0);
    }
}