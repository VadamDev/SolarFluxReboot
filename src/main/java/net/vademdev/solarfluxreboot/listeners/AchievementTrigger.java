package net.vademdev.solarfluxreboot.listeners;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.vademdev.solarfluxreboot.dependencies.Dependency;
import net.vademdev.solarfluxreboot.init.ModAchievements;
import net.vademdev.solarfluxreboot.init.ModBlocks;
import net.vademdev.solarfluxreboot.init.solar.SolarTier;

public class AchievementTrigger {
    @SubscribeEvent
    public void onPlayerPickup(PlayerEvent.ItemPickupEvent event) {
        checkCraftingAndPickupAchievement(event.player, event.pickedUp.getEntityItem().getItem());
    }

    @SubscribeEvent
    public void onPlayerCraftItem(PlayerEvent.ItemCraftedEvent event) {
        checkCraftingAndPickupAchievement(event.player, event.crafting.getItem());
    }

    private void checkCraftingAndPickupAchievement(EntityPlayer player, Item item) {
        if(item.equals(Item.getItemFromBlock(ModBlocks.solarMap.get(SolarTier.TIER_1)))) player.addStat(ModAchievements.ecologist, 1);

        if(Dependency.DRACONIC_EVOLUTION.isLoaded()) {
            if(item.equals(Item.getItemFromBlock(ModBlocks.solarMap.get(SolarTier.TIER_WYVERN)))) player.addStat(ModAchievements.wyvern_power, 1);
            else if(item.equals(Item.getItemFromBlock(ModBlocks.solarMap.get(SolarTier.TIER_DRACONIC)))) player.addStat(ModAchievements.draconic_power, 1);
            else if(item.equals(Item.getItemFromBlock(ModBlocks.solarMap.get(SolarTier.TIER_CHAOTIC)))) player.addStat(ModAchievements.chaotic_power, 1);
        }

        if(Dependency.AVARITIA.isLoaded()) {
            if(item.equals(Item.getItemFromBlock(ModBlocks.solarMap.get(SolarTier.TIER_NEUTRONIUM)))) player.addStat(ModAchievements.neutronium_power, 1);
            else if(item.equals(Item.getItemFromBlock(ModBlocks.solarMap.get(SolarTier.TIER_INFINITY)))) player.addStat(ModAchievements.infinity_power, 1);
        }
    }
}
