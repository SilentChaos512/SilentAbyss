package silentAbyss.block;

import net.minecraft.util.Icon;
import silentAbyss.lib.Strings;

public class GemItemBlock extends ItemBlockSA {

    public GemItemBlock(int par1) {

        super(par1);
        setHasSubtypes(true);
        setHasBasicGemSubtypes(true);
        setUnlocalizedName(Strings.GEM_BLOCK_NAME);
    }

    @Override
    public Icon getIconFromDamage(int par1) {

        return ModBlocks.gem.icons[par1];
    }
}
