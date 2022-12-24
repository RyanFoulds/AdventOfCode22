package xyz.foulds.aoc.year22.day23;

public enum Direction
{
    NORTH(0),
    SOUTH(1),
    WEST(2),
    EAST(3);

    private static final Direction[] ARRAY = new Direction[]{NORTH, SOUTH, WEST, EAST};

    private final int index;

    Direction(final int index)
    {
        this.index = index;
    }

    public Direction getNext(final int toMove)
    {
        return ARRAY[(this.index + toMove) % 4];
    }

    public static Direction get(final int index)
    {
        return ARRAY[index % 4];
    }
}
