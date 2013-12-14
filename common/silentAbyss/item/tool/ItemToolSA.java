package silentAbyss.item.tool;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ForgeHooks;
import silentAbyss.Abyss;
import silentAbyss.core.registry.SARegistry;
import silentAbyss.core.util.LogHelper;
import silentAbyss.item.ItemSA;
import silentAbyss.item.TorchBandolier;
import silentAbyss.lib.EnumGem;
import silentAbyss.lib.Names;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ItemToolSA extends ItemSA {

    private Block[] blocksEffectiveAgainst;
    public float efficiencyOnProperMaterial = 4.0f;
    public float damageVsEntity;
    protected EnumToolMaterial toolMaterial;
    protected EnumGem gemType;

    public ItemToolSA(int id, float baseDamage, EnumToolMaterial toolMaterial, EnumGem gemType, Block[] blocksEffectiveAgainst) {

        super(id);
        this.toolMaterial = toolMaterial;
        this.gemType = gemType;
        this.blocksEffectiveAgainst = blocksEffectiveAgainst;
        this.maxStackSize = 1;
        this.setMaxDamage(toolMaterial.getMaxUses());
        this.efficiencyOnProperMaterial = toolMaterial.getEfficiencyOnProperMaterial();
        this.damageVsEntity = baseDamage + toolMaterial.getDamageVsEntity();
        this.setCreativeTab(CreativeTabs.tabTools);
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
                if (item instanceof ItemBlock || item instanceof TorchBandolier) {
                    ForgeDirection d = ForgeDirection.VALID_DIRECTIONS[side];

                    int px = x + d.offsetX;
                    int py = y + d.offsetY;
                    int pz = z + d.offsetZ;
                    int playerX = (int) Math.floor(player.posX);
                    int playerY = (int) Math.floor(player.posY);
                    int playerZ = (int) Math.floor(player.posZ);

                    // Check for overlap with player, except for torches and
                    // torch bandolier
                    if (item.itemID != Block.torchWood.blockID && item.itemID != SARegistry.getItem(Names.TORCH_BANDOLIER).itemID
                            && px == playerX && (py == playerY || py == playerY + 1 || py == playerY - 1) && pz == playerZ) {
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
    
    @Override
    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack)
    {
        return stack.isItemEnchanted() ? EnumRarity.rare : EnumRarity.common;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack, int pass) {
        
        return stack.isItemEnchanted();
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        return getUnlocalizedName(Integer.toString(itemID), "tool");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entity1, EntityLivingBase entity2) {

        stack.damageItem(2, entity2);
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, int x, int y, int z, int par6, EntityLivingBase entity) {

        if ((double) Block.blocksList[x].getBlockHardness(world, y, z, par6) != 0.0D) {
            stack.damageItem(1, entity);
        }

        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean isFull3D() {

        return true;
    }

    @Override
    public int getItemEnchantability() {

        return this.toolMaterial.getEnchantability();
    }

    public String getToolMaterialName() {

        return this.toolMaterial.toString();
    }

    @Override
    public boolean getIsRepairable(ItemStack stack1, ItemStack stack2) {

        boolean isSupercharged = toolMaterial == Abyss.materialEnergizedAbyssGem;
        ItemStack material = new ItemStack(SARegistry.getItem(Names.GEM_ITEM), 1, gemType.id + (isSupercharged ? 6 : 0));
        if (material.itemID == stack2.itemID && material.getItemDamage() == stack2.getItemDamage()) {
            return true;
        }
        else {
            return super.getIsRepairable(stack1, stack2);
        }
    }

    @Override
    public Multimap getItemAttributeModifiers() {

        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e,
                "Tool modifier", (double) this.damageVsEntity, 0));
        return multimap;
    }

    @Override
    public float getStrVsBlock(ItemStack stack, Block block, int meta) {

        if (ForgeHooks.isToolEffective(stack, block, meta)) {
            return efficiencyOnProperMaterial;
        }
        return getStrVsBlock(stack, block);
    }
}
