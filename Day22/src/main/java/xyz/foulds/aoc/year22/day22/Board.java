package xyz.foulds.aoc.year22.day22;

import java.util.Arrays;
import java.util.List;

/**
 * 3D movement algorithm is very tightly coupled to the shape of the input I happened to get. (This one:)
 * <pre>
 *    ##
 *    #
 *   ##
 *   #
 *   </pre>
 */
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

    public void moveAlongPath(final String path, final boolean in3D)
    {
        final int[] steps =  Arrays.stream(path.split("[RL]"))
                                   .mapToInt(Integer::parseInt)
                                   .toArray();

        final String[] turns = path.split("\\d+");

        for (int i = 0; i < steps.length; i++)
        {
            turn(turns[i]);
            move(steps[i], in3D);
        }
    }

    private void move(final int steps, final boolean in3D)
    {
        for (int i = 0; i < steps; i++)
        {
            switch (facing)
            {
                case RIGHT:
                    moveRight(in3D);
                    break;
                case DOWN:
                    moveDown(in3D);
                    break;
                case LEFT:
                    moveLeft(in3D);
                    break;
                case UP:
                    moveUp(in3D);
                    break;
                default: throw new IllegalArgumentException();
            }
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

    private void moveRight(final boolean in3D)
    {
        if (in3D)
        {
            moveRight3D();
        }
        else
        {
            moveRight2D();
        }
    }

    private void moveRight2D()
    {
        final int toCheck = (currentX == board.length-1 || board[currentX+1][currentY] == 0)
                            ? findFirstFilledIndexOfRow(currentY) : currentX+1;
        if (board[toCheck][currentY] == '.')
        {
            currentX = toCheck;
        }
    }

    private void moveRight3D()
    {
        int x;
        int y;
        Direction newDirection;

        if (currentX == 149 && currentY >= 0 && currentY < 50)
        {
            x = 99;
            y = 149 - currentY;
            newDirection = Direction.LEFT;
        }
        else if (currentX == 99 && currentY >= 50 && currentY < 100)
        {
            x = 50 + currentY;
            y = 49;
            newDirection = Direction.UP;
        }
        else if (currentX == 99 && currentY >= 100 && currentY < 150)
        {
            x = 149;
            y = 149 - currentY;
            newDirection = Direction.LEFT;
        }
        else if (currentX == 49 && currentY >= 150 && currentY < 200)
        {
            x = currentY - 100;
            y = 149;
            newDirection = Direction.UP;
        }
        else
        {
            x = currentX + 1;
            y = currentY;
            newDirection = Direction.RIGHT;
        }

        if (board[x][y] == '.')
        {
            currentX = x;
            currentY = y;
            facing = newDirection;
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

    private void moveLeft(final boolean in3D)
    {
        if (in3D)
        {
            moveLeft3D();
        }
        else
        {
            moveLeft2D();
        }
    }
    private void moveLeft2D()
    {
        final int toCheck = (currentX == 0 || board[currentX-1][currentY] == 0)
                            ? findLastFilledIndexOfRow(currentY) : currentX-1;
        if (board[toCheck][currentY] == '.')
        {
            currentX = toCheck;
        }
    }

    private void moveLeft3D()
    {
        int x;
        int y;
        final Direction newDirection;
        if (currentX == 50 && currentY >= 0 && currentY < 50)
        {
            x = 0;
            y = 149 - currentY;
            newDirection = Direction.RIGHT;
        }
        else if (currentX == 50 && currentY >= 50 && currentY < 100)
        {
            x = currentY - 50;
            y = 100;
            newDirection = Direction.DOWN;
        }
        else if (currentX == 0 && currentY >= 100 && currentY < 150)
        {
            x = 50;
            y = 149 - currentY;
            newDirection = Direction.RIGHT;
        }
        else if (currentX == 0 && currentY >= 150 && currentY < 200)
        {
            x = currentY - 100;
            y = 0;
            newDirection = Direction.DOWN;
        }
        else
        {
            x = currentX-1;
            y = currentY;
            newDirection = Direction.LEFT;
        }

        if (board[x][y] == '.')
        {
            currentX = x;
            currentY = y;
            facing = newDirection;
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

    private void moveDown(final boolean in3D)
    {
        if (in3D)
        {
            moveDown3D();
        }
        else
        {
            moveDown2D();
        }
    }
    private void moveDown2D()
    {
        final int toCheck = (currentY == board[0].length-1 || board[currentX][currentY+1] == 0)
                            ? findFirstFilledIndexOfColumn(currentX) : currentY+1;
        if (board[currentX][toCheck] == '.')
        {
            currentY = toCheck;
        }
    }

    private void moveDown3D()
    {
        int x;
        int y;
        Direction newDirection;

        if (currentY == 49 && currentX >= 100 && currentX < 150)
        {
            x = 99;
            y = currentX - 50;
            newDirection = Direction.LEFT;
        }
        else if (currentY == 149 && currentX >= 50 && currentX < 100)
        {
            x = 49;
            y = currentX + 100;
            newDirection = Direction.LEFT;
        }
        else if (currentY == 199 && currentX >= 0 && currentX < 50)
        {
            x = currentX + 100;
            y = 0;
            newDirection = Direction.DOWN;
        }
        else
        {
            x = currentX;
            y = currentY + 1;
            newDirection = Direction.DOWN;
        }

        if (board[x][y] == '.')
        {
            currentX = x;
            currentY = y;
            facing = newDirection;
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

    private void moveUp(final boolean in3D)
    {
        if (in3D)
        {
            moveUp3D();
        }
        else
        {
            moveUp2D();
        }
    }
    private void moveUp2D()
    {
        final int toCheck = (currentY == 0 || board[currentX][currentY-1] == 0)
                            ? findLastFilledIndexOfColumn(currentX) : currentY-1;
        if (board[currentX][toCheck] == '.')
        {
            currentY = toCheck;
        }
    }

    private void moveUp3D()
    {
        int x;
        int y;
        Direction newDirection;

        if (currentY == 100 && currentX >= 0 && currentX < 50)
        {
            x = 50;
            y = 50 + currentX;
            newDirection = Direction.RIGHT;
        }
        else if (currentY == 0 && currentX >= 50 && currentX < 100)
        {
            x = 0;
            y = currentX + 100;
            newDirection = Direction.RIGHT;
        }
        else if (currentY == 0 && currentX >= 100 && currentX < 150)
        {
            x = currentX - 100;
            y = 199;
            newDirection = Direction.UP;
        }
        else
        {
            x = currentX;
            y = currentY - 1;
            newDirection = Direction.UP;
        }

        if (board[x][y] == '.')
        {
            currentX = x;
            currentY = y;
            facing = newDirection;
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
