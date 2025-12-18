package music_disk.command;

import music_disk.track.*;

import java.util.Scanner;

public class AddTrackCommand implements Command {
    private MusicDisk disk;
    private Scanner scanner = new Scanner(System.in);

    public AddTrackCommand(MusicDisk disk) {
        this.disk = disk;
    }

    @Override
    public void execute() {
        System.out.println("Select type: 1. Song, 2. Instrumental, 3. Vokal");
        System.out.print("You're choice: ");
        int type = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter artist: ");
        String artist = scanner.nextLine();

        System.out.print("Enter Genre (Rock, Pop, etc): ");
        String genre = scanner.nextLine();

        System.out.print("Enter duration (sec): ");
        int duration = Integer.parseInt(scanner.nextLine());

        MusicComposition newTrack = null;

        switch (type) {
            case 1: // Song
                System.out.print("Lyrics author: ");
                String author = scanner.nextLine();
                System.out.print("Instrumental producer: ");
                String producer = scanner.nextLine();
                newTrack = new Song(title, artist, duration, genre, author, producer);
                break;
            case 2: // Instrumental
                System.out.print("Instrumental producer: ");
                String instProducer = scanner.nextLine();
                newTrack = new Instrumental(title, artist, duration, genre, instProducer);
                break;
            case 3: // Vokal
                System.out.print("Lyrics author: ");
                String vocalAuthor = scanner.nextLine();
                newTrack = new Vokal(title, artist, duration, genre, vocalAuthor);
                break;
            default:
                System.out.println("Wrong type.");
                return;
        }

        disk.addTrack(newTrack);
    }

    @Override
    public String getCommandName() {
        return "Add composition to the album";
    }
}