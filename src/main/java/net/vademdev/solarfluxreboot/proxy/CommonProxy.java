package net.vademdev.solarfluxreboot.proxy;

import cpw.mods.fml.common.network.NetworkRegistry;
import net.vademdev.solarfluxreboot.Main;
import net.vademdev.solarfluxreboot.gui.GuiHandler;
import net.vademdev.solarfluxreboot.init.ModBlocks;
import net.vademdev.solarfluxreboot.init.ModCraftings;
import net.vademdev.solarfluxreboot.init.ModItems;

public class CommonProxy {
    public void preInit() {
        Main.instance.dependenciesManager.init();

        new ModBlocks();
        new ModItems();
    }

    public void init() {
        ModCraftings.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
    }
}
