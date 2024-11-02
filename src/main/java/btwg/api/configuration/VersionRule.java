package btwg.api.configuration;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.BiPredicate;

public class VersionRule {
    private VersionRule(Type type, Version reference) {
        this.type = type;
        this.reference = reference;
    }
    
    private VersionRule(Type type, Version version, VersionRule ... corequisites) {
        this(type, version);
        this.corequisites = corequisites;
    }
    
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull VersionRule equals(Version reference) { return new VersionRule(Type.EQUAL, reference); }
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull VersionRule lessThan(Version reference) { return new VersionRule(Type.LESS, reference); }
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull VersionRule lessThanOrEqual(Version reference) { return new VersionRule(Type.LESS_OR_EQUAL, reference); }
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull VersionRule greaterThan(Version reference) { return new VersionRule(Type.GREATER, reference); }
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull VersionRule greaterThanOrEqual(Version reference) { return new VersionRule(Type.GREATER_OR_EQUAL, reference); }
    @Contract("_, _ -> new")
    public static @NotNull VersionRule betweenInclusive(Version lower, Version upper) {
        if (!lower.modid().equals(upper.modid())) {
            throw new IllegalArgumentException("Versions must be from the same mod!");
        }
        
        return new VersionRule(Type.LESS_OR_EQUAL, upper, new VersionRule(Type.GREATER_OR_EQUAL, lower));
    }
    
    private final Type type;
    private final Version reference;
    private VersionRule[] corequisites;
    
    public boolean test(Version version) {
        if (!version.modid().equals(this.reference.modid())) {
            return false;
        }
        
        return this.type.predicate.test(this.reference, version) && Arrays.stream(this.corequisites).allMatch(coreq -> coreq.test(version));
    }
    
    public enum Type {
        EQUAL(Version::equals),
        LESS(Version::lessThan),
        LESS_OR_EQUAL(Version::lessThanOrEqual),
        GREATER(Version::greaterThan),
        GREATER_OR_EQUAL(Version::greaterThanOrEqual);
        
        public final BiPredicate<Version, Version> predicate;
        
        Type(BiPredicate<Version, Version> predicate) {
            this.predicate = predicate;
        }
    }
}
