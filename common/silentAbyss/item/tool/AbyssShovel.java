package silentAbyss.item.tool;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.oredict.ShapedOreRecipe;
import silentAbyss.Abyss;
import silentAbyss.item.EnumGem;
import silentAbyss.item.Gem;
import silentAbyss.item.ModItems;
import silentAbyss.item.TorchBandolier;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AbyssShovel extends ItemToolSA {

    public AbyssShovel(int id, EnumToolMaterial toolMaterial, int gemType) {

        super(id, 1.0f, toolMaterial, EnumGem.values()[gemType], ItemSpade.blocksEffectiveAgainst);
    }

    @Override
    public boolean canHarvestBlock(Block par1Block) {

        return par1Block == Block.snow ? true : par1Block == Block.blockSnow;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        StringBuilder s = new StringBuilder();
        s.append("tool.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append("shovelAbyss");
        s.append(Gem.names[gemType.getID()]);
        if (toolMaterial == Abyss.materialEnergizedAbyssGem) {
            s.append("Plus");
        }

        return s.toString();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {

        String s = "SilentAbyss:ShovelAbyss";

        switch (gemType.getID()) {
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
            GameRegistry.addRecipe(new ShapedOreRecipe(tool, true, new Object[] { " g ", " s ", " s ", 'g', material, 's',
                    new ItemStack(ModItems.craftingMaterial, 1, 0) }));
        }
        else {
            GameRegistry.addRecipe(new ShapedOreRecipe(tool, true, new Object[] { " g ", " s ", " s ", 'g', material, 's', "stickWood" }));
        }
    }
}
