package TestingProject.COEN6761;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;

class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    private void simulateInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    void testValidInitializeCommand() {
        simulateInput("I 10\nQ\n");
        source.main(new String[]{});
        assertTrue(outContent.toString().contains("Floor initialized with size 10x10"));
    }

    @Test
    void testValidPenUpCommand() {
        simulateInput("U\nQ\n");
        source.main(new String[]{});
        assertTrue(outContent.toString().contains("Pen is up."));
    }

    @Test
    void testValidPenDownCommand() {
        simulateInput("D\nQ\n");
        source.main(new String[]{});
        assertTrue(outContent.toString().contains("Pen is down."));
    }

    @Test
    void testValidTurnRightCommand() {
        simulateInput("R\nQ\n");
        source.main(new String[]{});
        assertTrue(outContent.toString().contains("Turned right."));
    }

    @Test
    void testValidTurnLeftCommand() {
        simulateInput("L\nQ\n");
        source.main(new String[]{});
        assertTrue(outContent.toString().contains("Turned left."));
    }

    @Test
    void testValidMoveCommand() {
        simulateInput("I 10\nM 4\nQ\n");
        source.main(new String[]{});
        assertTrue(outContent.toString().contains("Moved forward 4 steps."));
    }

    @Test
    void testValidPrintFloorCommand() {
        simulateInput("I 10\nP\nQ\n");
        source.main(new String[]{});
        assertTrue(outContent.toString().contains("Floor:"));
    }

    @Test
    void testValidPrintStatusCommand() {
        simulateInput("C\nQ\n");
        source.main(new String[]{});
        assertTrue(outContent.toString().contains("Position:"));
    }

    @Test
    void testValidHistoryCommand() {
        simulateInput("H\nQ\n");
        source.main(new String[]{});
        assertFalse(outContent.toString().contains("U ") || outContent.toString().contains("D ") || outContent.toString().contains("M "));
    }

    @Test
    void testInvalidCommand() {
        simulateInput("X\nQ\n");
        source.main(new String[]{});
        assertTrue(outContent.toString().contains("Error: Invalid command."));
    }
    
    @Test
    void testInvalidInitializeCommand() {
        simulateInput("I -5\nQ\n");
        source.main(new String[]{});
        assertTrue(outContent.toString().contains("Error: Size must be a positive integer."));
    }

    @Test
    void testInvalidMoveCommand() {
        simulateInput("M -5\nQ\n");
        source.main(new String[]{});
        assertTrue(outContent.toString().contains("Error: Steps must be a non-negative integer."));
    }

    @Test
    void testInvalidMoveZeroCommand() {
        simulateInput("M 0\nQ\n");
        source.main(new String[]{});
        assertTrue(outContent.toString().contains("Moved forward 0 steps.")); // Edge case, no movement but valid
    }

    @Test
    void testHistoryCommandWithNoHistory() {
        simulateInput("H\nQ\n");
        source.main(new String[]{});
        assertTrue(outContent.toString().contains("Redid command history.") || outContent.toString().isEmpty());
    }

    @Test
    void testQuitWithoutCommands() {
        simulateInput("Q\n");
        source.main(new String[]{});
        assertTrue(outContent.toString().contains("Exiting program.")); // Check for exit message
    }

    @Test
    void testPenUpDownMultipleTimes() {
        simulateInput("D\nU\nD\nU\nQ\n");
        source.main(new String[]{});
        assertTrue(outContent.toString().contains("Pen is down.") && outContent.toString().contains("Pen is up.") );
    }

    /*@Test
    void testMoveWithoutPenDown() {
        simulateInput("M 5\nQ\n");
        source.main(new String[]{});
        assertTrue(outContent.toString().contains("Moved forward 5 steps.") && outContent.toString().contains("Pen is up."));
    }*/

}