package silentAbyss.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import silentAbyss.lib.Names;
import silentAbyss.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Log extends BlockSA {

    public static final String[] names = new String[] { "Derp" };
    @SideOnly(Side.CLIENT)
    Icon[] iconSide, iconEnd;

    public Log(int id) {

        super(id, Material.wood);
        setHasSubtypes(true);
        setUnlocalizedName(Names.LOG);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister reg) {

        iconSide = new Icon[names.length];
        iconEnd = new Icon[names.length];
        String prefix = Strings.RESOURCE_PREFIX + blockName;

        for (int i = 0; i < names.length; ++i) {
            iconSide[i] = reg.registerIcon(prefix + names[i]);
            iconEnd[i] = reg.registerIcon(prefix + names[i] + "Top");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List list) {

        for (int i = 0; i < names.length; ++i) {
            list.add(new ItemStack(id, 1, i));
        }
    }

    @Override
    public int getRenderType() {

        return 31;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta) {

        int k = meta & 12;
        int l = meta & 3;
        return k == 0 && (side == 1 || side == 0) ? iconEnd[l] : (k == 4 && (side == 5 || side == 4) ? iconEnd[l] : (k == 8
                && (side == 2 || side == 3) ? iconEnd[l] : iconSide[l]));
    }
    
    @Override
    public int damageDropped(int meta) {
        
        return meta & 3;
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {

        int j1 = meta & 3;
        byte b0 = 0;

        switch (side) {
            case 0:
            case 1:
                b0 = 0;
                break;
            case 2:
            case 3:
                b0 = 8;
                break;
            case 4:
            case 5:
                b0 = 4;
        }

        return j1 | b0;
    }

    @Override
    public boolean canSustainLeaves(World world, int x, int y, int z) {

        return true;
    }

    @Override
    public boolean isWood(World world, int x, int y, int z) {

        return true;
    }

    public void breakBlock(World world, int x, int y, int z, int id, int meta) {

        byte b0 = 4;
        int j1 = b0 + 1;

        if (world.checkChunksExist(x - j1, y - j1, z - j1, x + j1, y + j1, z + j1)) {
            for (int k1 = -b0; k1 <= b0; ++k1) {
                for (int l1 = -b0; l1 <= b0; ++l1) {
                    for (int i2 = -b0; i2 <= b0; ++i2) {
                        int j2 = world.getBlockId(x + k1, y + l1, z + i2);

                        if (Block.blocksList[j2] != null) {
                            Block.blocksList[j2].beginLeavesDecay(world, x + k1, y + l1, z + i2);
                        }
                    }
                }
            }
        }
    }
}
