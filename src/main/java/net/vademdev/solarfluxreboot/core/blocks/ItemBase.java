package net.vademdev.solarfluxreboot.core.blocks;

import net.minecraft.item.Item;
import net.vademdev.solarfluxreboot.init.ModItems;

public class ItemBase extends Item {
    private boolean defaultLoaded;

    public ItemBase(boolean defaultLoaded) {
        this.defaultLoaded = defaultLoaded;
    }

    public ItemBase() {
        this(true);
    }

    @Override
    public Item setUnlocalizedName(String p_77655_1_) {
        if(defaultLoaded) ModItems.itemList.add(this);
        return super.setUnlocalizedName(p_77655_1_);
    }
}
