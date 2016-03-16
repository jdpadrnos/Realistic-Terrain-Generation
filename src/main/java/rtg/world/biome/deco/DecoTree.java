package rtg.world.biome.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.RandomUtil;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.tree.WorldGenTreeRTGMangrove;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPalmCustom;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPineBig;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPineSmall;
import rtg.world.gen.feature.tree.WorldGenTreeRTGTrees;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoTree extends DecoBase
{
    
	public int loops;
	public float strengthFactorForLoops; // If set, this overrides and dynamically calculates 'loops' based on the strength parameter.
	public boolean strengthNoiseFactorForLoops; // If true, this overrides and dynamically calculates 'loops' based on (noise * strength)
	public TreeType treeType; // Enum for the various tree presets.
	public DecoTree.Distribution distribution; // Parameter object for noise calculations.
	public TreeCondition treeCondition; // Enum for the various conditions/chances for tree gen.
	public float treeConditionNoise; // Only applies to a noise-related TreeCondition.
	public int treeConditionChance; // Only applies to a chance-related TreeCondition.
	public int maxY; // Height restriction.
	public Block logBlock;
	public byte logMeta;
	public Block leavesBlock;
	public byte leavesMeta;
	public int minSize; // Min tree height (only used with certain tree presets)
	public int maxSize; // Max tree height (only used with certain tree presets)
	
	public DecoTree()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.loops = 1;
		this.strengthFactorForLoops = 0;
		this.strengthNoiseFactorForLoops = false;
		this.treeType = TreeType.MEGA_JUNGLE_MANGROVE;
		this.distribution = new DecoTree.Distribution(100f, 5f, 0.8f);
		this.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		this.treeConditionNoise = 0f;
		this.treeConditionChance = 1;
		this.maxY = 255; // No height limit by default.
		this.logBlock = Blocks.log;
		this.logMeta = (byte)0;
		this.leavesBlock = Blocks.leaves;
		this.leavesMeta = (byte)-1;
		this.minSize = 2;
		this.maxSize = 4;
		
		this.addDecoTypes(DecoType.TREE);
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
	            for (int i = 0; i < loopCount; i++)
	            {
	                int intX = chunkX + rand.nextInt(16) + 8;
	                int intZ = chunkY + rand.nextInt(16) + 8;
	                int intY = world.getHeightValue(intX, intZ);
	                
	            	switch (this.treeType)
	            	{
		            	case MEGA_JUNGLE:
		            		
		            		if (intY <= this.maxY && isValidTreeCondition(noise, rand)) {

		                    	if (this.maxSize > this.minSize) {
			                        WorldGenerator worldgenerator = new WorldGenMegaJungle(false, this.minSize + rand.nextInt(this.maxSize - this.minSize), 0, 3, 3);
			                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			                        worldgenerator.generate(world, rand, intX, intY, intZ);
		                    	}
		                    	else if (this.maxSize == this.minSize) {
			                        WorldGenerator worldgenerator = new WorldGenMegaJungle(false, this.minSize, 0, 3, 3);
			                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			                        worldgenerator.generate(world, rand, intX, intY, intZ);
		                    	}
		            		}
		            		
		            		break;
		            		
		            	case MEGA_JUNGLE_MANGROVE:
		            		
		            		if (intY <= this.maxY && isValidTreeCondition(noise, rand)) {

			                    WorldGenerator worldgenerator =
			                        rand.nextInt(3) != 0
			                        ? new WorldGenMegaJungle(false, 10 + rand.nextInt(18), 20, 3, 3)
			                        : new WorldGenTreeRTGMangrove(Blocks.log, 3, Blocks.leaves, 3, 10 + rand.nextInt(18), 3 + rand.nextInt(2), 13f, RandomUtil.getRandomInt(rand, 4, 5),
			                        0.32f,
			                        0.2f);
			                    worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			                    worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case PALM_CUSTOM:
		            		
		            		if (intY <= this.maxY && isValidTreeCondition(noise, rand)) {

		                    	if (this.maxSize > this.minSize) {
			                        WorldGenerator worldgenerator = new WorldGenTreeRTGPalmCustom((float)(this.minSize + rand.nextInt(this.maxSize - this.minSize)));
			                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			                        worldgenerator.generate(world, rand, intX, intY, intZ);
		                    	}
		                    	else if (this.maxSize == this.minSize) {
			                        WorldGenerator worldgenerator = new WorldGenTreeRTGPalmCustom((float)(this.minSize));
			                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			                        worldgenerator.generate(world, rand, intX, intY, intZ);
		                    	}
		            		}
		            		
		            		break;
		            		
		            	case SMALL_PINES_TREES_FORESTS:
		            		
		            		if (intY <= this.maxY && isValidTreeCondition(noise, rand)) {

		                        WorldGenerator worldgenerator =
		                        rand.nextInt(4) != 0
		                        	? new WorldGenTreeRTGPineSmall(4 + rand.nextInt(7), 6 + rand.nextInt(9), 0)
		                        	: rand.nextInt(10) != 0
		                        		? new WorldGenTreeRTGTrees(false)
		                        		: new WorldGenForest(false, false);
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case BIG_PINES:
		            		
		            		if (intY <= this.maxY && isValidTreeCondition(noise, rand)) {

	                            if (rand.nextBoolean()) {
	                                WorldGenerator worldgenerator = new WorldGenTreeRTGPineBig(11 + rand.nextInt(11), 15 + rand.nextInt(15), 1, 1);
			                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			                        worldgenerator.generate(world, rand, intX, intY, intZ);
	                            }
	                            else {
	                                WorldGenerator worldgenerator = new WorldGenTreeRTGPineBig(11 + rand.nextInt(11), 15 + rand.nextInt(15), 0, 0);
			                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			                        worldgenerator.generate(world, rand, intX, intY, intZ);
	                            }
		            		}
		            		
		            		break;
		            		
		            	default:
		            		break;
	            	}
	            }
			}
		}
	}
	
	/**
	 * Parameter object for noise calculations.
	 * 
	 * simplex.noise2(chunkX / noiseDivisor, chunkY / noiseDivisor) * noiseFactor + noiseAddend;
	 * 
	 * @author WhichOnesPink
	 * @author Zeno410
	 *
	 */
	public static class Distribution
	{
	    public float noiseDivisor;
	    public float noiseFactor;
	    public float noiseAddend;
	    
	    public Distribution(float noiseDivisor, float noiseFactor, float noiseAddend)
	    {
	    	this.noiseDivisor = noiseDivisor;
	    	this.noiseFactor = noiseFactor;
	    	this.noiseAddend = noiseAddend;
	    }
	}
	
	public enum TreeType
	{
		BIG_PINES,
		MEGA_JUNGLE,
		MEGA_JUNGLE_MANGROVE,
		PALM_CUSTOM,
		SMALL_PINES_TREES_FORESTS;
	}
	
	public enum TreeCondition
	{
		ALWAYS_GENERATE,
		NOISE_GREATER_AND_RANDOM_CHANCE,
		RANDOM_CHANCE;
	}
	
	public boolean isValidTreeCondition(float noise, Random rand)
	{
		switch (this.treeCondition)
		{
			case NOISE_GREATER_AND_RANDOM_CHANCE:
				
				return (noise > this.treeConditionNoise && rand.nextInt(this.treeConditionChance) == 0);
			
			case RANDOM_CHANCE:
				
				return rand.nextInt(this.treeConditionChance) == 0;
				
			case ALWAYS_GENERATE:
				
				return true;
				
			default:
				
				return false;
		}
	}
}
