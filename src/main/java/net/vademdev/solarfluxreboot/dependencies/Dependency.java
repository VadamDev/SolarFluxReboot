package net.vademdev.solarfluxreboot.dependencies;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.vademdev.solarfluxreboot.Main;
import net.vademdev.solarfluxreboot.init.craftings.*;

public enum Dependency {
    THERMAL_EXPENTION("ThermalExpansion", new DefaultCraftings(), true),
    //THAUMCRAFT("Thaumcraft", null, false),
    BOTANIA("Botania", new BotaniaCraftings(), false),
    DRACONIC_EVOLUTION("DraconicEvolution", new DraconicEvolutionCraftings(), false),
    AVARITIA("Avaritia", new AvaritiaCraftings(), false);

    private String modid;
    private ICraftingHandler craftingHandler;
    private boolean defaultLoaded;

    Dependency(String modid, ICraftingHandler craftingHandler, boolean defaultLoaded) {
        this.modid = modid;
        this.craftingHandler = craftingHandler;
        this.defaultLoaded = defaultLoaded;
    }

    public Item getItem(String name) {
        return GameRegistry.findItem(modid, name);
    }

    public ItemStack getItemStackWithMetadata(String name, int metadata) {
        return new ItemStack(GameRegistry.findItem(modid, name), 1, metadata);
    }

    public Block getBlock(String name) {
        return GameRegistry.findBlock(modid, name);
    }

    public void registerCraftings() {
        if(craftingHandler == null) return;

        craftingHandler.registerShapedRecipies();
        craftingHandler.registerShapelessRecipies();
        craftingHandler.registerOthersRecipies();
    }

    public String getModid() {
        return modid;
    }

    public boolean isLoaded() {
        return !defaultLoaded ? Main.instance.dependenciesManager.isDependencyLoaded(this) : defaultLoaded;
    }

    public boolean isDefaultLoaded() {
        return defaultLoaded;
    }
}
