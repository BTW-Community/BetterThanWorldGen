package btwg.mixin;

import btwg.api.biome.BiomeInterface;
import btwg.api.biome.HeightData;
import net.minecraft.src.BiomeGenBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BiomeGenBase.class)
public class BiomeMixin implements BiomeInterface {
	@Shadow public float minHeight;
	@Shadow public float maxHeight;
	private HeightData heightData;
	
	private float weight;
	
	private boolean isVanilla = false;
	
	@Override
	public HeightData getHeightData() {
		if (this.heightData == null) {
			this.heightData = new HeightData(this.minHeight, this.maxHeight);
		}
		
		return this.heightData;
	}
	
	@Override
	public BiomeGenBase setHeightData(HeightData heightData) {
		this.heightData = heightData;
		return (BiomeGenBase) (Object) this;
	}
	
	@Override
	public float getWeight() {
		return this.weight;
	}
	
	@Override
	public BiomeGenBase setWeight(float weight) {
		this.weight = weight;
		return (BiomeGenBase) (Object) this;
	}
	
	@Override
	public boolean isVanilla() {
		return this.isVanilla;
	}
	
	@Override
	public BiomeGenBase setVanilla() {
		return null;
	}
}