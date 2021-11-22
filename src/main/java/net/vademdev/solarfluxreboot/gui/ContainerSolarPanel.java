package net.vademdev.solarfluxreboot.gui;

import com.google.common.collect.Maps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.vademdev.solarfluxreboot.init.tileentity.TileEntitySolarPanel;

import java.util.List;
import java.util.Map;

public class ContainerSolarPanel extends Container {
    private final Map<Integer, Integer> progressBarsValues = Maps.newHashMap();

    private TileEntitySolarPanel solarPanel;

    public ContainerSolarPanel(TileEntitySolarPanel solarPanel, InventoryPlayer inventoryPlayer) {
        this.solarPanel = solarPanel;

        for(int i = 0; i < 3; i++) for(int j = 0; j < 9; j++) addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 98 + i * 18));
        for(int k = 0; k < 9; k++) addSlotToContainer(new Slot(inventoryPlayer, k, 8 + k * 18, 156));
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return solarPanel.isUseableByPlayer(player);
    }

    /*
       Send Client Changes
     */

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        boolean forceUpdate = (solarPanel.getWorldObj().getWorldTime() % 40) == 0;

        sendProgressBarUpdateIfChanged(0, solarPanel.getEnergyStored() & 0xFFFF, forceUpdate);
        sendProgressBarUpdateIfChanged(1, solarPanel.getEnergyStored() >>> 16, forceUpdate);
        sendProgressBarUpdateIfChanged(2, solarPanel.getCurrentEnergyGeneration() & 0xFFFF, false);
        sendProgressBarUpdateIfChanged(3, solarPanel.getCurrentEnergyGeneration() >>> 16, false);
        sendProgressBarUpdateIfChanged(4, (int) (100 *  solarPanel.getSunIntensity()) & 0xFFFF, false);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int index, int value) {
        if(index == 0)
            solarPanel.setEnergyStored((solarPanel.getEnergyStored() & 0xFFFF0000) | value);
        else if (index == 1)
            solarPanel.setEnergyStored(solarPanel.getEnergyStored() & 0xFFFF | (value << 16));
        else if (index == 2)
            solarPanel.setCurrentEnergyGeneration((solarPanel.getCurrentEnergyGeneration() & 0xFFFF0000) | value);
        else if (index == 3)
            solarPanel.setCurrentEnergyGeneration(solarPanel.getCurrentEnergyGeneration() & 0xFFFF | (value << 16));
        else if(index == 4)
            solarPanel.setSunIntensity(value / 100f);

    }

    protected void sendProgressBarUpdateIfChanged(int type, int value, boolean force) {
        if (force || !(progressBarsValues.containsKey(type) && progressBarsValues.get(type).equals(value))) {
            for (ICrafting crafting : getCraftings()) crafting.sendProgressBarUpdate(this, type, value);
            progressBarsValues.put(type, value);
        }
    }

    protected List<ICrafting> getCraftings() {
        return crafters;
    }

    //Minecraft gui func nothing to do here
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotIndex);

        if(slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if(slotIndex < solarPanel.getSizeInventory()) {
                if(!this.mergeItemStack(itemstack1, solarPanel.getSizeInventory(), this.inventorySlots.size(), true)) return null;
            }else if(!this.mergeItemStack(itemstack1, 0, solarPanel.getSizeInventory(), false)) return null;

            if(itemstack1.stackSize == 0) slot.putStack(null);
            else slot.onSlotChanged();
        }

        return itemstack;
    }
}
