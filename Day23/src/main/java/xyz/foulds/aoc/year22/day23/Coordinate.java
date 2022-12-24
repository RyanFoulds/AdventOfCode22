package xyz.foulds.aoc.year22.day23;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private Set<Coordinate> getNorthPositions()
    {
        final Coordinate ne = new Coordinate(x+1, y-1);
        final Coordinate n = new Coordinate(x, y-1);
        final Coordinate nw = new Coordinate(x-1, y-1);
        return Stream.of(nw, n, ne).collect(Collectors.toSet());
    }

    private Set<Coordinate> getSouthPositions()
    {
        final Coordinate se = new Coordinate(x+1, y+1);
        final Coordinate s = new Coordinate(x, y+1);
        final Coordinate sw = new Coordinate(x-1, y+1);
        return Stream.of(sw, s, se).collect(Collectors.toSet());
    }

    private Set<Coordinate> getWestPositions()
    {
        final Coordinate sw = new Coordinate(x-1, y+1);
        final Coordinate w = new Coordinate(x-1, y);
        final Coordinate nw = new Coordinate(x-1, y-1);
        return Stream.of(sw, w, nw).collect(Collectors.toSet());
    }

    private Set<Coordinate> getEastPositions()
    {
        final Coordinate sw = new Coordinate(x+1, y+1);
        final Coordinate w = new Coordinate(x+1, y);
        final Coordinate nw = new Coordinate(x+1, y-1);
        return Stream.of(sw, w, nw).collect(Collectors.toSet());
    }

    public Set<Coordinate> getAllAdjacentPositions()
    {
        return Stream.of(getNorthPositions(), getEastPositions(),
                         getSouthPositions(), getWestPositions())
                     .flatMap(Collection::stream)
                     .collect(Collectors.toSet());
    }

    public Coordinate getAdjacent(final Direction direction)
    {
        switch (direction)
        {
            case NORTH: return new Coordinate(x, y-1);
            case SOUTH: return new Coordinate(x, y+1);
            case WEST: return new Coordinate(x-1, y);
            case EAST: return new Coordinate(x+1, y);
            default: throw new IllegalArgumentException();
        }
    }

    public Set<Coordinate> getAdjacentPositions(final Direction direction)
    {
        switch (direction)
        {
            case NORTH: return getNorthPositions();
            case SOUTH: return getSouthPositions();
            case WEST: return getWestPositions();
            case EAST: return getEastPositions();
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
}
