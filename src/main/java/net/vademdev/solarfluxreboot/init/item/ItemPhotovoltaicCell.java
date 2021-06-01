package net.vademdev.solarfluxreboot.init.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.vademdev.solarfluxreboot.core.blocks.ItemBase;
import net.vademdev.solarfluxreboot.dependencies.Dependency;

import java.util.List;

public class ItemPhotovoltaicCell extends ItemBase {
    private String[] types;

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public ItemPhotovoltaicCell() {
        if(Dependency.BOTANIA.isLoaded()) types = new String[] {"1", "2", "3", "4", "5", "6", "7"};
        else types = new String[] {"1", "2", "3", "4", "5", "6"};

        icons = new IIcon[types.length];

        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedNameInefficiently(ItemStack itemStack) {
        int metadata = itemStack.getItemDamage();
        if(metadata >= types.length || metadata < 0) return super.getUnlocalizedName() + ".unknown";
        return super.getUnlocalizedName() + "." + types[metadata];
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        for(int i = 0; i < types.length; i++) icons[i] = reg.registerIcon(iconString + "_" + types[i]);
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        for(int metadata = 0; metadata < types.length; metadata++) list.add(new ItemStack(item, 1, metadata));
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int metadata) {
        return metadata < types.length && metadata >= 0 ? icons[metadata] : null;
    }
}
