package silentAbyss.core.handlers;

import java.util.EnumSet;

import silentAbyss.enchantment.EnchantmentMending;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler {

	private int tick = 0;
	static final int baseTickRate = 20;
	
	private int worldTick = 0;
	public static final int baseWorldTickRate = 600;

	public void onPlayerTick(EntityPlayer player) {
		
		// Mending enchantment tick.
		ItemStack itemStack;
		for (int i = 0; i < InventoryPlayer.getHotbarSize(); ++i) {
			itemStack = player.inventory.getStackInSlot(i);
			if (itemStack != null) {
				EnchantmentMending.tryActivate(player.inventory.getStackInSlot(i));
			}
		}
		
		// Only do a tick every so often.
		++tick;
		
		if (tick >= baseTickRate) {
			tick = 0;
		}
		else {
			return;
		}
		
		// Code to run every second here.
	}
	
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
		if (type.equals(EnumSet.of(TickType.PLAYER))) {
			onPlayerTick((EntityPlayer)tickData[0]);
		}
		else if (type.equals(EnumSet.of(TickType.SERVER))) {
			onServerTick();
		}
		else if (type.equals(EnumSet.of(TickType.WORLD))) {
			onWorldTick((World)tickData[0]);
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.PLAYER, TickType.SERVER, TickType.WORLD);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

}
