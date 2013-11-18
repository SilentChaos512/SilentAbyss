package silentAbyss.item.tool;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.oredict.ShapedOreRecipe;
import silentAbyss.Abyss;
import silentAbyss.item.Gem;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AbyssPickaxe extends ItemPickaxe {

    private int gemType = 0;

    public AbyssPickaxe(int par1, EnumToolMaterial par2EnumToolMaterial, int gemType) {

        super(par1 - Reference.SHIFTED_ID_RANGE_CORRECTION, par2EnumToolMaterial);
        this.gemType = gemType;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        StringBuilder s = new StringBuilder();
        s.append("tool.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append("pickaxeAbyss");
        s.append(Gem.names[gemType]);
        if (toolMaterial == Abyss.materialEnergizedAbyssGem) {
            s.append("Plus");
        }

        return s.toString();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {

        String s = "SilentAbyss:PickaxeAbyss";

        switch (gemType) {
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

    @Override
    public boolean getIsRepairable(ItemStack stack1, ItemStack stack2) {

        boolean isSupercharged = toolMaterial == Abyss.materialEnergizedAbyssGem;
        ItemStack material = new ItemStack(ModItems.abyssGem, 1, gemType + (isSupercharged ? 6 : 0));
        if (material.itemID == stack2.itemID && material.getItemDamage() == stack2.getItemDamage()) {
            return true;
        }
        else {
            return super.getIsRepairable(stack1, stack2);
        }
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY,
            float hitZ) {
        
        boolean used = false;
        int toolSlot = player.inventory.currentItem;
        int itemSlot = toolSlot + 1;
        ItemStack nextStack = null;
        
        if (toolSlot < 8) {
            nextStack = player.inventory.getStackInSlot(itemSlot);
            if (nextStack != null) {
                Item item = nextStack.getItem();
                if (item instanceof ItemBlock) {
                    ForgeDirection d = ForgeDirection.VALID_DIRECTIONS[side];
                    
                    int px = x + d.offsetX;
                    int py = y + d.offsetY;
                    int pz = z + d.offsetZ;
                    int playerX = (int) Math.floor(player.posX);
                    int playerY = (int) Math.floor(player.posY);
                    int playerZ = (int) Math.floor(player.posZ);
                    
                    if (px == playerX && (py == playerY || py == playerY + 1 || py == playerY - 1) && pz == playerZ) {
                        return false;
                    }
                    
                    used = item.onItemUse(nextStack, player, world, x, y, z, side, hitX, hitY, hitZ);
                    if (nextStack.stackSize < 1) {
                        nextStack = null;
                        player.inventory.setInventorySlotContents(itemSlot, null);
                    }
                }
            }
        }
        
        return used;
    }

    public static void addRecipe(ItemStack tool, ItemStack material, boolean energized) {

        if (energized) {
            GameRegistry.addRecipe(new ShapedOreRecipe(tool, true, new Object[] { "ggg", " s ", " s ", 'g', material, 's',
                    new ItemStack(ModItems.craftingMaterial, 1, 0) }));
        }
        else {
            GameRegistry.addRecipe(new ShapedOreRecipe(tool, true, new Object[] { "ggg", " s ", " s ", 'g', material, 's', "stickWood" }));
        }
    }
}
