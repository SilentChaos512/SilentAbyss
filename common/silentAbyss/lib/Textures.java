package silentAbyss.lib;

import net.minecraft.util.ResourceLocation;
import silentAbyss.core.util.ResourceLocationHelper;

public class Textures {

	// Base file paths
    public final static String BLOCK_SHEET_LOCATION = "textures/blocks/";
    public final static String ENTITY_SHEET_LOCATION = "textures/entity/";
    public final static String ITEM_SHEET_LOCATION = "textures/items/";
	public final static String MODEL_SHEET_LOCATION = "textures/models/";
	public final static String GUI_SHEET_LOCATION = "textures/gui/";
	public final static String EFFECTS_LOCATION = "textures/effects/";
	
	// Item/Block sprite sheets
	public final static ResourceLocation TEST_BLOCK = ResourceLocationHelper.getResourceLocation(BLOCK_SHEET_LOCATION + "BlockAbyssite.png");
	
	// Armor sprite sheets
	
	// GUI textures
	public final static ResourceLocation GUI_SIGIL_INFUSER = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "GuiSigilInfuser.png");
	
	// Model textures
	public final static ResourceLocation ENTITY_GRUMBLING = ResourceLocationHelper.getResourceLocation(ENTITY_SHEET_LOCATION + "Grumbling.png");
	
	// Effect textures
	public final static ResourceLocation EFFECT_MAGIC_PROJECTILE =
	        ResourceLocationHelper.getResourceLocation(EFFECTS_LOCATION + "magic.png");
}
