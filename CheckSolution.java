
/**
 * CheckSolution is a utility class which can check if
 * a board position in an Aquarium puzzle is a solution.
 *
 * @author Anant Chhabda(21712878), Joshia Nambi(22976423)
 * @version 2
 */
import java.util.Arrays; 
import java.awt.*;

public class CheckSolution
{
    /**
     * Non-constructor for objects of class CheckSolution
     */
    private CheckSolution(){}

    /**
     * Returns the number of water squares in each row of Aquarium puzzle p, top down.
     */
    public static int[] rowCounts(Aquarium p)
    {
        // TODO 16
        int[] rowCount = new int[p.getSize()];
        for (int i=0; i<p.getSize(); i++)
        {
            for (int j=0; j<p.getSize(); j++)
            {
                if (p.getSpaces()[i][j] == Space.WATER) 
                {
                    rowCount[i]++;
                }
            }
        }
        return rowCount;
    }

    /**
     * Returns the number of water squares in each column of Aquarium puzzle p, left to right.
     */
    public static int[] columnCounts(Aquarium p)
    {
        // TODO 17
        int[] colCount = new int[p.getSize()];
        for (int j=0; j<p.getSize(); j++)
        {
            for (int i=0; i<p.getSize(); i++)
            {
                if (p.getSpaces()[i][j] == Space.WATER) 
                {
                    colCount[j]++;
                }
            }
        }
        return colCount;
    }

    /**
     * Returns a 2-int array denoting the collective status of the spaces 
     * in the aquarium numbered t on Row r of Aquarium puzzle p. 
     * The second element will be the column index c of any space r,c which is in t, or -1 if there is none. 
     * The first element will be: 
     * 0 if there are no spaces in t on Row r; 
     * 1 if they're all water; 
     * 2 if they're all not-water; or 
     * 3 if they're a mixture of water and not-water. 
     */
    public static int[] rowStatus(Aquarium p, int t, int r)
    {
        // TODO 18
        int[] status = {0,-1};
        int spaceCount = 0;
        int waterCount = 0;
        for (int j=0; j<p.getSize(); j++)
        {
            if (p.getAquariums()[r][j] == t) 
            {
                spaceCount++;
                status[1] = j; 
                if (p.getSpaces()[r][j] == Space.WATER) 
                {
                    waterCount++;
                }
            }
        }
        if (spaceCount == 0) 
        {
            return status;
        }
        else 
        if (spaceCount == waterCount) 
        {
            status[0] = 1;
            return status;
        }
        else 
        if (waterCount == 0)
        {
            status[0] = 2;
            return status;
        }
        else
        {
            status[0] = 3;
            return status;
        }
    }

    /**
     * Returns a statement on whether the aquarium numbered t in Aquarium puzzle p is OK. 
     * Every row must be either all water or all not-water, 
     * and all water must be below all not-water. 
     * Returns "" if the aquarium is ok; otherwise 
     * returns the indices of any square in the aquarium, in the format "r,c". 
     */
    public static String isAquariumOK(Aquarium p, int t)
    {
        // TODO 19
        int[][] finalstatus = new int[p.getSize()][2];
        for (int i=0; i<p.getSize(); i++)
        {
            finalstatus[i][0] = rowStatus(p,t,i)[0];
            finalstatus[i][1] = rowStatus(p,t,i)[1];
        }
        for (int j=0; j<finalstatus.length; j++)
        {
            if (finalstatus[j][0] == 3) return "" + j + "," + finalstatus[j][1];
            if (j+1 != finalstatus.length)
            {
                if ((finalstatus[j][0] ==1) && (finalstatus[j+1][0] == 2))
                {return "" + j + "," + finalstatus[j][1];}
            }
        }
        return "";
    }

    /**
     * Returns a statement on whether we have a correct solution to Aquarium puzzle p. 
     * Every row and column must have the correct number of water squares, 
     * and all aquariums must be OK. 
     * Returns three ticks if the solution is correct; 
     * otherwise see the LMS page for the expected results. 
     */
    public static String isSolution(Aquarium p)
    {
        // TODO 20
        for (int i=0; i<p.getSize(); i++)
        {
            if (rowCounts(p)[i] != p.getRowTotals()[i]) 
            {
                return("Row "+i+" is wrong");
            }
            if (columnCounts(p)[i] != p.getColumnTotals()[i]) 
            {
                return ("Column " + i + " is wrong");
            }
        }
        for (int z = p.getAquariums()[p.getSize()-1][p.getSize()-1]; z>=0; z--)
        {
            if (isAquariumOK(p,z) != "") 
            {
                return "The aquarium at " + isAquariumOK(p,z) + " is wrong";
            }
        }
        return "\u2713\u2713\u2713";
    }
}
