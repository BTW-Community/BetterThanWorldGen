package btwg.mixin;

import btwg.api.biome.BiomeData;
import btwg.api.biome.BiomeInterface;
import btwg.api.biome.HeightData;
import net.minecraft.src.BiomeGenBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(BiomeGenBase.class)
public class BiomeMixin implements BiomeInterface {
	@Shadow public float minHeight;
	@Shadow public float maxHeight;
	
	private HeightData heightData;
	private BiomeData<BiomeGenBase> subBiomeData;
	
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
	public BiomeInterface setHeightData(HeightData heightData) {
		this.heightData = heightData;
		return this;
	}
	
	@Override
	public float getWeight() {
		return this.weight;
	}
	
	@Override
	public BiomeInterface setWeight(float weight) {
		this.weight = weight;
		return this;
	}
	
	@Override
	public boolean isVanilla() {
		return this.isVanilla;
	}
	
	@Override
	public BiomeInterface setVanilla() {
		return null;
	}
	
	@Override
	public Optional<BiomeData<BiomeGenBase>> getSubBiomeData() {
		return Optional.ofNullable(this.subBiomeData);
	}
	
	@Override
	public BiomeInterface setSubBiomeData(BiomeData<BiomeGenBase> subBiomeData) {
		this.subBiomeData = subBiomeData;
		return this;
	}
}