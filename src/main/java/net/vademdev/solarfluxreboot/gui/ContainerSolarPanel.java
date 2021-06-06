package net.vademdev.solarfluxreboot.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.vademdev.solarfluxreboot.core.gui.ContainerBase;
import net.vademdev.solarfluxreboot.gui.slot.UpgradeSlot;
import net.vademdev.solarfluxreboot.init.tileentity.TileEntitySolarPanel;

public class ContainerSolarPanel extends ContainerBase {
    private TileEntitySolarPanel solarPanel;
    private InventoryPlayer inventoryPlayer;

    public ContainerSolarPanel(TileEntitySolarPanel solarPanel, InventoryPlayer inventoryPlayer) {
        this.solarPanel = solarPanel;
        this.inventoryPlayer = inventoryPlayer;

        //for (int i = 0; i < 5; i++) addSlotToContainer(new UpgradeSlot(solarPanel, i, 17 + i * 18, 59));

        bindPlayerInventory(inventoryPlayer, 8, 98, 156);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if(slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if(slotIndex < solarPanel.getSizeInventory()) {
                if(!this.mergeItemStack(itemstack1, solarPanel.getSizeInventory(), inventorySlots.size(), true))
                    return null;
            }else if(!this.mergeItemStack(itemstack1, 0, solarPanel.getSizeInventory(), false)) return null;

            if(itemstack1.stackSize == 0) slot.putStack(null);
            else slot.onSlotChanged();
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return solarPanel.isUseableByPlayer(player);
    }
}
