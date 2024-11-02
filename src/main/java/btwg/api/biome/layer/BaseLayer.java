package btwg.api.biome.layer;

import net.minecraft.src.*;

public class BaseLayer {
    public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType) {
        ContinentsLayer continentsLayer = new ContinentsLayer(1L);
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
        var5 = GenLayerZoom.magnify(1000L, riverInitLayer, scale + 2);
        GenLayer riverLayer = new GenLayerRiver(1L, var5);
        GenLayerSmooth smoothRiverLayer = new GenLayerSmooth(1000L, riverLayer);
        riverLayer = GenLayerZoom.magnify(1000L, smoothRiverLayer, 2);
        
        GenLayer var6 = GenLayerZoom.magnify(1000L, landShapeLayer, 0);
        BiomeLayer biomeLayer = new BiomeLayer(200L, var6, worldType, null);
        var6 = GenLayerZoom.magnify(1000L, biomeLayer, 2);
        
        GenLayer extrasLayer = new SubBiomeLayer(1000L, var6);
        
        for(int pass = 0; pass < scale; pass++) {
            extrasLayer = new GenLayerZoom(1000 + pass, extrasLayer);
            
            if (pass == 0) {
                extrasLayer = new GenLayerAddIsland(3L, extrasLayer);
            }
            
            if (pass == 1) {
                extrasLayer = new GenLayerShore(1000L, extrasLayer);
            }
            
            if (pass == 1) {
                extrasLayer = new GenLayerSwampRivers(1000L, extrasLayer);
            }
        }
        
        GenLayerSmooth biomeSmoothLayer = new GenLayerSmooth(1000L, extrasLayer);
        GenLayerRiverMix finalBiomeLayer = new GenLayerRiverMix(100L, biomeSmoothLayer, riverLayer);
        GenLayerVoronoiZoom voronoiLayer = new GenLayerVoronoiZoom(10L, finalBiomeLayer);
        
        finalBiomeLayer.initWorldGenSeed(seed);
        voronoiLayer.initWorldGenSeed(seed);
        
        return new GenLayer[] {finalBiomeLayer, voronoiLayer, finalBiomeLayer};
    }
}
