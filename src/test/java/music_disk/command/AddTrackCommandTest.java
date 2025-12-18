package music_disk.command;
import music_disk.track.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddTrackCommandTest {

    private MusicDisk disk;
    private final InputStream originalSystemIn = System.in;

    @BeforeEach
    void setUp() {
        disk = new MusicDisk("Test Disk");
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
    }

    @Test
    void testAddSong() {
        String input = "1\nTest Title\nTest Artist\nRock\n200\nTest Author\nTest Producer\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        AddTrackCommand command = new AddTrackCommand(disk);
        command.execute();

        List<MusicComposition> tracks = disk.getTracks();
        assertEquals(1, tracks.size());
        assertTrue(tracks.get(0) instanceof Song);
        assertEquals("Test Title", tracks.get(0).getTitle());
    }

    @Test
    void testAddInstrumental() {
        String input = "2\nInst Title\nInst Artist\nJazz\n300\nInst Producer\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        AddTrackCommand command = new AddTrackCommand(disk);
        command.execute();

        List<MusicComposition> tracks = disk.getTracks();
        assertEquals(1, tracks.size());
        assertTrue(tracks.get(0) instanceof Instrumental);
        assertEquals("Inst Title", tracks.get(0).getTitle());
    }

    @Test
    void testAddVokal() {
        String input = "3\nVokal Title\nVokal Artist\nPop\n150\nVokal Author\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        AddTrackCommand command = new AddTrackCommand(disk);
        command.execute();

        List<MusicComposition> tracks = disk.getTracks();
        assertEquals(1, tracks.size());
        assertTrue(tracks.get(0) instanceof Vokal);
        assertEquals("Vokal Title", tracks.get(0).getTitle());
    }

    @Test
    void testAddInvalidType() {
        String input = "99\nTest Title\nTest Artist\nRock\n200\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        AddTrackCommand command = new AddTrackCommand(disk);
        command.execute();

        assertTrue(disk.getTracks().isEmpty());
    }
}