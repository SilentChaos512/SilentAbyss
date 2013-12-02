package silentAbyss.block;

import silentAbyss.lib.Names;

public class BrickItemBlock extends ItemBlockSA {

    public BrickItemBlock(int id) {

        super(id);
        setHasSubtypes(true);
        setHasBasicGemSubtypes(true);
        setUnlocalizedName(Names.BRICK);
    }
}
