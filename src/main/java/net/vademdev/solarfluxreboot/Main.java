package net.vademdev.solarfluxreboot;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.vademdev.solarfluxreboot.dependencies.DependenciesManager;
import net.vademdev.solarfluxreboot.proxy.CommonProxy;

@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION, dependencies = References.DEPENDENCIES)
public class Main {
    @Mod.Instance(References.MOD_ID)
    public static Main instance;

    @SidedProxy(clientSide = "net.vademdev.solarfluxreboot.proxy.ClientProxy", serverSide = "net.vademdev.solarfluxreboot.proxy.CommonProxy")
    public static CommonProxy proxy;

    public DependenciesManager dependenciesManager = new DependenciesManager();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }
}
