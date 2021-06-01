package net.vademdev.solarfluxreboot.core.energy;

import buildcraft.api.tools.IToolWrench;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IWrenchable {
    default boolean onWrench(Block block, World world, int x, int y, int z, EntityPlayer player) {
        if(!world.isRemote && player.isSneaking()) {
            ItemStack tool = player.getCurrentEquippedItem();
            if (tool != null && tool.getItem() instanceof IToolWrench) {
                world.setBlockToAir(x, y, z);

                EntityItem entityItem = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, new ItemStack(block));
                entityItem.motionX = 0;
                entityItem.motionZ = 0;
                world.spawnEntityInWorld(entityItem);

                return true;
            }
        }

        return false;
    }
}
