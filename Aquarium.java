
/**
 * Aquarium represents a single problem in the game Aquarium.
 *
 * @author Anant Chhabda [21712878], Joshia Nambi [22976423]
 * @version 22/05/2020
 */
public class Aquarium 
{
    private int   size;         // the board is size x size
    private int[] columnTotals; // the totals at the top of the columns, left to right
    private int[] rowTotals;    // the totals at the left of the rows, top to bottom 

    // the board divided into aquariums, numbered from 1,2,3,...
    // spaces with the same number are part of the same aquarium
    private int[][] aquariums;
    // the board divided into spaces, each empty, water, or air
    private Space[][] spaces;

    /**
     * Constructor for objects of class Aquarium. 
     * Creates, initialises, and populates all of the fields.
     */
    public Aquarium(String filename)
    {
        // TODO 3
        FileIO file = new FileIO(filename);

        columnTotals = parseLine(file.getLines().get(0));
        rowTotals = parseLine(file.getLines().get(1));

        size = columnTotals.length;
        aquariums = new int[size][size];

        for (int i=3; i<file.getLines().size(); i++)
        {
            aquariums[i-3] = parseLine(file.getLines().get(i));
        }

        spaces = new Space[size][size];

        for (int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                spaces[i][j] = Space.EMPTY;
            }
        }
    }

    /**
     * Uses the provided example file on the LMS page.
     */
    public Aquarium()
    {
        this("Examples/a6_1.txt");
    }

    /**
     * Returns an array containing the ints in s, 
     * each of which is separated by one space. 
     * e.g. if s = "1 299 34 5", it will return {1,299,34,5} 
     */
    public static int[] parseLine(String s)
    {
        // TODO 2
        String[] list = s.split(" ");
        int[] list2 = new int[list.length];

        for (int i=0; i<list.length; i++)
        {
            list2[i] = (int) Integer.parseInt(list[i]);
        }

        return list2;
    }

    /**
     * Returns the size of the puzzle.
     */
    public int getSize()
    {
        // TODO 1a
        return size;
    }

    /**
     * Returns the column totals.
     */
    public int[] getColumnTotals()
    {
        // TODO 1b
        return columnTotals;
    }

    /**
     * Returns the row totals.
     */
    public int[] getRowTotals()
    {
        // TODO 1c
        return rowTotals;
    }

    /**
     * Returns the board in aquariums.
     */
    public int[][] getAquariums()
    {
        // TODO 1d
        return aquariums;
    }

    /**
     * Returns the board in spaces.
     */
    public Space[][] getSpaces()
    {
        // TODO 1e
        return spaces;
    }

    /**
     * Performs a left click on Square r,c if the indices are legal, o/w does nothing. 
     * A water space becomes empty; other spaces become water. 
     */
    public void leftClick(int r, int c)
    {
        // TODO 4

        if (r>=0 && c>=0 && r<size && c<size)
        {
            if (spaces[r][c] == Space.WATER) {
                spaces[r][c] = Space.EMPTY;
            }

            else {
                spaces[r][c] = Space.WATER;
            }
        }

    }

    /**
     * Performs a right click on Square r,c if the indices are legal, o/w does nothing. 
     * An air space becomes empty; other spaces become air. 
     */
    public void rightClick(int r, int c)
    {

        if (r>=0 && c>=0 && r<size && c<size)
        {
            if (spaces[r][c] == Space.AIR) {
                spaces[r][c] = Space.EMPTY;
            }

            else {
                spaces[r][c] = Space.AIR;
            }
        }
    }

    /**
     * Empties all of the spaces.
     */
    public void clear()
    {
        // TODO 6
        for (int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                spaces[i][j] = Space.EMPTY;
            }
        }
    }
}
