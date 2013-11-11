package silentAbyss.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import silentAbyss.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSA extends Item {

    public ItemSA(int id) {

        super(id - Reference.SHIFTED_ID_RANGE_CORRECTION);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        // Not sure about this...
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    /**
     * Adds all recipes to make this item to the GameRegistry. Used to separate
     * out recipe code so that ModItems is easier to read.
     */
    public void addRecipes() {

    };
}
