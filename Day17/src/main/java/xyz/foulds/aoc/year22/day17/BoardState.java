package xyz.foulds.aoc.year22.day17;

public class BoardState
{
    private final long height;
    private final long rockNum;

    public BoardState(long height, long rockNum)
    {
        this.height = height;
        this.rockNum = rockNum;
    }

    public long getHeight()
    {
        return height;
    }

    public long getRockNum()
    {
        return rockNum;
    }
}
