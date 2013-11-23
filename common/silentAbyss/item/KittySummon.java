package silentAbyss.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import silentAbyss.core.util.LocalizationHelper;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class KittySummon extends ItemSA {

    public KittySummon(int id) {

        super(id);
        isGlowing = true;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY,
            float hitZ) {

        if (!world.isRemote) {

            if (!player.capabilities.isCreativeMode) {
                --stack.stackSize;
            }

            int id = world.getBlockId(x, y, z);
            x += Facing.offsetsXForSide[side];
            y += Facing.offsetsYForSide[side];
            z += Facing.offsetsZForSide[side];
            double d = 0.0;

            if (side == 1 && Block.blocksList[id] != null && Block.blocksList[id].getRenderType() == 11) {
                d = 0.5;
            }

            EntityOcelot kitty = new EntityOcelot(world);
            kitty.setPosition(x, y, z);
            world.spawnEntityInWorld(kitty);
            
            // Make the ocelot tame
            kitty.setTamed(true);
            kitty.setTameSkin(1 + world.rand.nextInt(3));
            kitty.setOwner(player.getCommandSenderName());
            world.setEntityState(kitty, (byte)7);
        }

        return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        itemIcon = iconRegister.registerIcon(Strings.RESOURCE_PREFIX + Strings.YARN_BALL_NAME);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        return getUnlocalizedName(Strings.KITTY_SUMMON_NAME);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {

        list.add(LocalizationHelper.getMessageText(Strings.KITTY_SUMMON_NAME));
    }

    @Override
    public void addRecipes() {

        GameRegistry.addShapelessRecipe(new ItemStack(this), Item.fishRaw, Gem.getGem(Reference.INDEX_CONUNDRUMITE),
                CraftingMaterial.getStack(Strings.YARN_BALL_NAME));
    }
}
