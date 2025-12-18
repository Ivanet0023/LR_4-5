package music_disk.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class HelpCommandTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testExecutePrintsHelpInfo() {
        HelpCommand command = new HelpCommand();
        command.execute();

        String output = outputStreamCaptor.toString();

        assertTrue(output.contains("This program allows you to manage a music collection."));
        assertTrue(output.contains("1. Show tracks"));
        assertTrue(output.contains("6. Load from file"));
        assertTrue(output.contains("0. Exit"));
    }

    @Test
    void testGetCommandName() {
        HelpCommand command = new HelpCommand();
        assertEquals("Help", command.getCommandName());
    }
}