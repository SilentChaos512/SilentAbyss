package silentAbyss.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import silentAbyss.core.registry.SARegistry;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Names;

public class Ore extends BlockSA {

    public Ore(int id) {

        super(id, Material.rock);
        icons = new Icon[EnumGem.basic().length];
        setHardness(3.0f);
        setResistance(5.0f);
        setStepSound(Block.soundStoneFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
        setHasSubtypes(true);
        setHasBasicGemSubtypes(true);
        setUnlocalizedName(Names.ORE);
    }

    @Override
    public int idDropped(int par1, Random random, int par2) {

        return SARegistry.getItem(Names.GEM_ITEM).itemID;
    }

    @Override
    public int damageDropped(int meta) {

        return meta;
    }

    @Override
    public int quantityDropped(Random random) {

        return 1;
    }

    public int quantityDroppedWithBonus(int par1, Random random) {

        if (par1 > 0) {
            int j = random.nextInt(par1 + 2) - 1;

            if (j < 0) {
                j = 0;
            }

            return quantityDropped(random) * (j + 1);
        }
        else {
            return quantityDropped(random);
        }
    }

    @Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {

        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);

        if (idDropped(par5, par1World.rand, par7) != blockID) {
            int j1 = 1 + par1World.rand.nextInt(5);
            dropXpOnBlockBreak(par1World, par2, par3, par4, j1);
        }
    }

    @Override
    public String getUnlocalizedName() {

        return getUnlocalizedName("ore");
    }

    @Override
    public void addOreDict() {

        OreDictionary.registerOre("oreRuby", new ItemStack(this, 1, EnumGem.RUBY.id));
        OreDictionary.registerOre("oreEmerald", new ItemStack(this, 1, EnumGem.EMERALD.id));
        OreDictionary.registerOre("oreSapphire", new ItemStack(this, 1, EnumGem.SAPPHIRE.id));
        OreDictionary.registerOre("oreTopaz", new ItemStack(this, 1, EnumGem.TOPAZ.id));
        OreDictionary.registerOre("oreAbyssite", new ItemStack(this, 1, EnumGem.ABYSSITE.id));
        OreDictionary.registerOre("orePurite", new ItemStack(this, 1, EnumGem.PURITE.id));
    }
}
