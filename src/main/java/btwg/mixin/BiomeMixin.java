package btwg.mixin;

import btwg.api.biome.data.BiomeData;
import btwg.api.biome.BiomeInterface;
import btwg.api.biome.data.BiomeData.HeightData;
import btwg.api.world.surface.Surfacer;
import net.minecraft.src.BiomeGenBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(BiomeGenBase.class)
public class BiomeMixin implements BiomeInterface {
	@Shadow public float minHeight;
	@Shadow public float maxHeight;

    @Shadow
    public int waterColorMultiplier;
    private HeightData heightData;
    private BiomeData<Surfacer> surfacerData;
	private BiomeData<BiomeGenBase> subBiomeData;
	private BiomeData<BiomeGenBase> riverShoreBiomeData;
	private BiomeData<BiomeGenBase> riverBiomeData;
	private BiomeData.ConditionalBiomeData edgeBiomeData;
	private BiomeData<BiomeGenBase> beachBiomeData;
	
	private float weight;
	
	private boolean isVanilla = false;
	private boolean isRiver = false;
	private boolean hasEdges = true;
	private boolean makesBeaches = false;
	private boolean usesDefaultBeach = true;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void setWaterColor(CallbackInfo ci) {
        this.waterColorMultiplier = 0x3F76E4;
    }
	
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

    //------ Surfacer Data ------//

    @Override
    public Optional<BiomeData<Surfacer>> getSurfacer() {
        return Optional.ofNullable (this.surfacerData);
    }

    @Override
    public BiomeInterface setSurfacer(BiomeData<Surfacer> surfacer) {
        this.surfacerData = surfacer;
        return this;
    }

    @Override
    public BiomeInterface setSurfacer(Surfacer surfacer) {
        return this.setSurfacer(new BiomeData<>(surfacer));
    }
	
	//------ Sub-biome Data ------//

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
	public BiomeInterface setSubBiomeData(BiomeGenBase biome) {
		return this.setSubBiomeData(new BiomeData<>(biome));
	}
	
	//------ River Data ------//
	
	@Override
	public boolean isRiver() {
		return this.isRiver;
	}
	
	@Override
	public BiomeInterface setRiver() {
		this.isRiver = true;
		this.setRiverBiomeData((BiomeGenBase) (Object) this);
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
	public BiomeInterface setRiverShoreBiomeData(BiomeGenBase biome) {
		return this.setRiverShoreBiomeData(new BiomeData<>(biome));
	}
	
	@Override
	public Optional<BiomeData<BiomeGenBase>> getRiverBiomeData() {
		return Optional.ofNullable(this.riverBiomeData);
	}
	
	@Override
	public BiomeInterface setRiverBiomeData(BiomeData<BiomeGenBase> riverBiomeData) {
		this.riverBiomeData = riverBiomeData;
		return this;
	}
	
	@Override
	public BiomeInterface setRiverBiomeData(BiomeGenBase biome) {
		return this.setRiverBiomeData(new BiomeData<>(biome));
	}
	
	//------ Biome Edge Data ------//
	
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
	public Optional<BiomeData.ConditionalBiomeData> getEdgeData() {
		return Optional.ofNullable(edgeBiomeData);
	}
	
	@Override
	public BiomeInterface setEdgeData(BiomeData.ConditionalBiomeData edgeBiomeData) {
		this.edgeBiomeData = edgeBiomeData;
		return this;
	}

	@Override
	public BiomeInterface setEdgeData(BiomeGenBase biome) {
		return setEdgeData(new BiomeData.ConditionalBiomeData(b -> b.hasEdges() ? Optional.of(biome) : Optional.empty()));
	}
	
	//------ Beach Data ------//

	@Override
	public boolean makesBeaches() {
		return this.makesBeaches;
	}

	@Override
	public BiomeInterface setMakesBeaches() {
		this.makesBeaches = true;
		return this;
	}

	@Override
	public Optional<BiomeData<BiomeGenBase>> getBeachData() {
		if (this.usesDefaultBeach && this.beachBiomeData == null) {
			this.beachBiomeData = new BiomeData<>(BiomeGenBase.beach);
		}
		
		return Optional.ofNullable(this.beachBiomeData);
	}

	@Override
	public BiomeInterface setBeachBiomeData(BiomeData<BiomeGenBase> beachBiomeData) {
		this.beachBiomeData = beachBiomeData;
		this.usesDefaultBeach = false;
		return this;
	}

	@Override
	public BiomeInterface setBeachBiomeData(BiomeGenBase biome) {
		return this.setBeachBiomeData(new BiomeData<>(biome));
	}
}