package net.vademdev.solarfluxreboot.gui;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.vademdev.solarfluxreboot.core.gui.AdvancedGuiHandler;
import net.vademdev.solarfluxreboot.init.tileentity.TileEntitySolarPanel;

public class GuiHandler extends AdvancedGuiHandler {
    @Override
    public Object getGuiElement(int ID, EntityPlayer player, TileEntity tileEntity, Side side) {
        switch(side) {
            case SERVER:
                if(tileEntity instanceof TileEntitySolarPanel) {
                    return new ContainerSolarPanel((TileEntitySolarPanel) tileEntity, player.inventory);
                }

                break;
            case CLIENT:
                if(tileEntity instanceof TileEntitySolarPanel) {
                    return new GuiSolarPanel((TileEntitySolarPanel) tileEntity, player.inventory);
                }

                break;
        }

        return null;
    }
}
