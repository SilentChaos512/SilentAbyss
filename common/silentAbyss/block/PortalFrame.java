package silentAbyss.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import silentAbyss.core.util.PlayerHelper;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PortalFrame extends BlockSA {

    public PortalFrame(int id) {

        super(id, Material.iron);
        setHardness(5.0f);
        setResistance(20.0f);
        setStepSound(Block.soundMetalFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
        setUnlocalizedName(Strings.PORTAL_FRAME_NAME);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        if (world.getBlockId(x, y, z) == ModBlocks.portalFrame.blockID && PlayerHelper.isPlayerHoldingToolToCreatePortal(player)) {
            return ((Portal) ModBlocks.portal).tryToCreatePortal(world, x, y + 1, z);
        }
        return false;
    }

    @Override
    public void addRecipes() {

//        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.portalFrame), "sas", "isi", "sps", 's', Block.stone, 'i', Item.ingotIron, 'a',
//                EnumGem.ABYSSITE.getItem(), 'p', EnumGem.PURITE.getItem());
    }
}
