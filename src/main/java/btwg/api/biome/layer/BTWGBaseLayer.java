package btwg.api.biome.layer;

import btwg.api.configuration.WorldData;
import net.minecraft.src.*;

public abstract class BTWGBaseLayer extends GenLayer {
    protected WorldData generatorOptions;
    
    public BTWGBaseLayer(long seed, WorldData generatorOptions) {
        super(seed);
        this.generatorOptions = generatorOptions;
    }
    
    public BTWGBaseLayer(long seed, GenLayer parent, WorldData generatorOptions) {
        this(seed, generatorOptions);
        this.parent = parent;
    }
    
    public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType, WorldData worldData) {
        ContinentsLayer continentsLayer = new ContinentsLayer(1L, worldData);
        GenLayerFuzzyZoom continentZoomLayer = new GenLayerFuzzyZoom(2000L, continentsLayer);
        GenLayerAddIsland addIslandLayer = new GenLayerAddIsland(1L, continentZoomLayer);
        GenLayerZoom islandZoomLayer = new GenLayerZoom(2001L, addIslandLayer);
        addIslandLayer = new GenLayerAddIsland(2L, islandZoomLayer);
        islandZoomLayer = new GenLayerZoom(2002L, addIslandLayer);
        addIslandLayer = new GenLayerAddIsland(3L, islandZoomLayer);
        islandZoomLayer = new GenLayerZoom(2003L, addIslandLayer);
        addIslandLayer = new GenLayerAddIsland(4L, islandZoomLayer);
        GenLayerAddMushroomIsland landShapeLayer = new GenLayerAddMushroomIsland(5L, addIslandLayer);
        
        byte scale = 4;
        
        GenLayer var5 = GenLayerZoom.magnify(1000L, landShapeLayer, 0);
        GenLayerRiverInit riverInitLayer = new GenLayerRiverInit(100L, var5);
        var5 = GenLayerZoom.magnify(1000L, riverInitLayer, scale + 1);
        GenLayer riverLayer = new GenLayerRiver(1L, var5);
        GenLayerSmooth smoothRiverLayer = new GenLayerSmooth(1000L, riverLayer);
        riverLayer = GenLayerZoom.magnify(1000L, smoothRiverLayer, 2);
        
        GenLayer var6 = GenLayerZoom.magnify(1000L, landShapeLayer, 0);
        BiomeLayer biomeLayer = new BiomeLayer(200L, var6, worldType, worldData);
        var6 = GenLayerZoom.magnify(1000L, biomeLayer, 2);
        
        GenLayer extrasLayer = new SubBiomeLayer(1000L, var6, worldData);
        
        for(int pass = 0; pass < scale; pass++) {
            extrasLayer = new GenLayerZoom(1000 + pass, extrasLayer);
            
            if (pass == 0) {
                extrasLayer = new GenLayerAddIsland(3L, extrasLayer);
                
                extrasLayer = new EdgeLayer(1000L, extrasLayer, worldData);
            }
            
            if (pass == 1) {
                extrasLayer = new RiverShoreLayer(1000L, extrasLayer, smoothRiverLayer, worldData);
            }
            
            if (pass == 2) {
                extrasLayer = new BeachLayer(1000L, extrasLayer, worldData);
            }
        }
        
        GenLayerSmooth biomeSmoothLayer = new GenLayerSmooth(1000L, extrasLayer);
        RiverMixLayer finalBiomeLayer = new RiverMixLayer(100L, biomeSmoothLayer, riverLayer, worldData);
        GenLayerVoronoiZoom voronoiLayer = new GenLayerVoronoiZoom(10L, finalBiomeLayer);
        
        finalBiomeLayer.initWorldGenSeed(seed);
        voronoiLayer.initWorldGenSeed(seed);
        
        return new GenLayer[] {finalBiomeLayer, voronoiLayer, finalBiomeLayer};
    }
}
