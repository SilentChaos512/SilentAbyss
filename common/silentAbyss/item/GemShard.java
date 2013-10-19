package silentAbyss.item;

import java.util.List;

import silentAbyss.configuration.Config;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GemShard extends ItemSA {
	
	public static Icon[] icons = new Icon[5];

	public GemShard(int id) {
		
		super(id);
		
		this.setMaxStackSize(64);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return stack.getItemDamage() > 3;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return stack.getItemDamage() > 3 ? EnumRarity.rare : EnumRarity.common;
	}
	
	@Override
	public Icon getIconFromDamage(int i) {
		return icons[i];
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs tabs, List list) {
		for (int i = 0; i < 5; ++i) {
			list.add(new ItemStack(id, 1, i));
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		
		int d = stack.getItemDamage();
		
		StringBuilder sb = new StringBuilder();
		sb.append("item.");
		sb.append(Strings.RESOURCE_PREFIX);
		sb.append("abyss");
		
		if (d < 4) {
			sb.append(AbyssGem.names[d]);
		}
		else {
			sb.append("ite");
		}
		
		sb.append("Shard");
		
		return sb.toString();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		icons[0] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssRubyShard");
		icons[1] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssEmeraldShard");
		icons[2] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssSapphireShard");
		icons[3] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssTopazShard");
		icons[4] = iconRegister.registerIcon(Reference.MOD_ID + ":AbyssiteShard");
	}
	
	public static void addRecipes() {

		for (int i = 0; i < 4; ++i) {
			if (Config.SHARDS_PER_GEM == 9) {
				GameRegistry.addShapelessRecipe(new ItemStack(ModItems.abyssShard, 9, i), new ItemStack(ModItems.abyssGem, 1, i));
			}
			else {
				GameRegistry.addShapelessRecipe(new ItemStack(ModItems.abyssShard, 4, i), new ItemStack(ModItems.abyssGem, 1, i));
			}
		}
		
		if (Config.SHARDS_PER_GEM == 9) {
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.abyssShard, 9, 4), ModItems.abyssite);
		}
		else {
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.abyssShard, 4, 4), ModItems.abyssite);
		}
	}

}
