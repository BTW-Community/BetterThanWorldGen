package btwg.mod.world.feature.tree;

import btw.world.feature.trees.grower.TreeGrowers;
import btwg.mod.block.WoodType;

public class BTWGTreeWoodTypes {
    public static final TreeGrowers.TreeWoodType ACACIA = new TreeGrowers.TreeWoodType(
            WoodType.ACACIA.logID(), WoodType.ACACIA.logMetadata(),
            WoodType.ACACIA.stumpID(), WoodType.ACACIA.stumpMetadata(),
            WoodType.ACACIA.leavesID(), WoodType.ACACIA.leavesMetadata()
    );

    public static final TreeGrowers.TreeWoodType DARK_OAK = new TreeGrowers.TreeWoodType(
            WoodType.DARK_OAK.logID(), WoodType.DARK_OAK.logMetadata(),
            WoodType.DARK_OAK.stumpID(), WoodType.DARK_OAK.stumpMetadata(),
            WoodType.DARK_OAK.leavesID(), WoodType.DARK_OAK.leavesMetadata()
    );

    public static final TreeGrowers.TreeWoodType MANGROVE = new TreeGrowers.TreeWoodType(
            WoodType.MANGROVE.logID(), WoodType.MANGROVE.logMetadata(),
            WoodType.MANGROVE.stumpID(), WoodType.MANGROVE.stumpMetadata(),
            WoodType.MANGROVE.leavesID(), WoodType.MANGROVE.leavesMetadata()
    );

    public static final TreeGrowers.TreeWoodType CHERRY = new TreeGrowers.TreeWoodType(
            WoodType.CHERRY.logID(), WoodType.CHERRY.logMetadata(),
            WoodType.CHERRY.stumpID(), WoodType.CHERRY.stumpMetadata(),
            WoodType.CHERRY.leavesID(), WoodType.CHERRY.leavesMetadata()
    );

    public static final TreeGrowers.TreeWoodType PALE_OAK = new TreeGrowers.TreeWoodType(
            WoodType.PALE_OAK.logID(), WoodType.PALE_OAK.logMetadata(),
            WoodType.PALE_OAK.stumpID(), WoodType.PALE_OAK.stumpMetadata(),
            WoodType.PALE_OAK.leavesID(), WoodType.PALE_OAK.leavesMetadata()
    );

    public static final TreeGrowers.TreeWoodType DEAD = new TreeGrowers.TreeWoodType(
            WoodType.OAK.logID(), WoodType.OAK.logMetadata(),
            WoodType.OAK.stumpID(), WoodType.OAK.stumpMetadata(),
            0, 0
    );
}
