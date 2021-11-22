package net.vademdev.solarfluxreboot.core.energy;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileEnergized extends TileEntity implements IEnergyHandler {
    public EnergyStorage storage;

    public TileEnergized(EnergyStorage storage) {
        this.storage = storage;
    }

    //-------NBT-------

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        storage.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        storage.writeToNBT(nbt);
    }

    //-------Energy-------

    public void outputEnergy() {
        for (ForgeDirection vd : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity te = getWorldObj().getTileEntity(this.xCoord + vd.offsetX, this.yCoord + vd.offsetY, this.zCoord + vd.offsetZ);
            if(te instanceof IEnergyReceiver)
                extractEnergy(vd, ((IEnergyReceiver) te).receiveEnergy(vd.getOpposite(), extractEnergy(vd, this.storage.getMaxExtract(), true), false), false);
            else if(te instanceof IEnergyStorage)
                extractEnergy(vd, ((IEnergyStorage) te).receiveEnergy(extractEnergy(vd, this.storage.getMaxExtract(), true), false), false);
        }
    }

    //-------API Energy-------

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return storage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return storage.extractEnergy(Math.min(maxExtract, storage.getMaxExtract()), simulate);
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return storage.getMaxEnergyStored();
    }

    public int getEnergyStored() {
        return storage.getEnergyStored();
    }

    public void setEnergyStored(int energy) {
        storage.setEnergyStored(energy);
    }
}
