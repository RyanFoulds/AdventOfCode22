package xyz.foulds.aoc.year22.day14;

import java.util.HashSet;
import java.util.Set;

public class Cave
{
    private static final Coordinate SAND_SOURCE = new Coordinate(500, 0);
    private final Set<Coordinate> occupiedCoords = new HashSet<>();
    private boolean wallsLockedIn = false;
    private int maxDepth;
    private int sandCount = 0;

    /**
     * Returns true if sand can be added to the cave and will come to rest.
     */
    public boolean addSand(final boolean withFloor)
    {
        if (!wallsLockedIn)
        {
            throw new UnsupportedOperationException("Can't add sand until the walls have been locked in.");
        }

        if (occupiedCoords.contains(SAND_SOURCE)) return false;

        Coordinate sand = SAND_SOURCE;
        movementLoop:
        while(true)
        {
            if (!withFloor && sand.getY() > maxDepth) return false;
            for (final Coordinate target : sand.getNextCoords())
            {
                if (!(occupiedCoords.contains(target) || target.getY() == maxDepth+2))
                {
                    sand = target;
                    continue movementLoop;
                }
            }
            sandCount += 1;
            occupiedCoords.add(sand);
            break;
        }
        return true;
    }

    public void addWall(final Wall wall)
    {
        if (wallsLockedIn)
        {
            throw new UnsupportedOperationException("Can't add walls after they've been locked in.");
        }
        occupiedCoords.addAll(wall.getCoordinates());
    }

    public void lockInWalls()
    {
        if (wallsLockedIn)
        {
            return;
        }
        wallsLockedIn = true;
        maxDepth = occupiedCoords.stream()
                                 .mapToInt(Coordinate::getY)
                                 .max()
                                 .orElse(0);
    }

    public int getSandCount()
    {
        return sandCount;
    }
}
