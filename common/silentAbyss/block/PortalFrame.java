package silentAbyss.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import silentAbyss.core.util.PlayerHelper;
import silentAbyss.item.Gem;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PortalFrame extends Block {

    public PortalFrame(int id) {

        super(id, Material.iron);
        setHardness(5.0f);
        setResistance(20.0f);
        setStepSound(Block.soundMetalFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        if (world.getBlockId(x, y, z) == ModBlocks.portalFrame.blockID && PlayerHelper.isPlayerHoldingToolToCreatePortal(player)) {
            return ((Portal) ModBlocks.portal).tryToCreatePortal(world, x, y + 1, z);
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister reg) {

        blockIcon = reg.registerIcon(Reference.MOD_ID + ":PortalFrame");
    }

    @Override
    public String getUnlocalizedName() {

        StringBuilder s = new StringBuilder();
        s.append("tile.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append("portalFrame");
        return s.toString();
    }

    public static void addRecipes() {

        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.portalFrame), "sas", "isi", "sps", 's', Block.stone, 'i', Item.ingotIron, 'a',
                Gem.getGem(Reference.INDEX_ABYSSITE), 'p', Gem.getGem(Reference.INDEX_PURITE));
    }
}
