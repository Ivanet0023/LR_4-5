package music_disk.main;

import music_disk.command.*;
import music_disk.track.MusicDisk;
// 1. Додаємо імпорти для логування
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private static final Logger logger = LogManager.getLogger(Menu.class);

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

        logger.debug("Menu created. Commands loaded");
    }

    public void run() {
        logger.info("Launching the program.");

        while (running) {
            printMenu();
            int choice = getUserInput();

            logger.info("User picked : " + choice);

            if (choice == 0) {
                System.out.println("Closing the app");
                logger.info("User closed the app.");
                running = false;
                continue;
            }

            Command command = menuCommands.get(choice);

            if (command != null) {
                try {
                    command.execute();
                } catch (Exception e) {
                    System.out.println("Critical error occurred while executing command.");
                    logger.error("Critical error occurred while executing command" + command.getCommandName() + e);
                }
            } else {
                System.out.println("Incorrect input.");
                logger.warn("User entered unexpected num : " + choice);
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
            logger.warn("Error user input. Expected number, got: " + e.getMessage() + ".");
            return -1;
        }
    }
}