package silentAbyss.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import silentAbyss.item.Gem;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Names;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;

public class Lamp extends BlockSA {

    public Lamp(int id) {

        super(id, Material.glass);
        icons = new Icon[EnumGem.basic().length];
        setStepSound(Block.soundGlassFootstep);
        setLightValue(1.0f);
        setHasSubtypes(true);
        setHasBasicGemSubtypes(true);
        setUnlocalizedName(Names.LAMP);
    }

    @Override
    public void addRecipes() {

        for (int i = 0; i < icons.length; ++i) {
            GameRegistry.addShapedRecipe(new ItemStack(this, 1, i), "igi", "rsr", "igi", 'i', Item.ingotIron, 'g', Item.glowstone, 'r',
                    Item.redstone, 's', EnumGem.values()[i].getItem());
            GameRegistry.addShapedRecipe(new ItemStack(this, 1, i), "iri", "gsg", "iri", 'i', Item.ingotIron, 'g', Item.glowstone, 'r',
                    Item.redstone, 's', EnumGem.values()[i].getItem());
        }
    }
}
