package net.vademdev.solarfluxreboot.init.craftings;

public interface ICraftingHandler {
    default void registerShapedRecipies() {}
    default void registerShapelessRecipies() {}
    default void registerOthersRecipies() {}
}
