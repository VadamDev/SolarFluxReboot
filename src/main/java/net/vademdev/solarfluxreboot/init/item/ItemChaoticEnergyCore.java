package net.vademdev.solarfluxreboot.init.item;

import net.minecraft.item.ItemStack;
import net.vademdev.solarfluxreboot.core.blocks.ItemBase;

public class ItemChaoticEnergyCore extends ItemBase {
    public ItemChaoticEnergyCore(boolean defaultLoaded) {
        super(defaultLoaded);
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }
}
