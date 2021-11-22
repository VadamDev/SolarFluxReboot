package net.vademdev.solarfluxreboot.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.vademdev.solarfluxreboot.References;
import net.vademdev.solarfluxreboot.init.ModAchievements;

public class AchievementBase extends Achievement {
    public AchievementBase(String name, int x, int y, ItemStack icon, Achievement parent) {
        super("achievement." + References.MOD_ID + ":" + name, References.MOD_ID + ":" + name, x, y, icon, parent);
        registerStat();

        ModAchievements.modAchievements.add(this);
    }

    public AchievementBase(String name, int x, int y, Item icon, Achievement parent) {
        this(name, x, y, new ItemStack(icon), parent);
    }

    public AchievementBase(String name, int x, int y, Block icon, Achievement parent) {
        this(name, x, y, new ItemStack(icon), parent);
    }
}
