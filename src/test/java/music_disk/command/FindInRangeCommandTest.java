package music_disk.command;
import music_disk.track.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class FindInRangeCommandTest {

    private MusicDisk disk;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        disk = new MusicDisk("Test Disk");
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void testExecuteFoundResults() {
        disk.addTrack(new Song("Short Song", "Artist A", 50, "Pop", "Author", "Prod"));
        disk.addTrack(new Song("Target Song", "Artist B", 150, "Rock", "Author", "Prod"));
        disk.addTrack(new Song("Long Song", "Artist C", 300, "Jazz", "Author", "Prod"));

        String input = "100\n200\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        FindInRangeCommand command = new FindInRangeCommand(disk);
        outputStreamCaptor.reset();
        command.execute();

        String output = outputStreamCaptor.toString();

        assertTrue(output.contains("Target Song"));
        assertFalse(output.contains("Short Song"));
        assertFalse(output.contains("Long Song"));
    }

    @Test
    void testExecuteNoResults() {
        disk.addTrack(new Song("Song A", "Artist A", 50, "Pop", "Author", "Prod"));

        String input = "100\n200\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        FindInRangeCommand command = new FindInRangeCommand(disk);
        command.execute();

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Nothing is found."));
    }

}