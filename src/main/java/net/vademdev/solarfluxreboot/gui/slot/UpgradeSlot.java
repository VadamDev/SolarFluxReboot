package net.vademdev.solarfluxreboot.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class UpgradeSlot extends Slot {
    public UpgradeSlot(IInventory inventory, int slotIndex, int displayXPos, int displayYPos) {
        super(inventory, slotIndex, displayXPos, displayYPos);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return inventory.isItemValidForSlot(getSlotIndex(), itemStack);
    }
}
