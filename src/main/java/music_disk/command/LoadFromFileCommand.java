package music_disk.command;

import music_disk.track.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadFromFileCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LoadFromFileCommand.class);

    private MusicDisk disk;
    private static final String FILE_PATH = "music.txt";

    public LoadFromFileCommand(MusicDisk disk) {
        this.disk = disk;
    }

    @Override
    public void execute() {
        // Логуємо початок процесу
        logger.info("Reading file: " + FILE_PATH);

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            int count = 0;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length < 5) {
                    System.out.println("Detected empty line " + line);
                    logger.warn("Unexpected line: " + line);
                    continue;
                }

                String type = parts[0].trim();
                String title = parts[1].trim();
                String artist = parts[2].trim();
                int duration = Integer.parseInt(parts[3].trim());
                String genre = parts[4].trim();

                MusicComposition track = null;

                switch (type) {
                    case "Song":
                        if (parts.length >= 7) {
                            String lyricsAuthor = parts[5].trim();
                            String prod = parts[6].trim();
                            track = new Song(title, artist, duration, genre, lyricsAuthor, prod);
                        }
                        break;
                    case "Instrumental":
                        if (parts.length >= 6) {
                            String prod = parts[5].trim();
                            track = new Instrumental(title, artist, duration, genre, prod);
                        }
                        break;
                    case "Vokal":
                        if (parts.length >= 6) {
                            String lyricsAuthor = parts[5].trim();
                            track = new Vokal(title, artist, duration, genre, lyricsAuthor);
                        }
                        break;
                    default:
                        System.out.println("Incorrect type: " + type);
                        logger.warn("Incorrect type: " + type);
                }

                if (track != null) {
                    disk.addTrack(track);
                    count++;
                }
            }
            System.out.println("Successfully loaded " + count + " Tracks");
            logger.info("Successfully loaded: " + count + " Tracks");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            logger.error("Error to reach file " + FILE_PATH, e);
        } catch (NumberFormatException e) {
            System.out.println("Error:" + e.getMessage());
            logger.error("Critical error, incorrect number", e);
        }
    }

    @Override
    public String getCommandName() {
        return "Download from file";
    }
}