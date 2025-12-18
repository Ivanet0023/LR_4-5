package music_disk.command;
import music_disk.track.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoadFromFileCommandTest {

    private MusicDisk disk;
    private final File testFile = new File("music.txt");
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        disk = new MusicDisk("Test Disk");
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        if (testFile.exists()) {
            testFile.delete();
        }
        System.setOut(originalOut);
    }

    private void createTestFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write(content);
        }
    }

    @Test
    void testExecuteValidFile() throws IOException {
        String content = "Song;Shape of You;Ed Sheeran;240;Pop;Ed;Steve\n" +
                "Instrumental;Axel F;Harold;180;Synth;Harold\n" +
                "Vokal;Tom's Diner;Suzanne;129;Acapella;Suzanne\n";
        createTestFile(content);

        LoadFromFileCommand command = new LoadFromFileCommand(disk);
        command.execute();

        List<MusicComposition> tracks = disk.getTracks();
        assertEquals(3, tracks.size());

        assertTrue(tracks.get(0) instanceof Song);
        assertEquals("Shape of You", tracks.get(0).getTitle());

        assertTrue(tracks.get(1) instanceof Instrumental);
        assertEquals("Axel F", tracks.get(1).getTitle());

        assertTrue(tracks.get(2) instanceof Vokal);
        assertEquals("Tom's Diner", tracks.get(2).getTitle());
    }

    @Test
    void testExecuteInvalidLines() throws IOException {
        String content = "BadLine\n" +
                "Song;Short;Line\n" +
                "UnknownType;Title;Artist;100;Genre;A;P\n" +
                "Song;Title;Artist;NotANumber;Genre;A;P\n";
        createTestFile(content);

        LoadFromFileCommand command = new LoadFromFileCommand(disk);
        command.execute();

        List<MusicComposition> tracks = disk.getTracks();
        assertTrue(tracks.isEmpty());

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Detected empty line"));
        assertTrue(output.contains("Incorrect type"));
        assertTrue(output.contains("Error"));
    }

    @Test
    void testExecuteFileNotFound() {
        if (testFile.exists()) {
            testFile.delete();
        }

        LoadFromFileCommand command = new LoadFromFileCommand(disk);
        command.execute();

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Error"));
    }

    @Test
    void testGetCommandName() {
        LoadFromFileCommand command = new LoadFromFileCommand(disk);
        assertEquals("Download from file", command.getCommandName());
    }
}