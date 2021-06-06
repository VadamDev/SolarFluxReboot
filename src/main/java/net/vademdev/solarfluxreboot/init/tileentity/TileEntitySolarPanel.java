package net.vademdev.solarfluxreboot.init.tileentity;

import cofh.api.energy.EnergyStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import net.vademdev.solarfluxreboot.core.energy.TileEnergized;
import net.vademdev.solarfluxreboot.init.blocks.solar.SolarTier;

import java.util.Random;

public class TileEntitySolarPanel extends TileEnergized implements IInventory {
    private SolarTier solarTier;

    private final short tickShift;

    private float sunIntensity;
    private int energyGeneration;

    private ItemStack[] content = new ItemStack[5];

    public TileEntitySolarPanel(SolarTier solarTier) {
        super(new EnergyStorage(solarTier.getEnergyCapacity(), solarTier.getEnergyTransfer(), solarTier.getEnergyTransfer()));

        tickShift = (short) new Random().nextInt(100);

        this.solarTier = solarTier;
    }

    @Override
    public void updateEntity() {
        generateEnergy();

        if(!worldObj.isRemote) {
            if(atTickRate(20)) updateEnergyGeneration();

            outputEnergy();
        }
    }

    private void generateEnergy() {
        if(energyGeneration > 0) {
            storage.receiveEnergy(energyGeneration, false);
        }
    }

    private void updateEnergyGeneration() {
        computeSunIntensity();
        energyGeneration = Math.round(solarTier.getEnergyGeneration() * sunIntensity);
    }

    private void computeSunIntensity() {
        if(worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord)) {
            float celestialAngleRadians = worldObj.getCelestialAngleRadians(1.0f);

            if (celestialAngleRadians > Math.PI) celestialAngleRadians = (2 * 3.141592f - celestialAngleRadians);

            sunIntensity = 1.5f * MathHelper.cos(celestialAngleRadians / 1.2f);
            sunIntensity = Math.max(0, sunIntensity);
            sunIntensity = Math.min(1, sunIntensity);

            if (sunIntensity > 0) {
                if (worldObj.isRaining()) sunIntensity *= 0.4;
                else if (worldObj.isThundering()) sunIntensity *= 0.4;
            }
        }else sunIntensity = 0;
    }

    public boolean atTickRate(int tickRate) {
        return (getWorldObj().getTotalWorldTime() + tickShift) % tickRate == 0;
    }

    //------IInventory------

    @Override
    public int getSizeInventory() {
        return content.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.content[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if(content[slot] != null) {
            ItemStack itemstack;

            if(content[slot].stackSize <= amount) {
                itemstack = content[slot];
                content[slot] = null;

                markDirty();

                return itemstack;
            }else {
                itemstack = content[slot].splitStack(amount);
                if(content[slot].stackSize == 0) content[slot] = null;

                markDirty();

                return itemstack;
            }
        }else return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if(content[slot] != null) {
            ItemStack itemstack = content[slot];
            content[slot] = null;
            return itemstack;
        }else return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        content[slot] = itemStack;

        if(itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) itemStack.stackSize = this.getInventoryStackLimit();

        markDirty();
    }

    @Override
    public String getInventoryName() {
        return "Solar Panel";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return true;
    }

    @Override
    public void openInventory() {
        //Nothing to do here
    }

    @Override
    public void closeInventory() {
        //Nothing to do here
    }

    //------Energy------

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection forgeDirection) {
        return forgeDirection != ForgeDirection.UP;
    }

    public int getEnergyGeneration() {
        return energyGeneration;
    }
}
