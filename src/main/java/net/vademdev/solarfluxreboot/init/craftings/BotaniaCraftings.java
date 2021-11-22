package net.vademdev.solarfluxreboot.init.craftings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.vademdev.solarfluxreboot.dependencies.Dependency;
import net.vademdev.solarfluxreboot.init.ModBlocks;
import net.vademdev.solarfluxreboot.init.ModItems;
import net.vademdev.solarfluxreboot.init.solar.SolarTier;
import vazkii.botania.api.BotaniaAPI;

public class BotaniaCraftings implements ICraftingHandler {
    @Override
    public void registerOthersRecipies() {
        BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.photovoltaic_cell, 1, 6), new ItemStack(ModItems.photovoltaic_cell, 1, 2), 1000);
        BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModItems.photovoltaic_cell, 1, 7), new ItemStack(ModItems.photovoltaic_cell, 1, 2));

        GameRegistry.addRecipe(new ItemStack(ModBlocks.solarMap.get(SolarTier.TIER_MANASTEEL)),
                "ccc",
                "msm",
                "mnm",
                'c', new ItemStack(ModItems.photovoltaic_cell, 1, 6),
                'm', Dependency.BOTANIA.getItem("manaResource"),
                's', ModBlocks.solarMap.get(SolarTier.TIER_4),
                'n', Dependency.BOTANIA.getItemStackWithMetadata("manaResource", 17));

        GameRegistry.addRecipe(new ItemStack(ModBlocks.solarMap.get(SolarTier.TIER_ELEMENTIUM)),
                "ccc",
                "msm",
                "mnm",
                'c', new ItemStack(ModItems.photovoltaic_cell, 1, 7),
                'm', Dependency.BOTANIA.getItemStackWithMetadata("manaResource", 7),
                's', ModBlocks.solarMap.get(SolarTier.TIER_MANASTEEL),
                'n', Dependency.BOTANIA.getItemStackWithMetadata("manaResource", 19));

        GameRegistry.addRecipe(new ItemStack(ModBlocks.solarMap.get(SolarTier.TIER_TERRASTEEL)),
                "ccc",
                "msm",
                "mnm",
                'c', new ItemStack(ModItems.photovoltaic_cell, 1, 8),
                'm', Dependency.BOTANIA.getItemStackWithMetadata("manaResource", 4),
                's', ModBlocks.solarMap.get(SolarTier.TIER_ELEMENTIUM),
                'n', Dependency.BOTANIA.getItemStackWithMetadata("rune", 2));
    }
}
