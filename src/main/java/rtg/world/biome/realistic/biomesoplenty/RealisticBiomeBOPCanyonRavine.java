package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPCanyonRavine;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPCanyonRavine;
import biomesoplenty.api.content.BOPCBiomes;

public class RealisticBiomeBOPCanyonRavine extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.canyonRavine;
	
	public static Block topBlock = bopBiome.topBlock;
	public static Block fillerBlock = bopBiome.fillerBlock;
	
	public RealisticBiomeBOPCanyonRavine(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPCanyonRavine(true, 35f, 160f, 60f, 40f, 69f),
			new SurfaceBOPCanyonRavine(config, topBlock, (byte)0, fillerBlock, (byte)0, 0)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
