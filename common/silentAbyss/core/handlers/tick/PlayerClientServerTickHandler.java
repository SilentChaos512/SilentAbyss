package silentAbyss.core.handlers.tick;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import silentAbyss.configuration.Config;
import silentAbyss.core.util.KeyHelper;
import silentAbyss.core.util.LogHelper;
import silentAbyss.item.armor.PersonalElevationDevice;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class PlayerClientServerTickHandler implements ITickHandler {

    public void onPlayerTick(EntityPlayer player) {

        personalElevationDeviceTick(player);
    }

    private void personalElevationDeviceTick(EntityPlayer player) {

        // Personal Elevation Device
        if (Config.PED_CREATIVE_FLIGHT.value) {
            if (PersonalElevationDevice.playerHasEquipped(player) && player.ridingEntity == null) {
                player.capabilities.allowFlying = true;
            }
            else if (!player.capabilities.isCreativeMode) {
                player.capabilities.allowFlying = false;
            }
        }
        else {
            if (PersonalElevationDevice.playerHasEquipped(player) && Minecraft.getMinecraft().gameSettings.keyBindJump.pressed) {
                if (player.motionY < 0.4) {
                    player.motionY += 0.15;
                }
                // A failed attempt to boost horizontal movement speed.
//                Vec3 v = Vec3.fakePool.getVecFromPool(player.motionX, 0.0, player.motionZ);
//                if (v.lengthVector() < 64 && KeyHelper.isAnyMovement()) {
//                    //player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 100, 1, true));
//                    int dx = KeyHelper.isLeft() ? 1 : (KeyHelper.isRight() ? -1 : 0);
//                    int dz = KeyHelper.isForward() ? 1 : (KeyHelper.isBack() ? -1 : 0);
//                    float f = getMovementHeading(dx, dz);
//                    Vec3 v1 = Vec3.fakePool.getVecFromPool(player.motionX, 0.0, player.motionZ).normalize();
//                    v1.xCoord *= 0.1;
//                    v1.zCoord *= 0.1;
//                    v1.rotateAroundY(f);
//                    v = v.addVector(v1.xCoord, 0.0, v1.zCoord);
//                    player.motionX = v.xCoord;
//                    player.motionZ = v.zCoord;
//                }
            }
        }
    }
    
    private float getMovementHeading(int dx, int dz) {
        if (dx == -1 && dz == 0)
            return 0.0f;
        if (dx == -1 && dz == 1)
            return (float) Math.PI / 4;
        if (dx == 0 && dz == 1)
            return (float) Math.PI / 2;
        if (dx == 1 && dz == 1)
            return (float) (3 * Math.PI / 4);
        if (dx == 1 && dz == 0)
            return (float) Math.PI;
        if (dx == 1 && dz == -1)
            return (float) (5 * Math.PI / 4);
        if (dx == 0 && dz == -1)
            return (float) (3 * Math.PI / 2);
        if (dx == -1 && dz == -1)
            return (float) (7 * Math.PI / 4);
        return 0.0f;
    }
    
    private float getVectorAngle(Vec3 v) {
        
        double x = v.xCoord, z = v.zCoord;
        
        if (x == 0 && z == 0) {
            return 0.0f;
        }
        if (x == 0) {
            return z < 0 ? (float) (3 * Math.PI / 2) : (float) (Math.PI / 2);
        }
        if (z == 0) {
            return x < 0 ? (float) Math.PI : 0.0f;
        }
        
        float f = MathHelper.sin((float) (z / v.lengthVector()));
        
        if (x < 0 && z > 0) {
            return (float) Math.PI - f;
        }
        if (x < 0 && z < 0) {
            return -f + (float) Math.PI;
        }
        if (x > 0 && z < 0) {
            return (float) (2 * Math.PI) + f;
        }
        
        return f;
    }

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

        // LogHelper.debug("player cs tick");
        onPlayerTick((EntityPlayer) tickData[0]);
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {

    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.PLAYER);
    }

    @Override
    public String getLabel() {

        return "PlayerClientServer";
    }

}
