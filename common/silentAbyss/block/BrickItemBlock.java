package silentAbyss.block;

import net.minecraft.util.Icon;

public class BrickItemBlock extends ItemBlockSA {

    public BrickItemBlock(int id) {

        super(id);
        setHasSubtypes(true);
        setHasBasicGemSubtypes(true);
        setUnlocalizedName("Brick");
    }

    @Override
    public Icon getIconFromDamage(int damageValue) {

        return ModBlocks.brick.icons[damageValue];
    }
}
