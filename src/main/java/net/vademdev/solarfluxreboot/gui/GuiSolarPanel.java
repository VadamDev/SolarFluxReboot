package net.vademdev.solarfluxreboot.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.vademdev.solarfluxreboot.References;
import net.vademdev.solarfluxreboot.init.tileentity.TileEntitySolarPanel;
import org.lwjgl.opengl.GL11;

public class GuiSolarPanel extends GuiContainer {
    private final ResourceLocation BACKGROUND = new ResourceLocation(References.MOD_ID + ":textures/gui/solar_background.png");

    private TileEntitySolarPanel solarPanel;

    public GuiSolarPanel(TileEntitySolarPanel solarPanel, InventoryPlayer inventory) {
        super(new ContainerSolarPanel(inventory));

        this.solarPanel = solarPanel;

        this.xSize = 176;
        this.ySize = 180;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int mX, int mY) {
        GL11.glColor4f(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(BACKGROUND);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}
