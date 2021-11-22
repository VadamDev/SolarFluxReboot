package net.vademdev.solarfluxreboot.init.craftings;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.vademdev.solarfluxreboot.init.ModBlocks;
import net.vademdev.solarfluxreboot.init.ModItems;
import net.vademdev.solarfluxreboot.init.solar.SolarTier;

public class DefaultCraftings implements ICraftingHandler {
    @Override
    public void registerShapedRecipies() {
        //--------Crafting Items--------
        GameRegistry.addRecipe(new ItemStack(ModItems.photovoltaic_cell, 1, 0),
                "ggg",
                "lll",
                "mmm",
                'g', Blocks.glass, 'l', new ItemStack(Items.dye, 1, 4), 'm', ModItems.mirror);

        GameRegistry.addRecipe(new ItemStack(ModItems.photovoltaic_cell, 1, 1),
                "clc",
                "lcl",
                "mpm",
                'c', Items.clay_ball, 'l', new ItemStack(Items.dye, 1, 4),
                'p', new ItemStack(ModItems.photovoltaic_cell, 1, 0), 'm', ModItems.mirror);

        GameRegistry.addRecipe(new ItemStack(ModItems.photovoltaic_cell, 1, 2),
                "ggg",
                "sss",
                "opo",
                'g', Blocks.glass, 's', Items.glowstone_dust,
                'p', new ItemStack(ModItems.photovoltaic_cell, 1, 1), 'o', Blocks.obsidian);

        GameRegistry.addRecipe(new ItemStack(ModItems.photovoltaic_cell, 1, 3),
                "bbb",
                "gdg",
                "qpq",
                'b', Items.blaze_powder, 'g', Items.glowstone_dust, 'd', Items.diamond,
                'p', new ItemStack(ModItems.photovoltaic_cell, 1, 2), 'q', Blocks.quartz_block);

        GameRegistry.addRecipe(new ItemStack(ModItems.photovoltaic_cell, 1, 4),
                "rrr",
                "gdg",
                "qpq",
                'r', Items.blaze_rod, 'g', Blocks.glowstone, 'd', Blocks.diamond_block,
                'p', new ItemStack(ModItems.photovoltaic_cell, 1, 3), 'q', Blocks.quartz_block);

        GameRegistry.addRecipe(new ItemStack(ModItems.photovoltaic_cell, 1, 5),
                "eee",
                "gdg",
                "qpq",
                'e', Items.blaze_rod, 'g', Blocks.glowstone, 'd', Blocks.diamond_block,
                'p', new ItemStack(ModItems.photovoltaic_cell, 1, 4), 'q', Blocks.quartz_block);

        //--------Solar Panel--------
        GameRegistry.addRecipe(new ItemStack(ModBlocks.solarMap.get(SolarTier.TIER_1), 1),
                "mmm",
                "prp",
                "ppp",
                'm', ModItems.mirror, 'p', Blocks.planks, 'r', Items.redstone);

        GameRegistry.addRecipe(new ItemStack(ModBlocks.solarMap.get(SolarTier.TIER_2), 1),
                "sss",
                "sps",
                "sss",
                's', ModBlocks.solarMap.get(SolarTier.TIER_1), 'p', Blocks.planks);

        GameRegistry.addRecipe(new ItemStack(ModBlocks.solarMap.get(SolarTier.TIER_3), 1),
                "ppp",
                "srs",
                "sis",
                'p', new ItemStack(ModItems.photovoltaic_cell, 0), 's', ModBlocks.solarMap.get(SolarTier.TIER_2),
                'r', Items.repeater, 'i', Blocks.iron_block);

        GameRegistry.addRecipe(new ItemStack(ModBlocks.solarMap.get(SolarTier.TIER_4), 1),
                "ppp",
                "scs",
                "sis",
                'p', new ItemStack(ModItems.photovoltaic_cell, 1), 's', ModBlocks.solarMap.get(SolarTier.TIER_3),
                'c', Items.clock, 'i', Blocks.iron_block);

        GameRegistry.addRecipe(new ItemStack(ModBlocks.solarMap.get(SolarTier.TIER_5), 1),
                "ppp",
                "sgs",
                "sos",
                'p', new ItemStack(ModItems.photovoltaic_cell, 2), 's', ModBlocks.solarMap.get(SolarTier.TIER_4),
                'g', Items.glowstone_dust, 'o', Blocks.gold_block);

        GameRegistry.addRecipe(new ItemStack(ModBlocks.solarMap.get(SolarTier.TIER_6), 1),
                "ppp",
                "srs",
                "sds",
                'p', new ItemStack(ModItems.photovoltaic_cell, 3), 's', ModBlocks.solarMap.get(SolarTier.TIER_5),
                'r', Blocks.redstone_block, 'd', Blocks.diamond_block);

        GameRegistry.addRecipe(new ItemStack(ModBlocks.solarMap.get(SolarTier.TIER_7), 1),
                "ppp",
                "sgs",
                "sgs",
                'p', new ItemStack(ModItems.photovoltaic_cell, 4), 's', ModBlocks.solarMap.get(SolarTier.TIER_6),
                'g', Blocks.glowstone);

        GameRegistry.addRecipe(new ItemStack(ModBlocks.solarMap.get(SolarTier.TIER_8), 1),
                "ppp",
                "sds",
                "sds",
                'p', new ItemStack(ModItems.photovoltaic_cell, 5), 's', ModBlocks.solarMap.get(SolarTier.TIER_7),
                'd', Blocks.dragon_egg);
    }

    @Override
    public void registerShapelessRecipies() {
        GameRegistry.addRecipe(new ItemStack(ModItems.villager_disk),
                "m",
                "l",
                'm', ModItems.mirror,
                'l', Items.record_11);

        GameRegistry.addRecipe(new ItemStack(ModItems.mirror, 3),
                "ggg",
                "0i0",
                'g', Blocks.glass, 'i', Items.iron_ingot);
    }
}
