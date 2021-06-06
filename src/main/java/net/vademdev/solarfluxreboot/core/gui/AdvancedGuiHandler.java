package net.vademdev.solarfluxreboot.core.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class AdvancedGuiHandler implements IGuiHandler {
    public abstract Object getGuiElement(int ID, EntityPlayer player, TileEntity tileEntity, Side side);

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return getGuiElement(ID, player, world.getTileEntity(x, y, z), Side.SERVER);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return getGuiElement(ID, player, world.getTileEntity(x, y, z), Side.CLIENT);
    }
}
