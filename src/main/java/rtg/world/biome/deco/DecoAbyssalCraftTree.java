package rtg.world.biome.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.tree.abyssalcraft.WorldGenTreeACDarkwood;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoAbyssalCraftTree extends DecoTree
{
	
	public TreeType treeType;
	
	public DecoAbyssalCraftTree()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.loops = 1;
		this.strengthFactorForLoops = 0f;
		this.strengthNoiseFactorForLoops = false;
		this.strengthNoiseFactorXForLoops = false;
		this.treeType = TreeType.DARKWOOD;
		this.distribution = new DecoTree.Distribution(100f, 5f, 0.8f);
		this.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		this.treeConditionNoise = 0f;
		this.treeConditionChance = 1;
		this.minY = 1; // No lower height limit by default.
		this.maxY = 255; // No upper height limit by default.
		this.logBlock = Blocks.log;
		this.logMeta = (byte)0;
		this.leavesBlock = Blocks.leaves;
		this.leavesMeta = (byte)-1;
		this.minSize = 2;
		this.maxSize = 4;
		
		this.addDecoTypes(DecoType.TREE);
	}

    @Override
    public boolean properlyDefined() {
        return true;
    }

	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {
			
			if (TerrainGen.decorate(world, rand, chunkX, chunkY, TREE)) {
				
				float noise = simplex.noise2(chunkX / this.distribution.noiseDivisor, chunkY / this.distribution.noiseDivisor) * this.distribution.noiseFactor + this.distribution.noiseAddend;

                int loopCount = this.loops;
                loopCount = (this.strengthFactorForLoops > 0f) ? (int)(this.strengthFactorForLoops * strength) : loopCount;
                loopCount = (this.strengthNoiseFactorForLoops) ? (int)(noise * strength) : loopCount;
                loopCount = (this.strengthNoiseFactorXForLoops) ? (int)(noise * this.strengthFactorForLoops * strength) : loopCount;
	            for (int i = 0; i < loopCount; i++)
	            {
	                int intX = chunkX + rand.nextInt(16) + 8;
	                int intZ = chunkY + rand.nextInt(16) + 8;
	                int intY = world.getHeightValue(intX, intZ);
	                
	            	switch (this.treeType)
	            	{
		            		
		            	case DARKWOOD:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

		                        WorldGenerator worldgenerator = new WorldGenTreeACDarkwood(6 + rand.nextInt(6), 10 + rand.nextInt(10), 0, 0);
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;

		            	default:
		            		break;
	            	}
	            }
			}
		}
	}

	public enum TreeType
	{
		DARKWOOD;
	}
}
