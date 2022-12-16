package xyz.foulds.aoc.year22.day15;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Sensor
{
    private final int x;
    private final int y;
    private final Beacon closestBeacon;

    private final int manhattanDistance;

    public Sensor(final String string, final Beacon closestBeacon)
    {
        final String[] split = string.split(", y=");
        this.x = Integer.parseInt(split[0].substring(12));
        this.y = Integer.parseInt(split[1]);
        this.closestBeacon = closestBeacon;

        manhattanDistance = Math.abs(this.x - closestBeacon.getX())
                            + Math.abs(this.y - closestBeacon.getY());
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Beacon getClosestBeacon()
    {
        return closestBeacon;
    }

    public boolean contains(final Coordinate coordinate)
    {
        return (Math.abs(this.x - coordinate.getX()) + Math.abs(this.y - coordinate.getY())) < manhattanDistance;
    }


    public Range getExcludedFromRow(final int y)
    {
        final int size = manhattanDistance - Math.abs(this.y - y);
        return size < 1 ? null : new Range(this.x - size, this.x + size);
    }

    public Collection<Coordinate> getBoundaryIntersectionPoints(final Sensor other)
    {
        if (!this.overlapsWith(other))
        {
            return Collections.emptyList();
        }

        // |x-xs| + |y-ys| = r + 1 // becomes 4 eqs.
        // x + y = r +xs +ys +1 // x > xs, y > ys (a)
        // -x + y = r -xs +ys +1 // x < xs, y > ys (b)
        // x - y = r +xs -ys +1 // x > xs, y < ys (c)
        // -x - y = r -xs -ys +1 // x < xs, y < ys (d)
        final List<Coordinate> coordinates = new ArrayList<>(8);

        final int a1 = manhattanDistance + x + y + 1; //  x+y
        final int b1 = manhattanDistance - x + y + 1; //  y-x
        final int c1 = manhattanDistance + x - y + 1; //  x-y
        final int d1 = manhattanDistance - x - y + 1; // -x-y
        final int a2 = other.manhattanDistance + other.x + other.y + 1; //  x+y
        final int b2 = other.manhattanDistance - other.x + other.y + 1; //  y-x
        final int c2 = other.manhattanDistance + other.x - other.y + 1; //  x-y
        final int d2 = other.manhattanDistance - other.x - other.y + 1; // -x-y

        // 1a:2b,2c
        Coordinate candidate = new Coordinate((a1 - b2)/2, (a1 + b2)/2);
        if (candidate.getX() > x && candidate.getY() > y
            && candidate.getX() < other.x && candidate.getY() > other.y)
        {
            coordinates.add(candidate);
        }

        candidate = new Coordinate((a1 + c2)/2, (a1 - c2)/2);
        if (candidate.getX() > x && candidate.getY() > y
            && candidate.getX() > other.x && candidate.getY() < other.y)
        {
            coordinates.add(candidate);
        }

        // 1b:2a,2d
        candidate = new Coordinate((a2 - b1)/2, (b1 + a2)/2);
        if (candidate.getX() < x && candidate.getY() > y
            && candidate.getX() > other.x && candidate.getY() > other.y)
        {
            coordinates.add(candidate);
        }

        candidate = new Coordinate(-(b1 + d2)/2, (b1 - d2)/2);
        if (candidate.getX() < x && candidate.getY() > y
            && candidate.getX() < other.x && candidate.getY() < other.y)
        {
            coordinates.add(candidate);
        }

        // 1c:2a,2d
        candidate = new Coordinate((c1 + a2)/2, (a2 - c1)/2);
        if (candidate.getX() > x && candidate.getY() < y
            && candidate.getX() > other.x && candidate.getY() > other.y)
        {
            coordinates.add(candidate);
        }

        candidate = new Coordinate(-(c1 + d2)/2, (c1 - d2)/2);
        if (candidate.getX() > x && candidate.getY() < y
            && candidate.getX() < other.x && candidate.getY() < other.y)
        {
            coordinates.add(candidate);
        }

        // 1d:2b,2c
        candidate = new Coordinate(-(d1 + b2)/2, (b2 - d1)/2);
        if (candidate.getX() < x && candidate.getY() < y
            && candidate.getX() < other.x && candidate.getY() > other.y)
        {
            coordinates.add(candidate);
        }

        candidate = new Coordinate(-(d1 + c2)/2, (c2 - d1)/2);
        if (candidate.getX() < x && candidate.getY() < y
            && candidate.getX() > other.x && candidate.getY() < other.y)
        {
            coordinates.add(candidate);
        }

        return coordinates;
    }

    private boolean overlapsWith(final Sensor other)
    {
        final int xDiff = Math.abs(this.x - other.x);
        final int yDiff = Math.abs(this.y - other.y);
        return xDiff + yDiff < this.manhattanDistance + other.manhattanDistance;
    }

    public boolean intersectsRow(final int row)
    {
        return Math.abs(row - this.y) <= manhattanDistance;
    }
}
