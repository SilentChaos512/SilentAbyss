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
	
	//private AbyssWorldData worldData = null;
	
	// Chaos variables
//	final int chaosPerWorldTick = 10;
//	final int maxAmbientChaos = 10000; // Over 9000? ;)
	
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
		
		// Running shoes.
//		if (player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).itemID == Abyss.runningShoes.itemID) {
//			player.addPotionEffect(new PotionEffect(1, 219, 0, true));
//			player.addPotionEffect(new PotionEffect(8, 219, 3, true));
//		}
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
			//AbyssLog.oneTimePrint("PLAYER " + tickData.length);
			onPlayerTick((EntityPlayer)tickData[0]);
		}
		else if (type.equals(EnumSet.of(TickType.SERVER))) {
			//AbyssLog.oneTimePrint("SERVER " + tickData.length);
			onServerTick();
		}
		else if (type.equals(EnumSet.of(TickType.WORLD))) {
			//AbyssLog.oneTimePrint("WORLD " + tickData.length);
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
