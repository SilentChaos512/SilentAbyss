package silentAbyss.lib;

import java.util.Arrays;

import net.minecraft.item.ItemStack;
import silentAbyss.core.registry.SARegistry;

/**
 * Describes Abyss gems. This is now used everywhere.
 * 
 * @author SilentChaos512
 * 
 */
public enum EnumGem {

    RUBY(0, false, "Ruby"),
    EMERALD(1, false, "Emerald"),
    SAPPHIRE(2, false, "Sapphire"),
    TOPAZ(3, false, "Topaz"),
    ABYSSITE(4, false, "Abyssite"),
    PURITE(5, false, "Purite"),
    RUBY_PLUS(6, true, "RubyPlus"),
    EMERALD_PLUS(7, true, "EmeraldPlus"),
    SAPPHIRE_PLUS(8, true, "SapphirePlus"),
    TOPAZ_PLUS(9, true, "TopazPlus"),
    CONUNDRUMITE(10, false, "Conundrumite"),
    ABYSS_DIAMOND(11, false, "AbyssDiamond");
    
    private static EnumGem[] basicGems = null;

    public final byte id;
    public final boolean supercharged;
    public final String name;

    private EnumGem(int id, boolean supercharged, String name) {

        this.id = (byte) id;
        this.supercharged = supercharged;
        this.name = name;
    }
    
    public static EnumGem[] all() {
        
        return values();
    }
    
    /**
     * Returns an array of the six basic gems. The array is cached for faster access (hopefully).
     * @return
     */
    public static EnumGem[] basic() {
        
        if (basicGems == null) {
            basicGems = Arrays.copyOfRange(values(), 0, 6);
        }
        return basicGems;
    }
    
    /**
     * Returns an array with the four primary gems (the kind that tools can be made of.) The array is not cached.
     * @return
     */
    public static EnumGem[] primary() {
        
        return Arrays.copyOfRange(values(), 0, 4);
    }
    
    /**
     * Gets an ItemStack of one of the corresponding GemBlock, if there is one.
     * @return
     */
    public ItemStack getBlock() {
        
        return id < basic().length ? new ItemStack(SARegistry.getBlock(Names.GEM_BLOCK), 1, id) : null;
    }
    
    /**
     * Gets an ItemStack of one of the corresponding Gem.
     * @return
     */
    public ItemStack getItem() {
        
        return new ItemStack(SARegistry.getItem(Names.GEM_ITEM), 1, id);
    }
    
    /**
     * Gets an ItemStack of one of the corresponding GemShard, if there is one.
     * @return
     */
    public ItemStack getShard() {
        return id < basic().length ? new ItemStack(SARegistry.getItem(Names.SHARD), 1, id) : null;
    }

    /**
     * Currently unused, as far as I know, but I'll leave it for now.
     * @return
     */
    public String getUnlocalizedName() {

        StringBuilder s = new StringBuilder().append("gem.");

        if (id < all().length) {
            s.append(all()[id]);
        }
        else {
            s.append(id);
        }

        return s.toString();
    }
}
