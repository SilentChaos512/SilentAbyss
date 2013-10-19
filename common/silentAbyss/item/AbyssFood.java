package silentAbyss.item;

import java.util.List;

import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class AbyssFood extends ItemFood {

	private int damageValue;
	private Icon[] icons = new Icon[1];
	//public static String[] names = { "foodPotatoStick" };
	
	public AbyssFood(int id, int healAmount, float saturationModifier, boolean isWolfFavoriteMeat, int damageValue) {
		
		super(id - Reference.SHIFTED_ID_RANGE_CORRECTION, healAmount, saturationModifier, isWolfFavoriteMeat);
		this.damageValue = damageValue;
	}
	
	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
//		if (par1ItemStack.stackTagCompound == null) {
//			par1ItemStack.setTagCompound(new NBTTagCompound());
//		}
//		
//		par1ItemStack.stackTagCompound.setString("tip", "Totally not like the Mindcrack logo.");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (damageValue == 0) {
			// Potato on a stick.
			par3List.add("Totally not like the Mindcrack logo.");
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		icons[0] = iconRegister.registerIcon("SilentAbyss:PotatoStick");
		itemIcon = icons[0];
	}
	
	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onEaten(stack, world, player);
		if (damageValue == 0) {
			// Potato on a stick.
			// TODO: Return a stick to player?
		}
		
		return stack;
	}
	
	@Override
    public String getUnlocalizedName(ItemStack stack) {

        StringBuilder s = new StringBuilder();
        s.append("item.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append(Strings.POTATO_STICK_NAME);
        
        return s.toString();
    }
}
