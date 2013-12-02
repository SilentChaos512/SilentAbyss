package silentAbyss.block;

import silentAbyss.lib.Names;

public class OreItemBlock extends ItemBlockSA {

    public OreItemBlock(int par1) {

        super(par1);
        setHasSubtypes(true);
        setHasBasicGemSubtypes(true);
        setUnlocalizedName(Names.ORE);
    }
}
