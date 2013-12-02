package silentAbyss.item.tool;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import silentAbyss.Abyss;
import silentAbyss.core.registry.SARegistry;
import silentAbyss.item.Gem;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Names;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AbyssPickaxe extends ItemToolSA {

    public AbyssPickaxe(int id, EnumToolMaterial toolMaterial, int gemType) {

        super(id, 2.0f, toolMaterial, EnumGem.values()[gemType], ItemPickaxe.blocksEffectiveAgainst);
        MinecraftForge.setToolClass(this, "pickaxe", toolMaterial == Abyss.materialEnergizedAbyssGem ? 3 : 2);
        boolean b = toolMaterial == Abyss.materialEnergizedAbyssGem;
        addRecipe(new ItemStack(this), EnumGem.values()[gemType + (b ? 6 : 0)].getItem(), b);
    }

    @Override
    public boolean canHarvestBlock(Block block) {

        return block == Block.obsidian ? this.toolMaterial.getHarvestLevel() == 3
                : (block != Block.blockDiamond && block != Block.oreDiamond ? (block != Block.oreEmerald && block != Block.blockEmerald ? (block != Block.blockGold
                        && block != Block.oreGold ? (block != Block.blockIron && block != Block.oreIron ? (block != Block.blockLapis
                        && block != Block.oreLapis ? (block != Block.oreRedstone && block != Block.oreRedstoneGlowing ? (block.blockMaterial == Material.rock ? true
                        : (block.blockMaterial == Material.iron ? true : block.blockMaterial == Material.anvil))
                        : this.toolMaterial.getHarvestLevel() >= 2)
                        : this.toolMaterial.getHarvestLevel() >= 1)
                        : this.toolMaterial.getHarvestLevel() >= 1)
                        : this.toolMaterial.getHarvestLevel() >= 2)
                        : this.toolMaterial.getHarvestLevel() >= 2)
                        : this.toolMaterial.getHarvestLevel() >= 2);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, Block block) {

        return block != null
                && (block.blockMaterial == Material.iron || block.blockMaterial == Material.anvil || block.blockMaterial == Material.rock) ? this.efficiencyOnProperMaterial
                : super.getStrVsBlock(stack, block);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        StringBuilder s = new StringBuilder();
        s.append("tool.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append("pickaxeAbyss");
        s.append(Gem.names[gemType.id]);
        if (toolMaterial == Abyss.materialEnergizedAbyssGem) {
            s.append("Plus");
        }

        return s.toString();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {

        String s = "SilentAbyss:PickaxeAbyss";

        switch (gemType.id) {
            case 0:
                s += "Ruby";
                break;
            case 1:
                s += "Emerald";
                break;
            case 2:
                s += "Sapphire";
                break;
            case 3:
                s += "Topaz";
                break;
        }

        if (toolMaterial == Abyss.materialEnergizedAbyssGem) {
            s += "Plus";
        }

        itemIcon = iconRegister.registerIcon(s);
    }

    public static void addRecipe(ItemStack tool, ItemStack material, boolean energized) {

        if (energized) {
            GameRegistry.addRecipe(new ShapedOreRecipe(tool, true, new Object[] { "ggg", " s ", " s ", 'g', material, 's',
                    new ItemStack(SARegistry.getItem(Names.CRAFTING_MATERIALS), 1, 0) }));
        }
        else {
            GameRegistry.addRecipe(new ShapedOreRecipe(tool, true, new Object[] { "ggg", " s ", " s ", 'g', material, 's', "stickWood" }));
        }
    }
}
