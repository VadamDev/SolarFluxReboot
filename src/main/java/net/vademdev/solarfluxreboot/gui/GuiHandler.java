package net.vademdev.solarfluxreboot.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.vademdev.solarfluxreboot.init.tileentity.TileEntitySolarPanel;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);

        if(te instanceof TileEntitySolarPanel) {
            return new ContainerSolarPanel(player.inventory);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);

        if(te instanceof TileEntitySolarPanel) {
            return new GuiSolarPanel((TileEntitySolarPanel) te, player.inventory);
        }

        return null;
    }
}
