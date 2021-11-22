package net.vademdev.solarfluxreboot.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {
    private boolean defaultLoaded;

    public BlockBase(Material material, boolean defaultLoaded) {
        super(material);

        this.defaultLoaded = defaultLoaded;
    }

    public BlockBase(Material material) {
        this(material, true);
    }

    @Override
    public Block setBlockName(String p_149663_1_) {
        //if(defaultLoaded) ModBlocks.solarList.add(this);
        return super.setBlockName(p_149663_1_);
    }
}
