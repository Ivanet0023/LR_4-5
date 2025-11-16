package Command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private Map<Integer, Command> menuCommands = new LinkedHashMap<>();

    private Scanner scanner = new Scanner(System.in);
    private boolean running = true;

    public void addCommand(int key, Command command) {
        menuCommands.put(key, command);
    }

    public void run() {
        while (running) {
            printMenu();
            int choice = getUserInput();

            Command command = menuCommands.get(choice);

            if (command != null) {
                if (command instanceof ExitCommand) {
                    this.running = false;
                }

                command.execute();
            } else {
                System.out.println("Error, try again.");
            }
        }
        scanner.close();
    }

    private void printMenu() {
        for (Map.Entry<Integer, Command> entry : menuCommands.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getCommandName());
        }
        System.out.print("Your choose: ");
    }

    private int getUserInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}