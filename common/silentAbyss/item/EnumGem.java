package silentAbyss.item;

import silentAbyss.lib.Reference;

/**
 * Describes Abyss gems. Might use this more in the future to reduce some
 * redundancy/repetitiveness in the code.
 * 
 * @author SilentChaos512
 * 
 */
public enum EnumGem {

    RUBY(0, false), EMERALD(1, false), SAPPHIRE(2, false), TOPAZ(3, false), ABYSSITE(4, false), PURITE(5, false), RUBY_PLUS(6, true), EMERALD_PLUS(
            7, true), SAPPHIRE_PLUS(8, true), TOPAZ_PLUS(9, true), CONUNDRUMITE(10, false), ABYSS_DIAMOND(11, false);

    private final byte id;
    private final boolean supercharged;

    private EnumGem(int id, boolean supercharged) {

        this.id = (byte) id;
        this.supercharged = supercharged;
    }

    public byte getID() {

        return id;
    }

    public boolean getIsSupercharged() {

        return supercharged;
    }

    public String getUnlocalizedName() {

        StringBuilder s = new StringBuilder().append("gem.");

        if (id < Reference.allGemNames.length) {
            s.append(Reference.allGemNames[id]);
        }
        else {
            s.append(id);
        }

        return s.toString();
    }
}
