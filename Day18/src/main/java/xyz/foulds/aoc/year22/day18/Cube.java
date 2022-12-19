package xyz.foulds.aoc.year22.day18;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cube
{
    private final int x;
    private final int y;
    private final int z;

    public Cube(final String string)
    {
        final String[] inputs = string.split(",");
        this.x = Integer.parseInt(inputs[0]);
        this.y = Integer.parseInt(inputs[1]);
        this.z = Integer.parseInt(inputs[2]);
    }

    public Cube(final int x, final int y, final int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean isAdjacent(final Cube other)
    {
        final int diff = Math.abs(this.x - other.x) + Math.abs(this.y - other.y) + Math.abs(this.z - other.z);
        return diff == 1;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getZ()
    {
        return z;
    }

    public Collection<Cube> getAdjacentCubes()
    {
        return Stream.of(new Cube(x, y, z+1),
                         new Cube(x, y, z-1),
                         new Cube(x, y+1, z),
                         new Cube(x, y-1, z),
                         new Cube(x+1, y, z),
                         new Cube(x-1, y, z)).collect(Collectors.toList());
    }

    public Cube translate(final Cube translation)
    {
        return new Cube(this.x + translation.x, this.y + translation.y, this.z + translation.z);
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        final Cube cube = (Cube) o;
        return x == cube.x && y == cube.y && z == cube.z;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString()
    {
        return x + "," + y + "," + z;
    }
}
