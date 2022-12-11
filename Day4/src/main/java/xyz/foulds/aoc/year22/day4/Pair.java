package xyz.foulds.aoc.year22.day4;

public class Pair
{
    private final Elf first;
    private final Elf second;

    public Pair(final Elf first, final Elf second)
    {
        this.first = first;
        this.second = second;
    }

    public boolean hasContainedElf()
    {
        return first.isContainedWithin(second) || second.isContainedWithin(first);
    }

    public boolean hasOverlap()
    {
        return first.overlapsWith(second);
    }
}
