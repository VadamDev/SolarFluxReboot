package net.vademdev.solarfluxreboot.init.blocks.solar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.vademdev.solarfluxreboot.Main;
import net.vademdev.solarfluxreboot.core.blocks.BlockBase;
import net.vademdev.solarfluxreboot.core.energy.IWrenchable;
import net.vademdev.solarfluxreboot.init.tileentity.TileEntitySolarPanel;

public class BlockSolarPanel extends BlockBase implements IWrenchable {
    private SolarTier solarTier;
    
    @SideOnly(Side.CLIENT)
    private IIcon top, sides;

    public BlockSolarPanel(SolarTier solarTier) {
        super(Material.iron, false);
        this.solarTier = solarTier;

        setHardness(3.5f);
        setResistance(5f);
        setStepSound(soundTypeMetal);

        setBlockBounds(0f, 0f, 0f, 1f, 0.5f, 1f);
        setLightOpacity(255);
        useNeighborBrightness = true;
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
        if(onWrench(this, world, x, y, z, player)) return true;

        if(world.isRemote) {
            player.openGui(Main.instance, 0, world, x, y, z);
            return true;
        }

        return false;
    }

    public SolarTier getSolarTier() {
        return solarTier;
    }
}
