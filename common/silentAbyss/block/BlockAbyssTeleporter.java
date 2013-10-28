package silentAbyss.block;

import silentAbyss.Abyss;
import silentAbyss.configuration.Config;
import silentAbyss.core.handlers.ChaosHandler;
import silentAbyss.core.util.LogHelper;
import silentAbyss.core.util.NBTHelper;
import silentAbyss.core.util.PlayerHelper;
import silentAbyss.item.AbyssGem;
import silentAbyss.item.AbyssSigil;
import silentAbyss.item.ModItems;
import silentAbyss.item.tool.SigilScepter;
import silentAbyss.item.tool.TeleporterLinker;
import silentAbyss.lib.Reference;
import silentAbyss.lib.Strings;
import silentAbyss.tileentity.TileEntityAbyssTeleporter;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

public class BlockAbyssTeleporter extends BlockContainer {

	public static Object mod;
	private Ticket ticket;
	private ChunkCoordIntPair ticketChunk;
	private Icon[] iconArray;
	
	public BlockAbyssTeleporter(int par1) {
		
		super(par1, Material.iron);
		
		this.setHardness(12.0f);
		this.setResistance(12.0f);
		this.setStepSound(Block.soundGlassFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		
		return new TileEntityAbyssTeleporter();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		
		this.iconArray = new Icon[16];
		for (int i = 0; i < iconArray.length; ++i) {
			iconArray[i] = iconRegister.registerIcon("SilentAbyss:BlockAbyssTeleporter_" + i);
		}
		
		blockIcon = iconArray[0];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		
		return iconArray[meta];
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		
		if (world.isRemote) {
			return true;
		}
		
		// Dye
		if (PlayerHelper.isPlayerHoldingItem(player, Item.dyePowder)) {
			
			ItemStack dyeStack = player.inventory.getCurrentItem();
			int color = dyeStack.getItemDamage();
			color = ~color & 15;
			
			// If teleporter is this color already, ignore it. Otherwise, consume a dye (if not in creative mode.)
			if (world.getBlockMetadata(x, y, z) != color) {
				
				if (!player.capabilities.isCreativeMode) {
					--dyeStack.stackSize;
				}
				TileEntityAbyssTeleporter t = (TileEntityAbyssTeleporter) world.getBlockTileEntity(x, y, z);
				int tx, ty, tz, td;
				tx = t.destX;
				ty = t.destY;
				tz = t.destZ;
				td = t.destD;
				world.setBlock(x, y, z, this.blockID, color, 2);
				t = (TileEntityAbyssTeleporter) world.getBlockTileEntity(x, y, z);
				t.destX = tx;
				t.destY = ty;
				t.destZ = tz;
				t.destD = td;
			}
			return true;
		}
		// Link teleporters
		if (PlayerHelper.isPlayerHoldingItem(player, ModItems.teleporterLinker)) {
			
			ItemStack linker = player.inventory.getCurrentItem();
			if (linker.stackTagCompound == null) {
				TeleporterLinker.resetTagCompound(linker);
			}
			NBTTagCompound tags = linker.stackTagCompound;
			
			// Safety check
			if (!NBTHelper.hasValidXYZD(tags)) {
				tags.setInteger("State", 0);
			}
			
			if (tags.getInteger("State") == 0) {
				// Inactive state, set active state and location data.
				//LogHelper.debug("TP Linker active: " + LogHelper.coord(x, y, z));
				tags.setInteger("State", 1);
				NBTHelper.setXYZD(tags, x, y, z, player.dimension);
				linker.setItemDamage(1);
				player.addChatMessage(Strings.TELEPORTER_LINK_START);
				
				// Force load this chunk so we can find the first teleporter when linking to the second.
				ticket = ForgeChunkManager.requestTicket(mod, world, Type.NORMAL);
				ticketChunk = new ChunkCoordIntPair(x / 16, z / 16);
				ForgeChunkManager.forceChunk(ticket, ticketChunk);
				
			}
			else {
				// Active state, link teleporters and set inactive.
				TileEntityAbyssTeleporter t1, t2;
				t1 = (TileEntityAbyssTeleporter) world.getBlockTileEntity(tags.getInteger("X"), tags.getInteger("Y"), tags.getInteger("Z"));
				t2 = (TileEntityAbyssTeleporter) world.getBlockTileEntity(x, y, z);
				
				// Safety check
				if (t1 == null) {
					player.addChatMessage(Strings.TELEPORTER_LINK_FAIL);
					LogHelper.warning("A teleporter link failed because teleporter 1 could not be found.");
					return false;
				}
				else if (t2 == null) {
					player.addChatMessage(Strings.TELEPORTER_LINK_FAIL);
					LogHelper.warning("A teleporter link failed because teleporter 2 could not be found.");
					return false;
				}
				
				// Set destinations for both teleporters.
				t1.destX = x;
				t1.destY = y;
				t1.destZ = z;
				t1.destD = player.dimension;
				t2.destX = tags.getInteger("X");
				t2.destY = tags.getInteger("Y");
				t2.destZ = tags.getInteger("Z");
				t2.destD = tags.getInteger("D");
				player.addChatMessage(Strings.TELEPORTER_LINK_END);
				
				tags.setInteger("State", 0);
				linker.setItemDamage(0);
				try {
					ForgeChunkManager.unforceChunk(ticket, ticketChunk);
				}
				catch (NullPointerException ex) {
					LogHelper.warning("Possibly failed to unforce chunk when linking teleporters.");
				}
			}
			
			return true;
		}
		// Link sigils
		if (PlayerHelper.isPlayerHoldingItem(player, ModItems.abyssSigil)) {
			
			ItemStack sigil = player.inventory.getCurrentItem();
			// Does sigil have teleport?
			if (AbyssSigil.hasSigilEfffect(sigil, "Teleport")) {
				NBTHelper.setXYZD(sigil.stackTagCompound, x, y, z, player.dimension);
				player.addChatMessage(Strings.TELEPORTER_SIGIL_LINK);
				
				return true;
			}
		}
		// Link sigil scepter
		if (PlayerHelper.isPlayerHoldingItem(player, ModItems.sigilScepter)) {
			
			ItemStack scepter = player.inventory.getCurrentItem();
			// Does scepter have teleport?
			if (SigilScepter.getEffect(scepter).equals("Teleport")) {
				NBTHelper.setXYZD(scepter.stackTagCompound, x, y, z, player.dimension);
				player.addChatMessage(Strings.TELEPORTER_SCEPTER_LINK);
				
				return true;
			}
		}
			
		
		TileEntityAbyssTeleporter tile = (TileEntityAbyssTeleporter) world.getBlockTileEntity(x, y, z);
		
		if (tile != null) {
			// Safety checks:
			// Destination above y=0?
			if (tile.destY == 0) {
				player.addChatMessage(Strings.TELEPORTER_NO_DESTINATION);
				return true;
			}

			// Spawn some particles.
			// FIXME: Can't spawn particles?
			for (int l = 0; l < 128; ++l) {
				double d0 = (double)x - 0.5 + Abyss.rng.nextDouble();
				double d1 = (double)y + 0.5 + Abyss.rng.nextDouble() * 2 + 2;
				double d2 = (double)z - 0.5 + Abyss.rng.nextDouble();
				double d3 = 0;
				double d4 = 0;
				double d5 = 0;
                player.worldObj.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
            }
			
			// Teleport player if everything is OK.
			if (tile.destD != player.dimension) {
				player.travelToDimension(tile.destD);
			}
			player.setPositionAndUpdate(tile.destX + 0.5D, tile.destY + 1, tile.destZ + 0.5D);
			player.worldObj.playSoundAtEntity(player, "mob.endermen.portal", 1.0f, 1.0f);
			
			// Chaos cost
			ChaosHandler.addChaos(Config.CHAOS_COST_ABYSS_TELEPORTER);
		}
		else {
			LogHelper.warning("Teleporter at " + LogHelper.coord(x, y, z) + " not found!");
		}

		return true;
	}
	
	@Override
    public String getUnlocalizedName() {
        
        StringBuilder s = new StringBuilder();
        s.append("tile.");
        s.append(Strings.RESOURCE_PREFIX);
        s.append(Strings.ABYSS_TELEPORTER_NAME);
        return s.toString();
    }
	
	public static void addRecipes() {
	    
	    GameRegistry.addRecipe(new ItemStack(ModBlocks.abyssTeleporter, 2), "gwg", "geg", "gag",
	            'g', Item.ingotGold, 'w', Block.cloth, 'e', Item.enderPearl, 'a', AbyssGemBlock.getGem(Reference.INDEX_ABYSSITE));
	}
}
