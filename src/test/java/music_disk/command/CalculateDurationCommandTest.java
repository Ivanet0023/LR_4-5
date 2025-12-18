package music_disk.command;
import music_disk.track.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CalculateDurationCommandTest {

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
    void testExecuteWithZeroDuration() {
        CalculateDurationCommand command = new CalculateDurationCommand(disk);
        command.execute();

        assertEquals("Album is empty, or duration is 0.", outputStreamCaptor.toString().trim());
    }

    @Test
    void testExecuteWithPositiveDuration() {
        disk.addTrack(new Song("Test Title", "Test Artist", 100, "Pop", "Author", "Producer"));

        outputStreamCaptor.reset();
        CalculateDurationCommand command = new CalculateDurationCommand(disk);

        command.execute();

        String expectedOutput = "1min. 40sec. (100 total sec.)";
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
    }

}