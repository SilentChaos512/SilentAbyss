package silentAbyss.tool;

import silentAbyss.Abyss;
import silentAbyss.configuration.Config;
import silentAbyss.core.handlers.ChaosHandler;
import silentAbyss.enchantment.ModEnchantments;
import silentAbyss.item.AbyssGem;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class AbyssSword extends ItemSword {
    
    private int gemType = 0;
    private EnumToolMaterial toolMaterial;
	
	public AbyssSword(int par1, EnumToolMaterial par2EnumToolMaterial, int gemType) {
		super(par1 - Reference.SHIFTED_ID_RANGE_CORRECTION, par2EnumToolMaterial);
		this.gemType = gemType;
		this.toolMaterial = par2EnumToolMaterial;
		this.setUnlocalizedName("swordAbyss" + AbyssGem.names[gemType] + (par2EnumToolMaterial == Abyss.materialEnergizedAbyssGem ? "Plus" : ""));
	}
	
	@Override
    public String getUnlocalizedName(ItemStack stack) {

        StringBuilder s = new StringBuilder();
        s.append("tool.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append("swordAbyss");
        s.append(AbyssGem.names[this.gemType]);
        if (this.toolMaterial == Abyss.materialEnergizedAbyssGem) {
            s.append("Plus");
        }
        
        return s.toString();
    }

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		String s = "SilentAbyss:SwordAbyss";
		
		switch (this.gemType){
    		case 0: s += "Ruby"; break;
    		case 1: s += "Emerald"; break;
    		case 2: s += "Sapphire"; break;
    		case 3: s += "Topaz"; break;
		}
		
		if (this.toolMaterial == Abyss.materialEnergizedAbyssGem) {
			s += "Plus";
		}
		
		itemIcon = iconRegister.registerIcon(s);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		ItemStack material = new ItemStack(ModItems.abyssGem, 1, this.gemType);
		return material.itemID == par2ItemStack.itemID ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }
	
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		//EnchantmentAbsorption.tryActivate(par1ItemStack);
	}
	
	public static void addRecipe(ItemStack tool, ItemStack material, boolean energized) {
		if (energized) {
			GameRegistry.addRecipe(new ShapedOreRecipe(tool, true, new Object[] {" g ", " g ", " s ",
					'g', material, 's', ModItems.ornateStick}));
		}
		else {
			GameRegistry.addRecipe(new ShapedOreRecipe(tool, true, new Object[] {" g ", " g ", " s ",
					'g', material, 's', "stickWood"}));
		}
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase player) {
		
		// Nihil enchantment
		if (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.nihil.effectId, stack) > 0) {
			target.curePotionEffects(new ItemStack(Item.bucketMilk));
			// Chaos cost of Nihil
			ChaosHandler.addChaos(Config.CHAOS_COST_NIHIL);
		}
		
		return super.hitEntity(stack, target, player);
	}
}
