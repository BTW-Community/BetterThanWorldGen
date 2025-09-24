package btwg.mod.world.feature.tree;

import btw.world.feature.trees.grower.TreeGrowers;
import btwg.mod.block.WoodType;

public class BTWGTreeWoodTypes {
    public static final TreeGrowers.TreeWoodType ACACIA = new TreeGrowers.TreeWoodType(
            WoodType.ACACIA.logID(), WoodType.ACACIA.logMetadata(),
            WoodType.ACACIA.stumpID(), WoodType.ACACIA.stumpMetadata(),
            WoodType.ACACIA.leavesID(), WoodType.ACACIA.leavesMetadata()
    );

    public static final TreeGrowers.TreeWoodType DEAD = new TreeGrowers.TreeWoodType(
            WoodType.OAK.logID(), WoodType.OAK.logMetadata(),
            WoodType.OAK.stumpID(), WoodType.OAK.stumpMetadata(),
            0, 0
    );
}
