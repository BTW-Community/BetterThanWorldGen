package btwg.api.world;

import net.minecraft.src.WorldType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class WorldTypeUtils {
    public static WorldType createWorldType(int id, String name) {
        try {
            Constructor<WorldType> constructor = WorldType.class.getDeclaredConstructor(int.class, String.class);
            constructor.setAccessible(true);
            return constructor.newInstance(id, name);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
