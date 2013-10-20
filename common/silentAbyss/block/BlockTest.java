package silentAbyss.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import silentAbyss.lib.RenderIds;
import silentAbyss.lib.Strings;
import silentAbyss.tileentity.TileTest;

public class BlockTest extends BlockContainer {

	public BlockTest(int id) {
		
		super(id, Material.iron);

		this.setHardness(5f);
	}

	@Override
	public String getUnlocalizedName() {
		
		StringBuilder unlocalizedName = new StringBuilder();
		
		unlocalizedName.append("tile.");
		unlocalizedName.append(Strings.RESOURCE_PREFIX);
		unlocalizedName.append(Strings.TEST_BLOCK_NAME);
		
		return unlocalizedName.toString();
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		
		return new TileTest();
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		
		return false;
	}
	
	@Override
	public int getRenderType() {
		
		return RenderIds.testRender;
	}
}
