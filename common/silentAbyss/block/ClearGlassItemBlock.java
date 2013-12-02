package silentAbyss.block;

import silentAbyss.lib.Names;

public class ClearGlassItemBlock extends ItemBlockSA {

    public ClearGlassItemBlock(int id) {

        super(id);
        setHasSubtypes(true);
        setHasBasicGemSubtypes(true);
        setUnlocalizedName(Names.CLEAR_GLASS);
    }
}
