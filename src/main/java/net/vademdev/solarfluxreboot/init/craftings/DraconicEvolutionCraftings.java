package net.vademdev.solarfluxreboot.init.craftings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.vademdev.solarfluxreboot.dependencies.Dependency;
import net.vademdev.solarfluxreboot.init.ModBlocks;
import net.vademdev.solarfluxreboot.init.ModItems;
import net.vademdev.solarfluxreboot.init.solar.SolarTier;

public class DraconicEvolutionCraftings implements ICraftingHandler {
    @Override
    public void registerShapedRecipies() {
        //--------Crafting Items--------

        GameRegistry.addRecipe(new ItemStack(ModItems.chaotic_energy_core, 1),
                "aea",
                "ece",
                "aea",
                'a', Dependency.DRACONIC_EVOLUTION.getItem("draconicIngot"),
                'e', Dependency.DRACONIC_EVOLUTION.getItemStackWithMetadata("draconiumEnergyCore", 1),
                'c', Dependency.DRACONIC_EVOLUTION.getItem("chaoticCore"));

        //--------Solar Panel--------
        GameRegistry.addRecipe(new ItemStack(ModBlocks.solarMap.get(SolarTier.TIER_WYVERN), 2),
                "ses",
                "ece",
                "ses",
                's', ModBlocks.solarMap.get(SolarTier.TIER_8),
                'e', Dependency.DRACONIC_EVOLUTION.getItem("draconiumEnergyCore"),
                'c', Dependency.DRACONIC_EVOLUTION.getItem("wyvernCore"));

        GameRegistry.addRecipe(new ItemStack(ModBlocks.solarMap.get(SolarTier.TIER_DRACONIC), 2),
                "ses",
                "ece",
                "ses",
                's', ModBlocks.solarMap.get(SolarTier.TIER_WYVERN),
                'e', Dependency.DRACONIC_EVOLUTION.getItemStackWithMetadata("draconiumEnergyCore", 1),
                'c', Dependency.DRACONIC_EVOLUTION.getItem("awakenedCore"));

        GameRegistry.addRecipe(new ItemStack(ModBlocks.solarMap.get(SolarTier.TIER_CHAOTIC), 2),
                "sas",
                "aca",
                "sas",
                's', ModBlocks.solarMap.get(SolarTier.TIER_DRACONIC),
                'a', ModItems.chaotic_energy_core,
                'c', Dependency.DRACONIC_EVOLUTION.getItem("chaoticCore"));
    }
}
