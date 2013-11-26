package silentAbyss.block;

import net.minecraft.util.Icon;
import silentAbyss.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LampItemBlock extends ItemBlockSA {

    public LampItemBlock(int id) {

        super(id);
        setHasSubtypes(true);
        setHasBasicGemSubtypes(true);
        setUnlocalizedName(Strings.LAMP_NAME);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int meta) {

        return ModBlocks.lamp.getIcon(0, meta);
    }
}
