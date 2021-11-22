package net.vademdev.solarfluxreboot.init.solar;

import net.vademdev.solarfluxreboot.References;
import net.vademdev.solarfluxreboot.dependencies.Dependency;

public enum SolarTier {
    TIER_1(1, 1, 8, 25000),
    TIER_2(2, 8, 64, 125000),
    TIER_3(3, 32, 256, 425000),
    TIER_4(4, 128, 1024, 2000000),
    TIER_5(5, 512, 4096, 8000000),
    TIER_6(6, 2048, 16384, 32000000),
    TIER_7(7, 8192, 64000, 64000000),
    TIER_8(8, 32768, 256000, 128000000),

    TIER_WYVERN(9, Dependency.DRACONIC_EVOLUTION, 65536, 512000, 256000000),
    TIER_DRACONIC(10, Dependency.DRACONIC_EVOLUTION, 131072, 1024000, 512000000),
    TIER_CHAOTIC(11, Dependency.DRACONIC_EVOLUTION, 524288, 4096000, 2048000000),

    TIER_NEUTRONIUM(12, Dependency.AVARITIA, 8388608 , 32768000 , 2147483640),
    TIER_INFINITY(13, Dependency.AVARITIA, 33554432, 65536000  , 2147483640),

    TIER_MANASTEEL(14, Dependency.BOTANIA, 512, 4096, 8000000),
    TIER_ELEMENTIUM(15, Dependency.BOTANIA, 3072, 12288, 24000000),
    TIER_TERRASTEEL(16, Dependency.BOTANIA, 8192, 64000, 64000000);

    private int index, energyGeneration, energyTransfer, energyCapacity;
    private Dependency dependency;
    private String topIcon, sideIcon;

    SolarTier(int index, Dependency dependency, int energyGeneration, int energyTransfer, int energyCapacity) {
        this.index = index;
        this.dependency = dependency;
        this.energyGeneration = energyGeneration;
        this.energyTransfer = energyTransfer;
        this.energyCapacity = energyCapacity;

        this.topIcon = References.MOD_ID + ":solar_panel_" + index + "_top";
        this.sideIcon = References.MOD_ID + ":solar_panel_" + index + "_side";
    }

    SolarTier(int index, int energyGeneration, int energyTransfer, int energyCapacity) {
        this(index, Dependency.THERMAL_EXPENTION, energyGeneration, energyTransfer, energyCapacity);
    }

    public boolean isDependencyActive() {
        return dependency.isLoaded();
    }

    public int getEnergyGeneration() {
        return energyGeneration;
    }

    public int getEnergyTransfer() {
        return energyTransfer;
    }

    public int getEnergyCapacity() {
        return energyCapacity;
    }

    public BlockSolarPanel getSolarTierAsSolar() {
        return new BlockSolarPanel(this, "solar_panel_" + index);
    }

    public String getTopIcon() {
        return topIcon;
    }

    public String getSideIcon() {
        return sideIcon;
    }
}
