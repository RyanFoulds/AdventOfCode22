package xyz.foulds.aoc.year22.day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Grid
{
    /** grid[x][y] */
    private final int[][] grid;

    private int visibleTreeCount;

    private List<Integer> flatScores;

    public Grid(final String[] rows)
    {
        final int length = rows.length;
        final int width = rows[0].length();

        grid = new int[width][length];

        for (int y = 0; y < length; y++)
        {
            for (int x = 0; x < width; x++)
            {
                grid[x][y] = rows[y].charAt(x) - 48;
            }
        }
    }

    public int getVisibleTreeCount()
    {
        if (visibleTreeCount == 0)
        {
            for (int x = 0; x < grid[0].length; x++) {
                for (int y = 0; y < grid.length; y++) {
                    if (isTreeVisible(x, y))
                    {
                        visibleTreeCount += 1;
                    }
                }
            }
        }
        return visibleTreeCount;
    }

    public List<Integer> getAllTreeScores()
    {
        if (flatScores == null)
        {
            final List<Integer> scores = new ArrayList<>(grid.length * grid[0].length);
            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[x].length; y++) {
                    scores.add(getTreeScenicScore(x, y));
                }
            }
            this.flatScores = Collections.unmodifiableList(scores);
        }
        return flatScores;
    }

    private boolean isTreeVisible(final int x, final int y)
    {
        if (x == 0 || y == 0 || x == grid.length - 1 || y == grid[x].length - 1)
        {
            // Trees around the edges.
            return true;
        }

        final int up = Arrays.stream(grid[x], 0, y)
                             .max().orElse(-1);
        final int down = Arrays.stream(grid[x], y + 1, grid[x].length)
                               .max().orElse(-1);
        final int left = Arrays.stream(grid, 0, x)
                                .mapToInt(row -> row[y])
                                .max().orElse(-1);
        final int right = Arrays.stream(grid, x+1, grid[0].length)
                                .mapToInt(row -> row[y])
                                .max().orElse(-1);

        final int tree = grid[x][y];

        return tree > up || tree > down || tree > left || tree > right;
    }

    private int getTreeScenicScore(final int x, final int y)
    {
        if (x == 0 || y == 0 || x == grid.length - 1 || y == grid[0].length - 1)
        {
            // Trees around the edges.
            return 0;
        }

        return getUpScore(x, y) * getDownScore(x, y) * getLeftScore(x, y) * getRightScore(x, y);
    }

    private int getUpScore(final int x, final int y)
    {
        if (y == 0)
        {
            return 0;
        }
        int retVal = 0;
        for (int i = y-1; i >= 0; i--)
        {
            retVal += 1;
            if (grid[x][i] >= grid[x][y])
            {
                break;
            }
        }
        return retVal;
    }

    private int getDownScore(final int x, final int y)
    {
        if (y == grid.length - 1)
        {
            return 0;
        }
        int retVal = 0;
        for (int i = y+1; i < grid[x].length; i++)
        {
            retVal += 1;
            if (grid[x][i] >= grid[x][y])
            {
                break;
            }
        }
        return retVal;
    }
    private int getLeftScore(final int x, final int y)
    {
        if (x == 0)
        {
            return 0;
        }
        int retVal = 0;
        for (int i = x-1; i >= 0; i--)
        {
            retVal += 1;
            if (grid[i][y] >= grid[x][y])
            {
                break;
            }
        }
        return retVal;
    }

    private int getRightScore(final int x, final int y)
    {
        if (x < 0)
        {
            return 0;
        }
        int retVal = 0;
        for (int i = x+1; i < grid.length; i++)
        {
            retVal += 1;
            if (grid[i][y] >= grid[x][y])
            {
                break;
            }
        }
        return retVal;
    }
}
