package rtg.world.biome.realistic.extrabiomes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.extrabiomes.SurfaceEBXLMountainTaiga;
import rtg.world.gen.terrain.extrabiomes.TerrainEBXLMountainTaiga;
import extrabiomes.api.BiomeManager;

public class RealisticBiomeEBXLMountainTaiga extends RealisticBiomeEBXLBase
{	
	public static BiomeGenBase ebxlBiome = BiomeManager.mountaintaiga.get();
	
	public static Block topBlock = ebxlBiome.topBlock;
	public static Block fillerBlock = ebxlBiome.fillerBlock;
	
	public RealisticBiomeEBXLMountainTaiga(BiomeConfig config)
	{
		super(config, 
			ebxlBiome, BiomeGenBase.frozenRiver,
			new TerrainEBXLMountainTaiga(),
			new SurfaceEBXLMountainTaiga(config, topBlock, fillerBlock, false, null, 1.2f)
		);
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
	}
}
