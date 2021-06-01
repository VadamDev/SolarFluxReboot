package net.vademdev.solarfluxreboot.init;

import net.vademdev.solarfluxreboot.dependencies.Dependency;

public class ModCraftings {
    public static void init() {
        for (Dependency value : Dependency.values()) {
            if(!value.isLoaded()) continue;
            value.registerCraftings();
        }
    }
}
