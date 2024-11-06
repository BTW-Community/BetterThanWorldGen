package btwg.mod.world.feature;

import btw.world.feature.trees.grower.AbstractTreeGrower;
import btw.world.feature.trees.grower.BushGrower;
import btw.world.feature.trees.grower.StandardTreeGrower;
import btw.world.feature.trees.grower.TreeGrowers;

public abstract class BTWGTreeGrowers {
    public static final AbstractTreeGrower OAK_BUSH = new BushGrower("btw:jungle_bush", TreeGrowers.OAK_WOOD_TYPE);
    public static final AbstractTreeGrower TALL_OAK_TREE = new StandardTreeGrower("btw:oak", 5, 8, TreeGrowers.OAK_WOOD_TYPE);
}
