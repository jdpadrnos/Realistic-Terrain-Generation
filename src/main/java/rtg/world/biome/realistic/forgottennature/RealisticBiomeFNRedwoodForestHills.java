package rtg.world.biome.realistic.forgottennature;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.forgottennature.SurfaceFNRedwoodForestHills;
import rtg.world.gen.terrain.forgottennature.TerrainFNRedwoodForestHills;

public class RealisticBiomeFNRedwoodForestHills extends RealisticBiomeFNBase
{
    
    public RealisticBiomeFNRedwoodForestHills(BiomeGenBase fnBiome, BiomeConfig config)
    {
    
        super(config,
            fnBiome, BiomeGenBase.river,
            new TerrainFNRedwoodForestHills(),
            new SurfaceFNRedwoodForestHills(config, fnBiome.topBlock, fnBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.15f)
        );
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }
}
