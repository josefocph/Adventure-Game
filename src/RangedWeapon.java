public class RangedWeapon extends Weapon {

    private int ammo;

    public RangedWeapon(String name, String prefix, String description,
                        String damageType, int damage, int ammo) {
        super(name, prefix, description, damageType, damage);
        this.ammo = ammo;
    }

    @Override
    public boolean canUse() {
        return ammo > 0;
    }

    @Override
    public int use() {
        if (ammo > 0) {
            ammo--;
        }
        return ammo;
    }

    public int getAmmo() {
        return ammo;
    }
}


