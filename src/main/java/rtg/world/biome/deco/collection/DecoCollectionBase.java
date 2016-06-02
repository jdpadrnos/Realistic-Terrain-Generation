package rtg.world.biome.deco.collection;

import java.util.ArrayList;

import rtg.world.biome.deco.DecoBase;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoCollectionBase
{
	
	public ArrayList<DecoBase> decos;
	
	public DecoCollectionBase()
	{
		this.decos = new ArrayList<DecoBase>();
	}
	
	public void addDeco(DecoBase deco)
	{
        if (!deco.properlyDefined()) throw new RuntimeException();
		this.decos.add(deco);
	}
	
	public void addDeco(DecoBase deco, boolean allowed)
	{
		if (allowed) {
            if (!deco.properlyDefined()) throw new RuntimeException();
			this.decos.add(deco);
		}
	}
}
