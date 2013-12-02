package silentAbyss.block;

import silentAbyss.lib.Names;

public class GemItemBlock extends ItemBlockSA {

    public GemItemBlock(int par1) {

        super(par1);
        setHasSubtypes(true);
        setHasBasicGemSubtypes(true);
        setUnlocalizedName(Names.GEM_BLOCK);
    }
}
