package net.vademdev.solarfluxreboot.init.craftings;

import net.minecraft.item.ItemStack;
import net.vademdev.solarfluxreboot.init.ModItems;
import vazkii.botania.api.BotaniaAPI;

public class BotaniaCraftings implements ICraftingHandler {
    @Override
    public void registerOthersRecipies() {
        BotaniaAPI.registerManaInfusionRecipe(new ItemStack(ModItems.photovoltaic_cell, 1, 6), new ItemStack(ModItems.photovoltaic_cell, 1, 2), 1000);
    }
}
