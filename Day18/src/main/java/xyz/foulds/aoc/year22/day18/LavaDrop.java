package xyz.foulds.aoc.year22.day18;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class LavaDrop
{
    private final int minX;
    private final int minY;
    private final int minZ;
    private final List<Cube> cubes;
    private final Thing[][][] lavaDrop;

    private Integer externalSurfaceCount;

    public LavaDrop(final Collection<Cube> cubes)
    {
        this.cubes = new ArrayList<>(cubes);
        this.minX = cubes.stream().mapToInt(Cube::getX).min().getAsInt();
        final int maxX = cubes.stream().mapToInt(Cube::getX).max().getAsInt();
        this.minY = cubes.stream().mapToInt(Cube::getY).min().getAsInt();
        final int maxY = cubes.stream().mapToInt(Cube::getY).max().getAsInt();
        this.minZ = cubes.stream().mapToInt(Cube::getZ).min().getAsInt();
        final int maxZ = cubes.stream().mapToInt(Cube::getZ).max().getAsInt();
        lavaDrop = new Thing[maxX - minX + 3][maxY - minY + 3][maxZ - minZ + 3];
        for (int x = 0; x < lavaDrop.length; x++)
        {
            for (int y = 0; y < lavaDrop[0].length; y++)
            {
                for (int z = 0; z < lavaDrop[0][0].length; z++)
                {
                    lavaDrop[x][y][z] = Thing.AIR;
                }
            }
        }

        cubes.forEach(c -> lavaDrop[getX(c)][getY(c)][getZ(c)] = Thing.LAVA);
    }

    /*
     *
     * Public API VVVVVV
     *
     */

    public int getTotalSurfaceArea()
    {
        int touchingFaces = 0;
        for (int i = 0; i < cubes.size(); i++)
        {
            for (int j = i+1; j < cubes.size(); j++)
            {
                if (cubes.get(i).isAdjacent(cubes.get(j)))
                {
                    touchingFaces += 1;
                }
            }
        }

        return 6*cubes.size() - 2*touchingFaces;
    }

    public int getExternalSurfaceArea()
    {
        if (externalSurfaceCount == null)
        {
            externalSurfaceCount = 0;
            final Stack<Cube> toFill = new Stack<>();
            toFill.push(new Cube(0,0,0));

            fillFrom(toFill);
        }
        return externalSurfaceCount;
    }

    /*
    *
    * Private implementation VVVVVV
    *
    */

    private void fillFrom(final Stack<Cube> stack)
    {
        final int initialSize = lavaDrop.length * lavaDrop[0].length * lavaDrop[0][0].length;
        final Set<Cube> visited = new HashSet<>(initialSize);
        final Set<Cube> airAddedToStack = new HashSet<>(initialSize);
        while (!stack.isEmpty())
        {
            final Cube current = stack.pop();
            final int x = current.getX();
            final int y = current.getY();
            final int z = current.getZ();

            if (lavaDrop[x][y][z] == Thing.AIR)
            {
                lavaDrop[x][y][z] = Thing.WATER;
                visited.add(current);

                for (final Cube next : current.getAdjacentCubes())
                {
                    if (!visited.contains(next)
                        && !airAddedToStack.contains(next)
                        && next.getX() >= 0 && next.getY() >= 0 && next.getZ() >= 0
                        && next.getX() < lavaDrop.length
                        && next.getY() < lavaDrop[0].length
                        && next.getZ() < lavaDrop[0][0].length)
                    {
                        if (lavaDrop[next.getX()][next.getY()][next.getZ()] == Thing.AIR) airAddedToStack.add(next);
                        stack.push(next);
                    }
                }
            }
            else if (lavaDrop[x][y][z] == Thing.LAVA)
            {
                externalSurfaceCount += 1;
            }
        }
    }

    private int getX(final Cube cube)
    {
        return cube.getX() - minX + 1;
    }

    private int getY(final Cube cube)
    {
        return cube.getY() - minY + 1;
    }

    private int getZ(final Cube cube)
    {
        return cube.getZ() - minZ + 1;
    }
}
