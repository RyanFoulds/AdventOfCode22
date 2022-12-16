package xyz.foulds.aoc.year22.day15;

public class Range
{
    private final int start;
    private final int end;

    public Range(final int start, final int end)
    {
        this.start = start;
        this.end = end;
    }

    public int getStart()
    {
        return start;
    }

    public int getEnd()
    {
        return end;
    }

    public boolean contains(final int x)
    {
        return x >= start && x <= end; 
    }
}
