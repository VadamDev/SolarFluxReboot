package net.vademdev.solarfluxreboot.init.tileentity;

import cofh.api.energy.EnergyStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import net.vademdev.solarfluxreboot.core.energy.TileEnergized;
import net.vademdev.solarfluxreboot.init.solar.SolarTier;

import java.util.Random;

public class TileEntitySolarPanel extends TileEnergized implements IInventory{
    private SolarTier solarTier;

    private final short tickShift;
    private float sunIntensity;
    private int energyGeneration;

    private ItemStack[] contents = new ItemStack[5];

    public TileEntitySolarPanel() {
        this(SolarTier.TIER_1);
    }

    public TileEntitySolarPanel(SolarTier solarTier) {
        super(new EnergyStorage(solarTier.getEnergyCapacity(), solarTier.getEnergyTransfer(), solarTier.getEnergyTransfer()));

        tickShift = (short) new Random().nextInt(100);

        this.solarTier = solarTier;
    }

    //------Energy Generation------

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

    //------NBT------

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        solarTier = SolarTier.valueOf(compound.getString("SolarTier"));
        storage.setCapacity(solarTier.getEnergyCapacity());
        storage.setMaxTransfer(solarTier.getEnergyTransfer());

        super.readFromNBT(compound);

        //Save inventory
        NBTTagList nbttaglist = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        contents = new ItemStack[this.getSizeInventory()];
        for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);

            int j = nbttagcompound1.getByte("Slot") & 255;
            if(j >= 0 && j < contents.length) contents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setString("SolarTier", solarTier.name());

        //Save inventory
        NBTTagList nbttaglist = new NBTTagList();
        for(int i = 0; i < contents.length; ++i) {
            if(contents[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                contents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        compound.setTag("Items", nbttaglist);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbttagcompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.func_148857_g());
    }

    //------IInventory------

    @Override
    public int getSizeInventory() {
        return contents.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return contents[slot];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int amount)
    {
        if(contents[slotIndex] != null) {
            ItemStack itemstack;

            if(contents[slotIndex].stackSize <= amount) {
                itemstack = this.contents[slotIndex];
                contents[slotIndex] = null;
                markDirty();
                return itemstack;
            }else {
                itemstack = this.contents[slotIndex].splitStack(amount);

                if(contents[slotIndex].stackSize == 0) contents[slotIndex] = null;
                markDirty();

                return itemstack;
            }
        }else return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex) {
        if(contents[slotIndex] != null) {
            ItemStack itemstack = this.contents[slotIndex];
            contents[slotIndex] = null;
            return itemstack;
        }else return null;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack stack) {
        contents[slotIndex] = stack;
        if(stack != null && stack.stackSize > getInventoryStackLimit()) stack.stackSize = getInventoryStackLimit();
        markDirty();
    }

    @Override
    public String getInventoryName() {
        return "SolarPanel";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
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
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    //------Energy------

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection forgeDirection) {
        return forgeDirection != ForgeDirection.UP;
    }

    //------GETTER AND SETTERS------

    public int getCurrentEnergyGeneration() {
        return energyGeneration;
    }

    public void setCurrentEnergyGeneration(int energyGeneration) {
        this.energyGeneration = energyGeneration;
    }

    public float getSunIntensity() {
        return sunIntensity;
    }

    public void setSunIntensity(float sunIntensity) {
        this.sunIntensity = sunIntensity;
    }

    public SolarTier getSolarTier() {
        return solarTier;
    }
}
