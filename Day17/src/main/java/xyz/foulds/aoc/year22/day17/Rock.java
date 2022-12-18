package xyz.foulds.aoc.year22.day17;

import java.util.HashSet;
import java.util.Set;

public class Rock
{
    private final Set<Coordinate> coordinates = new HashSet<>();
    private final char type; 
    public Rock(final char type, final int startingHeight)
    {
        this.type = type;
        switch (type)
        {
            case 'a':
                createDash(startingHeight);
                break;
            case 'b':
                createPlus(startingHeight);
                break;
            case 'c':
                createL(startingHeight);
                break;
            case 'd':
                createPipe(startingHeight);
                break;
            case 'e':
                createSquare(startingHeight);
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    private void createDash(final int height)
    {
        for (int i = 0; i < 4; i++)
        {
            coordinates.add(new Coordinate(i+2, height));
        }
    }

    private void createPlus(final int height)
    {
        coordinates.add(new Coordinate(2, height+1));
        coordinates.add(new Coordinate(3, height));
        coordinates.add(new Coordinate(3, height+1));
        coordinates.add(new Coordinate(3, height+2));
        coordinates.add(new Coordinate(4, height+1));
    }

    private void createL(final int height)
    {
        coordinates.add(new Coordinate(2, height));
        coordinates.add(new Coordinate(3, height));
        coordinates.add(new Coordinate(4, height));
        coordinates.add(new Coordinate(4, height+1));
        coordinates.add(new Coordinate(4, height+2));
    }

    private void createPipe(final int height)
    {
        for (int i = 0; i<4; i++)
        {
            coordinates.add(new Coordinate(2, height+i));
        }
    }

    private void createSquare(final int height)
    {
        coordinates.add(new Coordinate(2, height));
        coordinates.add(new Coordinate(3, height));
        coordinates.add(new Coordinate(2, height+1));
        coordinates.add(new Coordinate(3, height+1));
    }

    public void move(final char direction, final boolean[][] board)
    {
        if ('<' == direction)
        {
            moveLeft(board);
            return;
        }
        if ('>' == direction)
        {
            moveRight(board);
            return;
        }
        throw  new UnsupportedOperationException();
    }

    private void moveLeft(final boolean[][] board)
    {
        if (coordinates.stream().anyMatch(c -> c.getX() == 0)
            || coordinates.stream().anyMatch(c -> board[c.getX() - 1][c.getY()]))
        {
            return;
        }
        coordinates.forEach(c -> c.setX(c.getX() - 1));
    }

    private void moveRight(final boolean[][] board)
    {
        if (coordinates.stream().anyMatch(c -> c.getX() == 6)
            || coordinates.stream().anyMatch(c -> board[c.getX() + 1][c.getY()]))
        {
            return;
        }
        coordinates.forEach(c -> c.setX(c.getX() + 1));
    }

    public boolean fall(final boolean[][] board)
    {
        if (coordinates.stream().anyMatch(c -> c.getY() == 0)
            || coordinates.stream().anyMatch(c -> board[c.getX()][c.getY()-1]))
        {
            return false;
        }
        coordinates.forEach(coordinate -> coordinate.setY(coordinate.getY() - 1));
        return true;
    }

    public Set<Coordinate> getCoordinates()
    {
        return coordinates;
    }

    public boolean isDash()
    {
        return 'a' == this.type;
    }
}
