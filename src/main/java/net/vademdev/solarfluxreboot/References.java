package net.vademdev.solarfluxreboot;

import net.vademdev.solarfluxreboot.core.utils.Logger;

public class References {
    public static final Logger LOGGER = new Logger(References.MOD_ID);

    public static final String MOD_ID = "SolarFluxReboot";
    public static final String MOD_NAME = "Solar Flux: Reboot";
    public static final String VERSION = "1.0.0";

    public static final String DEPENDENCIES = "required-after:ThermalExpansion;after:Avaritia;after:DraconicEvolution";
}
