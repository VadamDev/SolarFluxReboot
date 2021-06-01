package net.vademdev.solarfluxreboot.core.utils;

import cpw.mods.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public class Logger {
    private String modId;

    public Logger(String modId) {
        this.modId = modId;
    }

    public void trace(String str, Object... data) {
        log(Level.TRACE, str, data);
    }

    public void debug(String str, Object... data) {
        log(Level.DEBUG, str, data);
    }

    public void info(String str, Object... data) {
        log(Level.INFO, str, data);
    }

    public void warn(String str, Object... data) {
        log(Level.WARN, str, data);
    }

    public void error(String str, Object... data) {
        log(Level.ERROR, str, data);
    }

    public void fatal(String str, Object... data) {
        log(Level.FATAL, str, data);
    }

    public void off(String str, Object... data) {
        log(Level.OFF, str, data);
    }

    private void log(Level logLevel, String str, Object... data) {
        FMLLog.log(modId, logLevel, str, data);
    }
}
