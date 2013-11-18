package silentAbyss.block;

import java.util.List;

import silentAbyss.lib.Strings;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class BlockSA extends Block {

    public static Icon[] icons;
    
    public boolean hasSubtypes = true;

    public BlockSA(int id, Material material) {

        super(id, material);
        
        setHardness(3.0f);
        setResistance(10.0f);
        setStepSound(Block.soundMetalFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int side, int meta) {

        if (hasSubtypes) {
            return icons[meta];
        }
        else {
            return blockIcon;
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {

        if (hasSubtypes) {
            for (int i = 0; i < icons.length; ++i) {
                subItems.add(new ItemStack(this, 1, i));
            }
        }
        else {
            super.getSubBlocks(par1, tab, subItems);
        }
    }
    
    @Override
    public int damageDropped(int meta) {
        
        if (hasSubtypes) {
            return meta;
        }
        else {
            return 0;
        }
    }
    
    /**
     * This should be overridden in any deriving class.
     * @return
     */
    @Override
    public String getUnlocalizedName() {
        
        return (new StringBuilder()).append("tile.").append(Strings.RESOURCE_PREFIX).append(this.blockID).toString();
    }

    /**
     * This should be overridden in any deriving class and called in ModBlocks.initBlockRecipes.
     */
    public void addRecipes() {
        
    }
}
