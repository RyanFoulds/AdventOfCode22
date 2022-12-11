package xyz.foulds.aoc.year22.day9;

import java.util.Objects;

public class Coordinate
{
    private final int x;
    private final int y;

    public Coordinate(final int x, final int y)
    {
        this.x = x;
        this.y = y;
    }

    public Coordinate move(final Direction direction)
    {
        int newX = x;
        int newY = y;
        switch (direction)
        {
            case UP:
                newY += 1;
                break;
            case DOWN:
                newY -= 1;
                break;
            case LEFT:
                newX -= 1;
                break;
            case RIGHT:
                newX += 1;
                break;
            default:
                throw new IllegalArgumentException();
        }

        return new Coordinate(newX, newY);
    }

    public Coordinate follow(final Coordinate head)
    {
        final Coordinate tailToHeadVector = new Coordinate(head.x - this.x, head.y - this.y);
        if(tailToHeadVector.getMagnitude() < 2)
        {
            return this;
        }

        final int newX = this.x + Integer.signum(tailToHeadVector.getX());
        final int newY = this.y + Integer.signum(tailToHeadVector.getY());

        return new Coordinate(newX, newY);
    }

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    private double getMagnitude()
    {
        return Math.sqrt(x*x + y*y);
    }
    @Override
    public boolean equals(final Object other)
    {
        if (!other.getClass().equals(Coordinate.class))
        {
            return false;
        }
        final Coordinate otherCoord = (Coordinate) other;
        return other == this || (this.x == otherCoord.x && this.y == otherCoord.y);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
    @Override
    public String toString()
    {
        return x + "," + y;
    }
}
