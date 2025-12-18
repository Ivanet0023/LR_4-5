package music_disk.track;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MusicDiskTest {

    private MusicDisk disk;

    @BeforeEach
    void setUp() {
        disk = new MusicDisk("Test Album");
    }

    @Test
    void testAddAndGetTracks() {
        Song track = new Song("Test Title", "Test Artist", 180, "Pop", "Author", "Prod");

        disk.addTrack(track);

        List<MusicComposition> tracks = disk.getTracks();
        assertEquals(1, tracks.size(), "Має бути 1 трек");
        assertEquals("Test Title", tracks.get(0).getTitle(), "Назви мають співпадати");
    }

    @Test
    void testRemoveTrackSuccess() {
        disk.addTrack(new Song("S1", "A", 100, "Pop", "A", "P"));

        disk.removeTrack(0);

        assertTrue(disk.getTracks().isEmpty(), "Список має стати порожнім");
    }

    @Test
    void testRemoveTrackInvalidIndex() {
        disk.addTrack(new Song("S1", "A", 100, "Pop", "A", "P"));

        disk.removeTrack(5);
        disk.removeTrack(-1);

        assertEquals(1, disk.getTracks().size(), "Трек не мав видалитися");
    }

    @Test
    void testCalculateTotalDuration() {
        assertEquals(0, disk.calculateTotalDuration());

        disk.addTrack(new Song("S1", "A", 100, "Pop", "A", "P"));
        disk.addTrack(new Song("S2", "A", 200, "Rock", "A", "P"));

        assertEquals(300, disk.calculateTotalDuration());
    }

    @Test
    void testSortByStyleAscending() {
        disk.addTrack(new Song("1", "A", 1, "Rock", "A", "P"));
        disk.addTrack(new Song("2", "A", 1, "Pop", "A", "P"));

        disk.sortByStyle(false);

        assertEquals("Pop", disk.getTracks().get(0).getGenre());
        assertEquals("Rock", disk.getTracks().get(1).getGenre());
    }

    @Test
    void testSortByStyleDescending() {
        disk.addTrack(new Song("1", "A", 1, "Ambient", "A", "P"));
        disk.addTrack(new Song("2", "A", 1, "Zulu", "A", "P"));

        disk.sortByStyle(true);

        assertEquals("Zulu", disk.getTracks().get(0).getGenre());
        assertEquals("Ambient", disk.getTracks().get(1).getGenre());
    }

    @Test
    void testFindTracksByDuration() {
        disk.addTrack(new Song("Short", "A", 50, "Pop", "A", "P"));
        disk.addTrack(new Song("Target", "B", 150, "Rock", "A", "P"));
        disk.addTrack(new Song("Long", "C", 300, "Jazz", "A", "P"));

        List<MusicComposition> result = disk.findTracksByDuration(100, 200);

        assertEquals(1, result.size());
        assertEquals("Target", result.get(0).getTitle());
    }

    @Test
    void testFindTracksByDurationEmpty() {
        disk.addTrack(new Song("S", "A", 50, "Pop", "A", "P"));

        List<MusicComposition> result = disk.findTracksByDuration(1000, 2000);

        assertTrue(result.isEmpty());
    }
}