package silentAbyss.core.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyHelper {

    private static GameSettings gs;

    public static void init() {

        gs = Minecraft.getMinecraft().gameSettings;
    }

    public static boolean isAnyMovement() {

        return gs.keyBindForward.pressed || gs.keyBindBack.pressed || gs.keyBindLeft.pressed || gs.keyBindRight.pressed;
    }
    
    public static boolean isForward() {
        
        return gs.keyBindForward.pressed;
    }
    
    public static boolean isBack() {
        
        return gs.keyBindBack.pressed;
    }
    
    public static boolean isLeft() {
        
        return gs.keyBindLeft.pressed;
    }
    
    public static boolean isRight() {
        
        return gs.keyBindRight.pressed;
    }

    public static boolean isJump() {

        return gs.keyBindJump.pressed;
    }

}