
/**
 * AquariumViewer represents an interface for playing a game of Aquarium.
 *
 * @author Anant Chhabda[21712878], Joshia Nambi(22976423)
 * @version 2020
 */
import java.awt.*;
import java.awt.event.*; 
import javax.swing.SwingUtilities;

public class AquariumViewer implements MouseListener
{
    private final int BOXSIZE = 60;          // the size of each square
    private final int OFFSET  = BOXSIZE * 2; // the gap around the board
    private final int THICKNESS = 3; //thickness of the lines 

    private       int WINDOWSIZE;            // set this in the constructor 

    private Aquarium puzzle; // the internal representation of the puzzle
    private int        size; // the puzzle is size x size
    private SimpleCanvas sc; // the display window
    

   

    private final static Color WATER = Color.cyan;
    private final static Color AIR = Color.pink;
    private final static Color BACK = new Color(255,255,100);
    private final static Color BOUNDARY = Color.black;
    private final static Color AQR_BOUNDARY = Color.red;
    private final static Color SOLVED = Color.black;

    /**
     * Main constructor for objects of class AquariumViewer.
     * Sets all fields, and displays the initial puzzle.
     */
    public AquariumViewer(Aquarium puzzle)
    {
        // TODO 8
        this.puzzle = puzzle;
        size = puzzle.getSize();
        WINDOWSIZE = BOXSIZE*size + OFFSET*2;
        sc = new SimpleCanvas("Aquarium Puzzle", WINDOWSIZE, WINDOWSIZE, BACK);
        sc.addMouseListener(this);
        sc.setFont(new Font("phosphate", Font.BOLD, BOXSIZE/10 * 6));
        displayPuzzle();
    }

    /**
     * Selects from among the provided files in folder Examples. 
     * xyz selects axy_z.txt. 
     */
    public AquariumViewer(int n)
    {
        this(new Aquarium("Examples/a" + n / 10 + "_" + n % 10 + ".txt"));
    }

    /**
     * Uses the provided example file on the LMS page.
     */
    public AquariumViewer()
    {
        this(61);
    }

    /**
     * Returns the current state of the puzzle.
     */
    public Aquarium getPuzzle()
    {
        // TODO 7a44/
        return puzzle;
    }

    /**
     * Returns the size of the puzzle.
     */
    public int getSize()
    {
        // TODO 7b
        return size;
    }

    /**
     * Returns the current state of the canvas.
     */
    public SimpleCanvas getCanvas()
    {
        // TODO 7c
        return sc;
    }

    /**
     * Displays the initial puzzle; see the LMS page for the format.
     */
    private void displayPuzzle()
    {
        // TODO 13
        displayGrid();
        displayNumbers();
        displayAquariums();
        displayButtons();
    }

    /**
     * Displays the grid in the middle of the window.
     */
    public void displayGrid()
    {
        // TODO 9
        for (int i=2; i<=size+2; i++)
        {
            if (i == 2 || i== size+2)
            {
                sc.drawRectangle(BOXSIZE*i-THICKNESS,OFFSET-THICKNESS,
                    BOXSIZE*i+THICKNESS,WINDOWSIZE-OFFSET+THICKNESS,AQR_BOUNDARY);
                sc.drawRectangle(OFFSET-THICKNESS,BOXSIZE*i-THICKNESS,
                    WINDOWSIZE-OFFSET-THICKNESS,BOXSIZE*i+THICKNESS,AQR_BOUNDARY);
            }
            else

            {sc.drawRectangle(BOXSIZE*i-1,OFFSET+THICKNESS,BOXSIZE*i+1,
                    WINDOWSIZE-OFFSET,BOUNDARY);
                sc.drawRectangle(OFFSET+THICKNESS,BOXSIZE*i-1,WINDOWSIZE-OFFSET,
                    BOXSIZE*i+1,BOUNDARY);
            }
        }
    }

    /**
     * Displays the numbers around the grid.
     */
    public void displayNumbers()
    {
        // TODO 10

        for (int i=0; i<puzzle.getRowTotals().length; i++)
        {
            if (puzzle.getRowTotals()[i]>9) 
            {sc.drawString(puzzle.getRowTotals()[i],OFFSET-57,
                    (OFFSET + BOXSIZE*i) + (OFFSET/3), BOUNDARY);}

            if (puzzle.getRowTotals()[i]<=9) 
            {sc.drawString(puzzle.getRowTotals()[i],OFFSET-45,
                    (OFFSET + BOXSIZE*i) + (OFFSET/3), BOUNDARY);}

            if (puzzle.getColumnTotals()[i]>9)
            {sc.drawString(puzzle.getColumnTotals()[i],
                    (OFFSET + (BOXSIZE*i) + (OFFSET/5)-6),OFFSET-25, BOUNDARY);}

            if (puzzle.getColumnTotals()[i]<=9)
            {sc.drawString(puzzle.getColumnTotals()[i],
                    (OFFSET + (BOXSIZE*i) + (OFFSET/5)),OFFSET-25, BOUNDARY);}

        }

    }

    /**
     * Displays the aquariums.
     */
    public void displayAquariums()
    {
        // TODO 11
        int z;
        for (int i = 0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                z = puzzle.getAquariums()[i][j];

                if ((j+1) < size && !(puzzle.getAquariums()[i][j+1] == z) ) 
                {sc.drawRectangle(OFFSET + BOXSIZE*(j+1)-THICKNESS, 
                        OFFSET + BOXSIZE*(i)-THICKNESS,OFFSET + BOXSIZE*(j+1)+THICKNESS,
                        OFFSET + BOXSIZE*(i+1)+THICKNESS, AQR_BOUNDARY);}
                if  ((i+1) < size && !(puzzle.getAquariums()[i+1][j] == z))
                {sc.drawRectangle(OFFSET + BOXSIZE*(j)-THICKNESS, 
                        OFFSET + BOXSIZE*(i+1)-THICKNESS,OFFSET + BOXSIZE*(j+1)+THICKNESS,
                        OFFSET + BOXSIZE*(i+1)+THICKNESS,AQR_BOUNDARY);}
            }
        }
    }

    /**
     * Displays the buttons below the grid.
     */
    public void displayButtons()
    {
        // TODO 12
        sc.setFont(new Font("Phosphate",Font.BOLD,(BOXSIZE/8) *4));
        sc.drawString("SOLVED?", OFFSET, WINDOWSIZE-BOXSIZE,BOUNDARY);
        sc.drawString("CLEAR",WINDOWSIZE-(OFFSET/3)*5,
            WINDOWSIZE-BOXSIZE,BOUNDARY);
    }

    /**
     * Updates the display of Square r,c. 
     * Sets the display of this square to whatever is in the squares array. 
     */
    public void updateSquare(int r, int c)
    {
        // TODO 14
        if (puzzle.getSpaces()[r][c] == Space.WATER)
        {sc.drawRectangle(OFFSET + BOXSIZE*c, OFFSET+ BOXSIZE*r,
                OFFSET +  BOXSIZE*(c+1), OFFSET + BOXSIZE*(r+1),WATER);}

        else 
        if(puzzle.getSpaces()[r][c] == Space.AIR)
        {
            sc.drawRectangle(OFFSET + BOXSIZE*c, OFFSET+ BOXSIZE*r,
                OFFSET + BOXSIZE*(c+1), OFFSET + BOXSIZE*(r+1),BACK);
            sc.drawCircle(5*(BOXSIZE/2) + BOXSIZE*c, 5*(BOXSIZE/2) + BOXSIZE*r , BOXSIZE/5,Color.black);
            sc.drawCircle(5*(BOXSIZE/2) +BOXSIZE*c , 5*(BOXSIZE/2) + BOXSIZE*r ,BOXSIZE/10,BACK);
        }
        else
        {
            sc.drawRectangle(OFFSET + BOXSIZE*c, OFFSET+ BOXSIZE*r,
                OFFSET + BOXSIZE*(c+1), OFFSET + BOXSIZE*(r+1),BACK);
        }
        
        displayGrid();
        displayAquariums();
    }

    /**
     * Responds to a mouse click. 
     * If it's on the board, make the appropriate move and update the screen display. 
     * If it's on SOLVED?,   check the solution and display the result. 
     * If it's on CLEAR,     clear the puzzle and update the screen display. 
     */
    public void mousePressed(MouseEvent e) 
    {
        // TODO 15
        if (e.getX()>=OFFSET && e.getX()<=WINDOWSIZE-OFFSET &&
        e.getY()>=OFFSET && e.getY()<=WINDOWSIZE-OFFSET)
        {
            if (SwingUtilities.isLeftMouseButton(e))
            {
                sc.drawRectangle(OFFSET, WINDOWSIZE, WINDOWSIZE, WINDOWSIZE-BOXSIZE,BACK);
                puzzle.leftClick((e.getY()-OFFSET )/BOXSIZE,(e.getX()-OFFSET )/ BOXSIZE);
                updateSquare((e.getY()-OFFSET)/BOXSIZE,(e.getX()-OFFSET)/ BOXSIZE);
            }

            else 
            if (SwingUtilities.isRightMouseButton(e))
            {
                puzzle.rightClick((e.getY() -OFFSET)/BOXSIZE, (e.getX()-OFFSET)/ BOXSIZE);
                updateSquare((e.getY()-OFFSET)/BOXSIZE, (e.getX()-OFFSET) / BOXSIZE);
            }
        }

        if (e.getX()>=OFFSET && e.getX()<= OFFSET + BOXSIZE*2 && 
        e.getY()>=WINDOWSIZE-BOXSIZE- (BOXSIZE/8)*4 && e.getY() <= WINDOWSIZE-BOXSIZE)
        {
            if (CheckSolution.isSolution(puzzle) == "\u2713\u2713\u2713") 
            {
                sc.drawRectangle(OFFSET, WINDOWSIZE, WINDOWSIZE, 
                    WINDOWSIZE-BOXSIZE, BACK);
                sc.drawString(CheckSolution.isSolution(puzzle), OFFSET,
                    WINDOWSIZE - BOXSIZE/2, SOLVED);
            }
            else 
            {
                sc.drawRectangle(OFFSET,WINDOWSIZE, WINDOWSIZE, WINDOWSIZE-BOXSIZE,BACK);
                sc.drawString(CheckSolution.isSolution(puzzle),OFFSET,
                    WINDOWSIZE - BOXSIZE/2, AQR_BOUNDARY);
            }

        }

        if (e.getX()>=WINDOWSIZE-(OFFSET/3)*5 && e.getX()<=WINDOWSIZE-OFFSET && 
        e.getY()>=WINDOWSIZE-BOXSIZE- (BOXSIZE/8)*4 && e.getY()<=WINDOWSIZE-BOXSIZE)
        {
            puzzle.clear();
            for (int i=0; i<size; i++)
            {
                for (int j=0; j<size; j++)
                {
                    updateSquare(i,j);
                }
            }
            sc.drawRectangle(OFFSET, WINDOWSIZE, WINDOWSIZE, WINDOWSIZE-BOXSIZE, BACK);      
        }
    }

    public void mouseClicked(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}
