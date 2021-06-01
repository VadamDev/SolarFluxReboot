package net.vademdev.solarfluxreboot.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.vademdev.solarfluxreboot.References;
import net.vademdev.solarfluxreboot.core.blocks.ItemBase;
import net.vademdev.solarfluxreboot.dependencies.Dependency;
import net.vademdev.solarfluxreboot.init.blocks.solar.SolarTier;
import net.vademdev.solarfluxreboot.init.item.ItemChaoticEnergyCore;
import net.vademdev.solarfluxreboot.init.item.ItemPhotovoltaicCell;
import net.vademdev.solarfluxreboot.init.item.SolarPanelItemBlock;
import net.vademdev.solarfluxreboot.init.tileentity.TileEntitySolarPanel;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static List<Item> itemList = new ArrayList<>();

    public ModItems() {
        initItems();

        itemList.forEach(this::registerItem);
        itemList.clear();
    }

    public static Item mirror, photovoltaic_cell, chaotic_energy_core;

    private void initItems() {
        mirror = new ItemBase().setUnlocalizedName("mirror").setTextureName(References.MOD_ID + ":mirror").setCreativeTab(ModTabs.tab);
        photovoltaic_cell = new ItemPhotovoltaicCell().setUnlocalizedName("photovoltaic_cell").setTextureName(References.MOD_ID + ":photovoltaic_cell").setCreativeTab(ModTabs.tab);
        chaotic_energy_core = new ItemChaoticEnergyCore(Dependency.DRACONIC_EVOLUTION.isLoaded()).setUnlocalizedName("chaotic_energy_core").setTextureName(References.MOD_ID + ":chaotic_energy_core").setCreativeTab(ModTabs.tab);
    }

    private void registerItem(Item item) {
        GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
    }
}
