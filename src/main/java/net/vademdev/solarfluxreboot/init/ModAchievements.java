package net.vademdev.solarfluxreboot.init;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.vademdev.solarfluxreboot.core.AchievementBase;
import net.vademdev.solarfluxreboot.dependencies.Dependency;
import net.vademdev.solarfluxreboot.init.solar.SolarTier;
import net.vademdev.solarfluxreboot.listeners.AchievementTrigger;

import java.util.ArrayList;
import java.util.List;

public class ModAchievements {
    public static List<Achievement> modAchievements = new ArrayList<>();

    //Default
    public static Achievement ecologist;

    //Draconic
    public static Achievement wyvern_power, draconic_power, chaotic_power;

    //Avaritia
    public static Achievement neutronium_power, infinity_power;

    public static void init() {
        initDefaultAchievements();
        initDraconicEvolutionAchievements();
        initAvaritiaAchievements();

        AchievementPage.registerAchievementPage(new AchievementPage("Solar Flux: Reboot", modAchievements.toArray(new Achievement[0])));

        AchievementTrigger trigger = new AchievementTrigger();
        FMLCommonHandler.instance().bus().register(trigger);
        MinecraftForge.EVENT_BUS.register(trigger);
    }

    private static void initDefaultAchievements() {
        ecologist = new AchievementBase("ecologist", 0, 0, ModBlocks.solarMap.get(SolarTier.TIER_1), null);
    }

    private static void initDraconicEvolutionAchievements() {
        if(!Dependency.DRACONIC_EVOLUTION.isLoaded()) return;

        wyvern_power = new AchievementBase("wyvern_power", 0, 2, ModBlocks.solarMap.get(SolarTier.TIER_WYVERN), ecologist);
        draconic_power = new AchievementBase("draconic_power", 2, 2, ModBlocks.solarMap.get(SolarTier.TIER_DRACONIC), wyvern_power);
        chaotic_power = new AchievementBase("chaotic_power", 4, 2, ModBlocks.solarMap.get(SolarTier.TIER_CHAOTIC), draconic_power).setSpecial();
    }

    private static void initAvaritiaAchievements() {
        if(!Dependency.AVARITIA.isLoaded()) return;

        neutronium_power = new AchievementBase("neutronium_power", 0, -2, ModBlocks.solarMap.get(SolarTier.TIER_NEUTRONIUM), ecologist).setSpecial();
        infinity_power = new AchievementBase("infinity_power", 2, -2, ModBlocks.solarMap.get(SolarTier.TIER_INFINITY), neutronium_power).setSpecial();
    }
}
