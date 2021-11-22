package net.vademdev.solarfluxreboot.core.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class GuiContainerBase extends GuiContainer {
    private List<HoveringText> hoveringTexts = new ArrayList<>();

    public GuiContainerBase(Container container) {
        super(container);
    }

    @Override
    public void drawScreen(int mX, int mY, float opacity) {
        hoveringTexts.clear();

        super.drawScreen(mX, mY, opacity);

        for (HoveringText hoveringText : hoveringTexts) {
            if(hoveringText.canRender(mX, mY)) drawHoveringText(Collections.singletonList(hoveringText.getText()), mX, mY, fontRendererObj);
        }
    }

    protected void addHoveringText(String text, int xStart, int yStart, int xEnd, int yEnd) {
        hoveringTexts.add(new HoveringText(text, xStart, yStart, xEnd, yEnd));
    }

    protected boolean inBounds(int xStart, int yStart, int xEnd, int yEnd, int mX, int mY) {
        return mX <= xEnd && mX >= xStart && mY <= yEnd && mY >= yStart;
    }

    private class HoveringText {
        private int xStart, yStart, xEnd, yEnd;
        private String text;

        public HoveringText(String text, int xStart, int yStart, int xEnd, int yEnd) {
            this.xStart = xStart;
            this.yStart = yStart;
            this.xEnd = xEnd;
            this.yEnd = yEnd;
            this.text = text;
        }

        public boolean canRender(int mX, int mY) {
            return inBounds(xStart, yStart, xEnd, yEnd, mX, mY);
        }

        public String getText() {
            return text;
        }
    }
}
