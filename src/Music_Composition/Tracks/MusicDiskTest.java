package Music_Composition.Tracks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class MusicDiskTest {

    private MusicDisk disk;

    @BeforeEach
    void setUp() {
        disk = new MusicDisk("Test Disk");
    }

    @Test
    void testAddTrack() {
        Song song = new Song("Test Title", "Test Artist", 100, "Rock", "Author", "Prod");

        disk.addTrack(song);

        assertEquals(1, disk.getTracks().size(), "В диску має бути 1 трек");
        assertEquals("Test Title", disk.getTracks().get(0).title, "Назва треку має співпадати");
    }

    @Test
    void testCalculateTotalDuration() {
        // Arrange: Додаємо два треки (100 сек + 200 сек)
        disk.addTrack(new Song("S1", "A1", 100, "Pop", "A", "P"));
        disk.addTrack(new Instrumental("I1", "A2", 200, "Jazz", "P"));

        int total = disk.calculateTotalDuration();

        assertEquals(300, total, "Загальна тривалість має бути 300 секунд");
    }

    @Test
    void testFindTracksByDuration() {
        disk.addTrack(new Song("Short", "A", 50, "Pop", "A", "P"));  // Закороткий
        disk.addTrack(new Song("Medium", "B", 150, "Rock", "A", "P")); // Підходить!
        disk.addTrack(new Song("Long", "C", 300, "Jazz", "A", "P"));   // Задовгий

        List<MusicComposition> found = disk.findTracksByDuration(100, 200);

        assertEquals(1, found.size(), "Має знайтись рівно 1 трек");
        assertEquals("Medium", found.get(0).title, "Знайдений трек має бути Medium");
    }

    @Test
    void testSortByGenre() {
        disk.addTrack(new Song("S1", "A", 100, "Rock", "A", "P"));
        disk.addTrack(new Song("S2", "A", 100, "Pop", "A", "P"));
        disk.addTrack(new Song("S3", "A", 100, "Jazz", "A", "P"));

        disk.sortByStyle(false);
        List<MusicComposition> tracks = disk.getTracks();

        assertEquals("Jazz", tracks.get(0).getGenre());
        assertEquals("Pop", tracks.get(1).getGenre());
        assertEquals("Rock", tracks.get(2).getGenre());
    }

    @Test
    void testSortByStyleReverse() {
        disk.addTrack(new Song("S1", "A", 100, "Abba", "A", "P"));
        disk.addTrack(new Song("S2", "A", 100, "Zulu", "A", "P"));

        disk.sortByStyle(true);
        List<MusicComposition> tracks = disk.getTracks();

        assertEquals("Zulu", tracks.get(0).getGenre());
        assertEquals("Abba", tracks.get(1).getGenre());
    }

    @Test
    void testVokalConstructor() {
        String title = "Tom's Diner";
        String artist = "Suzanne Vega";
        int duration = 129;
        String genre = "Acapella";
        String author = "Suzanne Vega";

        Vokal vokalTrack = new Vokal(title, artist, duration, genre, author);

        assertEquals("Tom's Diner", vokalTrack.title, "Назва має співпадати"); // Доступно, бо поля protected, а тест у тому ж пакеті
        assertEquals(129, vokalTrack.getDuration(), "Тривалість має співпадати");
        assertEquals("Acapella", vokalTrack.getGenre(), "Жанр має співпадати");
    }
}

