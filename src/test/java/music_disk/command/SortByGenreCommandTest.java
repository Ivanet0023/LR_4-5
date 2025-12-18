package music_disk.command;
import music_disk.track.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortByGenreCommandTest {

    private MusicDisk disk;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        disk = new MusicDisk("Test Disk");

        disk.addTrack(new Song("1", "A", 100, "Rock", "A", "P"));
        disk.addTrack(new Song("2", "A", 100, "Pop", "A", "P"));
        disk.addTrack(new Song("3", "A", 100, "Ambient", "A", "P"));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testSortAscending() {
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        SortByGenreCommand command = new SortByGenreCommand(disk);

        // Очищаємо буфер від повідомлень додавання треків
        outputStreamCaptor.reset();

        command.execute();

        List<MusicComposition> tracks = disk.getTracks();
        assertEquals("Ambient", tracks.get(0).getGenre());
        assertEquals("Pop", tracks.get(1).getGenre());
        assertEquals("Rock", tracks.get(2).getGenre());

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Sorted by Genre (A-Z)"));
    }

    @Test
    void testSortDescending() {
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        SortByGenreCommand command = new SortByGenreCommand(disk);

        outputStreamCaptor.reset();

        command.execute();

        List<MusicComposition> tracks = disk.getTracks();
        assertEquals("Rock", tracks.get(0).getGenre());
        assertEquals("Pop", tracks.get(1).getGenre());
        assertEquals("Ambient", tracks.get(2).getGenre());

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Sorted by Genre (Z-A)"));
    }

    @Test
    void testSortInvalidInputDefaultToAscending() {
        String input = "any other text\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        SortByGenreCommand command = new SortByGenreCommand(disk);
        command.execute();

        List<MusicComposition> tracks = disk.getTracks();
        assertEquals("Ambient", tracks.get(0).getGenre());
    }

    @Test
    void testGetCommandName() {
        SortByGenreCommand command = new SortByGenreCommand(disk);
        assertEquals("Sort by Genre", command.getCommandName());
    }
}