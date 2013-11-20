package silentAbyss.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LampItemBlock extends ItemBlockSA {

    public LampItemBlock(int id) {

        super(id);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int meta) {

        return ModBlocks.lamp.getIcon(0, meta);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        int d = stack.getItemDamage();
        return getUnlocalizedName(Strings.LAMP_NAME + Reference.basicGemNames[d]);
    }
}
