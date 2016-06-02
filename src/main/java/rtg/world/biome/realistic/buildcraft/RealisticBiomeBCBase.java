package rtg.world.biome.realistic.buildcraft;

import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.buildcraft.config.BiomeConfigBC;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;

public class RealisticBiomeBCBase extends RealisticBiomeBase
{
    
    public static RealisticBiomeBase bcDesertOilField;
    public static RealisticBiomeBase bcOceanOilField;
    
    public RealisticBiomeBCBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
    {
    
        super(config, b, riverbiome, t, s);
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
    
    public static void addBiomes()
    {
    
        if (Loader.isModLoaded("BuildCraft|Core"))
        {
            BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();
            
            for (int i = 0; i < 256; i++)
            {
                if (b[i] != null)
                {
                    if (b[i].biomeName == null) {
                        Logger.warn("Biome ID %d has no name.", b[i].biomeID);
                        continue;
                    }
                    
                    BiomeGenBase bcBiome = b[i];
                    String biomeName = b[i].biomeName;
                    String biomeClass = b[i].getBiomeClass().getName();
                    
                    if (biomeName == "Desert Oil Field" && biomeClass == "buildcraft.energy.worldgen.BiomeGenOilDesert")
                    {
                        bcDesertOilField = new RealisticBiomeBCDesertOilField(bcBiome, BiomeConfigBC.biomeConfigBCDesertOilField);
                    }
                    else if (biomeName == "Ocean Oil Field" && biomeClass == "buildcraft.energy.worldgen.BiomeGenOilOcean")
                    {
                        bcOceanOilField = new RealisticBiomeBCOceanOilField(bcBiome, BiomeConfigBC.biomeConfigBCOceanOilField);
                    }
                }
            }
        }
    }
}
