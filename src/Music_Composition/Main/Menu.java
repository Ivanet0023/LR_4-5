package Music_Composition.Main;

import Music_Composition.Command.*;
import Music_Composition.Tracks.MusicDisk;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    // Карта команд
    private Map<Integer, Command> menuCommands = new LinkedHashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private boolean running = true;

    public Menu(MusicDisk disk) {
        menuCommands.put(1, new ShowTracksCommand(disk));
        menuCommands.put(2, new AddTrackCommand(disk));
        menuCommands.put(3, new CalculateDurationCommand(disk));
        menuCommands.put(4, new SortByGenreCommand(disk));
        menuCommands.put(5, new FindInRangeCommand(disk));
        menuCommands.put(6, new LoadFromFileCommand(disk));
        menuCommands.put(7, new HelpCommand());
    }

    public void run() {
        while (running) {
            printMenu();
            int choice = getUserInput();

            if (choice == 0) {
                System.out.println("Closing the app");
                running = false;
                continue;
            }

            Command command = menuCommands.get(choice);

            if (command != null) {
                command.execute();
            } else {
                System.out.println("Incorrect input.");
            }
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("Menu:");
        for (Map.Entry<Integer, Command> entry : menuCommands.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getCommandName());
        }
        System.out.println("0. To close");
        System.out.print("You're choice: ");
    }

    private int getUserInput() {
        try {
            String line = scanner.nextLine();
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}