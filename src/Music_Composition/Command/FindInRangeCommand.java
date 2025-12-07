package Music_Composition.Command;

import Music_Composition.Tracks.MusicComposition;
import Music_Composition.Tracks.MusicDisk;

import java.util.List;
import java.util.Scanner;

public class FindInRangeCommand implements Command {
    private MusicDisk disk;
    private Scanner scanner = new Scanner(System.in);

    public FindInRangeCommand(MusicDisk disk) {
        this.disk = disk;
    }

    @Override
    public void execute() {
        System.out.print("Enter minimal duration (sec): ");
        int min = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter maximum duration (sec): ");
        int max = Integer.parseInt(scanner.nextLine());

        List<MusicComposition> results = disk.findTracksByDuration(min, max);

        if (results.isEmpty()) {
            System.out.println("Nothing is found.");
        } else {
            for (MusicComposition track : results) {
                System.out.println(track.getFullInfo());
            }
        }
    }

    @Override
    public String getCommandName() {
        return "Find Compositions in range";
    }
}