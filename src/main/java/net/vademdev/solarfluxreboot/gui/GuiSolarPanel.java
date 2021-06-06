package net.vademdev.solarfluxreboot.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.vademdev.solarfluxreboot.References;
import net.vademdev.solarfluxreboot.init.tileentity.TileEntitySolarPanel;
import org.apache.commons.codec.language.bm.Lang;
import org.lwjgl.opengl.GL11;

public class GuiSolarPanel extends GuiContainer {
    private final ResourceLocation background = new ResourceLocation(References.MOD_ID.toLowerCase(), "textures/gui/background.png");
    private static final ResourceLocation elements = new ResourceLocation(References.MOD_ID.toLowerCase(), "textures/gui/elements.png");

    private TileEntitySolarPanel solarPanel;
    private InventoryPlayer inventoryPlayer;

    public GuiSolarPanel(TileEntitySolarPanel solarPanel, InventoryPlayer inventoryPlayer) {
        super(new ContainerSolarPanel(solarPanel, inventoryPlayer));

        this.solarPanel = solarPanel;
        this.inventoryPlayer = inventoryPlayer;

        ySize = 180;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int oX = (this.width - this.xSize) / 2;
        int oY = (this.height - this.ySize) / 2;

        //BACKGROUND
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(oX, oY, 0, 0, xSize, ySize);

        //SLOTS
        mc.getTextureManager().bindTexture(elements);
        GL11.glDisable(GL11.GL_LIGHTING);
        for(int i = 0; i < 5; i++) drawTexturedModalRect(oX + 17 + i * 18 - 1, oY + 59 - 1, 0, 0, 18, 18);
    }
}
