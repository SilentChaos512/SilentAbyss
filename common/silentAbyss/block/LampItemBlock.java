package silentAbyss.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LampItemBlock extends ItemBlock {

    public LampItemBlock(int id) {

        super(id);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta) {

        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int meta) {

        return Lamp.icons[meta];
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        return (new StringBuilder()).append("tile.").append(Strings.RESOURCE_PREFIX).append("lamp")
                .append(Reference.basicGemNames[stack.getItemDamage()]).toString();
    }
}
