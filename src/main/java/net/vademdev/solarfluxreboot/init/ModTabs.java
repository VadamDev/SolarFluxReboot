package net.vademdev.solarfluxreboot.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class ModTabs {
    public static CreativeTabs tab = new CreativeTabs("solarfluxreboottab") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(ModBlocks.solar_panel_icon);
        }
    };
}
