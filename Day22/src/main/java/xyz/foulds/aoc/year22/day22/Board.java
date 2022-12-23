package xyz.foulds.aoc.year22.day22;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Board
{
    /** '\u0000' is not a square, '.' is available, '#' is unavailable. */
    private final char[][] board;

    private Direction facing;
    private final int startingX;
    private int currentX;
    private int currentY = 0;

    public Board(final List<String> lines)
    {
        final int width = lines.stream().mapToInt(String::length).max().getAsInt();
        board = new char[width][lines.size()];
        for (int y = 0; y < lines.size(); y++)
        {
            final String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
            {
                final char val = line.charAt(x);
                board[x][y] = val == ' ' ? 0 : val;
            }
        }
        startingX = lines.get(0).indexOf(".");
        currentX = startingX;
        facing = Direction.RIGHT;
    }

    public void moveAlongPath(final String path)
    {
        final int[] steps =  Arrays.stream(path.split("[RL]"))
                                   .mapToInt(Integer::parseInt)
                                   .toArray();

        final String[] turns = path.split("\\d+");

        for (int i = 0; i < steps.length; i++)
        {
            turn(turns[i]);
            move(steps[i]);
        }
    }

    private void move(final int steps)
    {
        try
        {
            final Method move = Board.class.getDeclaredMethod("move" + facing);
            for (int i = 0; i < steps; i++)
            {
                move.invoke(this);
            }
        }
        catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e)
        {
            throw new IllegalArgumentException("Can't move in that direction", e);
        }
    }

    private void turn(final String turn)
    {
        if ("".equals(turn)) return;
        if ("R".equals(turn))
        {
            facing = facing.getClockwise();
        }
        else if ("L".equals(turn))
        {
            facing = facing.getAntiClockwise();
        }
    }

    // X+=1 wrapping around.
    private void moveRight()
    {
        final int toCheck = (currentX == board.length-1 || board[currentX+1][currentY] == 0)
                            ? findFirstFilledIndexOfRow(currentY) : currentX+1;
        if (board[toCheck][currentY] == '.')
        {
            currentX = toCheck;
        }
    }

    private int findFirstFilledIndexOfRow(final int y)
    {
        for (int x = 0; x < board.length; x++)
        {
            if (board[x][y] == '.' || board[x][y] == '#')
            {
                return x;
            }
        }
        throw new IllegalStateException();
    }
    private void moveLeft()
    {
        final int toCheck = (currentX == 0 || board[currentX-1][currentY] == 0)
                            ? findLastFilledIndexOfRow(currentY) : currentX-1;
        if (board[toCheck][currentY] == '.')
        {
            currentX = toCheck;
        }
    }
    private int findLastFilledIndexOfRow(final int y)
    {
        for (int x = board.length-1; x >= 0; x--)
        {
            if (board[x][y] == '.' || board[x][y] == '#')
            {
                return x;
            }
        }
        throw new IllegalStateException();
    }
    private void moveDown()
    {
        final int toCheck = (currentY == board[0].length-1 || board[currentX][currentY+1] == 0)
                            ? findFirstFilledIndexOfColumn(currentX) : currentY+1;
        if (board[currentX][toCheck] == '.')
        {
            currentY = toCheck;
        }
    }

    private int findFirstFilledIndexOfColumn(final int x)
    {
        for (int y = 0; x < board[0].length; y++)
        {
            if (board[x][y] == '.' || board[x][y] == '#')
            {
                return y;
            }
        }
        throw new IllegalStateException();
    }

    private void moveUp()
    {
        final int toCheck = (currentY == 0 || board[currentX][currentY-1] == 0)
                            ? findLastFilledIndexOfColumn(currentX) : currentY-1;
        if (board[currentX][toCheck] == '.')
        {
            currentY = toCheck;
        }
    }

    private int findLastFilledIndexOfColumn(final int x)
    {
        for (int y = board[0].length-1; y >= 0; y--)
        {
            if (board[x][y] == '.' || board[x][y] == '#')
            {
                return y;
            }
        }
        throw new IllegalStateException();
    }

    public void reset()
    {
        currentY = 0;
        currentX = startingX;
        facing = Direction.RIGHT;
    }

    public int getScore()
    {
        return 1000*(currentY+1) + 4*(currentX+1) + facing.getScore();
    }
}
