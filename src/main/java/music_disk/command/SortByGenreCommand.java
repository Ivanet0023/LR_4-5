package music_disk.command;

import music_disk.track.MusicDisk;

import java.util.Scanner;

public class SortByGenreCommand implements Command {
    private MusicDisk disk;
    private Scanner scanner = new Scanner(System.in);

    public SortByGenreCommand(MusicDisk disk) {
        this.disk = disk;
    }

    @Override
    public void execute() {
        System.out.println("1. Straight order (A-Z)");
        System.out.println("2. Reversed order (Z-A)");
        System.out.print("You're choice: ");

        String input = scanner.nextLine();
        boolean reverse = input.equals("2");
        disk.sortByStyle(reverse);
        new ShowTracksCommand(disk).execute();
    }

    @Override
    public String getCommandName() {
        return "Sort by Genre";
    }
}