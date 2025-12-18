package music_disk.main;

import music_disk.track.MusicDisk;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

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
    void testExitImmediately() {
        String input = "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Menu menu = new Menu(disk);
        menu.run();

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Closing the app"));
    }

    @Test
    void testInvalidNumberInput() {
        String input = "99\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Menu menu = new Menu(disk);
        menu.run();

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Incorrect input."));
        assertTrue(output.contains("Closing the app"));
    }

    @Test
    void testInvalidTextInput() {
        String input = "hello\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Menu menu = new Menu(disk);
        menu.run();

        String output = outputStreamCaptor.toString();
        // Метод getUserInput поверне -1, що викличе "Incorrect input"
        assertTrue(output.contains("Incorrect input."));
    }

    @Test
    void testExecuteHelpCommand() {
        String input = "7\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Menu menu = new Menu(disk);
        menu.run();

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Available actions:"));
        assertTrue(output.contains("Closing the app"));
    }

    @Test
    void testMenuPrint() {
        String input = "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Menu menu = new Menu(disk);
        menu.run();

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("1. Check the album"));
        assertTrue(output.contains("2. Add composition"));
        assertTrue(output.contains("0. To close"));
    }
}