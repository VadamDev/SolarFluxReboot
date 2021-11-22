package net.vademdev.solarfluxreboot.init.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;
import net.vademdev.solarfluxreboot.References;
import net.vademdev.solarfluxreboot.init.ModItems;

public class ItemModRecord extends ItemRecord {
    private final String record;

    public ItemModRecord(String record) {
        super(References.MOD_ID + ":" + record);

        this.record = References.MOD_ID + ":" + record;
    }

    @Override
    public Item setUnlocalizedName(String p_77655_1_) {
        ModItems.itemList.add(this);
        return super.setUnlocalizedName(p_77655_1_);
    }

    @Override
    public ResourceLocation getRecordResource(String name) {
        return new ResourceLocation(record);
    }
}
