package net.vademdev.solarfluxreboot.init.solar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.vademdev.solarfluxreboot.Main;
import net.vademdev.solarfluxreboot.core.blocks.BlockBase;
import net.vademdev.solarfluxreboot.core.energy.IWrenchable;
import net.vademdev.solarfluxreboot.init.ModTabs;
import net.vademdev.solarfluxreboot.init.tileentity.TileEntitySolarPanel;

public class BlockSolarPanel extends BlockBase implements IWrenchable {
    private SolarTier solarTier;
    
    @SideOnly(Side.CLIENT)
    private IIcon top, sides;

    public BlockSolarPanel(SolarTier solarTier, String name) {
        super(Material.iron, false);
        this.solarTier = solarTier;

        setBlockName(name);

        setHardness(3.5f);
        setResistance(5f);
        setStepSound(soundTypeMetal);

        setBlockBounds(0f, 0f, 0f, 1f, 0.5f, 1f);
        setLightOpacity(255);
        useNeighborBrightness = true;

        isBlockContainer = true;

        setCreativeTab(ModTabs.tab);
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntitySolarPanel(solarTier);
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        top = reg.registerIcon(solarTier.getTopIcon());
        sides = reg.registerIcon(solarTier.getSideIcon());
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 1 ? top : sides;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int pSide, float dX, float dY, float dZ) {
        if(world.isRemote) return false;

        if(onWrench(this, world, x, y, z, player)) return true;

        player.openGui(Main.instance, 0, world, x, y, z);
        return true;
    }

    public SolarTier getSolarTier() {
        return solarTier;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        TileEntity tileEntity = world.getTileEntity(x, y ,z);

        if(tileEntity instanceof IInventory) {
            IInventory inventory = (IInventory) tileEntity;

            for(int slot = 0; slot < inventory.getSizeInventory(); slot++) {
                ItemStack item = inventory.getStackInSlot(slot);

                if(item == null) continue;

                float f = world.rand.nextFloat() * 0.8F + 0.1F;
                float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
                EntityItem entityitem;

                for(float f2 = world.rand.nextFloat() * 0.8F + 0.1F; item.stackSize > 0; world.spawnEntityInWorld(entityitem)) {
                    int j1 = world.rand.nextInt(21) + 10;

                    if (j1 > item.stackSize) j1 = item.stackSize;

                    item.stackSize -= j1;

                    entityitem = new EntityItem(world, ((float) x + f), ((float) y + f1), ((float) z + f2), new ItemStack(item.getItem(), j1, item.getItemDamage()));

                    entityitem.motionX = ((float) world.rand.nextGaussian() * 0.05f);
                    entityitem.motionY = ((float) world.rand.nextGaussian() * 0.25f);
                    entityitem.motionZ = ((float) world.rand.nextGaussian() * 0.05f);

                    if(item.hasTagCompound()) entityitem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                }
            }

            world.func_147453_f(x, y, z, block);
        }

        super.breakBlock(world, x, y, z, block, metadata);
    }
}
