package xyz.foulds.aoc.year22.day9;

public enum Direction
{
    UP,
    DOWN,
    RIGHT,
    LEFT;

    public static Direction fromChar(final char character)
    {
        switch (character)
        {
            case 'U':
                return UP;
            case 'D':
                return DOWN;
            case 'R':
                return RIGHT;
            case 'L':
                return LEFT;
            default:
                throw new IllegalArgumentException();
        }
    }
}
