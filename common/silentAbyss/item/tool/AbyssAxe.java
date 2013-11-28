package silentAbyss.item.tool;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import silentAbyss.Abyss;
import silentAbyss.item.Gem;
import silentAbyss.item.ModItems;
import silentAbyss.item.TorchBandolier;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AbyssAxe extends ItemToolSA {

    public AbyssAxe(int id, EnumToolMaterial toolMaterial, int gemType) {

        super(id, 3.0f, toolMaterial, EnumGem.values()[gemType], ItemAxe.blocksEffectiveAgainst);
        MinecraftForge.setToolClass(this, "axe", toolMaterial == Abyss.materialEnergizedAbyssGem ? 3 : 2);
    }

    @Override
    public float getStrVsBlock(ItemStack stack, Block block) {

        return block != null
                && (block.blockMaterial == Material.wood || block.blockMaterial == Material.plants || block.blockMaterial == Material.vine || block.blockMaterial == Material.leaves) ? this.efficiencyOnProperMaterial
                : super.getStrVsBlock(stack, block);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        StringBuilder s = new StringBuilder();
        s.append("tool.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append("axeAbyss");
        s.append(Gem.names[gemType.id]);
        if (toolMaterial == Abyss.materialEnergizedAbyssGem) {
            s.append("Plus");
        }

        return s.toString();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {

        String s = "SilentAbyss:AxeAbyss";

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
            GameRegistry.addRecipe(new ShapedOreRecipe(tool, true, new Object[] { "gg ", "gs ", " s ", 'g', material, 's',
                    new ItemStack(ModItems.craftingMaterial, 1, 0) }));
        }
        else {
            GameRegistry.addRecipe(new ShapedOreRecipe(tool, true, new Object[] { "gg ", "gs ", " s ", 'g', material, 's', "stickWood" }));
        }
    }
}
