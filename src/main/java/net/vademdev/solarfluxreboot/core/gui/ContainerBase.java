package net.vademdev.solarfluxreboot.core.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public abstract class ContainerBase extends Container {
    public void bindPlayerInventory(InventoryPlayer inventory, int iX, int iY, int aY) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventory, j + i * 9 + 9, iX + j * 18, iY + i * 18));
            }
        }

        for(int k = 0; k < 9; k++) {
            addSlotToContainer(new Slot(inventory, k, iX + k * 18, aY));
        }
    }
}
