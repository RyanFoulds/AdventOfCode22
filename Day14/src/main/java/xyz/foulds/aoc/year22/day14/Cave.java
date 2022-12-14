package xyz.foulds.aoc.year22.day14;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class Cave
{
    private static final Coordinate SAND_SOURCE = new Coordinate(500, 0);
    private final Set<Coordinate> occupiedCoords = new HashSet<>();
    private final boolean floor;
    private final int maxDepth;
    private int sandCount = 0;

    /** Memoise the path taken by the sand, so we can start one
     * grain of sand at the penultimate position of the last. */
    private final Stack<Coordinate> lastSandPath = new Stack<>();

    public Cave(final Collection<Wall> walls, final boolean hasFloor)
    {
        this.floor = hasFloor;
        occupiedCoords.addAll(walls.stream()
                                   .map(Wall::getCoordinates)
                                   .flatMap(Collection::stream)
                                   .collect(Collectors.toSet()));

        maxDepth = occupiedCoords.stream()
                                 .mapToInt(Coordinate::getY)
                                 .max()
                                 .orElse(0);
    }

    public void pourSand()
    {
        while(addSand());
    }

    /**
     * Returns true if sand can be added to the cave and will come to rest.
     */
    private boolean addSand()
    {
        if (occupiedCoords.contains(SAND_SOURCE)) return false;

        Coordinate sand = lastSandPath.isEmpty() ? SAND_SOURCE : lastSandPath.pop();

        movementLoop: while(true)
        {
            if (!floor && sand.getY() > maxDepth) return false;
            for (final Coordinate target : sand.getNextCoords())
            {
                if (!(occupiedCoords.contains(target) || target.getY() == maxDepth+2))
                {
                    lastSandPath.push(sand);
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

    public int getSandCount()
    {
        return sandCount;
    }
}
