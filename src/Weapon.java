import java.util.Set;

public abstract class Weapon extends Item {
    private final String damageType;
    private final int damage;

    public Weapon(String name, String prefix, String description, String damageType, int damage) {
        super(name, prefix, description, Set.of("pickup", "attack"));
        this.damageType = damageType;
        this.damage = damage;
    }

    public abstract boolean canUse();
    public abstract int use();

    public String getDamageType() {
        return damageType;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return getShortName();   // use Item’s name
    }
}
