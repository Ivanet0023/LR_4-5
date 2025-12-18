package music_disk.command;

public class HelpCommand implements Command {

    @Override
    public void execute() {
        System.out.println("This program allows you to manage a music collection.");
        System.out.println("Available actions:");
        System.out.println("1. Show tracks - Displays all tracks currently in the disk.");
        System.out.println("2. Add composition - Manually add a new Song, Instrumental, or Vocal track.");
        System.out.println("3. Calculate duration - Calculates the total duration of all tracks.");
        System.out.println("4. Sort by style - Sorts tracks by genre (supports A-Z and Z-A).");
        System.out.println("5. Find by range - Finds tracks that fit within a specific time duration.");
        System.out.println("6. Load from file - Reads tracks from 'music.txt'.");
        System.out.println("7. Help - Displays this information.");
        System.out.println("0. Exit - Closes the program.");
    }

    @Override
    public String getCommandName() {
        return "Help";
    }
}