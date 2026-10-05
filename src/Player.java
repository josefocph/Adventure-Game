import java.util.ArrayList;
import java.util.List;

public class Player {
    private World world;
    private Room currentRoom;
    private int currentHealth;
    private int maxHealth;
    private Weapon equippedWeapon;

    //
    private final List<Item> inventory = new ArrayList<>();

    public Player(World world, Room currentRoom) {
        this.world = world;
        this.currentRoom = getWorld().getStartRoom();       // <---
        this.currentHealth = 55;
        this.maxHealth = 100;
    }

    public World getWorld() {
        return this.world;
    }
    public Room getCurrentRoom() { //nyt
        return currentRoom;
    }
    public int getCurrentHealth() {
        return currentHealth;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    //
    public String getInventory() {
        String result = "";
        for (Item item : inventory) {
            result += item.getDisplayName() + "\n";
        }
        return result;
    }   // Might become its own class later
    public Item getItem(String name) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(name) || item.getDisplayName().equalsIgnoreCase(name))
                return item;
        }
        return null;
    }
    public boolean hasItem(String name) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    } // for later torch integration
    public boolean hasAnyItems() {
        return !inventory.isEmpty();
    }

    public boolean move(String direction) {
        Room next = switch (direction) {
            case "north" -> currentRoom.getNorth();
            case "east"  -> currentRoom.getEast();
            case "south" -> currentRoom.getSouth();
            case "west"  -> currentRoom.getWest();
            default      -> null;
        };
        if (next == null) {
            return false;
        }
        currentRoom = next;
        return true;
    }

    public EatResult eat(String name) {
        Item inventoryItem = getItem(name);
        Item roomItem = currentRoom.getItem(name);

        if (inventoryItem == null && roomItem == null) {
            return EatResult.NOT_FOUND;
        } if ((roomItem instanceof Food food)) {
            currentRoom.removeItem(name);
            currentHealth = Math.min(currentHealth + food.getHealth(), maxHealth);    //math.min stops health from going over 100
            return EatResult.CONSUMED;
        } if ((inventoryItem instanceof Food food)) {
            inventory.remove(food);
            currentHealth = Math.min(currentHealth + food.getHealth(), maxHealth);    //math.min stops health from going over 100
            return EatResult.CONSUMED;
        } else {
            return EatResult.NOT_EDIBLE;
        }
    }

    public void grab(Item item) {
        inventory.add(item);
    }
    public void drop(Item item) {
        inventory.remove(item);
    }
    public void hit(int damage) {
        currentHealth -= damage;
        IO.println("You take " + damage + " damage.");

        if (currentHealth <= 0) {
            IO.println("You have died.");
            System.exit(0);
        }
    }

    public void attack(String enemyName) {

        // 1. Check if room has enemies
        if (currentRoom.getEnemies().isEmpty()) {
            IO.println("You attack empty air.");
            return;
        }

        // 2. Find enemy
        Enemy target;

        if (enemyName == null || enemyName.isBlank()) {
            target = currentRoom.getEnemies().get(0); // first enemy
        } else {
            target = currentRoom.getEnemy(enemyName);
            if (target == null) {
                IO.println("There is no enemy named '" + enemyName + "' here.");
                return;
            }
        }

        // 3. Check weapon equipped
        if (equippedWeapon == null) {
            IO.println("You have no weapon equipped.");
            return;
        }

        // 4. Check weapon ammo
        if (!equippedWeapon.canUse()) {
            IO.println("The " + equippedWeapon.getName() + " is empty.");
            return;
        }

        // 5. Player attacks enemy
        int damage = equippedWeapon.getDamage();
        int remaining = equippedWeapon.use();

        IO.println("You hit " + target.getLongName() +
                " with the " + equippedWeapon.getName() +
                " for " + damage + " " + equippedWeapon.getDamageType() + " damage.");

        target.hit(damage);

        // If enemy died, hit() already removed it from the room
        if (target.getHealth() <= 0) {
            return;
        }

        // 6. Enemy counter-attacks
        IO.println(target.getLongName() + " strikes back!");
        target.attack(this);

        // 7. Player death handled inside hit()
    }
    public void equip(String name) {
        Item item = getItem(name);
        if (item instanceof Weapon weapon) {
            equippedWeapon = weapon;
            IO.println("You equip the " + weapon.getName() + ".");
        } else {
            IO.println("You cannot equip " + name + ".");
        }
    }

}
