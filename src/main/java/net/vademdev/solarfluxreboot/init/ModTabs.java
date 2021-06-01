package net.vademdev.solarfluxreboot.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.vademdev.solarfluxreboot.init.blocks.solar.SolarTier;

public class ModTabs {
    public static CreativeTabs tab = new CreativeTabs("solarfluxreboottab") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(ModBlocks.solarMap.get(SolarTier.TIER_1));
        }
    };
}
