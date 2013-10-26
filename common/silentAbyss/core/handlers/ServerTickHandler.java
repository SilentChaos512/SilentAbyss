package silentAbyss.core.handlers;

import java.util.EnumSet;

import silentAbyss.Abyss;
import silentAbyss.configuration.Config;
import silentAbyss.core.chaos.ChaosEventCollection;
import silentAbyss.core.chaos.MeteorEvent;
import silentAbyss.core.util.LogHelper;
import silentAbyss.core.util.PlayerHelper;
import silentAbyss.enchantment.EnchantmentMending;
import silentAbyss.entity.projectile.EntityMeteor;
import silentAbyss.item.ModItems;
import silentAbyss.lib.Strings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3Pool;
import net.minecraft.world.World;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler {

	private int playerTick = 0;
	static final int basePlayerTickRate = 20;
	
	private int worldTick = 0;
	public static final int baseWorldTickRate = 600;
	
	ChaosEventCollection chaosEvents = new ChaosEventCollection();

	public void onPlayerTick(EntityPlayer player) {
		
		// Mending enchantment tick.
		ItemStack itemStack;
		for (int i = 0; i < InventoryPlayer.getHotbarSize(); ++i) {
			itemStack = player.inventory.getStackInSlot(i);
			if (itemStack != null) {
				EnchantmentMending.tryActivate(player.inventory.getStackInSlot(i));
			}
		}
		
		// Only update some things every so often.
		++playerTick;
		
		if (playerTick >= basePlayerTickRate) {
			playerTick = 0;
		}
		else {
			return;
		}
		
		// Code to run every second here.
		// Chaos events.
		// Meteor showers
		if (Abyss.rng.nextInt((int)(Config.METEOR_SHOWER_RARITY / ChaosHandler.getChaosFactor())) == 0) {
			player.addChatMessage(Strings.METEOR_SHOWER_INBOUND);
			chaosEvents.add(new MeteorEvent(player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ));
		}
		
		chaosEvents.doTick();
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
