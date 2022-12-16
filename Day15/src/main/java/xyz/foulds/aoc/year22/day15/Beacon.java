package xyz.foulds.aoc.year22.day15;

import java.util.Objects;

public class Beacon
{
    private final int x;
    private final int y;

    public Beacon(final String string)
    {
        final String[] split = string.split(", y=");
        this.x = Integer.parseInt(split[0].substring(24));
        this.y = Integer.parseInt(split[1]);
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj) return true;
        if (!obj.getClass().equals(Beacon.class)) return false;
        final Beacon other = (Beacon) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
}
