package silentAbyss.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import silentAbyss.item.AbyssGem;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;

public class BrickItemBlock extends ItemBlock {

    public BrickItemBlock(int id) {

        super(id);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damageValue) {

        return damageValue;
    }

    @Override
    public Icon getIconFromDamage(int damageValue) {

        return Brick.icons[damageValue];
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        int d = stack.getItemDamage();

        StringBuilder s = new StringBuilder();
        s.append("tile.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append("blockAbyssBrick");

        if (d == Reference.INDEX_ABYSSITE) {
            s.append("Dark");
        } else if (d == Reference.INDEX_PURITE) {
            s.append("Light");
        } else {
            s.append(AbyssGem.names[d]);
        }

        return s.toString();
    }

}
