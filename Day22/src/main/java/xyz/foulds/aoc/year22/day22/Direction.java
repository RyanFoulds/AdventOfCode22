package xyz.foulds.aoc.year22.day22;

public enum Direction
{
    UP(3),
    RIGHT(0),
    DOWN(1),
    LEFT(2);

    private final int score;

    Direction(final int score)
    {
        this.score = score;
    }

    public Direction getClockwise()
    {
        switch (this)
        {
            case UP: return RIGHT;
            case RIGHT: return DOWN;
            case DOWN: return LEFT;
            case LEFT: return UP;
            default: throw new IllegalArgumentException();
        }
    }

    public Direction getAntiClockwise()
    {
        switch (this)
        {
            case UP: return LEFT;
            case RIGHT: return UP;
            case DOWN: return RIGHT;
            case LEFT: return DOWN;
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString()
    {
        switch (this)
        {
            case UP: return "Up";
            case RIGHT: return "Right";
            case DOWN: return "Down";
            case LEFT: return "Left";
            default: throw new IllegalArgumentException();
        }
    }

    public int getScore()
    {
        return score;
    }
}
