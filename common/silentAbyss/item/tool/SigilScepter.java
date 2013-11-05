package silentAbyss.item.tool;

import java.util.List;

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
import net.minecraft.world.World;
import silentAbyss.configuration.Config;
import silentAbyss.core.handlers.ChaosHandler;
import silentAbyss.core.util.LogHelper;
import silentAbyss.core.util.NBTHelper;
import silentAbyss.entity.projectile.EntityProjectileMagic;
import silentAbyss.item.Gem;
import silentAbyss.item.ItemSA;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import silentAbyss.recipe.SigilScepterRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SigilScepter extends ItemSA {

    public Icon[] icons = new Icon[Gem.names.length];

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

        if (tags == null || world.isRemote) { return; }

        String color = "White", effect = "";

        if (tags.hasKey("Color")) {
            color = tags.getString("Color");
        }
        if (tags.hasKey("Effect")) {
            effect = tags.getString("Effect");
        } else {
            // No effect
            return;
        }

        int damage = Config.SIGIL_PROJECTILE_DAMAGE.value + Config.SIGIL_PROJECTILE_DAMAGE.value / 2;

        if (effect.equals("Fireball")) {
            EntityProjectileMagic p = new EntityProjectileMagic(world, player).setType(1).setColor(color).setDamage(damage);
            world.spawnEntityInWorld(p);
            world.playSoundAtEntity(player, "mob.ghast.fireball", 0.5f, 0.5f / (itemRand.nextFloat() * 0.4f + 0.8f));
        } else if (effect.equals("Icebolt")) {
            EntityProjectileMagic p = new EntityProjectileMagic(world, player).setType(2).setColor(color).setDamage(damage);
            world.spawnEntityInWorld(p);
        } else if (effect.equals("Lightning")) {
            EntityProjectileMagic p = new EntityProjectileMagic(world, player).setType(3).setColor(color).setDamage(damage);
            world.spawnEntityInWorld(p);
            world.playSoundAtEntity(player, "ambient.weather.thunder", 0.5f, 1.6f / (itemRand.nextFloat() * 0.4f + 0.8f));
        } else if (effect.equals("Earthquake")) {
            EntityProjectileMagic p = new EntityProjectileMagic(world, player).setType(4).setColor(color).setDamage(damage);
            world.spawnEntityInWorld(p);
        } else if (effect.equals("Healing")) {
            int k = 12;
            player.heal(k);
        } else if (effect.equals("Resistance")) {
            if (!world.isRemote) {
                player.addPotionEffect(new PotionEffect(11, Config.SIGIL_SUPPORT_DURATION.value * 2, 1));
            }
        } else if (effect.equals("Remedy")) {
            if (!world.isRemote) {
                player.curePotionEffects(new ItemStack(Item.bucketMilk));
            }
        } else if (effect.equals("Cloak")) {
            if (!world.isRemote) {
                player.addPotionEffect(new PotionEffect(14, Config.SIGIL_SUPPORT_DURATION.value * 2, 1));
            }
        } else if (effect.equals("Teleport")) {
            if (!tags.hasKey("Y") || tags.getInteger("Y") <= 0) {
                LogHelper.warning("Invalid location for teleport effect");
                player.addChatMessage("Invalid location for teleport effect");
            } else {
                int dx, dy, dz, dd;
                dx = tags.getInteger("X");
                dy = tags.getInteger("Y");
                dz = tags.getInteger("Z");
                dd = tags.getInteger("D");

                // Dismount and teleport mount
                if (player.ridingEntity != null) {
                    Entity mount = player.ridingEntity;
                    player.mountEntity((Entity) null);
                    if (dd != mount.dimension) {
                        mount.travelToDimension(dd);
                    }
                    mount.setLocationAndAngles(dx + 0.5, dy + 1, dz + 0.5, mount.rotationYaw, mount.rotationPitch);
                }
                // Teleport player
                if (dd != player.dimension) {
                    player.travelToDimension(dd);
                }
                player.setPositionAndUpdate(dx + 0.5, dy + 1, dz + 0.5);
            }
        } else {
            // Derp catcher
            player.addChatMessage("Effect " + effect + " is not implemented!");
            LogHelper.warning("Effect " + effect + " is not implemented! (Sigil Scepter)");
        }

        // Chaos cost
        ChaosHandler.addChaos(Config.CHAOS_COST_SIGIL_SCEPTER.value);
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
            list.add("\u00a76" + str);
            if (isTeleport && NBTHelper.hasValidXYZD(tags)) {
                list.add(LogHelper.coord(tags.getInteger("X"), tags.getInteger("Y"), tags.getInteger("Z")));
            } else if (isTeleport) {
                list.add("No destination set");
                list.add("Click on an Abyss Teleporter");
            }
        } else {
            list.add("No effect");
            list.add("Craft with a color and/or effect sigil stone");
        }
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {

        // Can't do anything here. It messes with effects when crafting with
        // sigil stones.
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {

        scepterEffects(stack, world, player);
        return stack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {

        // 25% speed bonus over regular sigils
        return Config.SIGIL_USE_DURATION.value * 3 / 4;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("Effect")
                && stack.stackTagCompound.getString("Effect").equals("Teleport") && !stack.stackTagCompound.hasKey("Y")) {
            // Teleport effect, but no set destination.
            if (!world.isRemote) {
                player.addChatMessage("No teleport destination set. Try right-clicking an Abyss Teleporter.");
            }
        } else {
            player.setItemInUse(stack, getMaxItemUseDuration(stack));
            // TODO: Chaos cost for sigil scepter?
        }
        return stack;
    }

    public static void addRecipes() {

        for (int i = 0; i < Gem.names.length; ++i) {
            GameRegistry.addShapedRecipe(new ItemStack(ModItems.sigilScepter, 1, i), "gng", "isi", "psp", 'g', new ItemStack(
                    ModItems.abyssGem, 1, i), 'n', Item.netherStar, 'i', Item.ingotGold, 's',
                    new ItemStack(ModItems.craftingMaterial, 1, 0), 'p', Gem.getGem(Reference.INDEX_PURITE));
        }

        // This allows the scepter's effect to be set by crafting it with sigil
        // stones.
        GameRegistry.addRecipe(new SigilScepterRecipe());
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

        return EnumRarity.epic;
    }

    @Override
    public Icon getIconFromDamage(int i) {

        return icons[i];
    }

    @Override
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
        s.append(Gem.names[stack.getItemDamage()]);
        return s.toString();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {

        for (int i = 0; i < 4; ++i) {
            icons[i] = iconRegister.registerIcon("SilentAbyss:SigilScepter" + Gem.names[i]);
        }
    }

    public static String getEffect(ItemStack scepter) {

        if (scepter.hasTagCompound() && scepter.stackTagCompound.hasKey("Effect")) { return scepter.stackTagCompound.getString("Effect"); }
        return "";
    }
}
