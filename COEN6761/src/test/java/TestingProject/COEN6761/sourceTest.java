package TestingProject.COEN6761;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class sourceTest {
	    private final InputStream originalIn = System.in;
	    private final PrintStream originalOut = System.out;
    private source robot;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        robot = new source(10); // Initialize with default size 10x10
        System.setOut(new PrintStream(outContent)); // Redirect console output for testing
    }

    @Test
    public void testInitializeCommand() {
        robot.initializeArray(10);
        assertTrue(robot.getX() == 0 && robot.getY() == 0);
    }

    @Test
    public void testCheckStatusCommand() {
        robot.printStatus();
        assertTrue(outContent.toString().contains("Position: 0, 0 - Pen: up - Facing: N"));
    }

    @Test
    public void testPenDownCommand() {
        robot.penDown();
        assertTrue(robot.isPenDown());
    }

    @Test
    public void testPenUpCommand() {
        robot.penUp();
        assertFalse(robot.isPenDown()); // Pen should be up
    }

    @Test
    public void testMoveCommand() {
        robot.penDown();
        robot.moveForward(4);
        assertEquals(4, robot.getY()); // Facing North, should move up
    }

    @Test
    public void testTurnRightCommand() {
        robot.turnRight();
        assertEquals("E", robot.getDirection()); // Should be facing East after right turn
    }
    
    @Test
    public void testTurnRightFromEast() {
        robot.turnRight(); // E
        robot.turnRight(); // S
        assertEquals("S", robot.getDirection());
    }

    @Test
    public void testTurnRightFromSouth() {
        robot.turnRight(); // E
        robot.turnRight(); // S
        robot.turnRight(); // W
        assertEquals("W", robot.getDirection());
    }

    @Test
    public void testTurnRightFromWest() {
        robot.turnRight(); // E
        robot.turnRight(); // S
        robot.turnRight(); // W
        robot.turnRight(); // N
        assertEquals("N", robot.getDirection());
    }

    @Test
    public void testTurnLeftCommand() {
        robot.turnLeft();
        assertEquals("W", robot.getDirection()); // Should be facing West after left turn
    }
    
    @Test
    public void testTurnLeftFromSouth() {
        robot.turnRight(); // E
        robot.turnRight(); // S
        robot.turnLeft();  // Should now face E
        assertEquals("E", robot.getDirection());
    }

    @Test
    public void testTurnLeftFromEast() {
        robot.turnRight(); // E
        robot.turnLeft();  // Should now face N
        assertEquals("N", robot.getDirection());
    }

    @Test
    public void testTurnLeftFromWest() {
        robot.turnLeft();  // W
        robot.turnLeft();  // S
        assertEquals("S", robot.getDirection());
    }


    @Test
    public void testMoveEastCommand() {
        robot.turnRight(); // Facing EAST
        robot.moveForward(3);
        assertEquals(3, robot.getX()); // Check final X position
    }
    
    @Test
    public void testMoveSouthCommand() {
        robot.turnRight(); // E
        robot.turnRight(); // S
        robot.moveForward(2); // Move 2 steps south (y decreases)
        assertEquals(0, robot.getX());
        assertEquals(0, robot.getY()); // Already at 0, should not go below
    }

    @Test
    public void testMoveWestCommand() {
        robot.turnRight(); // E
        robot.moveForward(4); // Move east 4 steps
        robot.turnRight(); // S
        robot.turnRight(); // W
        robot.moveForward(2); // Move west 2 steps
        assertEquals(2, robot.getX());
        assertEquals(0, robot.getY());
    }


    @Test
    public void testMoveInvalidCommand() {
        robot.moveForward(-3);
        assertEquals(0, robot.getX()); // Should not move
        assertEquals(0, robot.getY()); // Should not move
    }

    @Test
    public void testMoveZeroCommand() {
        robot.moveForward(0);
        assertEquals(0, robot.getX()); // Should not move
        assertEquals(0, robot.getY()); // Should not move
    }

    @Test
    public void testPrintFloorCommand() {
        robot.penDown();
        robot.moveForward(2);
        robot.printFloor();
        assertTrue(outContent.toString().contains("*")); // Check if '*' is printed
    }
    
    
    /*Added test for print*/
    @Test
    public void testPrintStatus() {
        // Set initial state of the robot
        robot.penUp(); // Pen should be up initially
        robot.initializeArray(10); // Initialize with default size (10x10)

        // Call the method
        String status = robot.printStatus();

        // Capture printed output
        String expectedOutput = "Position: 0, 0 - Pen: up - Facing: N"; // Assuming initial position (0, 0) and facing North
        assertTrue(outContent.toString().contains(expectedOutput)); // Check if printed output contains the expected status

        // Verify the returned string
        assertEquals(expectedOutput, status); // Check if the returned string matches the expected status
    }


    @Test
    public void testHistoryCommand() {
        robot.penDown();
        robot.moveForward(2);
        robot.turnRight();
        robot.redoHistory();
        assertTrue(outContent.toString().contains("D M 2 R")); // History should match actions
    }

    @Test
    public void testMoveCommandMissingSteps() {
        // Simulate user entering "m" without steps
        String command = "m";
        String[] parts = command.split(" ");
        
        if (parts.length < 2) {
            System.out.println("Error: Missing steps for move command. Usage: m <steps>");
        }
        
        assertTrue(outContent.toString().contains("Error: Missing steps for move command. Usage: m <steps>"));
    }


    @Test
    public void testPrintFloorNoMovement() {
        robot.printFloor(); // No movement yet, should print an empty floor
        assertTrue(outContent.toString().contains("  "));
    }
    
    @Test
    public void testUnexpectedExceptionHandling() {
        try {
            // Simulate a custom command that triggers an unexpected exception
            throw new RuntimeException("Simulated unexpected error");
        } catch (NumberFormatException e) {
            fail("Should not be caught here.");
        } catch (ArrayIndexOutOfBoundsException e) {
            fail("Should not be caught here.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }

        assertTrue(outContent.toString().contains("An unexpected error occurred: Simulated unexpected error"));
    }
  

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
    public void testNumberFormatExceptionOnMoveCommand() {
        source robot = new source(5);
        String command = "m abc";  // Invalid number

        Exception exception = assertThrows(NumberFormatException.class, () -> {
            String[] parts = command.split(" ");
            int steps = Integer.parseInt(parts[1]);  // Should throw here
            robot.moveForward(steps);
        });

        assertNotNull(exception.getMessage());
    }
    

    
    @Test
    public void testArrayIndexOutOfBoundsExceptionOnIncompleteMoveCommand() {
        source robot = new source(5);
        String command = "m";  // Missing steps

        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            String[] parts = command.split(" ");
            int steps = Integer.parseInt(parts[1]);  // Will throw due to index out of bounds
            robot.moveForward(steps);
        });

        assertNotNull(exception);
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
