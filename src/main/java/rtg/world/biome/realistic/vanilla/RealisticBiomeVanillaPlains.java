package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat.ChanceType;
import rtg.world.gen.feature.tree.rtg.TreeRTGQuercusRobur;
import rtg.world.gen.surface.vanilla.SurfaceVanillaPlains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaPlains;

public class RealisticBiomeVanillaPlains extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.plains.topBlock;
    public static Block fillerBlock = BiomeGenBase.plains.fillerBlock;
    
    public RealisticBiomeVanillaPlains(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.plains,
            BiomeGenBase.river,
            new TerrainVanillaPlains(),
            new SurfaceVanillaPlains(config, topBlock, fillerBlock)
        );
        
        // Very sparse shrubs.
        DecoShrub decoShrubOak = new DecoShrub();
        decoShrubOak.logBlock = Blocks.log;
        decoShrubOak.logMeta = 0;
        decoShrubOak.leavesBlock = Blocks.leaves;
        decoShrubOak.leavesMeta = 0;
        decoShrubOak.maxY = 110;
        decoShrubOak.loops = 1;
        decoShrubOak.chance = 36;
		this.addDeco(decoShrubOak);
        
		// The occasional flower.
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
		decoFlowersRTG.flowers = new int[] {0, 2, 3, 4, 5, 6, 7, 8, 9};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.strengthFactor = 2f;
        this.addDeco(decoFlowersRTG);
        
        // Lots of grass, but not as much as vanilla.
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.minY = 60;
		decoGrass.maxY = 128;
		decoGrass.loops = 6;
        this.addDeco(decoGrass);
        
        {
        // Very rare fat oak/birch trees.
        DecoTree oakTrees = new DecoTree(new TreeRTGQuercusRobur());
        oakTrees.treeType = DecoTree.TreeType.RTG_TREE;
        oakTrees.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        oakTrees.distribution = new DecoTree.Distribution(100f, 6f, 0.8f);
        oakTrees.treeConditionNoise = 0.4f;
        oakTrees.treeConditionChance = 48;
        oakTrees.logBlock = Blocks.log;
        oakTrees.logMeta = (byte)0;
        oakTrees.leavesBlock = Blocks.leaves;
        oakTrees.leavesMeta = (byte)0;
        oakTrees.noLeaves = false;
        DecoTree birchTrees = new DecoTree(oakTrees);
        birchTrees.logMeta = (byte)2;
        birchTrees.leavesMeta = (byte)2;

        this.addDeco(new DecoHelperThisOrThat(4, ChanceType.NOT_EQUALS_ZERO, oakTrees, birchTrees));
        }
		
        // Vanilla trees look awful in this biome, so let's make sure they don't generate.
        
//		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
//		this.addDeco(decoBaseBiomeDecorations);
    }
}
