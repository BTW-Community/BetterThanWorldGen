package btwg.api.world.feature;

import btw.world.util.BlockPos;
import btwg.api.block.StoneType;
import net.minecraft.src.*;

import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

public class OreGenerator extends WorldGenerator {
    protected final Function<StoneType, Integer> blockID;
    protected final Function<StoneType, Integer> metadata;

    protected final int size;
    protected float airExposureRatio;

    public OreGenerator(Function<StoneType, Integer> blockID, Function<StoneType, Integer> metadata, int size) {
        super(true);

        this.blockID = blockID;
        this.metadata = metadata;
        this.size = size;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        float var6 = rand.nextFloat() * (float)Math.PI;

        double var7 = (x + 8) + MathHelper.sin(var6) * this.size / 8.0f;
        double var9 = (x + 8) - MathHelper.sin(var6) * this.size / 8.0f;
        double var11 = (z + 8) + MathHelper.cos(var6) * this.size / 8.0f;
        double var13 = (z + 8) - MathHelper.cos(var6) * this.size / 8.0f;

        double var15 = y + rand.nextInt(3) - 2;
        double var17 = y + rand.nextInt(3) - 2;

        for (int var19 = 0; var19 <= this.size; ++var19) {
            double var20 = var7 + (var9 - var7) * var19 / this.size;
            double var22 = var15 + (var17 - var15) * var19 / this.size;
            double var24 = var11 + (var13 - var11) * var19 / this.size;

            double var26 = rand.nextDouble() * this.size / 16.0;

            double var28 = (MathHelper.sin((float) (var19 * Math.PI / this.size)) + 1.0f) * var26 + 1.0;
            double var30 = (MathHelper.sin((float) (var19 * Math.PI / this.size)) + 1.0f) * var26 + 1.0;

            int minX = MathHelper.floor_double(var20 - var28 / 2.0);
            int minY = MathHelper.floor_double(var22 - var30 / 2.0);
            int minZ = MathHelper.floor_double(var24 - var28 / 2.0);
            int maxX = MathHelper.floor_double(var20 + var28 / 2.0);
            int maxY = MathHelper.floor_double(var22 + var30 / 2.0);
            int maxZ = MathHelper.floor_double(var24 + var28 / 2.0);

            for (int i = minX; i <= maxX; ++i) {
                double distX = (i + 0.5 - var20) / (var28 / 2.0);

                if (!(distX * distX < 1.0))
                    continue;

                for (int j = minY; j <= maxY; ++j) {
                    double distY = (j + 0.5 - var22) / (var30 / 2.0);

                    if (!(distX * distX + distY * distY < 1.0))
                        continue;

                    for (int k = minZ; k <= maxZ; ++k) {
                        double distZ = (k + 0.5 - var24) / (var28 / 2.0);

                        int blockID = world.getBlockId(i, j, k);
                        int metadata = world.getBlockMetadata(i, j, k);

                        Optional<StoneType> stoneType = StoneType.getStoneType(blockID, metadata);

                        if (!(distX * distX + distY * distY + distZ * distZ < 1.0)
                                || stoneType.isEmpty()
                                || !this.shouldSpawnOreBlock(world, rand, i, j, k))
                            continue;

                        world.setBlock(i, j, k, this.blockID.apply(stoneType.get()), this.metadata.apply(stoneType.get()), 2);
                    }
                }
            }
        }

        return true;
    }

    public OreGenerator setNeedsAirExposure(float ratio) {
        if (ratio < 0.0f || ratio > 1.0f) {
            throw new IllegalArgumentException("Ratio for air exposure must be between 0 and 1, inclusive");
        }
        this.airExposureRatio = ratio;
        return this;
    }

    public OreGenerator setNeedsAirExposure() {
        return this.setNeedsAirExposure(1.0f);
    }

    public OreGenerator setNoAirExposure(float ratio) {
        if (ratio < 0.0f || ratio > 1.0f) {
            throw new IllegalArgumentException("Ratio for air exposure must be between 0 and 1, inclusive");
        }
        this.airExposureRatio = -ratio;
        return this;
    }

    public OreGenerator setNoAirExposure() {
        return this.setNoAirExposure(1.0f);
    }

    private boolean shouldSpawnOreBlock(World world, Random rand, int x, int y, int z) {
        if (this.airExposureRatio == 0.0f) {
            return true;
        }
        boolean blockExposedToAir = this.isBlockExposedToAir(world, x, y, z);
        if (!blockExposedToAir && this.airExposureRatio > 0.0f) {
            return rand.nextFloat() > this.airExposureRatio;
        }
        if (blockExposedToAir && this.airExposureRatio < 0.0f) {
            float ratio = -this.airExposureRatio;
            return rand.nextFloat() > ratio;
        }
        return true;
    }

    private boolean isBlockExposedToAir(World world, int x, int y, int z) {
        for (int i = 0; i < 6; ++i) {
            BlockPos pos = new BlockPos(x, y, z);
            pos.addFacingAsOffset(i);
            if (!world.isAirBlock(pos.x, pos.y, pos.z)) continue;
            return true;
        }
        return false;
    }
}
