package Command;

import Tracks.MusicComposition;

import java.util.ArrayList;
import java.util.List;

public class MusicDisk {

    private String title;
    private List<MusicComposition> tracks; // Поле є, але логіка його не використовує

    public MusicDisk(String title) {
        this.title = title;
        this.tracks = new ArrayList<>();
    }

    public void addTrack(MusicComposition track) {

    }

    public List<MusicComposition> getTracks() {

        return this.tracks;
    }

    public int calculateTotalDuration() {

        return 0;
    }

    public void sortByGenre() {

    }

    public List<MusicComposition> findTracksByDuration(int minSec, int maxSec) {

        // порожній список
        return new ArrayList<MusicComposition>();
    }
}