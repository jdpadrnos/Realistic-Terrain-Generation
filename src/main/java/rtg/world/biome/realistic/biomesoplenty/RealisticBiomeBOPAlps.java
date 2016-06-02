package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPAlps;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPAlps;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPAlps extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.alps;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPAlps(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.frozenRiver,
			new TerrainBOPAlps(),
			new SurfaceBOPAlps(config, topBlock, fillerBlock, false, null, 0.45f)
		);
		this.generatesEmeralds = true;
        this.noLakes = true;
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
