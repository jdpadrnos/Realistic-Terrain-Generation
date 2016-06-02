package rtg.world.gen.terrain.tofucraft;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainTOFUTofuForestHills extends TerrainBase
{
    private float hillStrength = 40f;

    public TerrainTOFUTofuForestHills()
    {
        this(72f , 40f);
    }

    public TerrainTOFUTofuForestHills(float bh, float hs)
    {
        base = bh;
        hillStrength = hs;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return terrainHighland(x, y, simplex, cell, river, 10f, 68f, hillStrength, base-62f);

    }
}
