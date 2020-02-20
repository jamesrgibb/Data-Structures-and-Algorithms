package assignment10;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

public class PathFinderTest {

    @Ignore
    public int countPeriodsInFile(String fileName) {
        SimpleReader inReader = new SimpleReader1L(fileName);
        String[] dims = inReader.nextLine().split(" ");
        int numOfPeriods = 0;
        for (int i = 0; i < Integer.parseInt(dims[0]); i++) {
            char[] eachChar = inReader.nextLine().toCharArray();
            for (int j = 0; j < eachChar.length; j++) {
                if(eachChar[j] == '.') {
                    numOfPeriods++;
                }
            }
        }
        inReader.close();
        return numOfPeriods + 1;
    }

    @Test
    public void testDemoMaze() {
        int x = PathFinder.solveMaze("data/demoMaze.txt", "data/demoMazeTest.txt");
        int y = countPeriodsInFile("data/demoMazeSol.txt");
        assertEquals(y, x);
    }

    @Test
    public void testBigMaze() {
        int x = PathFinder.solveMaze("data/bigMaze.txt", "data/bigMazeTest.txt");
        int y = countPeriodsInFile("data/bigMazeSol.txt");
        assertEquals(y, x);
    }

    @Test
    public void testClassicMaze() {
        int x = PathFinder.solveMaze("data/classic.txt", "data/classicTest.txt");
        int y = countPeriodsInFile("data/classicSol.txt");
        assertEquals(y, x);
    }

    @Test
    public void testMediumMaze() {
        int x = PathFinder.solveMaze("data/mediumMaze.txt", "data/mediumMazeTest.txt");
        int y = countPeriodsInFile("data/mediumMazeSol.txt");
        assertEquals(y, x);
    }

    @Test
    public void testRandomMaze() {
        int x = PathFinder.solveMaze("data/randomMaze.txt", "data/randomMazeTest.txt");
        int y = countPeriodsInFile("data/randomMazeSol.txt");
        assertEquals(y, x);
    }

    @Test
    public void testStraightMaze() {
        int x = PathFinder.solveMaze("data/straight.txt", "data/straightTest.txt");
        int y = countPeriodsInFile("data/straightSol.txt");
        assertEquals(y, x);
    }

    @Test
    public void testTinyMaze() {
        int x = PathFinder.solveMaze("data/tinyMaze.txt", "data/tinyMazeTest.txt");
        int y = countPeriodsInFile("data/tinyMazeSol.txt");
        assertEquals(y, x);
    }

    @Test
    public void testTinyOpenMaze() {
        int x = PathFinder.solveMaze("data/tinyOpen.txt", "data/tinyOpenTest.txt");
        int y = countPeriodsInFile("data/tinyOpenSol.txt");
        assertEquals(y, x);
    }

    @Test
    public void testTurnMaze() {
        int x = PathFinder.solveMaze("data/turn.txt", "data/turnTest.txt");
        int y = countPeriodsInFile("data/turnSol.txt");
        assertEquals(y, x);
    }

    @Test
    public void testUnsolvableMaze() {
        int x = PathFinder.solveMaze("data/unsolvable.txt", "data/unsolvableTest.txt");
        assertEquals(-1, x);
    }

}
