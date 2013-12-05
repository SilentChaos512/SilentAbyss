package silentAbyss.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import silentAbyss.core.registry.SARegistry;
import silentAbyss.core.util.PlayerHelper;
import silentAbyss.lib.Names;

public class PortalFrame extends BlockSA {

    public PortalFrame(int id) {

        super(id, Material.iron);
        setHardness(5.0f);
        setResistance(20.0f);
        setStepSound(Block.soundMetalFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
        setUnlocalizedName(Names.PORTAL_FRAME);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        if (world.getBlockId(x, y, z) == SARegistry.getBlock(Names.PORTAL_FRAME).blockID
                && PlayerHelper.isPlayerHoldingToolToCreatePortal(player)) {
            return ((Portal) SARegistry.getBlock(Names.PORTAL)).tryToCreatePortal(world, x, y + 1, z);
        }
        return false;
    }

    @Override
    public void addRecipes() {

        // GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.portalFrame), "sas", "isi", "sps", 's', Block.stone,
        // 'i', Item.ingotIron, 'a',
        // EnumGem.ABYSSITE.getItem(), 'p', EnumGem.PURITE.getItem());
    }
}
