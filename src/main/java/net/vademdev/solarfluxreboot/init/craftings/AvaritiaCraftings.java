package net.vademdev.solarfluxreboot.init.craftings;

import fox.spiteful.avaritia.crafting.ExtremeCraftingManager;
import net.minecraft.item.ItemStack;
import net.vademdev.solarfluxreboot.dependencies.Dependency;
import net.vademdev.solarfluxreboot.init.ModBlocks;
import net.vademdev.solarfluxreboot.init.solar.SolarTier;

public class AvaritiaCraftings implements ICraftingHandler {
    @Override
    public void registerOthersRecipies() {
        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.solarMap.get(SolarTier.TIER_NEUTRONIUM), 2),
                "  nn nn  ",
                " nmmsmmn ",
                "nm  u  mn",
                "nm ppp mn",
                " supcpus ",
                "nm ppp mn",
                "nm  u  mn",
                " nmmsmmn ",
                "  nn nn  ",

                'n', Dependency.AVARITIA.getItemStackWithMetadata("Resource", 4), //NEUTRONIUM INGOT
                'm', Dependency.AVARITIA.getItemStackWithMetadata("Resource", 1), //MATRIX
                's', ModBlocks.solarMap.get(SolarTier.TIER_8), //SOLAR
                'u', Dependency.AVARITIA.getItemStackWithMetadata("Resource", 3), //NEUTRONIUM NUGGET
                'p', Dependency.AVARITIA.getItemStackWithMetadata("Resource", 2), //NEUTRONIUM POWDER
                'c', Dependency.AVARITIA.getItemStackWithMetadata("Resource", 5) //CATALYST
        );

        ExtremeCraftingManager.getInstance().addRecipe(new ItemStack(ModBlocks.solarMap.get(SolarTier.TIER_INFINITY), 3),
                "  nn nn  ",
                " nmmbmmn ",
                "nm  u  mn",
                "nm pip mn",
                " buisiub ",
                "nm pip mn",
                "nm  u  mn",
                " nmmbmmn ",
                "  nn nn  ",

                'n', Dependency.AVARITIA.getItemStackWithMetadata("Resource", 4), //NEUTRONIUM INGOT
                'm', Dependency.AVARITIA.getItemStackWithMetadata("Resource", 1), //MATRIX
                'b', Dependency.AVARITIA.getItem("Resource_Block"), //NEUTRONIUM BLOCK
                'u', Dependency.AVARITIA.getItemStackWithMetadata("Resource", 3), //NEUTRONIUM NUGGET
                'p', Dependency.AVARITIA.getItemStackWithMetadata("Resource", 2), //NEUTRONIUM POWDER
                'i', Dependency.AVARITIA.getItemStackWithMetadata("Resource", 6), //INFINITY INGOT
                's', ModBlocks.solarMap.get(SolarTier.TIER_NEUTRONIUM) //SOLAR
        );
    }
}
