import java.util.Scanner;

public class UserInterface {
    private Adventure adventure;
    private Scanner scanner = new Scanner(System.in);
    public UserInterface(Adventure adventure){
        this.adventure = adventure;
    }
    public void start(){
        IO.println("==================================");
        IO.println("  Welcome to the adventure game");
        IO.println("==================================");
        IO.println("Type help, if you don't know where to go");
        boolean running = true;
        while (running){
            IO.print(">");
            String command = scanner.nextLine().toLowerCase();
            running = handleCommand(command);
        }
    }
    private boolean handleCommand(String command){
        switch (command) {
            case "go north":
            case "north":
            case "n":
                adventure.goNorth();
                break;
            case "go east":
            case "east":
            case "e":
                adventure.goEast();
                break;
            case "go south":
            case "south":
            case "s":
                adventure.goSouth();
                break;
            case "go west":
            case "west":
            case "w":
                adventure.goWest();
                break;
            case "look":
                IO.println(adventure.getCurrentRoom().getDescription());
                break;
            case "help":
                IO.println("Commands: ");
                IO.println("n,e,s,w : Move north, east, south, west");
                IO.println("look:     Looking around the room");
                IO.println("help      Show the menu");
                IO.println("Exit      Leave the game");
                break;
            case "exit":
                IO.println("Goodbye");
                return false;
            default:
                IO.println("Unknown command.");
        }
        return true;
    }
}
