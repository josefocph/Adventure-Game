import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private String description;
    private List<Item> items = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();   // <-- NEW

    private Room north;
    private Room east;
    private Room south;
    private Room west;
    private boolean isVisited;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.isVisited = false;
    }

    public void printVisited() {
        IO.println(isVisited == false
                ? description
                : "You've already been here...");
        this.isVisited = true;
    }

    // -----------------------------
    // Items
    // -----------------------------
    public void addItem(Item item) {
        items.add(item);
    }

    public boolean removeItem(String name) {
        Item item = getItem(name);
        if (item == null)
            return false;
        items.remove(item);
        return true;
    }

    public List<Item> getItems() {
        return items;
    }

    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getShortName().equalsIgnoreCase(name) ||
                    item.getDisplayName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public boolean hasItem(String name) {
        return getItem(name) != null;
    }

    // -----------------------------
    // Enemies (NEW)
    // -----------------------------
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Enemy getEnemy(String name) {
        for (Enemy e : enemies) {
            if (e.getShortName().equalsIgnoreCase(name) ||
                    e.getLongName().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null;
    }

    // -----------------------------
    // Directions
    // -----------------------------
    public Room getNorth() {
        return north;
    }
    public Room getEast() {
        return east;
    }
    public Room getSouth() {
        return south;
    }
    public Room getWest() {
        return west;
    }

    public void setNorth(Room room) {
        this.north = room;
    }
    public void setEast(Room room) {
        this.east = room;
    }
    public void setSouth(Room room) {
        this.south = room;
    }
    public void setWest(Room room) {
        this.west = room;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        StringBuilder sb = new StringBuilder(description);

        if (!enemies.isEmpty()) {
            sb.append("\nBeware! Here lurks: ");
            for (int i = 0; i < enemies.size(); i++) {
                Enemy e = enemies.get(i);
                sb.append(e.getLongName());
                if (i < enemies.size() - 1) {
                    sb.append(", ");
                }
            }
        }

        return sb.toString();
    }

}
