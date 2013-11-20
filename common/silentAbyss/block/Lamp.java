package silentAbyss.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import silentAbyss.item.Gem;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class Lamp extends BlockSA {

    public Lamp(int id) {

        super(id, Material.glass);
        
        icons = new Icon[Reference.GEM_TYPE_COUNT];
        setStepSound(Block.soundGlassFootstep);
        setLightValue(1.0f);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {

        icons[Reference.INDEX_RUBY] = iconRegister.registerIcon(Strings.RESOURCE_PREFIX + "LampRuby");
        icons[Reference.INDEX_EMERALD] = iconRegister.registerIcon(Strings.RESOURCE_PREFIX + "LampEmerald");
        icons[Reference.INDEX_SAPPHIRE] = iconRegister.registerIcon(Strings.RESOURCE_PREFIX + "LampSapphire");
        icons[Reference.INDEX_TOPAZ] = iconRegister.registerIcon(Strings.RESOURCE_PREFIX + "LampTopaz");
        icons[Reference.INDEX_ABYSSITE] = iconRegister.registerIcon(Strings.RESOURCE_PREFIX + "LampAbyssite");
        icons[Reference.INDEX_PURITE] = iconRegister.registerIcon(Strings.RESOURCE_PREFIX + "LampPurite");
    }

    @Override
    public String getUnlocalizedName() {

        StringBuilder s = new StringBuilder();
        s.append("tile.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append(Strings.LAMP_NAME);
        return s.toString();
    }
    
    @Override
    public void addRecipes() {
        
        for (int i = 0; i < icons.length; ++i) {
            GameRegistry.addShapedRecipe(new ItemStack(this, 1, i), "igi", "rsr", "igi",
                    'i', Item.ingotIron, 'g', Item.glowstone, 'r', Item.redstone, 's', Gem.getGem(i));
            GameRegistry.addShapedRecipe(new ItemStack(this, 1, i), "iri", "gsg", "iri",
                    'i', Item.ingotIron, 'g', Item.glowstone, 'r', Item.redstone, 's', Gem.getGem(i));
        }
    }
}
