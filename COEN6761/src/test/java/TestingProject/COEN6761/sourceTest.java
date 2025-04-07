package TestingProject.COEN6761;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class sourceTest {
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

    @Test
    public void testHistoryCommand() {
        robot.penDown();
        robot.moveForward(2);
        robot.turnRight();
        robot.redoHistory();
        assertTrue(outContent.toString().contains("D M 2 R")); // History should match actions
    }



    @Test
    public void testPrintFloorNoMovement() {
        robot.printFloor(); // No movement yet, should print an empty floor
        assertTrue(outContent.toString().contains("  "));
    }


}
