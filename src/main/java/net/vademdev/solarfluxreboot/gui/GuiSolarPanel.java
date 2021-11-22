package net.vademdev.solarfluxreboot.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.vademdev.solarfluxreboot.References;
import net.vademdev.solarfluxreboot.core.gui.GuiContainerBase;
import net.vademdev.solarfluxreboot.init.tileentity.TileEntitySolarPanel;
import org.apache.commons.codec.language.bm.Lang;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;

public class GuiSolarPanel extends GuiContainerBase {
    private final ResourceLocation background = new ResourceLocation(References.MOD_ID.toLowerCase(), "textures/gui/background.png");
    private final ResourceLocation elements = new ResourceLocation(References.MOD_ID.toLowerCase(), "textures/gui/elements.png");

    private TileEntitySolarPanel solarPanel;
    private InventoryPlayer playerInventory;

    public GuiSolarPanel(TileEntitySolarPanel solarPanel, InventoryPlayer inventoryPlayer) {
        super(new ContainerSolarPanel(solarPanel, inventoryPlayer));

        this.solarPanel = solarPanel;
        this.playerInventory = inventoryPlayer;

        ySize = 180;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mX, int mY) {
        //TODO: GAUGE AND TEXT HERE

        fontRendererObj.drawString(playerInventory.hasCustomInventoryName() ? playerInventory.getInventoryName() : I18n.format(playerInventory.getInventoryName()), 8, ySize - 96 + 2, 0x404040);
        fontRendererObj.drawString(String.format("%s: %,d %s", "Generation", solarPanel.getCurrentEnergyGeneration(), "RF/T"), 8, 8 + 10, 0x404040);
        fontRendererObj.drawString(String.format("%s: %,d %s", "Transfer", solarPanel.getSolarTier().getEnergyTransfer(), "RF/T"), 8, 8 + 20, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int mX, int mY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int oX = (this.width - this.xSize) / 2;
        int oY = (this.height - this.ySize) / 2;

        //BACKGROUND
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(oX, oY, 0, 0, xSize, ySize);

        //SLOTS
        mc.getTextureManager().bindTexture(elements);

        GL11.glDisable(GL11.GL_LIGHTING);

        for(int i = 0; i < 5; i++)
            drawTexturedModalRect(oX + 17 + i * 18 - 1, oY + 59 - 1, 18, 0, 18, 18);

        drawPowerGauge(oX + 150, oY + 12);
        drawSunIntencityGauge(oX + 150, oY + 61);
    }

    private void drawPowerGauge(int x, int y) {
        int energyStored = solarPanel.getEnergyStored();
        int maxEnergyStored = solarPanel.getSolarTier().getEnergyCapacity();

        drawTexturedModalRect(x, y, 16, 36, 14, 42);
        drawTexturedModalRect(x, y, 0, 36, 14, (int) (42 - (energyStored * 42L / maxEnergyStored)));

        addHoveringText(energyStored + " / " + maxEnergyStored + " RF", x, y, x + 14, y + 42);
    }

    private void drawSunIntencityGauge(int x, int y) {
        float dayAlpha = solarPanel.getSunIntensity();

        if(dayAlpha <= 0.11) {
            drawTexturedModalRect(x, y, 33, 38, 14, 14);
        }else {
            GL11.glPushMatrix();

            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, dayAlpha);

            drawTexturedModalRect(x, y, 49, 38, 14, 14);

            GL11.glPopMatrix();
        }

        addHoveringText("Sun Intencity: " + Math.round(dayAlpha * 100) + "%", x, y, x + 14, y + 14);
    }
}
