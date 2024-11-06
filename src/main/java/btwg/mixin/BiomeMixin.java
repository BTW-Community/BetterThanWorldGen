package btwg.mixin;

import btwg.api.biome.data.BiomeData;
import btwg.api.biome.BiomeInterface;
import btwg.api.biome.data.BiomeData.HeightData;
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
	private BiomeData<BiomeGenBase> riverShoreBiomeData;
	private BiomeData.ConditionalBiomeData edgeBiomeData;
	
	private float weight;
	
	private boolean isVanilla = false;
	private boolean isRiver = false;
	private boolean hasEdges = true;
	
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
	public boolean isRiver() {
		return this.isRiver;
	}
	
	@Override
	public BiomeInterface setRiver() {
		this.isRiver = true;
		return this;
	}

	@Override
	public boolean hasEdges() {
		return this.hasEdges;
	}

	@Override
	public BiomeInterface setNoEdges() {
		this.hasEdges = false;
		return this;
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
	
	@Override
	public Optional<BiomeData<BiomeGenBase>> getRiverShoreBiomeData() {
		return Optional.ofNullable(this.riverShoreBiomeData);
	}
	
	@Override
	public BiomeInterface setRiverShoreBiomeData(BiomeData<BiomeGenBase> riverShoreBiomeData) {
		this.riverShoreBiomeData = riverShoreBiomeData;
		return this;
	}
	
	@Override
	public Optional<BiomeData.ConditionalBiomeData> getEdgeData() {
		return Optional.ofNullable(edgeBiomeData);
	}
	
	@Override
	public BiomeInterface setEdgeData(BiomeData.ConditionalBiomeData edgeBiomeData) {
		this.edgeBiomeData = edgeBiomeData;
		return this;
	}
}