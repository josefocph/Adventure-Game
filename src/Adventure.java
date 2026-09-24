public class Adventure {
    private Room currentRoom;
    public Adventure(){
        createMap();
    }
    private void createMap(){
        Room room1 = new Room("Room 1", "A room with no distinct features.");
        Room room2 = new Room("Room 2", "Water drips from the ceiling.");
        Room room3 = new Room("Room 3", "A narrow passage.");
        Room room4 = new Room("Room 4", "Dusty and quite.");
        Room room5 = new Room("Room 5", "A mysterious central chamber.");
        Room room6 = new Room("Room 6", "A cold stone room.");
        Room room7 = new Room("Room 7", "A room with strange markings.");
        Room room8 = new Room("Room 8", "A dimly lit hallway.");
        Room room9 = new Room("Room 9", "A windy large chamber.");

        room1.setEast(room2);
        room2.setWest(room1);

        room2.setEast(room3);
        room3.setWest(room2);

        room1.setSouth(room4);
        room4.setNorth(room1);

        room4.setEast(room5);
        room5.setWest(room4);

        room5.setEast(room6);
        room6.setWest(room5);

        room4.setSouth(room7);
        room7.setNorth(room4);

        room7.setEast(room8);
        room8.setWest(room7);

        room8.setEast(room9);
        room9.setWest(room8);

        room2.setSouth(room5);
        room5.setNorth(room2);

        room3.setSouth(room6);
        room6.setNorth(room3);

        room5.setSouth(room8);
        room8.setNorth(room5);

        currentRoom = room1;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void goNorth(){
        moveTo(currentRoom.getNorth());
    }
    public void goEast(){
        moveTo(currentRoom.getEast());
    }
    public void goSouth(){
        moveTo(currentRoom.getSouth());
    }
    public void goWest(){
        moveTo(currentRoom.getWest());
    }
    private void moveTo(Room nextRoom){
        if(nextRoom == null){
            IO.println("There is a solid wall. You cannot go that way");
        }
        else {
            currentRoom = nextRoom;
            IO.println("You are in " + currentRoom.getName());
            IO.println(currentRoom.getDescription());
        }
    }
}
