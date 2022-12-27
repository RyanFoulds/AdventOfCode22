package xyz.foulds.aoc.year22.day24;

public class Blizzard
{
    private Coordinate coordinate;
    private final char direction;

    public Blizzard(final Coordinate coordinate, final char direction)
    {
        this.coordinate = coordinate;
        this.direction = direction;
    }

    public Coordinate getCoordinate()
    {
        return coordinate;
    }

    public char getDirection()
    {
        return direction;
    }

    public void setCoordinate(final Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }
}
