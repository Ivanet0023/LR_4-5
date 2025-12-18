package music_disk.track;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MusicDisk {

    private static final Logger logger = LogManager.getLogger(MusicDisk.class);

    private String title;
    private List<MusicComposition> tracks;

    public MusicDisk(String title) {
        this.title = title;
        this.tracks = new ArrayList<>();
        logger.info("New disk created: " + title);
    }

    public void addTrack(MusicComposition track) {
        this.tracks.add(track);
        System.out.println("Track '" + track.getTitle() + "' added.");
        logger.info("Track '" + track.getTitle() + "' added.");;
    }

    public void removeTrack(int index) {
        if (index >= 0 && index < tracks.size()) {
            MusicComposition removed = tracks.remove(index);
            System.out.println("Track '" + removed.getTitle() + "' deleted.");
            logger.info("Track '" + removed.getTitle() + "' deleted.");
        } else {
            System.out.println("Wrong track number.");
            logger.warn("Trying to delete unexisted track: " + index);
        }
    }

    public List<MusicComposition> getTracks() {
        return this.tracks;
    }

    public int calculateTotalDuration() {
        int total = 0;
        for (MusicComposition track : tracks) {
            total += track.getDuration();
        }
        logger.debug("Total Duration: " + total);
        return total;
    }

    public void sortByStyle(boolean reverse) {
        Comparator<MusicComposition> comparator = Comparator.comparing(MusicComposition::getGenre);

        if (reverse) {
            comparator = comparator.reversed();
        }

        this.tracks.sort(comparator);
        System.out.println("Sorted by Genre " + (reverse ? "(Z-A)" : "(A-Z)"));
        logger.info("Sorted by Genre. Реверс: " + reverse);
    }

    public List<MusicComposition> findTracksByDuration(int min, int max) {
        logger.info("Searching in : " + min + " - " + max);
        List<MusicComposition> result = new ArrayList<>();
        for (MusicComposition track : tracks) {
            if (track.getDuration() >= min && track.getDuration() <= max) {
                result.add(track);
            }
        }
        logger.debug("Founded: " + result.size());
        return result;
    }
}