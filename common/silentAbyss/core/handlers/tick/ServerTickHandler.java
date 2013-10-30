package silentAbyss.core.handlers.tick;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import silentAbyss.Abyss;
import silentAbyss.configuration.Config;
import silentAbyss.core.chaos.ChaosEventCollection;
import silentAbyss.core.chaos.MeteorEvent;
import silentAbyss.core.handlers.ChaosHandler;
import silentAbyss.core.util.LogHelper;
import silentAbyss.core.util.PlayerHelper;
import silentAbyss.enchantment.EnchantmentMending;
import silentAbyss.entity.projectile.EntityMeteor;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Strings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3Pool;
import net.minecraft.world.World;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler {

	private int worldTick = 0;
	public static final int baseWorldTickRate = 600;
	
	public void onServerTick() {
		
	}
	
	public void onWorldTick(World world) {
		
		++worldTick;
		
		if (worldTick >= baseWorldTickRate) {
			worldTick = 0;
			ChaosHandler.doWorldTick(world);
		}
	}
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	    
		if (type.equals(EnumSet.of(TickType.SERVER))) {
			onServerTick();
		}
		else if (type.equals(EnumSet.of(TickType.WORLD))) {
			onWorldTick((World)tickData[0]);
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public EnumSet<TickType> ticks() {
	    
		return EnumSet.of(TickType.SERVER, TickType.WORLD);
	}

	@Override
	public String getLabel() {

		return "Server";
	}

}
