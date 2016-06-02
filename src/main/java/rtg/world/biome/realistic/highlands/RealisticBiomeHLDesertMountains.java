package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.highlands.SurfaceHLDesertMountains;
import rtg.world.gen.terrain.highlands.TerrainHLDesertMountains;

public class RealisticBiomeHLDesertMountains extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.desertMountains;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLDesertMountains(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLDesertMountains(230f, 85f, 68f),
            new SurfaceHLDesertMountains(config, topBlock, fillerBlock, false, null, 0f, 1.5f, 90f, 30f, 1.5f)
        );
        noLakes = true;
        noWaterFeatures = true;
        disallowStoneBeaches = true;
        
        // nothing to suppress the highlands inverted sandstone parabolas
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		decoBaseBiomeDecorations.allowed = false;
		this.addDeco(decoBaseBiomeDecorations);
    }
}
