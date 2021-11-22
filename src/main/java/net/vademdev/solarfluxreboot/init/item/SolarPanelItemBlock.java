package net.vademdev.solarfluxreboot.init.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.vademdev.solarfluxreboot.init.solar.BlockSolarPanel;
import net.vademdev.solarfluxreboot.init.solar.SolarTier;

import java.util.List;

public class SolarPanelItemBlock extends ItemBlock {
    private SolarTier solarTier;

        public SolarPanelItemBlock(Block block) {
        super(block);

        solarTier = ((BlockSolarPanel) block).getSolarTier();
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List lines, boolean b) {
        lines.add(" ");
        lines.add("Generation: " + solarTier.getEnergyGeneration() + " RF/T");
        lines.add("Transfer: " + solarTier.getEnergyTransfer() + " RF/T");
    }
}
