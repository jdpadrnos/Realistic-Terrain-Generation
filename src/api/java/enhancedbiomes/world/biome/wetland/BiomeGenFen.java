package enhancedbiomes.world.biome.wetland;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.world.biome.base.BiomeGenWetlandBase;
import enhancedbiomes.world.gen.WorldGenPool;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.*;

public class BiomeGenFen extends BiomeGenWetlandBase
{
    public BiomeGenFen(int par1)
    {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.grassPerChunk = 100;
    }
    
    public void decorate(World worldObj, Random rand, int x, int z)
    {
        super.decorate(worldObj, rand, x, z);
		
        for(int c = 100; c > 0; c--)
       	{
    		int j2 = x + rand.nextInt(16) + 8;
    		int l3 = rand.nextInt(120);
        	int j5 = z + rand.nextInt(16) + 8;
        	(new WorldGenPool(Blocks.flowing_water, 25, EnhancedBiomesMod.grassList[biomeID])).generate(worldObj, rand, j2, l3, j5);
    	}
    }
}
