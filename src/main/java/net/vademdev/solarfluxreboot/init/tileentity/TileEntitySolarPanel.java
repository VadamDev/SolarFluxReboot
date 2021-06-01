package net.vademdev.solarfluxreboot.init.tileentity;

import cofh.api.energy.EnergyStorage;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import net.vademdev.solarfluxreboot.core.energy.TileEnergized;
import net.vademdev.solarfluxreboot.init.blocks.solar.SolarTier;

import java.util.Random;

public class TileEntitySolarPanel extends TileEnergized {
    private SolarTier solarTier;

    private final short tickShift;
    private float sunIntensity;
    private int energyGeneration;

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
            if (celestialAngleRadians > Math.PI) {
                celestialAngleRadians = (2 * 3.141592f - celestialAngleRadians);
            }

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

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection forgeDirection) {
        return forgeDirection != ForgeDirection.UP;
    }
}
