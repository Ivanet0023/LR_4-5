package music_disk.command;
import music_disk.track.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ShowTracksCommandTest {

    private MusicDisk disk;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        disk = new MusicDisk("Test Disk");
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testExecuteEmptyDisk() {
        ShowTracksCommand command = new ShowTracksCommand(disk);
        outputStreamCaptor.reset();

        command.execute();

        String output = outputStreamCaptor.toString().trim();

        assertEquals("(Empty)", output);
    }

    @Test
    void testExecuteWithTracks() {
        disk.addTrack(new Song("Track 1", "Artist 1", 100, "Pop", "A", "P"));
        disk.addTrack(new Song("Track 2", "Artist 2", 200, "Rock", "A", "P"));

        ShowTracksCommand command = new ShowTracksCommand(disk);

        outputStreamCaptor.reset();

        command.execute();

        String output = outputStreamCaptor.toString();

        assertTrue(output.contains("1. "));
        assertTrue(output.contains("Track 1"));
        assertTrue(output.contains("2. "));
        assertTrue(output.contains("Track 2"));
    }

    @Test
    void testGetCommandName() {
        ShowTracksCommand command = new ShowTracksCommand(disk);
        assertEquals("Check the album", command.getCommandName());
    }
}