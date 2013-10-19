package silentAbyss.item;

import java.util.List;

import silentAbyss.AbyssLog;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class SigilStone extends ItemSA {
	
	public static final String[] names = {
		"Empty",
		
		"Black",		//1
		"Red",
		"Green",
		"Brown",
		"Blue",
		"Purple",
		"Cyan",
		"LightGray",
		"Gray",
		"Pink",
		"Lime",
		"Yellow",
		"LightBlue",
		"Magenta",
		"Orange",
		"White",		// 16
		
		"Fireball",		// 17
		"Icebolt",
		"Lightning",
		"Earthquake",
		"Healing",
		"Resistance",
		"Remedy",
		"Cloak",
		"Teleport",		// 25
		"Amplify",
		"Speed"
	};

	public SigilStone(int par1) {
		super(par1);
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setUnlocalizedName(Strings.SIGIL_STONE_NAME);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon("SilentAbyss:Sigil");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
    public void getSubItems(int itemID, CreativeTabs tabs, List list) {
		for (int i = 0; i < names.length; ++i) {
			list.add(new ItemStack(itemID, 1, i));
		}
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
//		if (stack.stackTagCompound == null) {
//			stack.setTagCompound(new NBTTagCompound());
//		}
//		
//		if (stack.getItemDamage() == 25) {
//			stack.stackTagCompound.setInteger("X", (int)player.posX);
//			stack.stackTagCompound.setInteger("Y", (int)player.posY);
//			stack.stackTagCompound.setInteger("Z", (int)player.posZ);
//			stack.stackTagCompound.setInteger("D", player.dimension);
//		}
		
//		int damage = stack.getItemDamage();
//		
//		if (damage >= names.length) {
//			stack.stackTagCompound.setString("Name", "unknown:" + damage);
//		}
//		else {
//			stack.stackTagCompound.setString("Name", names[damage]);
//		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		
		int damage = stack.getItemDamage();
		if (damage < names.length){
			list.add(names[damage]);
		}
		
		if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("Y")) {
			NBTTagCompound tags = stack.getTagCompound();
			list.add(AbyssLog.coord(tags.getInteger("X"), tags.getInteger("Y"), tags.getInteger("Z")));
		}
		
//		NBTTagCompound tags = stack.getTagCompound();
//		
//		if (tags != null && tags.hasKey("Name")) {
//			list.add(tags.getString("Name"));
//		}
//		else {
//			int damage = stack.getItemDamage();
//			if (damage < names.length) {
//				//tags.setString("Name", names[damage]);
//				list.add(names[damage]);
//			}
//			else {
//				//tags.setString("Name", "Unknown");
//				list.add("Unknown");
//			}
//			//stack.setTagCompound(tags);
//		}
//		
//		if (tags.hasKey("Y")) {
//			list.add(AbyssLog.coord(tags.getInteger("X"), tags.getInteger("Y"), tags.getInteger("Z")));
//		}
	}
	
	public static void addRecipes() {
		// Base sigil stone.
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 64), "sss", "sas", "sss",
				's', Block.stone, 'a', ModItems.abyssite);
		
		// Colors
		for (int i = 0; i < ItemDye.dyeColorNames.length; ++i) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.sigilStone, 8, i + 1), true,
					new Object[] {"sss", "sds", "sss", 's', ModItems.sigilStone, 'd', "dye" + names[i + 1]}));
		}

		// Fireball - Abyss Ruby
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 17), "sss", "sgs", "sss",
				's', ModItems.sigilStone, 'g', new ItemStack(ModItems.abyssGem, 1, 0));
		// Icebolt - Abyss Sapphire
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 18), "sss", "sgs", "sss",
				's', ModItems.sigilStone, 'g', new ItemStack(ModItems.abyssGem, 1, 2));
		// Lightning - Abyss Emerald
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 19), "sss", "sgs", "sss",
				's', ModItems.sigilStone, 'g', new ItemStack(ModItems.abyssGem, 1, 1));
		// Earthquake - Abyss Topaz
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 20), "sss", "sgs", "sss",
				's', ModItems.sigilStone, 'g', new ItemStack(ModItems.abyssGem, 1, 3));
		// Healing - Glister melon
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 21), "sss", "sms", "sss",
				's', ModItems.sigilStone, 'm', Item.speckledMelon);
		// Resistance - Diamond
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 22), "sss", "sds", "sss",
				's', ModItems.sigilStone, 'd', Item.diamond);
		// Remedy - Spider eye
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 23), "sss", "ses", "sss",
				's', ModItems.sigilStone, 'e', Item.spiderEye);
		// Cloak - Golden carrot
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 24), "sss", "scs", "sss",
				's', ModItems.sigilStone, 'c', Item.goldenCarrot);
		// Teleport - Ender pearl
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 25), "sss", "ses", "sss",
				's', ModItems.sigilStone, 'e', Item.enderPearl);
		// Amplify - Abyssite
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 26), "sss", "sas", "sss",
				's', ModItems.sigilStone, 'a', ModItems.abyssite);
		// Speed - Gold
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilStone, 8, 27), "sss", "sgs", "sss",
				's', ModItems.sigilStone, 'g', Item.ingotGold);
	}
	
	public static boolean isColorSigil(String name) {
		for (int i = 1; i < 17; ++i) {
			if (names[i].equals(name)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
    public String getUnlocalizedName(ItemStack stack) {

        StringBuilder s = new StringBuilder();
        s.append("item.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append(Strings.SIGIL_STONE_NAME);
        
        return s.toString();
    }
}
