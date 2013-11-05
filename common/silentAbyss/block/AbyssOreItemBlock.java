package silentAbyss.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import silentAbyss.item.AbyssGem;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;

public class AbyssOreItemBlock extends ItemBlock {

    public AbyssOreItemBlock(int par1) {

        super(par1);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damageValue) {

        return damageValue;
    }

    @Override
    public Icon getIconFromDamage(int par1) {

        return AbyssOre.icons[par1];
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        int d = stack.getItemDamage();

        StringBuilder s = new StringBuilder();
        s.append("tile.");
        s.append(Strings.RESOURCE_PREFIX);

        if (d == Reference.INDEX_ABYSSITE) {
            s.append("oreAbyssite");
            return s.toString();
        } else if (d == Reference.INDEX_PURITE) {
            s.append("orePurite");
            return s.toString();
        }

        s.append("oreAbyss");
        s.append(AbyssGem.names[d]);
        return s.toString();
    }
}
