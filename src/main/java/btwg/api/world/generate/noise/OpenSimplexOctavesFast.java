package btwg.api.world.generate.noise;

import java.util.Random;

import opensimplex2.OpenSimplex2;
import opensimplex2.OpenSimplex2F;
import opensimplex2.OpenSimplex2S;

public class OpenSimplexOctavesFast {
	private final OpenSimplex2F[] generators;

    private double lastX;
    private double lastZ;
    private double lastNoise2;
	
	public OpenSimplexOctavesFast(long seed, int numOctaves) {
		Random rand = new Random();
		rand.setSeed(seed);
		
		this.generators = new OpenSimplex2F[numOctaves];
		
		for (int i = 0; i < numOctaves; i++) {
			this.generators[i] = new OpenSimplex2F(rand.nextLong());
		}
	}
	
	public double noise2(double x, double y) {
		return this.noise2(x, y, 1);
	}
	
	public double noise2(double x, double y, double noiseScale) {
        if (x == this.lastX && y == this.lastZ) {
            return this.lastNoise2;
        }

		float octaveScale = 1;
		double noise = 0;

        for (OpenSimplex2F generator : generators) {
            noise += generator.noise2(x * noiseScale / octaveScale, y * noiseScale / octaveScale) * octaveScale;
            octaveScale /= 2;
        }

        this.lastNoise2 = noise;
		return noise;
	}
	
	public double noise3(double x, double y, double z) {
		float octaveScale = 1;
		double noise = 0;

        for (OpenSimplex2F generator : generators) {
            noise += generator.noise3_XZBeforeY(x / octaveScale, y / octaveScale, z / octaveScale) * octaveScale;
            octaveScale /= 2;
        }
		
		return noise;
	}
	
	public double noise3(double x, double y, double z, double noiseScale) {
		float octaveScale = 1;
		double noise = 0;

        for (OpenSimplex2F generator : generators) {
            noise += generator.noise3_XZBeforeY(x * noiseScale / octaveScale, y * noiseScale / octaveScale, z * noiseScale / octaveScale) * octaveScale;
            octaveScale /= 2;
        }
		
		return noise;
	}
	
	public double noise3(double x, double y, double z, double noiseScaleXZ, double noiseScaleY) {
		float octaveScale = 1;
		double noise = 0;

        for (OpenSimplex2F generator : generators) {
            noise += generator.noise3_XZBeforeY(x * noiseScaleXZ / octaveScale, y * noiseScaleY / octaveScale, z * noiseScaleXZ / octaveScale) * octaveScale;
            octaveScale /= 2;
        }
		
		return noise;
	}
}