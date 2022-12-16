package xyz.foulds.aoc.year22.day15;

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

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public long getFrequency()
    {
        return 4000000L*x + y;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj) return true;
        if (!Coordinate.class.equals(obj.getClass())) return false;
        final Coordinate other = (Coordinate) obj;
        return this.x == other.x && this.y == other.y;
    }
}
