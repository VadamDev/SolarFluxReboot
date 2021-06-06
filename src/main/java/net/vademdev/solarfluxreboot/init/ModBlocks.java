package net.vademdev.solarfluxreboot.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.vademdev.solarfluxreboot.init.blocks.solar.SolarTier;
import net.vademdev.solarfluxreboot.init.item.SolarPanelItemBlock;
import net.vademdev.solarfluxreboot.init.tileentity.TileEntitySolarPanel;

import java.util.HashMap;
import java.util.Map;

public class ModBlocks {
    public static Block solar_panel_icon;

    public static Map<SolarTier, Block> solarMap = new HashMap<>();

    public ModBlocks() {
        initTilesEntities();
        initBlocks();

        solarMap.forEach(this::registerSolarBlock);
    }

    private void initBlocks() {
        for (SolarTier value : SolarTier.values()) if(value.isDependencyActive()) solarMap.put(value, value.getSolarTierAsSolar());
        solar_panel_icon = solarMap.get(SolarTier.TIER_8);
    }

    private void initTilesEntities() {
        registerTileEntity(TileEntitySolarPanel.class);
    }

    private void registerSolarBlock(SolarTier tier, Block block) {
        GameRegistry.registerBlock(block, SolarPanelItemBlock.class, block.getUnlocalizedName().substring(5));
    }

    private void registerTileEntity(Class<? extends TileEntity> tileEntity) {
        GameRegistry.registerTileEntity(tileEntity, tileEntity.getSimpleName());
    }
}
