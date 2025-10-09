package btwg.api.world.feature;

import btwg.api.block.blocks.RegolithBlock;
import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

import java.util.Random;

public class ClayGenerator extends WorldGenerator {
    private int clayBlockId;
    private int radius;

    public ClayGenerator(int count) {
        this.clayBlockId = Block.blockClay.blockID;
        this.radius = count;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        if (world.getBlockMaterial(x, y, z) != Material.water) {
            return false;
        }

        int n = random.nextInt(this.radius - 2) + 2;
        int n2 = 1;

        for (int i = x - n; i <= x + n; ++i) {
            for (int k = z - n; k <= z + n; ++k) {
                int distX = i - x;
                int distZ = k - z;

                if (distX * distX + distZ * distZ > n * n) continue;

                for (int j = y - n2; j <= y + n2; ++j) {
                    int blockID = world.getBlockId(i, j, k);
                    Block block = Block.blocksList[blockID];

                    if (blockID != Block.dirt.blockID && blockID != Block.blockClay.blockID && !(block instanceof RegolithBlock)) continue;

                    world.setBlock(i, j, k, this.clayBlockId, 0, 2);
                }
            }
        }
        return true;
    }
}
