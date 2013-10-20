package silentAbyss.item;

import java.util.List;

import silentAbyss.configuration.Config;
import silentAbyss.lib.Strings;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class AbyssGem extends ItemSA {

	public static final String[] names = { "Ruby", "Emerald", "Sapphire", "Topaz" };
	
	public static Icon[] icons = new Icon[9];
	
	public AbyssGem(int par1) {
		super(par1);

		setMaxStackSize(64);
		setHasSubtypes(true);
		setMaxDamage(0);
		setCreativeTab(CreativeTabs.tabMaterials);
	}
	
	@Override
	public boolean hasEffect(ItemStack itemStack) {
		return itemStack.getItemDamage() > 3;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack itemStack) {
		return itemStack.getItemDamage() > 3 ? EnumRarity.rare : EnumRarity.common;
	}
	
	@Override
	public Icon getIconFromDamage(int i) {
		return icons[MathHelper.clamp_int(i, 0, 8)];
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
    public void getSubItems(int itemID, CreativeTabs tabs, List list) {
		for (int i = 0; i < 9; ++i) {
			list.add(new ItemStack(itemID, 1, i));
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int d = itemStack.getItemDamage();
		
		StringBuilder s = new StringBuilder();
		s.append("item.");
		s.append(Strings.RESOURCE_PREFIX);
		s.append("abyss");
		
		if (d == 8) {
			// Abyss diamond
			s.append("Diamond");
		}
		else if (d < 4) {
			// Regular gems.
			s.append(names[d]);
		}
		else {
			// Energized gems.
			s.append(names[d - 4]);
			s.append("Plus");
		}
		
		return s.toString();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		icons[0] = iconRegister.registerIcon("SilentAbyss:AbyssRuby");
		icons[1] = iconRegister.registerIcon("SilentAbyss:AbyssEmerald");
		icons[2] = iconRegister.registerIcon("SilentAbyss:AbyssSapphire");
		icons[3] = iconRegister.registerIcon("SilentAbyss:AbyssTopaz");
		
		icons[4] = iconRegister.registerIcon("SilentAbyss:AbyssRuby");
		icons[5] = iconRegister.registerIcon("SilentAbyss:AbyssEmerald");
		icons[6] = iconRegister.registerIcon("SilentAbyss:AbyssSapphire");
		icons[7] = iconRegister.registerIcon("SilentAbyss:AbyssTopaz");
		
		icons[8] = iconRegister.registerIcon("SilentAbyss:AbyssDiamond");
	}
	
	public static void addRecipes() {
		
		for (int i = 0; i < 4; ++i) {
		    // Supercharged gems
		    GameRegistry.addRecipe(new ItemStack(ModItems.abyssGem, 1, i + 4), "rar", "rgr", "rar",
		            'r', Item.redstone, 'a', ModItems.abyssite, 'g', new ItemStack(ModItems.abyssGem, 1, i));
		    
		    // Shards
			if (Config.SHARDS_PER_GEM == 9) {
				GameRegistry.addShapedRecipe(new ItemStack(ModItems.abyssGem, 1, i), "ggg", "ggg", "ggg", 'g', new ItemStack(ModItems.abyssShard, 1, i));
			}
			else {
				GameRegistry.addShapedRecipe(new ItemStack(ModItems.abyssGem, 1, i), "gg", "gg", 'g', new ItemStack(ModItems.abyssShard, 1, i));
			}
		}
		
		// Abyss diamond
		GameRegistry.addRecipe(new ItemStack(ModItems.abyssGem, 1, 8), "rar", "rgr", "rar",
                'r', Item.redstone, 'a', ModItems.abyssite, 'g', Item.diamond);
	}
}
