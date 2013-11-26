package silentAbyss.block;

import net.minecraft.util.Icon;
import silentAbyss.lib.Strings;

public class OreItemBlock extends ItemBlockSA {

    public OreItemBlock(int par1) {

        super(par1);
        setHasSubtypes(true);
        setHasBasicGemSubtypes(true);
        setUnlocalizedName(Strings.ORE_NAME);
    }

    @Override
    public Icon getIconFromDamage(int par1) {

        return ModBlocks.ore.icons[par1];
    }
}
