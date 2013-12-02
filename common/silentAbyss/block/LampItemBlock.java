package silentAbyss.block;

import silentAbyss.lib.Names;

public class LampItemBlock extends ItemBlockSA {

    public LampItemBlock(int id) {

        super(id);
        setHasSubtypes(true);
        setHasBasicGemSubtypes(true);
        setUnlocalizedName(Names.LAMP);
    }
}
