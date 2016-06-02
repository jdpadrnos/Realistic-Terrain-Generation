package rtg.world.biome.realistic.highlands;

import highlands.Highlands;
import highlands.api.HighlandsBiomes;
import highlands.api.HighlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.highlands.config.BiomeConfigHLAutumnForest;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.world.gen.feature.tree.rtg.TreeRTGPiceaPungens;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;
import rtg.world.gen.surface.highlands.SurfaceHLAutumnForest;
import rtg.world.gen.terrain.highlands.TerrainHLAutumnForest;

public class RealisticBiomeHLAutumnForest extends RealisticBiomeHLBase {
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.autumnForest;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLAutumnForest(BiomeConfig config) {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLAutumnForest(0f, 50f, 68f, 200f),
            new SurfaceHLAutumnForest(config, topBlock, fillerBlock)
        );
        this.noWaterFeatures = false;
        
		DecoTree yellowPines = new DecoTree(new TreeRTGPinusPonderosa());
		yellowPines.logBlock = Blocks.log;
		yellowPines.logMeta = (byte)0;
		yellowPines.leavesBlock = Highlands.vanillaBlocksFlag ? Blocks.leaves : HighlandsBlocks.autumnYellowLeaves;
		yellowPines.leavesMeta = Highlands.vanillaBlocksFlag ? (byte)0 : (byte)0;
		yellowPines.minTrunkSize = 6;
		yellowPines.maxTrunkSize = 14;
		yellowPines.minCrownSize = 8;
		yellowPines.maxCrownSize = 20;
		yellowPines.strengthFactorForLoops = 6f;
		yellowPines.treeType = TreeType.RTG_TREE;
		yellowPines.distribution.noiseDivisor = 100f;
		yellowPines.distribution.noiseFactor = 6f;
		yellowPines.distribution.noiseAddend = 0.8f;
		yellowPines.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		yellowPines.treeConditionNoise = -0.4f;
		yellowPines.treeConditionChance = 1;
		yellowPines.maxY = 110;
		
		DecoTree orangePines = new DecoTree(yellowPines);
		orangePines.leavesBlock = Highlands.vanillaBlocksFlag ? Blocks.leaves : HighlandsBlocks.autumnOrangeLeaves;
		orangePines.leavesMeta = Highlands.vanillaBlocksFlag ? (byte)2 : (byte)0;
		
		DecoTree spruceTrees = new DecoTree(new TreeRTGPiceaPungens());
		spruceTrees.logBlock = Blocks.log;
		spruceTrees.logMeta = (byte)1;
		spruceTrees.leavesBlock = Blocks.leaves;
		spruceTrees.leavesMeta = (byte)1;
		spruceTrees.minTrunkSize = 4;
		spruceTrees.maxTrunkSize = 10;
		spruceTrees.minCrownSize = 6;
		spruceTrees.maxCrownSize = 17;
		spruceTrees.strengthFactorForLoops = 5f;
		spruceTrees.treeType = TreeType.RTG_TREE;
		spruceTrees.distribution.noiseDivisor = 100f;
		spruceTrees.distribution.noiseFactor = 6f;
		spruceTrees.distribution.noiseAddend = 0.8f;
		spruceTrees.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		spruceTrees.treeConditionNoise = 0f;
		spruceTrees.treeConditionChance = 1;
		spruceTrees.maxY = 110;
		
		DecoHelperRandomSplit decoTrees = new DecoHelperRandomSplit();
		decoTrees.decos = new DecoBase[]{yellowPines, orangePines, spruceTrees};
		decoTrees.chances = new int[]{8, 8, 4};
		this.addDeco(decoTrees);
		
		DecoTree deadPineTree = new DecoTree(new TreeRTGPinusPonderosa());
		deadPineTree.logBlock = Blocks.log;
		deadPineTree.logMeta = (byte)0;
		deadPineTree.leavesBlock = Blocks.leaves;
		deadPineTree.leavesMeta = (byte)0;
		deadPineTree.minTrunkSize = 4;
		deadPineTree.maxTrunkSize = 7;
		deadPineTree.minCrownSize = 8;
		deadPineTree.maxCrownSize = 18;
		deadPineTree.treeType = TreeType.RTG_TREE;
		deadPineTree.treeCondition = TreeCondition.RANDOM_CHANCE;
		deadPineTree.treeConditionChance = 18;
		deadPineTree.maxY = 120;
		deadPineTree.noLeaves = true;
		this.addDeco(deadPineTree);
		
        DecoFallenTree decoFallenOak = new DecoFallenTree();
        decoFallenOak.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenOak.logConditionChance = 20;
        decoFallenOak.maxY = 90;
        decoFallenOak.logBlock = Blocks.log;
        decoFallenOak.logMeta = (byte)0;
        decoFallenOak.leavesBlock = Blocks.leaves;
        decoFallenOak.leavesMeta = (byte)-1;
        decoFallenOak.minSize = 4;
        decoFallenOak.maxSize = 8;
		
        DecoFallenTree decoFallenSpruce = new DecoFallenTree(decoFallenOak);
        decoFallenSpruce.logConditionChance = 24;
        decoFallenSpruce.logMeta = (byte)1;
        decoFallenSpruce.minSize = 3;
        decoFallenSpruce.maxSize = 5;
        
        DecoHelper5050 decoFallenTree = new DecoHelper5050(decoFallenOak, decoFallenSpruce);
		this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigHLAutumnForest.decorationLogsId));
		
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		decoBaseBiomeDecorations.notEqualsZeroChance = 3;
		this.addDeco(decoBaseBiomeDecorations);
		
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.minY = 53;
		decoGrass.maxY = 128;
		decoGrass.loops = 8;
        this.addDeco(decoGrass);
    }
}
