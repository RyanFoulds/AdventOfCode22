package xyz.foulds.aoc.year22.day14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    public int getY()
    {
        return y;
    }

    public List<Coordinate> getNextCoords()
    {
        return Arrays.asList(new Coordinate(x, y+1),
                             new Coordinate(x-1, y+1),
                             new Coordinate(x+1, y+1));
    }

    public List<Coordinate> getAllInLineTo(final Coordinate other)
    {
        final List<Coordinate> retVal = new ArrayList<>();
        if (this.x == other.x && this.y != other.y)
        {
            final boolean increase = this.y < other.y;
            final int range = Math.abs(y- other.y);
            for (int i = 1; i < range; i++)
            {
                retVal.add(new Coordinate(x, y + (increase ? i : -i)));
            }
            return retVal;
        }
        if (this.x != other.x && this.y == other.y)
        {
            final boolean increase = this.x < other.x;
            final int range = Math.abs(x - other.x);
            for (int i = 1; i < range; i++)
            {
                retVal.add(new Coordinate(x + (increase ? i : -i), y));
            }
            return retVal;
        }
        if (this.equals(other))
        {
            return Collections.emptyList();
        }

        // Can't handle arbitrary diagonals.
        throw new IllegalArgumentException();
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (obj == this) return true;
        if (obj.getClass() != Coordinate.class) return false;
        final Coordinate other = (Coordinate) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
}
