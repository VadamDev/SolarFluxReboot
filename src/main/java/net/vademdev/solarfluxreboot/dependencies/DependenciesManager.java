package net.vademdev.solarfluxreboot.dependencies;

import cpw.mods.fml.common.Loader;
import net.vademdev.solarfluxreboot.References;
import net.vademdev.solarfluxreboot.core.utils.Logger;

import javax.swing.plaf.BorderUIResource;
import java.util.HashMap;
import java.util.Map;

public class DependenciesManager {
    private Map<Dependency, Boolean> dependencyActiveMap = new HashMap<>();

    private Logger logger = References.LOGGER;

    public void init() {
        for (Dependency dependency : Dependency.values()) {
            String dependencyId = dependency.getModid();
            boolean dependencyLoaded = Loader.isModLoaded(dependencyId);

            dependencyActiveMap.put(dependency, dependencyLoaded);

            if(dependencyLoaded) logger.info("Loaded " + dependencyId + " dependency !");
        }
    }

    protected boolean isDependencyLoaded(Dependency dependency) {
        return dependencyActiveMap.get(dependency);
    }
}
