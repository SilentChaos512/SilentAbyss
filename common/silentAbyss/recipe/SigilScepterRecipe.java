package silentAbyss.recipe;

import silentAbyss.item.ModItems;
import silentAbyss.item.SigilStone;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class SigilScepterRecipe implements IRecipe {

	@Override
	public boolean matches(InventoryCrafting inventorycrafting, World world) {
		int numColorSigils = 0;
		int numEffectSigils = 0;
		int numScepters = 0;
		
		ItemStack s;
		
		// Count valid ingredients and look for invalid
		for (int i = 0; i < inventorycrafting.getSizeInventory(); ++i) {
			s = inventorycrafting.getStackInSlot(i);
			if (s != null) {
				if (s.getItem().itemID == ModItems.sigilStone.itemID) {
					// Item is sigil stone.
					if (isColorSigil(s)) {
						++numColorSigils;
					}
					else if (isEffectSigil(s)) {
						++numEffectSigils;
					}
					else {
						// Invalid sigil stone for scepters.
						return false;
					}
				}
				else if (s.getItem().itemID == ModItems.sigilScepter.itemID) {
					// Item is sigil scepter.
					++numScepters;
				}
				else {
					// Invalid item for sigil scepter recipe.
					return false;
				}
			}
		}
		
		// Is this configuration valid?
		return numColorSigils <= 1 &&
				numEffectSigils <= 1 &&
				numScepters == 1;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventorycrafting) {
		ItemStack scepter = null,
				colorSigil = null,
				effectSigil = null,
				s = null;
		
		// Find ingredients.
		for (int i = 0; i < inventorycrafting.getSizeInventory(); ++i) {
			s = inventorycrafting.getStackInSlot(i);
			if (s != null) {
				if (isColorSigil(s)) {
					colorSigil = s;
				}
				else if (isEffectSigil(s)) {
					effectSigil = s;
				}
				else if (s.getItem().itemID == ModItems.sigilScepter.itemID) {
					scepter = s;
				}
			}
		}
		
		if (scepter == null) {
			return null;
		}
		
		// Result is the same scepter with changed NBT.
		ItemStack result = new ItemStack(ModItems.sigilScepter, 1, scepter.getItemDamage());
		
		if (scepter.hasTagCompound()) {
			result.setTagCompound((NBTTagCompound)scepter.stackTagCompound.copy());
		}
		else {
			result.setTagCompound(new NBTTagCompound());
		}
		
		// Set color
		if (colorSigil != null) {
			result.stackTagCompound.setString("Color", SigilStone.names[colorSigil.getItemDamage()]);
		}
		
		// Set effect
		if (effectSigil != null) {
			result.stackTagCompound.setString("Effect", SigilStone.names[effectSigil.getItemDamage()]);
		}
		
		return result;
	}

	@Override
	public int getRecipeSize() {
		// TODO What's this?
		return 3;
	}

	@Override
	public ItemStack getRecipeOutput() {
		// TODO What's this?
		return null;
	}

	private boolean isColorSigil(ItemStack stack) {
		return stack != null &&												// not null,
				stack.getItem().itemID == ModItems.sigilStone.itemID &&		// is a sigil stone, and
				stack.getItemDamage() > 0 && stack.getItemDamage() < 17;	// damage value in [1, 16]
	}
	
	private boolean isEffectSigil(ItemStack stack) {
		return stack != null &&												// not null,
				stack.getItem().itemID == ModItems.sigilStone.itemID &&		// is a sigil stone, and
				stack.getItemDamage() > 16 && stack.getItemDamage() < 26;	// damage value in [17, 25]
	}
}
