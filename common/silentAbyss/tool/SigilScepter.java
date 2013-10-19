package silentAbyss.tool;

import java.util.List;

import silentAbyss.configuration.Config;
import silentAbyss.core.handlers.ChaosHandler;
import silentAbyss.core.util.LogHelper;
import silentAbyss.core.util.NBTHelper;
import silentAbyss.entity.projectile.EntityProjectileMagic;
import silentAbyss.item.AbyssGem;
import silentAbyss.item.ItemSA;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Strings;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SigilScepter extends ItemSA {

	public Icon[] icons = new Icon[4];
	
	public SigilScepter(int par1) {
		super(par1);
		
		setFull3D();
		setMaxStackSize(1);
		setHasSubtypes(true);
		setMaxDamage(0);
		setCreativeTab(CreativeTabs.tabTools);
	}
	
	public void scepterEffects(ItemStack stack, World world, EntityPlayer player) {
		NBTTagCompound tags = stack.getTagCompound();
		
		if (tags == null || world.isRemote) {
			return;
		}
		
		String color = "White", effect = "";
		
		if (tags.hasKey("Color")) {
			color = tags.getString("Color");
		}
		if (tags.hasKey("Effect")) {
			effect = tags.getString("Effect");
		}
		else {
			// No effect
			return;
		}
		
		if (effect.equals("Fireball")) {
			EntityProjectileMagic p = new EntityProjectileMagic(world, player).setType(1).setColor(color).setDamage(Config.SIGIL_BASE_PROJECTILE_DAMAGE + Config.SIGIL_BASE_PROJECTILE_DAMAGE / 2);
			world.spawnEntityInWorld(p);
			world.playSoundAtEntity(player, "mob.ghast.fireball", 0.5f, 0.5f / (itemRand.nextFloat() * 0.4f + 0.8f));
		}
		else if (effect.equals("Icebolt")) {
			EntityProjectileMagic p = new EntityProjectileMagic(world, player).setType(2).setColor(color).setDamage(Config.SIGIL_BASE_PROJECTILE_DAMAGE + Config.SIGIL_BASE_PROJECTILE_DAMAGE / 2);
			world.spawnEntityInWorld(p);
		}
		else if (effect.equals("Lightning")) {
			EntityProjectileMagic p = new EntityProjectileMagic(world, player).setType(3).setColor(color).setDamage(Config.SIGIL_BASE_PROJECTILE_DAMAGE + Config.SIGIL_BASE_PROJECTILE_DAMAGE / 2);
			world.spawnEntityInWorld(p);
			world.playSoundAtEntity(player, "ambient.weather.thunder", 0.5f, 1.6f / (itemRand.nextFloat() * 0.4f + 0.8f));
		}
		else if (effect.equals("Earthquake")) {
			EntityProjectileMagic p = new EntityProjectileMagic(world, player).setType(4).setColor(color).setDamage(Config.SIGIL_BASE_PROJECTILE_DAMAGE + Config.SIGIL_BASE_PROJECTILE_DAMAGE / 2);
			world.spawnEntityInWorld(p);
		}
		else if (effect.equals("Healing")) {
			int k = 12;
			player.heal(k);
		}
		else if (effect.equals("Resistance")) {
			if (!world.isRemote) {
				player.addPotionEffect(new PotionEffect(11, Config.SIGIL_BASE_SUPPORT_DURATION * 2, 1));
			}
		}
		else if (effect.equals("Remedy")) {
			if (!world.isRemote) {
				player.curePotionEffects(new ItemStack(Item.bucketMilk));
			}
		}
		else if (effect.equals("Cloak")) {
			if (!world.isRemote) {
				player.addPotionEffect(new PotionEffect(14, Config.SIGIL_BASE_SUPPORT_DURATION * 2, 1));
			}
		}
		else if (effect.equals("Teleport")) {
			if (!tags.hasKey("Y") || tags.getInteger("Y") <= 0) {
				LogHelper.warning("Invalid location for teleport effect");
				player.addChatMessage("Invalid location for teleport effect");
			}
			else {
				int dx, dy, dz, dd;
				dx = tags.getInteger("X");
				dy = tags.getInteger("Y");
				dz = tags.getInteger("Z");
				dd = tags.getInteger("D");
				
				if (dd != player.dimension) {
					player.travelToDimension(dd);
				}
				player.setPositionAndUpdate(dx + 0.5, dy + 1, dz + 0.5);
			}
		}
		else {
			// Derp catcher
			player.addChatMessage("Effect " + effect + " is not implemented!");
			LogHelper.warning("Effect " + effect + " is not implemented! (Sigil Scepter)");
		}
		
		// Chaos cost
		ChaosHandler.addChaos(Config.CHAOS_COST_SIGIL_SCEPTER);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		if (stack.stackTagCompound == null) {
			stack.stackTagCompound = new NBTTagCompound();
		}
		
		NBTTagCompound tags = stack.getTagCompound();
		String str = "";
		boolean isTeleport = false;
		
		if (tags.hasKey("Color")) {
			str += tags.getString("Color");
		}
		if (tags.hasKey("Effect") && !tags.getString("Effect").equals("")) {
			str += (str.equals("") ? "" : " ") + tags.getString("Effect");
			isTeleport = tags.getString("Effect").equals("Teleport");
		}
		
		if (!str.equals("")) {
			list.add(str);
			if (isTeleport && NBTHelper.hasValidXYZD(tags)) {
				list.add(LogHelper.coord(tags.getInteger("X"), tags.getInteger("Y"), tags.getInteger("Z")));
			}
			else if (isTeleport) {
				list.add("No destination set");
				list.add("Click on an Abyss Teleporter");
			}
		}
		else {
			list.add("No effect");
			list.add("Craft with a color and/or effect sigil stone");
		}
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		
		// Can't do anything here. It messes with effects when crafting with sigil stones.
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		scepterEffects(stack, world, player);
		return stack;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		// 25% speed bonus over regular sigils
		return Config.SIGIL_BASE_USE_DURATION * 3 / 4;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("Effect") &&
			stack.stackTagCompound.getString("Effect").equals("Teleport") &&
			!stack.stackTagCompound.hasKey("Y")) {
			// Teleport effect, but no set destination.
			if (!world.isRemote) {
				player.addChatMessage("No teleport destination set. Try right-clicking an Abyss Teleporter.");
			}
		}
		else {
			player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
			// TODO: Chaos cost for sigil scepter?
		}
		return stack;
	}

	public static void addRecipe(ItemStack gem, int damage) {
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilScepter, 1, damage), "gng", "isi", " s ",
				'g', gem, 'n', Item.netherStar, 'i', Item.ingotGold, 's', ModItems.ornateStick);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.bow;
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		// TODO
		return false;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		// TODO
		return EnumRarity.epic;
	}
	
	@Override
	public Icon getIconFromDamage(int i) {
		return icons[MathHelper.clamp_int(i, 0, 4)];
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tabs, List list) {
		for (int i = 0; i < 4; ++i) {
			list.add(new ItemStack(itemID, 1, i));
		}
	}
	
	@Override
	public float getDamageVsEntity(Entity entity, ItemStack stack) {
		return 6.0f;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
	    
	    StringBuilder s = new StringBuilder();
	    s.append("item.");
	    s.append(Strings.RESOURCE_PREFIX);
	    s.append(Strings.SIGIL_SCEPTER_NAME);
	    s.append(AbyssGem.names[stack.getItemDamage()]);
		return s.toString();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		for (int i = 0; i < 4; ++i) {
			icons[i] = iconRegister.registerIcon("SilentAbyss:SigilScepter" + AbyssGem.names[i]);
		}
	}
	
	public static String getEffect(ItemStack scepter) {
		if (scepter.hasTagCompound() && scepter.stackTagCompound.hasKey("Effect")) {
			return scepter.stackTagCompound.getString("Effect");
		}
		return "";
	}
}
